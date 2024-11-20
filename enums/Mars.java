package enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents various entities within the Mars-themed game system.
 * This enumeration captures a variety of objects and creatures, each with a unique
 * symbol (key) and a descriptive name. These entities range from plants and animals
 * to Martian creatures and environmental obstacles.
 * 
 * Utility methods are also provided to fetch specific entities based on their keys,
 * obtain all keys, and retrieve the symbol for the 'Dot' entity, which typically
 * represents an empty space or a placeholder in the game grid.
 * 
 * @author Han Wu, whw10@student.unimelb.edu.au, 1468664
 */

public enum Mars {
    Apple("A", "Apple"),
    Banana("B", "Banana"),
    Cow("C", "Cow"),
    Dog("D", "Dog"),
    Eucalyptus("E", "Eucalyptus"),
    Goat("G", "Goat"),
    Lily("L", "Lily"),
    Onion("O", "Onion"),
    Potato("P", "Potato"),
    Rose("R", "Rose"),
    Sheep("S", "Sheep"),
    Tomato("T", "Tomato"),

    SpaceRover("X", "SpaceRover"),
    SpaceRobot("Z", "SpaceRobot"),
    MartianJeebie("J", "MartianJeebie"),
    MartianHeebie("H", "MartianHeebie"),

    Mineral("*", "Mineral"),
    Rock("@", "Rock"),
    DOT(".", "Dot"),
    WELLNO("#", "WellNo");

    private String key;
    private String entityName;

    Mars(String key, String entityName) {
        this.key = key;
        this.entityName = entityName;
    }

    public static Mars getMarsByKey(String key) {
        for (Mars value : values()) {
            if (value.getKey().equals(key)) {
                return value;
            }
        }
        return null;
    }

    public static List<String> getKeys() {
        List<String> keysList = new ArrayList<>();
        for (Mars value : values()) {
            keysList.add(value.getKey());
        }
        return keysList;
    }

    public static String dot() {
        return DOT.getKey();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

}
