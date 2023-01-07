enum class DangerLevel(private val num: Int) {
    HIGH(3),
    MEDIUM(2),
    LOW(1);

    fun getLevel() = this.num
}