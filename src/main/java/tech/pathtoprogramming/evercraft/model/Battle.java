package tech.pathtoprogramming.evercraft.model;

import tech.pathtoprogramming.evercraft.TwentySidedDie;

import static java.lang.Math.max;

public class Battle {

    private final TwentySidedDie twentySidedDie;

    public Battle(TwentySidedDie twentySidedDie) {
        this.twentySidedDie = twentySidedDie;
    }

    public void recordAttack(Character combatant, Character enemyCombatant) {
        int roll = twentySidedDie.roll();
        int modifier = combatant.abilities().calculateModifierFor(roll);
        if (enemyCombatant.isHit(roll + modifier)) {
            int damageDealt = max(1 + modifier, 1);
            enemyCombatant.takeDamage(damageDealt);
            combatant.addExperience(10);
        }
    }
}
