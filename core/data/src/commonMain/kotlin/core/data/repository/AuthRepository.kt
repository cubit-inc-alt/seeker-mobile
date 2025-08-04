package core.data.repository

import core.common.decodeTo
import core.common.toJsonString
import kotlinx.coroutines.flow.Flow
import core.database.RoomDB
import core.datastore.DataStore
import core.models.local.User
import core.network.AppRemoteApi


class AuthRepository(
  private val dataStore: DataStore,
  private val remoteApi: AppRemoteApi,
  private val roomDB: RoomDB,
) {
  fun getIsLoggedIn(): Boolean {
    return dataStore.isLoggedIn == true
  }

  fun getUserLoggedInFlow(): Flow<Boolean> {
    return dataStore.getBooleanFlow(DataStore.Companion.IS_LOGGED_IN)
  }
  fun setUser(user: User){
    dataStore.userDetail = user.toJsonString()
    dataStore.userId = user.userId
  }
  fun getUser(): User? {
    return dataStore.userDetail?.decodeTo<User>()
  }
}
