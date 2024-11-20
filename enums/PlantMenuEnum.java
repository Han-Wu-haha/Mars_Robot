package enums;

// Required imports
import constant.Constant;

/**
 * Enum representing different plant options available for 
 * planting in Mars by the Space Robot.
 * 
 * Each enum constant corresponds to a plant's menu key 
 * and its associated key on Mars.
 * 
 * Implements the SpaceRobotMoveEnum interface for actions 
 * a space robot can perform on Mars.
 *
 * @author Han Wu, whw10@student.unimelb.edu.au, 1468664
 */
public enum PlantMenuEnum implements SpaceRobotMoveEnum {

    // Enum instances representing various plants for planting
    POTATO(Constant.MENU_PLAN_TREE_POTATO, Mars.Potato.getKey()),
    TOMATO(Constant.MENU_PLAN_TREE_TOMATO, Mars.Tomato.getKey()),
    ONION(Constant.MENU_PLAN_TREE_ONION, Mars.Onion.getKey()),
    APPLE(Constant.MENU_PLAN_TREE_APPLE, Mars.Apple.getKey()),
    BANANA(Constant.MENU_PLAN_TREE_BANANA, Mars.Banana.getKey()),
    LILY(Constant.MENU_PLAN_TREE_LILY, Mars.Lily.getKey()),
    ROSE(Constant.MENU_PLAN_TREE_ROSE, Mars.Rose.getKey()),
    EUCALYPTUS(Constant.MENU_PLAN_TREE_EUCALYPTUS, Mars.Eucalyptus.getKey()),

    ;

    // Field for storing the menu key
    private String keyNum;
    
    // Field for storing the Mars key for the plant
    private String marsKey;

    /**
     * Constructor to initialize a PlantMenuEnum instance.
     *
     * @param keyNum The menu key associated with the plant.
     * @param marsKey The Mars key associated with the plant.
     */
    PlantMenuEnum(String keyNum, String marsKey) {
        this.keyNum = keyNum;
        this.marsKey = marsKey;
    }

    // Getter for the menu key
    public String getKeyNum() {
        return keyNum;
    }

    // Setter for the menu key
    public void setKeyNum(String keyNum) {
        this.keyNum = keyNum;
    }

    // Getter for the Mars key, as defined by the SpaceRobotMoveEnum interface
    @Override
    public String getMarsKey() {
        return marsKey;
    }

    /**
     * Constructs a message indicating a plant has been planted. 
     * The message prefixes "An" for plants whose Mars key starts with "O" or "A", 
     * else "A" is prefixed.
     *
     * @return Formatted string message.
     */
    @Override
    public String getPrintMsg() {
        return String.format("%s %s has been planted.",
                (this.getMarsKey().startsWith("O") || this.getMarsKey().startsWith("A")) ? "An" : "A",
                Mars.getMarsByKey(this.getMarsKey()).getEntityName());
    }

    // Setter for the Mars key
    public void setMarsKey(String marsKey) {
        this.marsKey = marsKey;
    }

    /**
     * Finds the corresponding plant based on its menu key.
     *
     * @param keyNum The menu key to search.
     * @return The matching PlantMenuEnum instance or null if not found.
     */
    public static SpaceRobotMoveEnum getByKeyNum(String keyNum) {
        for (PlantMenuEnum menuEnum : PlantMenuEnum.values()) {
            if (menuEnum.getKeyNum().equalsIgnoreCase(keyNum)) {
                return menuEnum;
            }
        }
        return null;
    }

    /**
     * Checks if the provided key number corresponds to a valid plant menu key.
     *
     * @param keyNum The key number to check.
     * @return true if the keyNum matches a plant's menu key, false otherwise.
     */
    public static boolean isPlantMenuKeyNum(String keyNum) {
        for (PlantMenuEnum menuEnum : PlantMenuEnum.values()) {
            if (menuEnum.getKeyNum().equalsIgnoreCase(keyNum)) {
                return true;
            }
        }
        return false;
    }
}

