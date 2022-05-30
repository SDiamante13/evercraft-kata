package tech.pathtoprogramming.evercraft.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import tech.pathtoprogramming.evercraft.Character;

class ACharacterShould {
    @Test
    void haveAName() {
        Character tim = new Character("Tim");

        Assertions.assertThat(tim.name()).isEqualTo("Tim");
    }
}
