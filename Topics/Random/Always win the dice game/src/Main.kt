import kotlin.random.Random

fun createDiceGameRandomizer(n: Int): Int {
    // write your code here
    var found = false
    var seed = 1
    while (!found) {
        val generator = Random(seed)
        var sumFriend = 0
        var sumMe = 0
        for (i in 1..n) {
            sumFriend += generator.nextInt(1, 7)
        }
        for (i in 1..n) {
            sumMe += generator.nextInt(1, 7)
        }
        if (sumMe > sumFriend) found = true else seed++
    }
    return seed
}