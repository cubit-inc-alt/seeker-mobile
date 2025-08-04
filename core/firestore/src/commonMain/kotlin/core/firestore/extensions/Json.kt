package core.firestore.extensions

import kotlinx.serialization.json.*

private fun JsonElement.serialize(): Any? = when (this) {
  is JsonPrimitive -> when {
    this.isString -> this.content
    this.booleanOrNull != null -> this.boolean
    this.intOrNull != null -> this.int
    this.longOrNull != null -> this.long
    this.doubleOrNull != null -> this.double
    else -> this.content
  }

  is JsonObject -> this.toMap()
  is JsonArray -> this.map { it.serialize() }
}

fun JsonObject.toMap(): Map<String, Any?> =
  this.mapValues { (_, value) -> value.serialize() }
