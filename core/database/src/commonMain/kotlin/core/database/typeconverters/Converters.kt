package core.database.typeconverters

import androidx.room.TypeConverter
import core.common.decodeTo
import core.common.toJsonString
import kotlinx.datetime.Instant


object InstantTypeConverter {
  @TypeConverter
  fun from(data: Instant): String = data.toString()

  @TypeConverter
  fun to(data: String): Instant = Instant.parse(data)
}


object SetOfStringTypeConverter {
  @TypeConverter
  fun from(data: Set<String>): String = data.toJsonString()

  @TypeConverter
  fun to(data: String): Set<String> = data.decodeTo<Set<String>>()
}
