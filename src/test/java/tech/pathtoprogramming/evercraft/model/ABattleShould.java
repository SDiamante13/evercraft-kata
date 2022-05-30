package tech.pathtoprogramming.evercraft.model;

import org.junit.jupiter.api.Test;
import tech.pathtoprogramming.evercraft.TwentySidedDie;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//    As a combatant I want to be able to attack other combatants so that I can survive to fight another day
//
//    roll a 20 sided die (don't code the die)
//            roll must meet or beat opponents armor class to hit
//            a natural roll of 20 always hits

// Battle - 20 sided die
// battle.attack(combatant, enemyCombatant) changes state - returns void
class ABattleShould {
    private static final int MISSED_ROLL = 3;
    private static final int HIT_ROLL = 10;
    public static final int ENEMY_HIT_POINTS = 5;
    private static final int CRITICAL_HIT_ROLL = 20;

    private final TwentySidedDie twentySidedDie = mock(TwentySidedDie.class);
    private final Battle battle = new Battle(twentySidedDie);

    @Test
    void notReduceAnyHitPointsFromEnemyCombatantOnAMiss() {
        when(twentySidedDie.roll())
                .thenReturn(MISSED_ROLL);
        Character combatant = new Character("Combatant", Alignment.GOOD);
        Character enemyCombatant = new Character("Enemy", Alignment.EVIL, 10, ENEMY_HIT_POINTS);

        battle.recordAttack(combatant, enemyCombatant);

        assertThat(enemyCombatant.hitPoints()).isEqualTo(5);
    }

    @Test
    void reduceHitPointsFromEnemyCombatantOnAHit() {
        when(twentySidedDie.roll())
                .thenReturn(HIT_ROLL);
        Character combatant = new Character("Combatant", Alignment.GOOD);
        Character enemyCombatant = new Character("Enemy", Alignment.EVIL, 10, ENEMY_HIT_POINTS);

        battle.recordAttack(combatant, enemyCombatant);

        assertThat(enemyCombatant.hitPoints()).isEqualTo(ENEMY_HIT_POINTS - 1);
    }

    @Test
    void reduceDoubleTheHitPointsForACriticalHit() {
        when(twentySidedDie.roll())
                .thenReturn(CRITICAL_HIT_ROLL);
        Character combatant = new Character("Combatant", Alignment.GOOD);
        Character enemyCombatant = new Character("Enemy", Alignment.EVIL, 10, ENEMY_HIT_POINTS);

        battle.recordAttack(combatant, enemyCombatant);

        assertThat(enemyCombatant.hitPoints()).isEqualTo(ENEMY_HIT_POINTS - 2);
    }
}
