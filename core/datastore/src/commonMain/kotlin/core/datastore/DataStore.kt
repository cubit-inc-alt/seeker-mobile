package core.datastore

import com.russhwolf.settings.ObservableSettings
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

internal expect fun deviceDataStore(): ObservableSettings

internal expect fun userDataStore(): ObservableSettings

class DataStore(
  private val deviceStore: ObservableSettings = deviceDataStore(),
  private val userStore: ObservableSettings = userDataStore(),
) {
  var accessToken by deviceStore.value<String>(ACCESS_TOKEN)
  var refreshToken by deviceStore.value<String>(REFRESH_TOKEN)
  var userDetail by deviceStore.value<String?>(USER_DETAIL)
  var userId by deviceStore.value<String?>(USER_ID)
  var isLoggedIn by deviceStore.value<Boolean?>(IS_LOGGED_IN, defaultValue = false)

  fun clearUserData() {
    userStore.clear()
    deviceStore.clear()
  }

  fun getBooleanFlow(key: String): Flow<Boolean> =
    callbackFlow {
      val listener =
        deviceStore.addBooleanListener(key, defaultValue = false) { value ->
          trySend(value)
        }

      // Emit the initial value
      trySend(deviceStore.getBoolean(key, false))

      awaitClose { listener.deactivate() }
    }

  companion object {
    private const val ACCESS_TOKEN = "access_token"
    private const val REFRESH_TOKEN = "refresh_token"
    private const val USER_DETAIL = "user_detail"
    private const val USER_ID = "user_id"
    private const val CURRENT_CHAT_ROOM_ID = "current_chat_room_id"
    const val IS_LOGGED_IN = "isLoggedIn"

  }
}
