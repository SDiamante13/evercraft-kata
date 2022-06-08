package tech.pathtoprogramming.evercraft.model;

import org.junit.jupiter.api.Test;
import tech.pathtoprogramming.evercraft.TwentySidedDie;
import tech.pathtoprogramming.evercraft.assertions.NumberAssert;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static tech.pathtoprogramming.evercraft.model.Character.DEFAULT_ARMOR_CLASS;

class ADungeonMasterShould {
    private static final int MISS = 3;
    private static final int HIT = 10;
    private static final int CRITICAL_HIT = 20;
    public static final int STARTING_ENEMY_HP = 10;

    private final TwentySidedDie twentySidedDie = mock(TwentySidedDie.class);
    private final DungeonMaster dungeonMaster = new DungeonMaster(twentySidedDie);

    // region Basic Combat Tests
    @Test
    void notReduceAnyHitPointsFromEnemyCombatantOnAMiss() {
        Character enemy = anEnemy();
        stubRollOf(MISS);

        dungeonMaster.battle(aHero(), enemy);

        assertThat(enemy.hitPoints()).isEqualTo(STARTING_ENEMY_HP);
    }

    @Test
    void reduceHitPointsFromEnemyCombatantOnAHit() {
        Character enemy = anEnemy();
        stubRollOf(HIT);

        dungeonMaster.battle(aHero(), enemy);

        assertThat(enemy.hitPoints()).isEqualTo(STARTING_ENEMY_HP - 1);
    }

    @Test
    void reduceDoubleTheHitPointsForACriticalHit() {
        Character enemy = anEnemy();
        stubRollOf(CRITICAL_HIT);

        dungeonMaster.battle(aHero(), enemy);

        assertThat(enemy.hitPoints()).isEqualTo(STARTING_ENEMY_HP - 2);
    }
    // endregion Basic Combat Tests

    // region Strength Modifier Tests
    @Test
    void addStrengthModifierToAttackRoll() {
        // Llewellyn suggested that I make the SCORE A CONSTANT like MODIFIER_+1
        Character aHeroWithPlusOneStrengthModifier = aHeroWithStrengthOf(12);
        Character enemy = anEnemyWith(11);
        stubRollOf(HIT);

        dungeonMaster.battle(aHeroWithPlusOneStrengthModifier, enemy);

        assertThat(enemy.hitPoints()).isEqualTo(STARTING_ENEMY_HP - 1 - 1);
    }

    @Test
    void addStrengthModifierToDamageDealt() {
        Character aHeroWithPlusTwoStrengthModifier = aHeroWithStrengthOf(14);
        Character enemy = anEnemy();
        stubRollOf(HIT);

        dungeonMaster.battle(aHeroWithPlusTwoStrengthModifier, enemy);

        assertThat(enemy.hitPoints()).isEqualTo(STARTING_ENEMY_HP - 1 - 2);
    }

    @Test
    void doubleTheStrengthModifierForACriticalHit() {
        Character aHeroWithPlusFourStrengthModifier = aHeroWithStrengthOf(18);
        Character enemy = anEnemy();
        stubRollOf(CRITICAL_HIT);

        dungeonMaster.battle(aHeroWithPlusFourStrengthModifier, enemy);

        assertThat(enemy.hitPoints()).isEqualTo(STARTING_ENEMY_HP - 9);
    }

    @Test
    void alwaysDealAtLeastOneDamageOnAHit() {
        Character heroWithNegativeFourStrengthModifier = aHeroWithStrengthOf(3);
        Character enemy = anEnemy();
        stubRollOf(14);

        dungeonMaster.battle(heroWithNegativeFourStrengthModifier, enemy);

        assertThat(enemy.hitPoints()).isEqualTo(STARTING_ENEMY_HP - 1);
    }
    // endregion Strength Modifier Tests

    // region Experience Points Tests

    @Test
    void addExperiencePointsToCharacter() {
        Character hero = aHero();
        stubRollOf(HIT);

        assertThat(hero.experiencePoints()).isZero();

        dungeonMaster.battle(hero, anEnemy());

        NumberAssert.assertThat(hero.experiencePoints()).isTen();
    }

    @Test
    void unsuccessfulAttacksDoNotYieldExperiencePointsToCharacter() {
        Character hero = aHero();
        stubRollOf(MISS);

        assertThat(hero.experiencePoints()).isZero();

        dungeonMaster.battle(hero, anEnemy());

        assertThat(hero.experiencePoints()).isZero();
    }
    // endregion Experience Points Tests

    private void stubRollOf(int total) {
        when(twentySidedDie.roll())
                .thenReturn(total);
    }

    private Character aHero() {
        return new Character("Hero", Alignment.GOOD);
    }

    private Character aHeroWithStrengthOf(int score) {
        Character combatant = new Character("Hero", Alignment.GOOD);

        combatant.abilities().setStrengthAbilityWith(score);
        return combatant;
    }

    private Character anEnemy() {
        return new Character("Enemy", Alignment.EVIL, DEFAULT_ARMOR_CLASS, STARTING_ENEMY_HP);
    }

    private Character anEnemyWith(int armorClass) {
        return new Character("Enemy", Alignment.EVIL, armorClass, STARTING_ENEMY_HP);
    }

}
