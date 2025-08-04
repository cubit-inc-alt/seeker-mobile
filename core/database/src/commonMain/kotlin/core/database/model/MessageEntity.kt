package core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant

@Entity(tableName = "messages")
data class MessageEntity(
  @PrimaryKey val id: String,
  val chatRoomId: String,
  val senderId: String,
  val message: String,
  val sentAt: Instant,
  val likedBy: Set<String>
)
