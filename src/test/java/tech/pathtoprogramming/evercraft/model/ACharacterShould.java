package tech.pathtoprogramming.evercraft.model;

import org.junit.jupiter.api.Test;

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

    @Test
    void reduceItsOwnHitPointsByOne() {
        Character tim = new Character(null, null, 10, 5);

        tim.takeDamage();

        assertThat(tim.hitPoints()).isEqualTo(4);
    }

    @Test
    void determineIfTheRollIsAHit() {
        Character tim = new Character(null, null, 10, 5);

        boolean isHit = tim.isHit(10);

        assertThat(isHit).isTrue();
    }

    @Test
    void determineIfTheRollIsAMiss() {
        Character tim = new Character(null, null, 10, 5);

        boolean isHit = tim.isHit(1);

        assertThat(isHit).isFalse();
    }

    @Test
    void beDeadWhenItsHitPointsAreZeroOrBelow() {
        Character tim = new Character(null, null, 10, 1);

        tim.takeDamage();

        assertThat(tim.isDead()).isTrue();
    }
}
