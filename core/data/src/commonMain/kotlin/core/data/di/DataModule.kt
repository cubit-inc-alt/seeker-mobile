package core.data.di

import core.data.repository.AuthRepository
import core.data.repository.ChatRepository
import core.database.RoomDB
import core.database.dao.MessageDao
import core.database.di.databaseModule
import core.datastore.DataStore
import core.datastore.di.dataStoreModule
import core.firestore.Firestore
import core.network.AppRemoteApi
import core.network.di.networkModule
import org.koin.core.module.Module
import org.koin.dsl.module

fun dataModule(): Module = module {
  includes(
    databaseModule(),
    networkModule(),
    dataStoreModule(),
  )

  single<AuthRepository> {
    AuthRepository(
      get<DataStore>(),
      get<AppRemoteApi>(),
      get<RoomDB>(),
    )
  }

  single<ChatRepository> {
    ChatRepository(
      get<MessageDao>(),
      get<DataStore>(),
      get<Firestore>()
    )
  }
}


