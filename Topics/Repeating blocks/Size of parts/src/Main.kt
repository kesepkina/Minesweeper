fun main() {
    // put your code here
    val n = readln().toInt()
    var perfect = 0
    var larger = 0
    var rejections = 0
    var x: Int
    repeat(n) {
        x = readln().toInt()
        if (x == -1) rejections++ else if (x == 0) perfect++ else larger++
    }
    print("$perfect $larger $rejections")
}