package tech.pathtoprogramming.evercraft.model;

import tech.pathtoprogramming.evercraft.TwentySidedDie;

import static java.lang.Math.max;

public class DungeonMaster {

    private final TwentySidedDie twentySidedDie;

    public static final int XP_FOR_SUCCESSFUL_ATTACK = 10;

    public DungeonMaster(TwentySidedDie twentySidedDie) {
        this.twentySidedDie = twentySidedDie;
    }

    public void battle(Character combatant, Character enemyCombatant) {
        int attackRoll = twentySidedDie.roll();
        int modifier = combatant.modifier(attackRoll);

        if (enemyCombatant.isHit(attackRoll + modifier)) {
            int damage = max(1 + modifier, 1);
            enemyCombatant.take(damage);
            combatant.addExperience(XP_FOR_SUCCESSFUL_ATTACK);
        }
    }
}
