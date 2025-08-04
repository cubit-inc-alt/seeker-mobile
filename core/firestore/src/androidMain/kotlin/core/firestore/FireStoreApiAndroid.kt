package core.firestore

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob


class FireStoreApiAndroid() : Firestore {
  private var fireStoreScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
  private val firestore = FirebaseFirestore.getInstance()

  override fun setDocument(documentPath: String, data: Map<String, Any?>) {
    firestore.document(documentPath)
      .set(data)
  }

  override fun addDocument(collection: String, data: Map<String, Any?>) {
    firestore.collection(collection)
      .add(data)
  }

  override fun listenForCollectionSnapShot(
    collection: String,
    onUpdate: (List<Map<String, Any?>>) -> Unit
  ): () -> Unit {
    val listener = firestore.collection(collection)
      .addSnapshotListener { snapshot, error ->
        snapshot?.map { it.data.toMap() }?.also { onUpdate(it) }
      }

    return {
      listener.remove()
    }
  }


//  override suspend fun createChatRoom(participants: List<User>): String {
//    val newChatRoomRef = firestore.collection(COLLECTION_CHAT_ROOMS).document()
//    val chatRoom = ChatRoom(
//      id = newChatRoomRef.id,
//      participants = participants,
//    )
//    newChatRoomRef.set(chatRoom).await()
//    return newChatRoomRef.id
//  }
//
//  override suspend fun addMessageToRoom(roomId: String, userId: String, message: String) {
//    val newChatRef =
//      firestore.collection(COLLECTION_CHAT_ROOMS).document(roomId).collection(COLLECTION_CHATS).document()
//    val fireStoreChat = FireStoreChat(
//      id = newChatRef.id,
//      message = message,
//      type = ChatValueType.TEXT,
//      dateTime = Clock.System.now().toEpochMilliseconds(),
//      likes = listOf(),
//      userId = userId
//    )
//    newChatRef.set(fireStoreChat).await()
//  }
//
//  override suspend fun endChatRoom() {
//    firestore.terminate()
//  }
//
//  override suspend fun startListeningToChatRoom(roomId: String) {
//    fireStoreScope.launch {
//
//      val roomDoc = firestore.collection(COLLECTION_CHAT_ROOMS).document(roomId)
//      val roomSnapshot = roomDoc.get().await()
//      val participants = roomSnapshot["participants"] as List<Map<String, String>>
//
//      val messagesCollection = roomDoc.collection(COLLECTION_CHATS)
//      val messagesSnapshot = messagesCollection.get().await()
//      messagesSnapshot.documents.forEach { doc ->
//        val id = doc.getString("id") ?: return@forEach
//        val message = doc.getString("message") ?: ""
//        val type = ChatValueType.valueOf(doc.getString("type") ?: "TEXT")
//        val dateTime = doc.getLong("dateTime") ?: 0L
//        val likes = (doc["likes"] as? List<String>) ?: emptyList()
//        val userId = doc.getString(USER_ID) ?: ""
//
//        val chat = FireStoreChat(
//          id = id, message = message, type = type, dateTime = dateTime, likes = likes, userId = userId
//        )
//      }
//    }
//  }
}
