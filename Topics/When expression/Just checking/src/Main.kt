fun main() {
    // write your code here
    val ans = readln().toInt()
    println(when(ans) {
        1, 3, 4 -> "No!"
        2 -> "Yes!"
        else -> "Unknown number"
    })
}