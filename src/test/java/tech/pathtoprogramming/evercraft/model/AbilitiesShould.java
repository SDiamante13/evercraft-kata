package tech.pathtoprogramming.evercraft.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AbilitiesShould {

    @Test
    void haveDefaults() {
        Abilities abilities = new Abilities();

        assertThat(abilities.strength().score()).isEqualTo(10);
        assertThat(abilities.dexterity().score()).isEqualTo(10);
        assertThat(abilities.constitution().score()).isEqualTo(10);
        assertThat(abilities.wisdom().score()).isEqualTo(10);
        assertThat(abilities.intelligence().score()).isEqualTo(10);
        assertThat(abilities.charisma().score()).isEqualTo(10);
    }
}
