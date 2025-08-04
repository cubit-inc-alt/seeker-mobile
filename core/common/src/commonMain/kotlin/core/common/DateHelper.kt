package core.common


import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

fun LocalDateTime.formatAsDefault(): String {
    return buildString {
        append(dayOfMonth.toString().padStart(2, '0'))
        append("/")
        append(monthNumber.toString().padStart(2, '0'))
        append("/")
        append(year)
    }
}

fun LocalDateTime.formatAsISO(): String {
    return toInstant(timeZone = TimeZone.UTC).toString()
}



fun getRelativeTimeString(timestampMillis: Long): String {
  val now = Clock.System.now()
  val eventTime = Instant.fromEpochMilliseconds(timestampMillis)
  val diff = now - eventTime

  val seconds = diff.inWholeSeconds
  val minutes = diff.inWholeMinutes
  val hours = diff.inWholeHours
  val days = diff.inWholeDays
  val weeks = days / 7

  return when {
    seconds < 60 -> "Just now"
    minutes < 60 -> "${minutes}m ago"
    hours < 24 -> "${hours}h ago"
    days < 7 -> "${days}d ago"
    weeks < 4 -> "${weeks}w ago"
    else -> {
      val dateTime = eventTime.toLocalDateTime(TimeZone.currentSystemDefault())
      "${dateTime.dayOfMonth} ${dateTime.month.name.lowercase().replaceFirstChar { it.uppercase() }}"
    }
  }
}
