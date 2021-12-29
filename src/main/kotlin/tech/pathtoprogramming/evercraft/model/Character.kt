package tech.pathtoprogramming.evercraft.model

import tech.pathtoprogramming.evercraft.Dice
import org.slf4j.LoggerFactory

data class Character(
    val name: String = "",
    val alignment: Alignment = Alignment.NEUTRAL,
    val armorClass: Int = 10,
    var hitPoints: Int = 5,
    var status: Status = Status.ALIVE,
    val dice: Dice = Dice()
) {

    private val log = LoggerFactory.getLogger(Character::class.java)

    fun attack(target: Character) {
        val roll = dice.roll()
        val isCriticalHit = roll == 20
        val isHit = roll >= target.armorClass
        val damage = if (isCriticalHit) 2 else 1

        log.info("Attacking opponent with AC of ${target.armorClass}. Roll: $roll. isHit: $isHit.")
        if (isHit) target.hitPoints -= damage
        if (target.hitPoints < 1) target.status = Status.DEAD
    }
}

enum class Alignment {
    GOOD, NEUTRAL, EVIL
}

enum class Status {
    ALIVE, DEAD
}