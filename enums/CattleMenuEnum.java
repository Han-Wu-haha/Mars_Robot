package enums;

import constant.Constant;

/**
 * Enum representing different types of cattle and their corresponding menu keys 
 * and Mars keys.
 * 
 * @author Han Wu, whw10@student.unimelb.edu.au, 1468664
 */
public enum CattleMenuEnum implements SpaceRobotMoveEnum {

    // Enum instances
    GOAT(Constant.MENU_REAR_CATTLE_GOAT, Mars.Goat.getKey()),   // Represents a Goat
    SHEEP(Constant.MENU_REAR_CATTLE_SHEEP, Mars.Sheep.getKey()), // Represents a Sheep
    COW(Constant.MENU_REAR_CATTLE_COW, Mars.Cow.getKey()),      // Represents a Cow
    DOG(Constant.MENU_REAR_CATTLE_DOG, Mars.Dog.getKey()),      // Represents a Dog

    ;

    // Fields for storing menu key and Mars key
    private String keyNum;
    private String marsKey;

    /**
     * Constructor to initialize a CattleMenuEnum instance.
     *
     * @param keyNum The menu key associated with the cattle type.
     * @param marsKey The Mars key associated with the cattle type.
     */
    CattleMenuEnum(String keyNum, String marsKey) {
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
     * Constructs a message indicating an entity has been added. If the Mars key
     * starts with "C", no prefix is added, else "A " is prefixed.
     *
     * @return Formatted string message.
     */
    @Override
    public String getPrintMsg() {
        return String.format("%s%s has been added.",
                this.getMarsKey().startsWith("C") ? "" : "A ",
                Mars.getMarsByKey(this.getMarsKey()).getEntityName());
    }

    // Setter for the Mars key
    public void setMarsKey(String marsKey) {
        this.marsKey = marsKey;
    }

    /**
     * Looks up a CattleMenuEnum instance by its menu key.
     *
     * @param keyNum The menu key to look up.
     * @return The matching CattleMenuEnum instance or null if not found.
     */
    public static SpaceRobotMoveEnum getByKeyNum(String keyNum) {
        for (CattleMenuEnum menuEnum : CattleMenuEnum.values()) {
            if (menuEnum.getKeyNum().equalsIgnoreCase(keyNum)) {
                return menuEnum;
            }
        }
        return null;
    }
}

