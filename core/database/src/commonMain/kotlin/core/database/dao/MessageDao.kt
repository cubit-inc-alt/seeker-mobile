package core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import core.database.model.MessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun upsert(message: MessageEntity)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun upsert(messages: List<MessageEntity>)


  @Query("SELECT * FROM messages where chatRoomId = :roomId")
  fun getMessagesInRoom(roomId: String): Flow<List<MessageEntity>>

  @Query("DELETE FROM messages")
  suspend fun deleteAll()
}
