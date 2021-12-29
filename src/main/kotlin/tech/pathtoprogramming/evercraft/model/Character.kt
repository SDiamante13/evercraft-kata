package tech.pathtoprogramming.evercraft.model

import org.slf4j.LoggerFactory
import tech.pathtoprogramming.evercraft.*
import java.lang.IllegalArgumentException

val defaultAbility = Ability(score = 10)

data class Character(
    val name: String,
    val alignment: Alignment = Alignment.NEUTRAL,
    private val armorClass: Int = 10,
    private var hitPoints: Int = 5,
    var status: Status = Status.ALIVE,
    val abilities: MutableMap<String, Ability> = mutableMapOf(
        STRENGTH to defaultAbility,
        DEXTERITY to defaultAbility,
        CONSTITUTION to defaultAbility,
        WISDOM to defaultAbility,
        INTELLIGENCE to defaultAbility,
        CHARISMA to defaultAbility
    ),
    val dice: Dice = Dice()
) {

    private val log = LoggerFactory.getLogger(Character::class.java)

    fun getArmorClass(): Int {
        val dexterityMod = this.abilities[DEXTERITY]?.deriveModifier() ?: 0
        return this.armorClass + dexterityMod
    }

    fun setHitPoints(newHp: Int) {
        this.hitPoints = newHp
    }

    fun getHitPoints(): Int {
        val constitutionMod = this.abilities[CONSTITUTION]?.deriveModifier() ?: 0
        val hp = this.hitPoints + constitutionMod
        return if (hp < 1 && constitutionMod < 0) 1 else hp
    }

    fun attack(target: Character) {
        val roll = dice.roll()
        val isCriticalHit = roll == 20
        var strengthMod = abilities[STRENGTH]?.deriveModifier() ?: 0
        if (isCriticalHit) strengthMod *= 2
        val isHit = roll + strengthMod >= target.armorClass
        val damage = calculateDamage(isCriticalHit, strengthMod)
        log.info("Attacking opponent with AC of ${target.armorClass}." +
                " Roll: $roll. isHit: $isHit. isCriticalHit: $isCriticalHit strength mod: $strengthMod")
        if (isHit) target.setHitPoints(target.getHitPoints() - damage)
        if (target.hitPoints < 1) target.status = Status.DEAD
    }

    private fun calculateDamage(isCriticalHit: Boolean, strengthMod: Int): Int {
        var damage = if (isCriticalHit) 2 + strengthMod else 1 + strengthMod
        if (damage < 1) damage = 1
        return damage
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