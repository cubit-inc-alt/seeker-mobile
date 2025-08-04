package core.models.response.message

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Message(
  val id: String,
  val chatRoomId: String,
  val senderId: String,
  val message: String,
  val sentAt: Instant,
  val likedBy: Set<String>
)
