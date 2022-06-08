package tech.pathtoprogramming.evercraft.model;

public class Character {

    public static final int DEFAULT_ARMOR_CLASS = 10;
    public static final int DEFAULT_HIT_POINTS = 5;
    public static final int CRITICAL_HIT = 20;
    public static final int CRITICAL_HIT_MULTIPLIER = 2;
    public static final int CRITICAL_HIT_DAMAGE_BONUS = 1;

    private final String name;
    private final Alignment alignment;
    private final int armorClass;
    private int hitPoints;
    private final Abilities abilities;
    private int experiencePoints;

    public Character(String name, Alignment alignment) {
        this(name, alignment, DEFAULT_ARMOR_CLASS, DEFAULT_HIT_POINTS);
    }

    public Character(String name, Alignment alignment, int armorClass, int hitPoints) {
        this.name = name;
        this.alignment = alignment;
        this.armorClass = armorClass;
        this.hitPoints = hitPoints;
        this.abilities = new Abilities();
    }


    public String name() {
        return name;
    }

    public Alignment alignment() {
        return alignment;
    }

    public int armorClass() {
        return armorClass + abilities.dexterity().modifier();
    }

    public int hitPoints() {
        return Math.max(hitPoints + abilities.constitution().modifier() + hitPointBonusPerLevel(), 1);
    }

    private int hitPointBonusPerLevel() {
        return (level() - 1) * 5;
    }

    public Abilities abilities() {
        return abilities;
    }

    // TODO: Rename to damage?
    public int modifier(int roll) {
        int criticalHitDamage = roll == CRITICAL_HIT ?
                abilities.strength().modifier() * CRITICAL_HIT_MULTIPLIER + CRITICAL_HIT_DAMAGE_BONUS :
                abilities.strength().modifier();
        int bonusForLevel = level() / 2;
        return criticalHitDamage + bonusForLevel;
    }

    public void take(int amount) {
        hitPoints -= amount;
    }

    public boolean isHit(int roll) {
        return roll >= armorClass();
    }

    public boolean isDead() {
        return hitPoints <= 0;
    }

    public int experiencePoints() {
        return experiencePoints;
    }

    public void addExperience(int experience) {
        experiencePoints += experience;
    }

    public int level() {
        return (experiencePoints + 1000) / 1000;
    }
}
