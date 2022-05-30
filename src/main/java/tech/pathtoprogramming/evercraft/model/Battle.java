package tech.pathtoprogramming.evercraft.model;

import tech.pathtoprogramming.evercraft.TwentySidedDie;

public class Battle {

    private final TwentySidedDie twentySidedDie;

    public Battle(TwentySidedDie twentySidedDie) {
        this.twentySidedDie = twentySidedDie;
    }

    public void recordAttack(Character combatant, Character enemyCombatant) {
        int roll = twentySidedDie.roll();

        if (enemyCombatant.isHit(roll)) { // feature envy?
            enemyCombatant.takeDamage();
        }
    }

}
