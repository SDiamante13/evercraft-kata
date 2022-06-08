package tech.pathtoprogramming.evercraft.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import tech.pathtoprogramming.evercraft.assertions.NumberAssert;

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

        tim.take(1);

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

        tim.take(1);

        assertThat(tim.isDead()).isTrue();
    }

    @Test
    void applyTheDexterityModifierToTheArmorClass() {
        Character tim = new Character(null, null, 10, 5);
        tim.abilities().setDexterityAbilityWith(14);

        assertThat(tim.armorClass()).isEqualTo(12);
    }

    @Test
    void applyTheConstitutionModifierToHitPoints() {
        Character tim = new Character(null, null, 10, 1);
        tim.abilities().setConstitutionAbilityWith(16);

        assertThat(tim.hitPoints()).isEqualTo(4);
    }

    @Test
    void hitPointsIsAtMinimumOne() {
        Character tim = new Character(null, null, 10, 1);
        tim.abilities().setConstitutionAbilityWith(6);

        assertThat(tim.hitPoints()).isEqualTo(1);
    }

    @Test
    void startAtLevelOne() {
        Character tim = new Character(null, null, 10, 1);

        assertThat(tim.level()).isEqualTo(1);
    }

    @Test
    void levelUpEvery1000ExperiencePointsAchievedLevel() {
        Character tim = new Character(null, null, 10, 1);

        tim.addExperience(8000);

        assertThat(tim.level()).isEqualTo(9);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1000,1",
            "3000,2",
            "5000,3",
            "7000,4",
            "9000,5",
            "11000,6",
    })
    void oneIsAddedToAttackRollForEveryEvenLevelAchieved(int experiencePoints, int expectedBonus) {
        Character tim = new Character(null, null, 10, 1);
        tim.addExperience(experiencePoints);

        int actualModifier = tim.modifier(10);

        assertThat(actualModifier).isEqualTo(expectedBonus);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1000,8",
            "2000,13",
            "3000,18",
            "4000,23",
            "5000,28",
            "6000,33",
            "7000,38",
            "8000,43",
            "9000,48",
    })
    void haveTheirHitPointsIncreasedBy5PlusConModifierPerLevel(int experiencePoints, int expectedHitPoints) {
        Character tim = new Character(null, null, 10, 1);
        tim.abilities().setConstitutionAbilityWith(15);

        tim.addExperience(experiencePoints);

        assertThat(tim.hitPoints()).isEqualTo(expectedHitPoints);
    }
}
