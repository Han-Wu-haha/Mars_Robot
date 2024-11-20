package entities;

/**
 * This class provides an abstract representation of earth animals in the game.
 * Each earth animal inherits the properties and methods from the Entity class and has 
 * additional properties like health, quantity, base score, and action score.
 * 
 * @author Han Wu, whw10@student.unimelb.edu.au, 1468664
 */
public abstract class EntityEarthAnimal extends Entity {

    // Health of the earth animal.
    protected int health = 0;

    // Quantity of the earth animal.
    protected int qty = 0;

    // Base score for the earth animal, defaulted to 5.
    protected int baseScore = 5;

    // Action score representing certain actions performed by or on the earth animal.
    protected int actionScore;

    // Unique key identifier for the earth animal.
    private String key;

    // Name of the earth animal.
    private String name;

    /**
     * Constructor to initialize the earth animal with a key and name.
     *
     * @param key The key identifier for the earth animal.
     * @param name The name of the earth animal.
     */
    public EntityEarthAnimal(String key, String name) {
        this.key = key;
        this.name = name;
    }

    @Override
    public void setActionScore(int actionScore) {
        this.actionScore = actionScore;
    }

    @Override
    public int getActionScore() {
        return this.actionScore;
    }

    @Override
    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public int getQty() {
        return qty;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getBaseScore() {
        return this.baseScore;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}

