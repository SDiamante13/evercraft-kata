package tech.pathtoprogramming.evercraft.model

data class Character(
    val name: String = "",
    val alignment: Alignment = Alignment.NEUTRAL,
    val armorClass: Int = 10,
    val hitPoints: Int = 5
)

enum class Alignment {
    GOOD, NEUTRAL, EVIL
}