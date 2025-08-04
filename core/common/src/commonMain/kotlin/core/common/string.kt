package core.common

fun String.maskString(): String {
  return if (this.length <= 8) {
    this // Too short to mask
  } else {
    val start = this.take(4)
    val end = this.takeLast(4)
    "$start****$end"
  }
}
