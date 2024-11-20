package enums;

import constant.Constant;

/**
 * Enum representing different movement options with corresponding 
 * menu keys, prompts, and descriptions.
 * 
 * @author Han Wu, whw10@student.unimelb.edu.au, 1468664
 */
public enum MoveEnum {

    // Enum instances for various movement options
    // Represents the option to move a Space Robot
    MoveSpaceRobot(Constant.MAIN_MENU_MOVE_SPACE_ROBOT, 
                   Constant.PROMPT_MOVE_SPACE_ROBOT, 
                   "Space Robot"),

    // Represents the option to move a Space Rover
    MoveSpaceRover(Constant.MAIN_MENU_MOVE_SPACE_ROVER, 
                   Constant.PROMPT_MOVE_SPACE_ROVER, 
                   "Space Rover"),

    // Represents the option to move Martian Animals
    MoveMartianAnimals(Constant.MAIN_MENU_MOVE_MARTIAN_ANIMALS, 
                       Constant.PROMPT_MOVE_MARTIAN_ANIMALS, 
                       "MartianAnimal");

    // Field for storing the menu key
    private String keyNum;

    // Field for storing the prompt associated with the movement option
    private String prompt;

    // Field for storing a brief description of the movement option
    private String desc;

    /**
     * Constructor to initialize a MoveEnum instance.
     *
     * @param keyNum The menu key associated with the movement option.
     * @param prompt The prompt that will be shown to the user.
     * @param desc A brief description of the movement option.
     */
    MoveEnum(String keyNum, String prompt, String desc) {
        this.keyNum = keyNum;
        this.prompt = prompt;
        this.desc = desc;
    }

    // Getter for the menu key
    public String getKeyNum() {
        return keyNum;
    }

    // Setter for the menu key
    public void setKeyNum(String keyNum) {
        this.keyNum = keyNum;
    }

    // Getter for the prompt
    public String getPrompt() {
        return prompt;
    }

    // Setter for the prompt
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    // Getter for the description
    public String getDesc() {
        return desc;
    }

    // Setter for the description
    public void setDesc(String desc) {
        this.desc = desc;
    }
}

