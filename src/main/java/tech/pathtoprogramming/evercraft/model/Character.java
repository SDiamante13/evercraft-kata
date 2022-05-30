package tech.pathtoprogramming.evercraft.model;

public class Character {

    public static final int DEFAULT_ARMOR_CLASS = 10;
    public static final int DEFAULT_HIT_POINTS = 5;

    private final String name;
    private final Alignment alignment;
    private final int armorClass;
    private int hitPoints;

    public Character(String name, Alignment alignment) {
        this(name, alignment, DEFAULT_ARMOR_CLASS, DEFAULT_HIT_POINTS);
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

    public void takeDamage() {
        hitPoints--;
    }

    public boolean isHit(int roll) {
        return roll >= armorClass;
    }

    public boolean isDead() {
        return hitPoints <= 0;
    }
}
