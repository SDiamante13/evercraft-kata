package tech.pathtoprogramming.evercraft.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ACharacterShould {

    private static final Builder __ = new Builder();

    @Test
    void haveAName() {
        Character tim = __.name("Tim").__;

        assertThat(tim.name()).isEqualTo("Tim");
    }

    @Test
    void haveAnAlignment() {
        Character tim =__.alignment(Alignment.GOOD).__;

        assertThat(tim.alignment()).isEqualTo(Alignment.GOOD);
    }

    @Test
    void haveDefaults() {
        Character tim = new Character(null, null);

        assertThat(tim.armorClass()).isEqualTo(10);
        assertThat(tim.hitPoints()).isEqualTo(5);
    }

    @Test
    void reduceItsOwnHitPointsByOne() {
        Character tim = __.hitPoints(5).__;

        tim.take(1);

        assertThat(tim.hitPoints()).isEqualTo(4);
    }

    @Test
    void determineIfTheRollIsAHit() {
        int armor = 10;
        Character tim = __.armorClass(armor).__;

        assertThat(tim.isHit(armor - 1)).isFalse();
        assertThat(tim.isHit(armor)).isTrue();
        assertThat(tim.isHit(armor + 1)).isTrue();
    }

    @Test
    void beDeadWhenItsHitPointsAreZeroOrBelow() {
        Character tim = __.hitPoints(1).__;

        tim.take(1);

        assertThat(tim.isDead()).isTrue();
    }

    @Test
    void applyTheDexterityModifierToTheArmorClass() {
        Character tim = __.armorClass(10).__;
        tim.abilities().setDexterityAbilityWith(14);

        assertThat(tim.armorClass()).isEqualTo(12);
    }

    @Test
    void applyTheConstitutionModifierToHitPoints() {
        Character tim = __.hitPoints(1).__;
        tim.abilities().setConstitutionAbilityWith(16);

        assertThat(tim.hitPoints()).isEqualTo(4);
    }

    @Test
    void haveAtMinimumOneHitPoint() {
        Character tim = __.hitPoints(1).__;
        tim.abilities().setConstitutionAbilityWith(6);

        assertThat(tim.hitPoints()).isEqualTo(1);
    }

    @Test
    void startAtLevelOne() {
        Character tim = __.hitPoints(1).__;

        assertThat(tim.level()).isEqualTo(1);
    }

    @Test
    void levelUpEvery1000ExperiencePointsAchievedLevel() {
        Character tim = __.__().__;

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
    void oneIsAddedToAttackRollForEveryEvenLevelAchieved(int experiencePoints, int expectedBonusDamage) {
        Character tim = __.__().__;
        tim.addExperience(experiencePoints);

        assertThat(tim.modifier(10)).isEqualTo(expectedBonusDamage);
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
        Character tim = __.hitPoints(1).__;
        tim.abilities().setConstitutionAbilityWith(15);

        tim.addExperience(experiencePoints);

        assertThat(tim.hitPoints()).isEqualTo(expectedHitPoints);
    }

    static class Builder {

        protected Map<String, Object> details = new HashMap<>();
        private Character __;

        public Builder() {
        }

        public Builder(Map<String, Object> old, String field, Object newValue) {
            this.details = new HashMap<>(old);
            this.details.put(field, newValue);
            __ = create();
        }

        public Builder name(String name) {
            return new Builder(details, "name", name);
        }

        public Builder alignment(Alignment alignment) {
            return new Builder(details, "alignment", alignment);

        }

        public Builder armorClass(int armorClass) {
            return new Builder(details, "armorClass", armorClass);

        }

        public Builder hitPoints(int hitPoints) {
            return new Builder(details, "hitPoints", hitPoints);
        }

        public Builder __() {
            return new Builder(details, "", 0);
        }

        public Character create() {

            return new Character((String) details.getOrDefault("name", ""),
                    (Alignment) details.getOrDefault("alignment", null),
                    (Integer) details.getOrDefault("armorClass", 0),
                    (Integer) details.getOrDefault("hitPoints", 0)
            );
        }
    }
}
