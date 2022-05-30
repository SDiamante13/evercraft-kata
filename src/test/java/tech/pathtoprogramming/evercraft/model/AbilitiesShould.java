package tech.pathtoprogramming.evercraft.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AbilitiesShould {


    // character has abilities? Map? <abilityName, Score>
    // tim.abilities().strength() returns strength ability score
    // Ability is enum that has a score and method to get modifier.


    @Test
    void returnTheScoreOfTheStrengthAbility() {
        Abilities abilities = new Abilities();

        assertThat(abilities.strength()).isEqualTo(10);
    }

    @Test
    void returnTheScoreOfTheDexterityAbility() {
        Abilities abilities = new Abilities();

        assertThat(abilities.dexterity()).isEqualTo(10);
    }

    @Test
    void returnTheScoreOfTheConstitutionAbility() {
        Abilities abilities = new Abilities();

        assertThat(abilities.constitution()).isEqualTo(10);
    }

    @Test
    void returnTheScoreOfTheWisdomAbility() {
        Abilities abilities = new Abilities();

        assertThat(abilities.wisdom()).isEqualTo(10);
    }

    @Test
    void returnTheScoreOfTheIntelligenceAbility() {
        Abilities abilities = new Abilities();

        assertThat(abilities.intelligence()).isEqualTo(10);
    }

    @Test
    void returnTheScoreOfTheCharismaAbility() {
        Abilities abilities = new Abilities();

        assertThat(abilities.charisma()).isEqualTo(10);
    }
}
