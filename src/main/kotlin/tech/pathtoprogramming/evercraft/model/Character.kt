package tech.pathtoprogramming.evercraft.model

import tech.pathtoprogramming.evercraft.Dice
import org.slf4j.LoggerFactory

data class Character(
    val name: String = "",
    val alignment: Alignment = Alignment.NEUTRAL,
    val armorClass: Int = 10,
    val hitPoints: Int = 5,
    val dice: Dice = Dice()
) {

    private val log = LoggerFactory.getLogger(Character::class.java)

    fun attack(target: Character): Boolean {
        val roll = dice.roll()
        val isHit = roll >= target.armorClass
        log.info("Attacking opponent with AC of ${target.armorClass}." +
                " Result: $roll. isHit: $isHit")
        return isHit
    }
}

enum class Alignment {
    GOOD, NEUTRAL, EVIL
}