package tech.pathtoprogramming.evercraft.model

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import tech.pathtoprogramming.evercraft.Dice
import tech.pathtoprogramming.evercraft.model.Alignment.*

class CharacterTest {

    private lateinit var mockDice: Dice

    @BeforeEach
    fun setUp() {
        mockDice = mockk()
    }

    @Test
    fun characterReturnsCharacterProperties() {
        val character = Character(
            name = "Drew",
            alignment = GOOD,
            armorClass = 15,
            hitPoints = 10
        )

        assertThat(character.name).isEqualTo("Drew")
        assertThat(character.alignment.name).isEqualTo("GOOD")
        assertThat(character.armorClass).isEqualTo(15)
        assertThat(character.hitPoints).isEqualTo(10)
        assertThat(character.status.name).isEqualTo("ALIVE")
    }

    @Test
    fun armorClassShouldReturn10ByDefault() {
        val character = Character(name = "Drew", alignment = NEUTRAL);

        assertThat(character.armorClass).isEqualTo(10)
    }

    @Test
    fun hitPointsShouldReturn5ByDefault() {
        val character = Character(name = "Drew", alignment = EVIL);

        assertThat(character.hitPoints).isEqualTo(5)
    }

    @Test
    fun attackDealsDamageToTargetWhenRollIsEqualOrGreaterThanOpponentArmorClass() {
        val character = Character(name = "Drew", alignment = EVIL, dice = mockDice)
        val monster = Character(name = "Goblin", hitPoints = 10, armorClass = 12)
        every { mockDice.roll() } returns 15

        character.attack(monster)

        assertThat(monster.hitPoints).isEqualTo(9)
    }

    @Test
    fun attackDoesNotDealDamageWhenRollIsLessThanOpponentArmorClass() {
        val character = Character(name = "Drew", alignment = EVIL, dice = mockDice)
        val monster = Character(name = "Goblin", hitPoints = 10, armorClass = 12)
        every { mockDice.roll() } returns 11

        character.attack(monster)

        assertThat(monster.hitPoints).isEqualTo(10)
    }

    @Test
    fun attackDealsCriticalDamageToTargetWhenRollIsANaturalTwenty() {
        val character = Character(name = "Drew", alignment = EVIL, dice = mockDice)
        val monster = Character(name = "Goblin", hitPoints = 10, armorClass = 12)
        every { mockDice.roll() } returns 20

        character.attack(monster)

        assertThat(monster.hitPoints).isEqualTo(8)
        assertThat(monster.status.name).isEqualTo("ALIVE")
    }

    @Test
    fun attackKillsTargetWhenTheHitPointsAreLessThanOrEqualToZero() {
        val character = Character(name = "Drew", alignment = EVIL, dice = mockDice)
        val monster = Character(name = "Goblin", hitPoints = 1, armorClass = 12)
        every { mockDice.roll() } returns 14

        character.attack(monster)

        assertThat(monster.hitPoints).isEqualTo(0)
        assertThat(monster.status.name).isEqualTo("DEAD")
    }
}