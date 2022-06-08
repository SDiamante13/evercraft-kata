package tech.pathtoprogramming.evercraft.model;

import tech.pathtoprogramming.evercraft.TwentySidedDie;

import static java.lang.Math.max;

public class Battle {

    public static final int XP_FOR_SUCCESSFUL_ATTACK = 10;

    private final TwentySidedDie twentySidedDie;

    public Battle(TwentySidedDie twentySidedDie) {
        this.twentySidedDie = twentySidedDie;
    }

    public void recordAttack(Character combatant, Character enemyCombatant) {
        int roll = twentySidedDie.roll();
        int modifier = combatant.abilities().calculateModifierFor(roll);

        if (enemyCombatant.isHit(roll + modifier)) {
            dealDamage(enemyCombatant, modifier);
            combatant.addExperience(XP_FOR_SUCCESSFUL_ATTACK);
        }
    }

    private void dealDamage(Character enemyCombatant, int modifier) {
        int damageDealt = max(1 + modifier, 1);
        enemyCombatant.takeDamage(damageDealt);
    }
}
