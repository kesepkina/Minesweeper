fun main() {
    // write your code here
    val a = readln().toInt()
    val b = readln().toInt()
    val c = readln().toInt()
    print (if (a < b + c && b < a + c && c < a + b) "YES" else "NO")
}