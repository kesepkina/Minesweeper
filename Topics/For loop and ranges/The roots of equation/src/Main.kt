fun main() {
    // put your code here
    val coefs = MutableList(4) { readln().toInt() }
    for (i in 0..1000) {
        if (coefs[0] * i * i * i + coefs[1] * i * i + coefs[2] * i + coefs[3] == 0) println(i)
    }
}