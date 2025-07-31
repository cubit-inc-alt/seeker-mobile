package core.models.local

import core.models.enum.ChatType

data class Chat(
  val id: String,
  val message: String,
  val type: ChatType,
  val dateTime: Long,
  val likes: Int,
  val isLiked: Boolean
)
