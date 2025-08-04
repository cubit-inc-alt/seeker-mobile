package core.models.local
data class ChatRoom(
  val id: String,
  val participants: List<User>,
)
