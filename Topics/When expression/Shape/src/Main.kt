fun main(args: Array<String>) {
    // write your code here
    val input = readln().toInt()
    val start = "You have chosen a "
    print(when(input) {
        1 -> start + "square"
        2 -> start + "circle"
        3 -> start + "triangle"
        4 -> start + "rhombus"
        else -> "There is no such shape!"
    })
}