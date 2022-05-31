package tech.pathtoprogramming.evercraft.model;

import tech.pathtoprogramming.evercraft.TwentySidedDie;

import static java.lang.Math.max;

public class Battle {

    public static final int CRITICAL_HIT = 20;

    private final TwentySidedDie twentySidedDie;

    public Battle(TwentySidedDie twentySidedDie) {
        this.twentySidedDie = twentySidedDie;
    }

    public void recordAttack(Character combatant, Character enemyCombatant) {
        int roll = twentySidedDie.roll();
        int modifier = calculateModifier(combatant, roll);
        if (enemyCombatant.isHit(roll + modifier)) {
            int damageDealt = max(1 + modifier, 1);
            enemyCombatant.takeDamage(damageDealt);
        }
    }

    private int calculateModifier(Character combatant, int roll) {
        final int originalModifier = combatant.abilities().strength().modifier();
        final int CRITICAL_HIT_MULTIPLIER = 2;
        final int CRITICAL_HIT_DAMAGE_BONUS = 1;
        return isCriticalHit(roll) ?
                originalModifier * CRITICAL_HIT_MULTIPLIER + CRITICAL_HIT_DAMAGE_BONUS :
                originalModifier;
    }

    private boolean isCriticalHit(int roll) {
        return roll == CRITICAL_HIT;
    }
}
