fun f(x: Double): Double {
    // call your implemented functions here
    return when {
        x <= 0 -> f1(x)
        x < 1 -> f2(x)
        else -> f3(x)
    }
}

// implement your functions here
fun f1(x: Double): Double {
    return x * x + 1
}

fun f2(x: Double): Double {
    return 1 / (x * x)
}

fun f3(x: Double): Double {
    return x * x - 1
}