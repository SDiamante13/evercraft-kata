package tech.pathtoprogramming.evercraft;

import tech.pathtoprogramming.evercraft.model.Alignment;

public class Character {

    private final String name;
    private final Alignment alignment;

    public Character(String name, Alignment alignment) {
        this.name = name;
        this.alignment = alignment;
    }


    public String name() {
        return name;
    }

    public Alignment alignment() {
        return alignment;
    }
}
