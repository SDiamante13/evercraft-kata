package tech.pathtoprogramming.evercraft.model;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import tech.pathtoprogramming.evercraft.TwentySidedDie;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static tech.pathtoprogramming.evercraft.model.Character.DEFAULT_ARMOR_CLASS;

class ADungeonMasterShould {
    private static final int MISS = 3;
    private static final int HIT = 10;
    public static final int STARTING_ENEMY_HP = 10;
    private static final int CRITICAL_HIT = 20;

    private final TwentySidedDie twentySidedDie = mock(TwentySidedDie.class);
    private final DungeonMaster dungeonMaster = new DungeonMaster(twentySidedDie);

    @Test
    void notReduceAnyHitPointsFromEnemyCombatantOnAMiss() {
        stubRollOf(MISS);

        dungeonMaster.battle(aHero(), anEnemy());

        assertThat(anEnemy().hitPoints()).isEqualTo(STARTING_ENEMY_HP);
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

    @Test
    void addStrengthModifierToAttackRoll() {
        Character enemy = anEnemyWith(11);
        stubRollOf(HIT);

        dungeonMaster.battle(aHeroWithStrengthOf(12), enemy);

        assertThat(enemy.hitPoints()).isEqualTo(STARTING_ENEMY_HP - 1 - 1);
    }

    @Test
    void addStrengthModifierToDamageDealt() {
        Character enemy = anEnemy();
        stubRollOf(HIT);

        dungeonMaster.battle(aHeroWithStrengthOf(14), enemy);

        assertThat(enemy.hitPoints()).isEqualTo(STARTING_ENEMY_HP - 1 - 2);
    }


    @Test
    void doubleTheStrengthModifierForACriticalHit() {
        Character enemy = anEnemy();
        stubRollOf(CRITICAL_HIT);

        dungeonMaster.battle(aHeroWithStrengthOf(18), enemy);

        assertThat(enemy.hitPoints()).isEqualTo(STARTING_ENEMY_HP - 9);
    }

    @Test
    void alwaysDealAtLeastOneDamageOnAHit() {
        Character enemy = anEnemy();
        stubRollOf(14);

        dungeonMaster.battle(aHeroWithStrengthOf(3), enemy);

        assertThat(enemy.hitPoints()).isEqualTo(STARTING_ENEMY_HP - 1);
    }

    @Test
    void addExperiencePointsToCharacter() {
        Character hero = aHero();
        stubRollOf(HIT);

        assertThat(hero.experiencePoints()).isZero();

        dungeonMaster.battle(hero, anEnemy());

        assertThat(hero.experiencePoints()).isEqualTo(10);
    }

    @Test
    void unsuccessfulAttacksDoNotYieldExperiencePointsToCharacter() {
        Character hero = aHero();
        stubRollOf(MISS);

        assertThat(hero.experiencePoints()).isZero();

        dungeonMaster.battle(aHero(), anEnemy());

        assertThat(hero.experiencePoints()).isZero();
    }

    // move tests out too long of file

    private void stubRollOf(int total) {
        when(twentySidedDie.roll())
                .thenReturn(total);
    }

    private Character aHero() {
        return new Character("Hero", Alignment.GOOD);
    }

    private Character aHeroWith(int armorClass, int hitPoints) {
        return new Character("Hero", Alignment.GOOD, armorClass, hitPoints);
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
