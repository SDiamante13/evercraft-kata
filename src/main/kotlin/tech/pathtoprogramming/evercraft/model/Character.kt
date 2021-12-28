package tech.pathtoprogramming.evercraft.model

import tech.pathtoprogramming.evercraft.Dice

data class Character(
    val name: String = "",
    val alignment: Alignment = Alignment.NEUTRAL,
    val armorClass: Int = 10,
    val hitPoints: Int = 5,
    val dice: Dice = Dice()
) {
    fun attack(target: Character): Boolean {
        return dice.roll() >= target.armorClass
    }
}

enum class Alignment {
    GOOD, NEUTRAL, EVIL
}