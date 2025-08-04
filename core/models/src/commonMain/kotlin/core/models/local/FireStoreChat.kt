package core.models.local

import core.models.enum.ChatValueType

data class FireStoreChat(
    val id: String,
    val message: String,
    val type: ChatValueType,
    val dateTime: Long,
    val likes: List<String> = emptyList(),
    val userId: String
)
