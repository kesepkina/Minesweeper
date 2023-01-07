fun main() {
    val totalSeconds = System.currentTimeMillis() / 1000 // dont change this line
    // enter your code
    val hours = totalSeconds % (3600 * 24) / 3600
    val minutes = totalSeconds % 3600 / 60
    val seconds = totalSeconds % 60
    println("$hours:$minutes:$seconds")
}