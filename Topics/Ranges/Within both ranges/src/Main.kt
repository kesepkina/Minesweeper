fun main() {
    // write your code here
    val a = readln().toInt()
    val b = readln().toInt()
    val c = readln().toInt()
    val d = readln().toInt()
    val x = readln().toInt()
    print(x in a..b && x in c..d)
}