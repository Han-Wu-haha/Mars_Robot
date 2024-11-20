package constant;

/**
 * This class provides a set of constants used throughout the program.
 * 
 * @author Han Wu, whw10@student.unimelb.edu.au, 1468664
 */
public class Constant {

    // Initial values for columns and rows
    public static final int COL_INIT_VALUE = 0;
    public static final int ROW_INIT_VALUE = 0;

    // Prompts for user input
    public static final String PROMPT_CHOOSE_MAP_PATH = "Please enter\n"
            + "[1] to load Martian map from a file\n"
            + "[2] to load default Martian map\n"
            + "> ";
    public static final String PROMPT_MAIN_MENU = "Please enter\n"
            + "[1] to move Space Robot\n"
            + "[2] to move Space Rover\n"
            + "[3] to move Martian animals\n"
            + "[4] to print the current habitability stats\n"
            + "[5] to print the old habitability stats\n"
            + "[6] to exit\n"
            + "> ";

    // Menu options for loading Martian map
    public static final String MENU_LOAD_MAP_FROM_FILE = "1";
    public static final String MENU_LOAD_DEFAULT_MAP   = "2";

    // Path for the default map
    public static final String DEFAULT_MAP_PATH   = "resources/default.in";

    // Main menu options
    public static final String MAIN_MENU_MOVE_SPACE_ROBOT     = "1";
    public static final String MAIN_MENU_MOVE_SPACE_ROVER     = "2";
    public static final String MAIN_MENU_MOVE_MARTIAN_ANIMALS = "3";
    public static final String MAIN_MENU_PRINT_STATS_CURRENT  = "4";
    public static final String MAIN_MENU_PRINT_STATS_OLD      = "5";
    public static final String MAIN_MENU_EXIT                 = "6";

    // Menu options for directions
    public static final String MENU_DIRECTION_MOVE_NORTH             = "1";
    public static final String MENU_DIRECTION_MOVE_WEST              = "2";
    public static final String MENU_DIRECTION_MOVE_EAST              = "3";
    public static final String MENU_DIRECTION_MOVE_SOUTH             = "4";
    public static final String MENU_DIRECTION_MOVE_NORTH_WEST        = "5";
    public static final String MENU_DIRECTION_MOVE_SOUTH_WEST        = "6";
    public static final String MENU_DIRECTION_MOVE_NORTH_EAST        = "7";
    public static final String MENU_DIRECTION_MOVE_SOUTH_EAST        = "8";
    public static final String MENU_DIRECTION_MOVE_BACK_TO_MAIN_MENU = "0";

    // Menu options for planting and rearing
    public static final String MENU_PLAN_TREE   = "1";
    public static final String MENU_REAR_CATTLE = "2";
    public static final String MENU_GO_PREVIOUS = "0";

    // Menu options for planting trees
    public static final String MENU_PLAN_TREE_POTATO = "1";
    public static final String MENU_PLAN_TREE_TOMATO = "2";
    public static final String MENU_PLAN_TREE_ONION = "3";
    public static final String MENU_PLAN_TREE_APPLE = "4";
    public static final String MENU_PLAN_TREE_BANANA = "5";
    public static final String MENU_PLAN_TREE_LILY = "6";
    public static final String MENU_PLAN_TREE_ROSE = "7";
    public static final String MENU_PLAN_TREE_EUCALYPTUS = "8";

    // Menu options for rearing cattle
    public static final String MENU_REAR_CATTLE_GOAT = "1";
    public static final String MENU_REAR_CATTLE_SHEEP = "2";
    public static final String MENU_REAR_CATTLE_COW = "3";
    public static final String MENU_REAR_CATTLE_DOG = "4";

    // Prompts with placeholders for moving entities
    public static final String PROMPT_MOVE_SPACE_ROBOT    = "There are %s Space Robot found. Select";
    public static final String PROMPT_MOVE_SPACE_ROVER    = "There are %s Space Rover found. Select";
    public static final String PROMPT_MOVE_MARTIAN_ANIMALS     = "There are %s Martian animal found. Select";
    public static final String PROMPT_MOVE_SUB_MENU     = "Please select\n"
            + "[1] to plant a tree\n"
            + "[2] to rear cattle\n"
            + "[0] to go back to previous menu\n"
            + "> ";

    // Directional movement prompts for space entities
    public static final String PROMPT_DIRECTION_SPACE       = "%s can move in following directions\n"
            + "[1] to move north.\n"
            + "[2] to move west.\n"
            + "[3] to move east.\n"
            + "[4] to move south.\n"
            + "[5] to move north-west.\n"
            + "[6] to move south-west.\n"
            + "[7] to move north-east.\n"
            + "[8] to move south-east.\n"
            + "[0] to go back to main menu\n"
            + "Please enter a direction.\n"
            + "> ";

    // Prompts for planting trees
    public static final String PROMPT_MENU_PLAN_TREE = "Let's Plant something\n"
            + "[1] to plant a potato\n"
            + "[2] to plant a tomato\n"
            + "[3] to plant an onion\n"
            + "[4] to plant an apple tree\n"
            + "[5] to plant a banana tree\n"
            + "[6] to plant a lily\n"
            + "[7] to plant a rose\n"
            + "[8] to plant a eucalyptus tree\n"
            + "[0] to go back to previous menu\n";

    // Prompts for rearing cattle
    public static final String PROMPT_MENU_REAR_CATTLE = "Let's add some cattle \n"
            + "[1] to add a goat \n"
            + "[2] to add a sheep \n"
            + "[3] to add cow \n"
            + "[4] to add a dog \n"
            + "[0] to go back to previous menu\n"
            + "> ";

    // Prompts for actions on occupied cells
    public static final String PROMPT_SPACE_MOVE_TO_PLANT_OCCUPIED  = "Do you want to water the plant?Enter Y for yes, N for No\n> ";
    public static final String PROMPT_SPACE_MOVE_TO_EARTHANIMAL_OCCUPIED = "Do you want to feed the animals?Enter Y for yes, N for No\n> ";

}

