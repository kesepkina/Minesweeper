class City(val name: String) {
    var degrees: Int = when(name) {"Moscow" -> 5 "Hanoi" -> 20 else -> 30}
        set(value) {
            if (value in -92..57) field = value
        }
}        

fun main() {
    val first = readLine()!!.toInt()
    val second = readLine()!!.toInt()
    val third = readLine()!!.toInt()
    val firstCity = City("Dubai")
    firstCity.degrees = first
    val secondCity = City("Moscow")
    secondCity.degrees = second
    val thirdCity = City("Hanoi")
    thirdCity.degrees = third

    //implement comparing here
    print(
        when {
            firstCity.degrees < secondCity.degrees && firstCity.degrees < thirdCity.degrees -> firstCity.name
            secondCity.degrees < firstCity.degrees && secondCity.degrees < thirdCity.degrees -> secondCity.name
            thirdCity.degrees < secondCity.degrees && thirdCity.degrees < firstCity.degrees -> thirdCity.name
            else -> "neither"
        }
    )
}