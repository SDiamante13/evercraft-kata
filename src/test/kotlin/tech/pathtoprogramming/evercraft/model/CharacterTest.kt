package tech.pathtoprogramming.evercraft.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

import tech.pathtoprogramming.evercraft.model.Alignment.*;

class CharacterTest {

    @Test
    fun character_shouldReturnCharacterProperties() {
        val character = Character("Drew", GOOD);
        
        assertEquals("Drew", character.name);
        assertEquals("GOOD", character.alignment.name);
    }
}