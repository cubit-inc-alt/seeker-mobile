package core.database.di

import org.koin.core.module.Module
import org.koin.dsl.module
import core.database.RoomDB
import core.database.dao.MessageDao

fun daoModules(): Module = module {
    single<MessageDao> { get<RoomDB>().chatRoomDao() }
}
