package tech.pathtoprogramming.evercraft.model

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import tech.pathtoprogramming.evercraft.Dice
import tech.pathtoprogramming.evercraft.model.Alignment.*
import java.lang.IllegalArgumentException


class CharacterTest {

    private lateinit var mockDice: Dice

    @BeforeEach
    fun setUp() {
        mockDice = mockk()
    }

    @Test
    fun characterReturnsCharacterProperties() {
        val character = Character(
            name = "Lancelot",
            alignment = GOOD,
            armorClass = 15,
            hitPoints = 10
        )

        assertThat(character.name).isEqualTo("Lancelot")
        assertThat(character.alignment.name).isEqualTo("GOOD")
        assertThat(character.armorClass).isEqualTo(15)
        assertThat(character.hitPoints).isEqualTo(10)
        assertThat(character.status.name).isEqualTo("ALIVE")
        assertThat(character.abilities["Strength"]?.score).isEqualTo(10)
        assertThat(character.abilities["Strength"]?.deriveModifier()).isEqualTo(0)
        assertThat(character.abilities["Dexterity"]?.score).isEqualTo(10)
        assertThat(character.abilities["Dexterity"]?.deriveModifier()).isEqualTo(0)
        assertThat(character.abilities["Constitution"]?.score).isEqualTo(10)
        assertThat(character.abilities["Constitution"]?.deriveModifier()).isEqualTo(0)
        assertThat(character.abilities["Wisdom"]?.score).isEqualTo(10)
        assertThat(character.abilities["Wisdom"]?.deriveModifier()).isEqualTo(0)
        assertThat(character.abilities["Intelligence"]?.score).isEqualTo(10)
        assertThat(character.abilities["Intelligence"]?.deriveModifier()).isEqualTo(0)
        assertThat(character.abilities["Charisma"]?.score).isEqualTo(10)
        assertThat(character.abilities["Charisma"]?.deriveModifier()).isEqualTo(0)
    }

    @Test
    fun armorClassShouldReturn10ByDefault() {
        val character = Character(name = "Lancelot", alignment = NEUTRAL);

        assertThat(character.armorClass).isEqualTo(10)
    }

    @Test
    fun hitPointsShouldReturn5ByDefault() {
        val character = Character(name = "Lancelot", alignment = EVIL);

        assertThat(character.hitPoints).isEqualTo(5)
    }

    @Test
    fun attackDealsDamageToTargetWhenRollIsEqualOrGreaterThanOpponentArmorClass() {
        val character = Character(name = "Lancelot", alignment = EVIL, dice = mockDice)
        val monster = Character(name = "Goblin", hitPoints = 10, armorClass = 12)
        every { mockDice.roll() } returns 15

        character.attack(monster)

        assertThat(monster.hitPoints).isEqualTo(9)
    }

    @Test
    fun attackDoesNotDealDamageWhenRollIsLessThanOpponentArmorClass() {
        val character = Character(name = "Lancelot", alignment = EVIL, dice = mockDice)
        val monster = Character(name = "Goblin", hitPoints = 10, armorClass = 12)
        every { mockDice.roll() } returns 11

        character.attack(monster)

        assertThat(monster.hitPoints).isEqualTo(10)
    }

    @Test
    fun attackDealsCriticalDamageToTargetWhenRollIsANaturalTwenty() {
        val character = Character(name = "Lancelot", alignment = EVIL, dice = mockDice)
        val monster = Character(name = "Goblin", hitPoints = 10, armorClass = 12)
        every { mockDice.roll() } returns 20

        character.attack(monster)

        assertThat(monster.hitPoints).isEqualTo(8)
        assertThat(monster.status.name).isEqualTo("ALIVE")
    }

    @Test
    fun attackKillsTargetWhenTheHitPointsAreLessThanOrEqualToZero() {
        val character = Character(name = "Lancelot", alignment = EVIL, dice = mockDice)
        val monster = Character(name = "Goblin", hitPoints = 1, armorClass = 12)
        every { mockDice.roll() } returns 14

        character.attack(monster)

        assertThat(monster.hitPoints).isEqualTo(0)
        assertThat(monster.status.name).isEqualTo("DEAD")
    }

    @ParameterizedTest(name = "Ability score of {0} yields modifier of {1}")
    @CsvSource(
        "1,-5",
        "2,-4",
        "3,-4",
        "4,-3",
        "5,-3",
        "6,-2",
        "7,-2",
        "8,-1",
        "9,-1",
        "10,0",
        "11,0",
        "12,1",
        "13,1",
        "14,2",
        "15,2",
        "16,3",
        "17,3",
        "18,4",
        "19,4",
        "20,5",
    )
    fun abilityScoresAndModifiers(score: Int, expectedModifier: Int) {
        val character = Character(name = "Lancelot")

        character.abilities["Constitution"] = Ability(score = score)

        assertThat(character.abilities["Constitution"]?.deriveModifier()).isEqualTo(expectedModifier)
    }

    @Test
    fun abilityScoreOutOfRangeWillThrowIllegalArgumentExceptionWhenDeriveModifierIsCalled() {
        val character = Character(name = "Lancelot")

        character.abilities["Wisdom"] = Ability(score = 99)

        assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy { character.abilities["Wisdom"]?.deriveModifier() }
            .withMessage("Score must be between 1 and 20")
    }
}