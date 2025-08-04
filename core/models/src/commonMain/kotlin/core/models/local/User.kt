package core.models.local

import kotlinx.serialization.Serializable

@Serializable
data class User(val userId: String, val username: String)
