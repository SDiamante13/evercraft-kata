package tech.pathtoprogramming.evercraft.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CharacterTest {

    @Test
    fun getName_shouldReturnName() {
        val character = Character("Drew");

        assertEquals("Drew", character.name);
    }
}