package entities;

/**
 * EntityPlant represents a type of Entity that models plant-based entities in the game.
 * These entities have attributes like base score, health, quantity, action score, and an identifying key and name.
 * 
 * @author Han Wu, whw10@student.unimelb.edu.au, 1468664
 */
public abstract class EntityPlant extends Entity {

    // Default base score for a plant entity
    private int baseScore = 2;

    // Health attribute of the plant entity, initialized to 0 by default
    private int health = 0;

    // Quantity attribute of the plant entity
    private int qty = 0;

    // Represents the action score associated with the plant entity
    private int actionScore;

    // Unique identifier for the plant entity
    private String key;

    // Human-readable name of the plant entity
    private String name;

    /**
     * Constructor to create a new instance of EntityPlant.
     * 
     * @param key  The unique identifier for this entity.
     * @param name The name of the entity.
     */
    public EntityPlant(String key, String name) {
        this.key = key;
        this.name = name;
    }

    /**
     * Sets the action score for the plant entity.
     * 
     * @param actionScore The action score value.
     */
    @Override
    public void setActionScore(int actionScore) {
        this.actionScore = actionScore;
    }

    /**
     * Returns the action score of the plant entity.
     * 
     * @return The current action score.
     */
    @Override
    public int getActionScore() {
        return this.actionScore;
    }

    /**
     * Sets the quantity attribute for the plant entity.
     * 
     * @param qty The quantity value.
     */
    @Override
    public void setQty(int qty) {
        this.qty = qty;
    }

    /**
     * Retrieves the quantity of the plant entity.
     * 
     * @return The current quantity.
     */
    @Override
    public int getQty() {
        return qty;
    }

    /**
     * Retrieves the health of the plant entity.
     * 
     * @return The current health.
     */
    @Override
    public int getHealth() {
        return health;
    }

    /**
     * Sets the health for the plant entity.
     * 
     * @param health The health value.
     */
    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Returns the base score for the plant entity.
     * 
     * @return The current base score.
     */
    @Override
    public int getBaseScore() {
        return this.baseScore;
    }

    /**
     * Retrieves the name of the plant entity.
     * 
     * @return The current name.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name for the plant entity.
     * 
     * @param name The human-readable name.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the unique key for the plant entity.
     * 
     * @param key The unique identifier.
     */
    @Override
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Retrieves the unique key of the plant entity.
     * 
     * @return The current key.
     */
    @Override
    public String getKey() {
        return this.key;
    }
}

