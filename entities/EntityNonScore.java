package entities;

/**
 * EntityNonScore represents a type of Entity that doesn't contribute to the game's score.
 * Unlike other game entities, instances of this type might have specific gameplay mechanics
 * or other effects but don't affect the player's score directly.
 * 
 * @author Han Wu, whw10@student.unimelb.edu.au, 1468664
 */
public abstract class EntityNonScore extends Entity {

    // Health attribute of the non-score entity, initialized to 0 by default
    protected int health = 0;

    // Represents the action score associated with the non-score entity
    protected int actionScore;

    // Unique identifier for the non-score entity
    private String key;

    // Human-readable name of the non-score entity
    private String name;

    /**
     * Constructor to create a new instance of EntityNonScore.
     * 
     * @param key  The unique identifier for this entity.
     * @param name The name of the entity.
     */
    public EntityNonScore(String key, String name) {
        this.key = key;
        this.name = name;
    }

    /**
     * Sets the action score for the entity.
     * 
     * @param actionScore The action score value.
     */
    @Override
    public void setActionScore(int actionScore) {
        this.actionScore = actionScore;
    }

    /**
     * Returns the action score of the entity.
     * 
     * @return The current action score.
     */
    @Override
    public int getActionScore() {
        return this.actionScore;
    }

    /**
     * Retrieves the health of the entity.
     * 
     * @return The current health.
     */
    @Override
    public int getHealth() {
        return health;
    }

    /**
     * Sets the health for the entity.
     * 
     * @param health The health value.
     */
    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Since EntityNonScore doesn't have a quantity, this method is a no-op.
     * 
     * @param qty The quantity value (ignored).
     */
    @Override
    public void setQty(int qty) {}

    /**
     * Returns 0 as the quantity, because EntityNonScore doesn't have a quantity attribute.
     * 
     * @return 0, always.
     */
    @Override
    public int getQty() {
        return 0;
    }

    /**
     * Returns 0 as the base score, since EntityNonScore doesn't contribute to score.
     * 
     * @return 0, always.
     */
    @Override
    public int getBaseScore() {
        return 0;
    }

    /**
     * Retrieves the unique key of the entity.
     * 
     * @return The current key.
     */
    @Override
    public String getKey() {
        return key;
    }

    /**
     * Sets the key for the entity.
     * 
     * @param key The unique identifier.
     */
    @Override
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Retrieves the name of the entity.
     * 
     * @return The current name.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Sets the name for the entity.
     * 
     * @param name The human-readable name.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }
}

