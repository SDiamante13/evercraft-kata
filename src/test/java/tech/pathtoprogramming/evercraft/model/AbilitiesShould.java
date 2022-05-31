package tech.pathtoprogramming.evercraft.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AbilitiesShould {


    // character has abilities? Map? <abilityName, Score>
    // tim.abilities().strength() returns strength ability score


    @Test
    void returnTheScoreOfTheStrengthAbility() {
        Abilities abilities = new Abilities();

        assertThat(abilities.strength().score()).isEqualTo(10);
    }

    @Test
    void returnTheScoreOfTheDexterityAbility() {
        Abilities abilities = new Abilities();

        assertThat(abilities.dexterity().score()).isEqualTo(10);
    }

    @Test
    void returnTheScoreOfTheConstitutionAbility() {
        Abilities abilities = new Abilities();

        assertThat(abilities.constitution().score()).isEqualTo(10);
    }

    @Test
    void returnTheScoreOfTheWisdomAbility() {
        Abilities abilities = new Abilities();

        assertThat(abilities.wisdom().score()).isEqualTo(10);
    }

    @Test
    void returnTheScoreOfTheIntelligenceAbility() {
        Abilities abilities = new Abilities();

        assertThat(abilities.intelligence().score()).isEqualTo(10);
    }

    @Test
    void returnTheScoreOfTheCharismaAbility() {
        Abilities abilities = new Abilities();

        assertThat(abilities.charisma().score()).isEqualTo(10);
    }

    @Test
    void returnTheModifierOfTheCharismaAbility() {
        Abilities abilities = new Abilities();

        assertThat(abilities.charisma().modifier()).isEqualTo(0);
    }
}
