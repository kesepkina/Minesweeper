fun main() {
    // write your code here
    val n = readln().toInt()
    val range = MutableList(n) { readln().toInt() }
    var min = range[0]
    for (x in range) {
        if (x < min) min=x
    }
    print(min)
}