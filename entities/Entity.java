package entities;

import constant.Constant;

/**
 * This class provides an abstract representation of an entity in the game.
 * Each entity has coordinates x and y and various methods to interact with its properties.
 * The class also supports cloning of its instances.
 * 
 * @author Han Wu, whw10@student.unimelb.edu.au, 1468664
 */
public abstract class Entity implements Cloneable {

    // X and Y coordinates of the entity. Initialized with default values from the Constant class.
    protected int y = Constant.ROW_INIT_VALUE;
    protected int x = Constant.COL_INIT_VALUE;

    /**
     * Calculate the sum of score by multiplying quantity with base score.
     *
     * @return Sum of the score.
     */
    public int getSumScore() {
        return getQty() * getBaseScore();
    }

    // Abstract methods related to action score of an entity.
    public abstract void setActionScore(int score);
    public abstract int getActionScore();

    // Abstract methods related to quantity of an entity.
    public abstract void setQty(int qty);
    public abstract int getQty();

    // Abstract methods related to health of an entity.
    public abstract int getHealth();
    public abstract void setHealth(int health);

    // Abstract method to get base score of an entity.
    public abstract int getBaseScore();

    // Abstract methods related to the name of an entity.
    public abstract String getName();
    public abstract void setName(String name);

    // Abstract methods related to the key of an entity.
    public abstract void setKey(String key);
    public abstract String getKey();

    // Getter and Setter methods for Y-coordinate.
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    // Getter and Setter methods for X-coordinate.
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Overrides the default clone method to provide deep cloning.
     * If cloning is not supported, it will print the stack trace.
     *
     * @return A cloned object of the entity.
     */
    @Override
    public Object clone() {
        Entity entity = null;
        try {
            entity = (Entity) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return entity;
    }
}

