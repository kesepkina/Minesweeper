import kotlin.math.sqrt

fun main() {
    // write your code here
    val form = readln()
    println(
        when (form) {
            "triangle" -> {
                val a = readln().toDouble()
                val b = readln().toDouble()
                val c = readln().toDouble()
                val s = (a + b + c) / 2.0
                sqrt(s * (s - a) * (s - b) * (s - c))
            }
            "rectangle" -> {
                val a = readln().toDouble()
                val b = readln().toDouble()
                a * b
            }
            "circle" -> {
                val r = readln().toDouble()
                3.14 * r * r
            }
            else -> "Form is not defined"
        }
    )
}