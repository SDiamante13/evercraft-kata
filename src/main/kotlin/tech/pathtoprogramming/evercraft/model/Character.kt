package tech.pathtoprogramming.evercraft.model

import tech.pathtoprogramming.evercraft.Dice
import org.slf4j.LoggerFactory
import java.lang.IllegalArgumentException

val defaultAbility = Ability(score = 10)

data class Character(
    val name: String = "",
    val alignment: Alignment = Alignment.NEUTRAL,
    val armorClass: Int = 10,
    var hitPoints: Int = 5,
    var status: Status = Status.ALIVE,
    val abilities: MutableMap<String, Ability> = mutableMapOf(
        "Strength" to defaultAbility,
        "Dexterity" to defaultAbility,
        "Constitution" to defaultAbility,
        "Wisdom" to defaultAbility,
        "Intelligence" to defaultAbility,
        "Charisma" to defaultAbility
    ),
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

data class Ability(val score: Int) {
    fun deriveModifier(): Int {
        return when (score) {
            1 -> -5
            2,3 -> -4
            4,5 -> -3
            6,7 -> -2
            8,9 -> -1
            10,11 -> 0
            12,13 -> 1
            14,15 -> 2
            16,17 -> 3
            18,19 -> 4
            20 -> 5
            else -> {
                throw IllegalArgumentException("Score must be between 1 and 20")
            }
        }
    }
}