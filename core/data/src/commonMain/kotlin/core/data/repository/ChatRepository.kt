package core.data.repository

import core.common.decodeTo
import core.common.json
import core.database.dao.MessageDao
import core.database.model.MessageEntity
import core.datastore.DataStore
import core.firestore.Firestore
import core.firestore.addObject
import core.firestore.onCollectionSnapshot
import core.models.response.message.Message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


fun Collection<*>.toJsonElement(): JsonElement = JsonArray(mapNotNull { it.toJsonElement() })

fun Map<*, *>.toJsonElement(): JsonElement = JsonObject(
  mapNotNull {
    (it.key as? String ?: return@mapNotNull null) to it.value.toJsonElement()
  }.toMap(),
)

fun Any?.toJsonElement(): JsonElement = when (this) {
  null -> JsonNull
  is Map<*, *> -> toJsonElement()
  is Collection<*> -> toJsonElement()
  else -> JsonPrimitive(toString())
}

class ChatRepository(
  private val chatRoomDao: MessageDao,
  private val dataStore: DataStore,
  private val firestore: Firestore
) {


  init {
    GlobalScope.launch {
      chatRoomDao.deleteAll()
    }
  }

  suspend fun listenForMessageInRoom(roomId: String) = withContext(Dispatchers.IO) {
    firestore.onCollectionSnapshot(roomCollection(roomId))
      .collect {
        val messages = it.mapNotNull { message ->
          try {
            json.decodeFromJsonElement<Message>(message.toJsonElement()).toEntity()
          } catch (e: RuntimeException) {
            null
          }
        }

        chatRoomDao.upsert(messages)
      }
  }

  @OptIn(ExperimentalUuidApi::class)
  suspend fun sentNewMessage(
    roomId: String,
    userId: String,
    message: String
  ): Unit = withContext(Dispatchers.IO) {

    val message = Message(
      id = Uuid.random().toString(),
      chatRoomId = roomId,
      senderId = userId,
      message = message,
      sentAt = Clock.System.now(),
      likedBy = emptySet()
    )

    firestore.addObject(roomCollection(roomId), message)
    chatRoomDao.upsert(message.toEntity())
  }

  fun getMessageInRoom(roomId: String): Flow<List<MessageEntity>> {
    return chatRoomDao.getMessagesInRoom(roomId)
  }

}

fun Message.toEntity() = MessageEntity(
  id = id,
  chatRoomId = chatRoomId,
  senderId = senderId,
  message = message,
  sentAt = sentAt,
  likedBy = likedBy
)

private fun roomCollection(id: String) = "chatRooms/$id/messages"
