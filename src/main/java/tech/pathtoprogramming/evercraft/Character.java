package tech.pathtoprogramming.evercraft;

import tech.pathtoprogramming.evercraft.model.Alignment;

public class Character {

    private final String name;
    private final Alignment alignment;
    private final int armorClass;
    private final int hitPoints;

    public Character(String name, Alignment alignment) {
        this(name, alignment, 10, 5);
    }

    public Character(String name, Alignment alignment, int armorClass, int hitPoints) {
        this.name = name;
        this.alignment = alignment;
        this.armorClass = armorClass;
        this.hitPoints = hitPoints;
    }


    public String name() {
        return name;
    }

    public Alignment alignment() {
        return alignment;
    }

    public int armorClass() {
        return armorClass;
    }

    public int hitPoints() {
        return hitPoints;
    }
}
