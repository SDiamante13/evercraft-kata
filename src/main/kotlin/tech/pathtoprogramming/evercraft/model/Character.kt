package tech.pathtoprogramming.evercraft.model

data class Character(val name: String, val alignment: Alignment)

enum class Alignment {
   GOOD, NEUTRAL, EVIL
}