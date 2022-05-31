package tech.pathtoprogramming.evercraft.model;

import java.util.EnumMap;
import java.util.Map;

import static tech.pathtoprogramming.evercraft.model.Ability.*;

public class Abilities {

    public static final int CRITICAL_HIT = 20;
    public static final int CRITICAL_HIT_MULTIPLIER = 2;
    public static final int CRITICAL_HIT_DAMAGE_BONUS = 1;

    private final Map<Ability, AbilityScore> abilityMap;

    public Abilities() {
        this.abilityMap = new EnumMap<>(Ability.class);
        this.abilityMap.put(STRENGTH, new AbilityScore(10));
        this.abilityMap.put(DEXTERITY, new AbilityScore(10));
        this.abilityMap.put(CONSTITUTION, new AbilityScore(10));
        this.abilityMap.put(WISDOM, new AbilityScore(10));
        this.abilityMap.put(INTELLIGENCE, new AbilityScore(10));
        this.abilityMap.put(CHARISMA, new AbilityScore(10));
    }

    public AbilityScore strength() {
        return abilityMap.get(STRENGTH);
    }

    public AbilityScore dexterity() {
        return abilityMap.get(DEXTERITY);
    }

    public AbilityScore constitution() {
        return abilityMap.get(CONSTITUTION);
    }

    public AbilityScore wisdom() {
        return abilityMap.get(WISDOM);
    }

    public AbilityScore intelligence() {
        return abilityMap.get(INTELLIGENCE);
    }

    public AbilityScore charisma() {
        return abilityMap.get(CHARISMA);
    }

    public void setStrengthAbilityWith(int score) {
        this.abilityMap.replace(STRENGTH, new AbilityScore(score));
    }

    int calculateModifierFor(int roll) {
        return roll == CRITICAL_HIT ?
                strength().modifier() * CRITICAL_HIT_MULTIPLIER + CRITICAL_HIT_DAMAGE_BONUS :
                strength().modifier();
    }

    public void setDexterityAbilityWith(int score) {
        this.abilityMap.replace(DEXTERITY, new AbilityScore(score));
    }

    public void setConstitutionAbilityWith(int score) {
        this.abilityMap.replace(CONSTITUTION, new AbilityScore(score));
    }
}
