import kotlin.math.abs

const val TEN = 10

fun Int.lastDigit(): Int = abs(this % TEN)