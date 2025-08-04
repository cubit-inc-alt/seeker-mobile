package core.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import core.database.DatabaseConfig.DATABASE_VERSION
import core.database.dao.MessageDao
import core.database.model.MessageEntity
import core.database.typeconverters.InstantTypeConverter
import core.database.typeconverters.SetOfStringTypeConverter


@Database(
  entities = [
    MessageEntity::class
  ],
  version = DATABASE_VERSION,
  exportSchema = true
)
@TypeConverters(InstantTypeConverter::class, SetOfStringTypeConverter::class)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class RoomDB : RoomDatabase() {
  abstract fun chatRoomDao(): MessageDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<RoomDB> {
  override fun initialize(): RoomDB
}
