package tech.pathtoprogramming.evercraft.model

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple
import org.junit.jupiter.api.Test
import tech.pathtoprogramming.evercraft.model.Alignment.*

class CharacterTest {

    @Test
    fun character_shouldReturnCharacterProperties() {
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
    }

    @Test
    fun armorClassShouldReturn10ByDefault() {
        val character = Character("Drew", NEUTRAL);

        assertThat(character.armorClass).isEqualTo(10)
    }

    @Test
    fun hitPointsShouldReturn5ByDefault() {
        val character = Character("Drew", EVIL);

        assertThat(character.hitPoints).isEqualTo(5)
    }
}