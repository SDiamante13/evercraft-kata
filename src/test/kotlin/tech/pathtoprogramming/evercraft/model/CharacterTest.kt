package tech.pathtoprogramming.evercraft.model

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import tech.pathtoprogramming.evercraft.*
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
            alignment = Alignment.GOOD,
            armorClass = 15,
            hitPoints = 10
        )

        assertThat(character.name).isEqualTo("Lancelot")
        assertThat(character.alignment.name).isEqualTo(GOOD)
        assertThat(character.getArmorClass()).isEqualTo(15)
        assertThat(character.getHitPoints()).isEqualTo(10)
        assertThat(character.status.name).isEqualTo(ALIVE)
        assertThat(character.abilities[STRENGTH]?.score).isEqualTo(10)
        assertThat(character.abilities[STRENGTH]?.deriveModifier()).isEqualTo(0)
        assertThat(character.abilities[DEXTERITY]?.score).isEqualTo(10)
        assertThat(character.abilities[DEXTERITY]?.deriveModifier()).isEqualTo(0)
        assertThat(character.abilities[CONSTITUTION]?.score).isEqualTo(10)
        assertThat(character.abilities[CONSTITUTION]?.deriveModifier()).isEqualTo(0)
        assertThat(character.abilities[WISDOM]?.score).isEqualTo(10)
        assertThat(character.abilities[WISDOM]?.deriveModifier()).isEqualTo(0)
        assertThat(character.abilities[INTELLIGENCE]?.score).isEqualTo(10)
        assertThat(character.abilities[INTELLIGENCE]?.deriveModifier()).isEqualTo(0)
        assertThat(character.abilities[CHARISMA]?.score).isEqualTo(10)
        assertThat(character.abilities[CHARISMA]?.deriveModifier()).isEqualTo(0)
    }

    @Test
    fun armorClassShouldReturn10ByDefault() {
        val character = Character(name = "Lancelot", alignment = Alignment.NEUTRAL);

        assertThat(character.getArmorClass()).isEqualTo(10)
    }

    @Test
    fun hitPointsShouldReturn5ByDefault() {
        val character = Character(name = "Lancelot", alignment = Alignment.EVIL);

        assertThat(character.getHitPoints()).isEqualTo(5)
    }

    @Test
    fun attackDealsDamageToTargetWhenRollIsEqualOrGreaterThanOpponentArmorClass() {
        val character = Character(name = "Lancelot", dice = mockDice)
        character.abilities[STRENGTH] = Ability(score = 20)
        val monster = Character(name = "Goblin", hitPoints = 10, armorClass = 12)
        every { mockDice.roll() } returns 15

        character.attack(monster)

        assertThat(monster.getHitPoints()).isEqualTo(4)
    }

    @Test
    fun attackDoesNotDealDamageWhenRollIsLessThanOpponentArmorClass() {
        val character = Character(name = "Lancelot", dice = mockDice)
        val monster = Character(name = "Goblin", hitPoints = 10, armorClass = 12)
        every { mockDice.roll() } returns 11

        character.attack(monster)

        assertThat(monster.getHitPoints()).isEqualTo(10)
    }

    @Test
    fun attackDealsCriticalDamageToTargetWhenRollIsANaturalTwenty() {
        val character = Character(name = "Lancelot", dice = mockDice)
        val monster = Character(name = "Goblin", hitPoints = 10, armorClass = 12)
        every { mockDice.roll() } returns 20

        character.attack(monster)

        assertThat(monster.getHitPoints()).isEqualTo(8)
        assertThat(monster.status.name).isEqualTo(ALIVE)
    }

    @Test
    fun attackKillsTargetWhenTheHitPointsAreLessThanOrEqualToZero() {
        val character = Character(name = "Lancelot", dice = mockDice)
        val monster = Character(name = "Goblin", hitPoints = 1, armorClass = 12)
        every { mockDice.roll() } returns 14

        character.attack(monster)

        assertThat(monster.getHitPoints()).isEqualTo(0)
        assertThat(monster.status.name).isEqualTo(DEAD)
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

        character.abilities[CONSTITUTION] = Ability(score = score)

        assertThat(character.abilities[CONSTITUTION]?.deriveModifier()).isEqualTo(expectedModifier)
    }

    @Test
    fun abilityScoreOutOfRangeWillThrowIllegalArgumentExceptionWhenDeriveModifierIsCalled() {
        val character = Character(name = "Lancelot")

        character.abilities[WISDOM] = Ability(score = 99)

        assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy { character.abilities[WISDOM]?.deriveModifier() }
            .withMessage("Score must be between 1 and 20")
    }

    @Test
    fun strengthModifierIncreasesAttackRoll() {
        val character = Character(name = "Lancelot", dice = mockDice)
        character.abilities[STRENGTH] = Ability(score = 16)
        val monster = Character(name = "Goblin", hitPoints = 10, armorClass = 12)
        every { mockDice.roll() } returns 11

        character.attack(monster)

        assertThat(monster.getHitPoints()).isEqualTo(6)
    }

    @Test
    fun strengthModifierDecreasesAttackRoll() {
        val character = Character(name = "Lancelot", dice = mockDice)
        character.abilities[STRENGTH] = Ability(score = 5)
        val monster = Character(name = "Goblin", hitPoints = 10, armorClass = 12)
        every { mockDice.roll() } returns 13

        character.attack(monster)

        assertThat(monster.getHitPoints()).isEqualTo(10)
    }

    @Test
    fun strengthModifierIsDoubledOnCriticalHits() {
        val character = Character(name = "Lancelot", dice = mockDice)
        character.abilities[STRENGTH] = Ability(score = 18)
        val monster = Character(name = "Goblin", hitPoints = 19, armorClass = 12)
        every { mockDice.roll() } returns 20

        character.attack(monster)

        assertThat(monster.getHitPoints()).isEqualTo(9)
    }

    @Test
    fun minimumDamageIsAlwaysOne() {
        val character = Character(name = "Lancelot", dice = mockDice)
        character.abilities[STRENGTH] = Ability(score = 4)
        val monster = Character(name = "Goblin", hitPoints = 10, armorClass = 12)
        every { mockDice.roll() } returns 15

        character.attack(monster)

        assertThat(monster.getHitPoints()).isEqualTo(9)
    }

    @Test
    fun minimumDamageIsAlwaysOneEvenForCriticalHits() {
        val character = Character(name = "Lancelot", dice = mockDice)
        character.abilities[STRENGTH] = Ability(score = 4)
        val monster = Character(name = "Goblin", hitPoints = 10, armorClass = 6)
        every { mockDice.roll() } returns 20

        character.attack(monster)

        assertThat(monster.getHitPoints()).isEqualTo(9)
    }

    @Test
    fun addDexterityModifierToArmorClass() {
        val character = Character(name = "Lancelot", armorClass = 13)
        character.abilities[DEXTERITY] = Ability(score = 12)

        val actualArmorClass = character.getArmorClass()

        assertThat(actualArmorClass).isEqualTo(14)
    }

    @Test
    fun addConstitutionModifierToHitPoints() {
        val character = Character(name = "Lancelot", hitPoints = 15)
        character.abilities[CONSTITUTION] = Ability(score = 16)

        val actualHitPoints = character.getHitPoints()

        assertThat(actualHitPoints).isEqualTo(18)
    }

    @Test
    fun getHitPointsWillReturnAtLeast1HitPointIfAlive() {
        val character = Character(name = "Lancelot", hitPoints = 4)
        character.abilities[CONSTITUTION] = Ability(score = 1)

        val actualHitPoints = character.getHitPoints()

        assertThat(actualHitPoints).isEqualTo(1)
    }
}