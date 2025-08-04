package core.firestore

import core.common.json
import core.firestore.extensions.toMap
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject


interface Firestore {

  fun setDocument(documentPath: String, data: Map<String, Any?>)

  fun addDocument(collection: String, data: Map<String, Any?>)

  fun listenForCollectionSnapShot(collection: String, onUpdate: (List<Map<String, Any?>>) -> Unit): () -> Unit

}


inline fun <reified T> Firestore.addObject(collection: String, value: T) {
  addDocument(collection, data = json.encodeToJsonElement(value).jsonObject.toMap())
}


fun Firestore.onCollectionSnapshot(
  collection: String
): Flow<List<Map<String, Any?>>> = callbackFlow {
  val removeListener = listenForCollectionSnapShot(
    collection = collection,
    onUpdate = { it: List<Map<String, Any?>> ->
      trySend(element = it)
    }
  )

  awaitClose{
    removeListener.invoke()
  }

}
