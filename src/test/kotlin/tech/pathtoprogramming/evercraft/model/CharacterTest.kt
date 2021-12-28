package tech.pathtoprogramming.evercraft.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

import tech.pathtoprogramming.evercraft.model.Alignment.*;

class CharacterTest {

    @Test
    fun character_shouldReturnCharacterProperties() {
        val character = Character("Drew", GOOD, 15, 10);

        assertEquals("Drew", character.name);
        assertEquals("GOOD", character.alignment.name);
        assertEquals(15, character.armorClass);
        assertEquals(10, character.hitPoints);
    }

    @Test
    fun armorClassShouldReturn10ByDefault() {
        val character = Character("Drew", NEUTRAL);

        assertEquals(10, character.armorClass)
    }

    @Test
    fun hitPointsShouldReturn5ByDefault() {
        val character = Character("Drew", EVIL);

        assertEquals(5, character.hitPoints)
    }
}