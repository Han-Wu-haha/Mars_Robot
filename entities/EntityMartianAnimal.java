package entities;

/**
 * This class provides an abstract representation of Martian animals in the game.
 * Each Martian animal inherits the properties and methods from the Entity class and has 
 * additional properties like health, quantity, and action score.
 * 
 * @author Han Wu, whw10@student.unimelb.edu.au, 1468664
 */
public abstract class EntityMartianAnimal extends Entity {

    // Quantity of the Martian animal.
    protected int qty = 0;

    // Health of the Martian animal, defaulted to 15.
    protected int health = 15;

    // Action score representing certain actions performed by or on the Martian animal.
    protected int actionScore;

    // Unique key identifier for the Martian animal.
    private String key;

    // Name of the Martian animal.
    private String name;

    /**
     * Constructor to initialize the Martian animal with a key and name.
     *
     * @param key The key identifier for the Martian animal.
     * @param name The name of the Martian animal.
     */
    public EntityMartianAnimal(String key, String name) {
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
        return this.qty;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    // Martian animals do not have a base score, hence it returns 0.
    @Override
    public int getBaseScore() {
        return 0;
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

