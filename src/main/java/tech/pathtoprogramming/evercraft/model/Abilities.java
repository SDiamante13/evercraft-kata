package tech.pathtoprogramming.evercraft.model;

import java.util.HashMap;
import java.util.Map;

import static tech.pathtoprogramming.evercraft.model.Ability.*;
import static tech.pathtoprogramming.evercraft.model.Ability.DEXTERITY;
import static tech.pathtoprogramming.evercraft.model.Ability.STRENGTH;

public class Abilities {
    private Map<Ability, Integer> abilityMap; // TODO: Refactor Integer to AbilityScore so you can have score and modifer

    public Abilities() {
        this.abilityMap = new HashMap<>();
        this.abilityMap.put(STRENGTH, 10);
        this.abilityMap.put(DEXTERITY, 10);
        this.abilityMap.put(CONSTITUTION, 10);
        this.abilityMap.put(WISDOM, 10);
        this.abilityMap.put(INTELLIGENCE, 10);
        this.abilityMap.put(CHARISMA, 10);
    }

    public int strength() {
        return abilityMap.get(STRENGTH);
    }

    public int dexterity() {
        return abilityMap.get(DEXTERITY);
    }

    public int constitution() {
        return abilityMap.get(CONSTITUTION);
    }

    public int wisdom() {
        return abilityMap.get(WISDOM);
    }

    public int intelligence() {
        return abilityMap.get(INTELLIGENCE);
    }

    public int charisma() {
        return abilityMap.get(CHARISMA);
    }
}
