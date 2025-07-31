package core.data.di

import org.koin.core.module.Module
import org.koin.dsl.module
import core.data.repository.AuthRepository
import core.database.RoomDB
import core.database.di.databaseModule
import core.datastore.DataStore
import core.datastore.di.dataStoreModule
import core.network.AppRemoteApi
import core.network.di.networkModule

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
}


