package tech.pathtoprogramming.evercraft.model;

import org.junit.jupiter.api.Test;
import tech.pathtoprogramming.evercraft.Character;

import static org.assertj.core.api.Assertions.assertThat;

class ACharacterShould {

    @Test
    void haveAName() {
        Character tim = new Character("Tim", null);

        assertThat(tim.name()).isEqualTo("Tim");
    }

    @Test
    void haveAnAlignment() {
        Character tim = new Character(null, Alignment.GOOD);

        assertThat(tim.alignment().name()).isEqualTo("GOOD");
    }

    @Test
    void haveAnArmorClassThatDefaultsToTen() {
        Character tim = new Character(null, null);

        assertThat(tim.armorClass()).isEqualTo(10);
    }

    @Test
    void haveHitPointsThatDefaultToFive() {
        Character tim = new Character(null, null);

        assertThat(tim.hitPoints()).isEqualTo(5);
    }
}
