package tech.pathtoprogramming.evercraft.model;

import tech.pathtoprogramming.evercraft.TwentySidedDie;

public class Battle {

    public static final int CRITICAL_HIT = 20;

    private final TwentySidedDie twentySidedDie;

    public Battle(TwentySidedDie twentySidedDie) {
        this.twentySidedDie = twentySidedDie;
    }

    public void recordAttack(Character combatant, Character enemyCombatant) {
        int roll = twentySidedDie.roll();

        recordDamageForSuccessfulAttack(enemyCombatant, roll, combatant.abilities().strength().modifier());

        recordCriticalDamage(enemyCombatant, roll);
    }

    private void recordDamageForSuccessfulAttack(Character enemyCombatant, int roll, int modifier) {
        if (enemyCombatant.isHit(roll + modifier)) {
            enemyCombatant.takeDamage(1 + modifier);
        }
    }

    private void recordCriticalDamage(Character enemyCombatant, int roll) {
        if (roll == CRITICAL_HIT) {
            enemyCombatant.takeDamage(1);
        }
    }

}
