import kotlin.random.Random

fun makeDecision(): String {
    // write here
    val randomN = Random.nextInt(1, 4)
    return if (randomN == 1) {
        "Rock"
    } else if (randomN == 2) {
        "Paper"
    } else {
        "Scissors"
    }
}