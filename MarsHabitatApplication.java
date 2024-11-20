import constant.Constant;
import entities.*;
import enums.Mars;
import enums.MoveEnum;
import exception.InvalidFileException;
import exception.UnknownEntityException;
import vo.Location;

import java.io.*;
import java.util.*;

/**
 * this class is the entrypoint for the projce missionmars
 * 
 * @author Han Wu, whw10@student.unimelb.edu.au, 1468664
 */
public class MarsHabitatApplication {
    private       Entity[][]                entities       =null;
    private final String[]                  args; // to collect command line arguments
    private       Map<String, List<Entity>> entityGroupMap = null;
    public int                              actionScore    = 0;

    /**
     * The main entry point for the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        MarsHabitatApplication app = new MarsHabitatApplication(args);
        try {
            app.displayMessage();
            app.start();
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
    }

    /**
     * Constructor initializes the parking lot engine with any given arguments.
     *
     * @param args command line arguments
     */
    public MarsHabitatApplication(String[] args) {
        this.args = args;
    }

    /**
     * Initiates the map.
     * Load a file given in command line arguments.
     * Load from a default file
     * Load from a saved file.
     */
    private void start() throws Exception {
        String path = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        if (args != null && args.length == 2) {
            //load a file given in command eg:java MarsHabitatApplication --f resources/someInputFile
            path = args[1];
        } else if (args != null && args.length == 4) {
            path = args[1];
            //java MarsHabitatApplication --f resources/v1.in --l resources/habitability.log
        } else {
            path = getMapPathBySelect(reader);
        }
        List<String> mapList = loadMartianMap(path);
        if (mapList == null || mapList.size() <= 0) {
            return;
        }
        entities = MarsUtils.initArrayEntity(mapList);
        //default map no space
        if (!Constant.DEFAULT_MAP_PATH.equals(path) && !(args != null && args.length == 2)) {
            System.out.print(" ");
        }
        displayMapDetails(entities);

        System.out.print(Constant.PROMPT_MAIN_MENU);

        String inCmd = reader.readLine();
        while (inCmd != null) {
            switch (inCmd.toLowerCase()) {
                    case Constant.MAIN_MENU_MOVE_SPACE_ROBOT:
                        inCmd = doMoveSpace(reader,MoveEnum.MoveSpaceRobot);
                        break;
                    case Constant.MAIN_MENU_MOVE_SPACE_ROVER:
                        inCmd = doMoveSpace(reader,MoveEnum.MoveSpaceRover);
                        break;
                    case Constant.MAIN_MENU_MOVE_MARTIAN_ANIMALS:
                        inCmd = doMoveSpace(reader,MoveEnum.MoveMartianAnimals);
                        break;
                    case Constant.MAIN_MENU_PRINT_STATS_CURRENT:
                        inCmd = doPrintCurrHabyStats(reader,entities);
                        break;
                    case Constant.MAIN_MENU_PRINT_STATS_OLD:
                        inCmd = doPrintOldHabyStats();
                        if (inCmd == null) {
                            return;
                        }
                        break;
                    case Constant.MAIN_MENU_EXIT:
                        inCmd = doExit(reader);
                        return;
                    default:
                        System.out.print(Constant.PROMPT_MAIN_MENU);
            }
            inCmd = reader.readLine();
        }
    }

    /**
     * resources/m1.in
     * @param reader
     * @return
     * @throws Exception
     */
    private String getMapPathBySelect(BufferedReader reader)throws Exception {
        System.out.print(Constant.PROMPT_CHOOSE_MAP_PATH);
        String inCmd = reader.readLine();
        while (inCmd != null) {
            //System.out.println("input:"+inCmd);
            if (Constant.MENU_LOAD_MAP_FROM_FILE.equalsIgnoreCase(inCmd)) {
                System.out.print("Enter a file name to setup Martian Land Map\n>");
                inCmd = reader.readLine();//get the map
                break;
            } else if (Constant.MENU_LOAD_DEFAULT_MAP.equalsIgnoreCase(inCmd)) {
                inCmd = Constant.DEFAULT_MAP_PATH;
                break;
            } else {//keep inputting if not successful
                System.out.print(Constant.PROMPT_CHOOSE_MAP_PATH);
                inCmd = reader.readLine();
            }
        }
        return inCmd;
    }

    /**
     * readline and load map
     * @param path
     * @return
     */
    private List<String> loadMartianMap(String path) {
        List<String> tentList = new ArrayList<>();
        String property = System.getProperty("user.dir");
        File file = new File(property + File.separator + path);
        BufferedReader br = null;
        Set<Integer> lineSet = new HashSet<Integer>();
        try {
            if (!file.exists()) {
                throw new FileNotFoundException(" File Not Found, aborting mission.");
            }
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                tentList.add(line);
                lineSet.add(line.length());
            }
            if (lineSet.size() > 1) {
                //If not all rows in the file are of the same length, then throw a custom exception
                // named InvalidFileException and terminate the program
                throw new InvalidFileException(" Invalid file content, Aborting mission.");
            }
            //judge whether every line of the file is legal
            for (String txt : tentList) {
                if (!txt.endsWith("#") || !txt.startsWith("#")) {
                    // If all the rows in the file don't match in length or the boundary is not defined with ‘#’,
                    // throw an error message and terminate the program.
                    throw new InvalidFileException(" Invalid file content, Aborting mission.");
                }
                //UnknownEntityException - In the file, there exists a symbol that doesn't match our specified symbols.
                // You should throw an error message and terminate the program.
                for (char c : txt.toCharArray()) {
                    if (!Mars.getKeys().contains(String.valueOf(c))) {
                        throw new UnknownEntityException(" An unknown item found in Martian land. Aborting mission.");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (InvalidFileException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }catch (UnknownEntityException e){
            System.out.println(e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return tentList;
    }

    /**
     * print the map and score
     * @param arrs
     */
    private void displayMapDetails(Entity[][] arrs) {
        System.out.println("Here is a layout of Martian land.\n");

        Map<String, Entity> marsMap = new HashMap<>();
        entityGroupMap = new HashMap<>();
        for (int i = 0; i < arrs.length; i++) {
            for (int k = 0; k < arrs[i].length; k++) {
                //Entity en = arrs[i][k];
                Entity en = (Entity) arrs[i][k].clone();
                if (en instanceof SpaceRobot) {
                    if (entityGroupMap.containsKey(Constant.MAIN_MENU_MOVE_SPACE_ROBOT)) {
                        entityGroupMap.get(Constant.MAIN_MENU_MOVE_SPACE_ROBOT).add(en);
                    }else{
                        List<Entity> entityList = new ArrayList<>();
                        entityList.add(en);
                        entityGroupMap.put(Constant.MAIN_MENU_MOVE_SPACE_ROBOT, entityList);
                    }
                }
                if (en instanceof SpaceRover) {
                    if (entityGroupMap.containsKey(Constant.MAIN_MENU_MOVE_SPACE_ROVER)) {
                        entityGroupMap.get(Constant.MAIN_MENU_MOVE_SPACE_ROVER).add(en);
                    }else{
                        List<Entity> entityList = new ArrayList<>();
                        entityList.add(en);
                        entityGroupMap.put(Constant.MAIN_MENU_MOVE_SPACE_ROVER, entityList);
                    }
                }
                if (en instanceof EntityMartianAnimal) {
                    if (entityGroupMap.containsKey(Constant.MAIN_MENU_MOVE_MARTIAN_ANIMALS)) {
                        entityGroupMap.get(Constant.MAIN_MENU_MOVE_MARTIAN_ANIMALS).add(en);
                    }else{
                        List<Entity> entityList = new ArrayList<>();
                        entityList.add(en);
                        entityGroupMap.put(Constant.MAIN_MENU_MOVE_MARTIAN_ANIMALS, entityList);
                    }
                }
                if (en instanceof Mineral || en instanceof EntityPlant || en instanceof EntityEarthAnimal) {
                    if (marsMap.containsKey(en.getName())) {
                        marsMap.get(en.getName()).setQty(marsMap.get(en.getName()).getQty() + 1);
                        marsMap.put(en.getName(),marsMap.get(en.getName()));
                    }else{
                        en.setQty(1);
                        marsMap.put(en.getName(), en);
                    }
                }
                System.out.print(en.getKey());
            }
            System.out.println();
        }
        System.out.println();
        displayHabitabilityScores(marsMap);
    }

/**
 * Refreshes and categorizes entities into their respective groups.
 * The method first clears the existing entity groups map to start fresh.
 *
 * @param arrs A 2D array of Entity objects that needs to be processed.
 */
private void refreshGroupEntities(Entity[][] arrs) {
    // Clears the current mapping of entities to ensure we're working with a fresh map.
    entityGroupMap.clear();

    // Iterate through the array of Entity arrays.
    for (int i = 0; i < arrs.length; i++) {
        for (int k = 0; k < arrs[i].length; k++) {
            // Clone the entity to avoid modifying the original array's objects.
            Entity en = (Entity) arrs[i][k].clone();

            // Check if the entity is an instance of SpaceRobot and add it to the respective group.
            if (en instanceof SpaceRobot) {
                // If the group already exists, add the entity to the existing group.
                if (entityGroupMap.containsKey(Constant.MAIN_MENU_MOVE_SPACE_ROBOT)) {
                    entityGroupMap.get(Constant.MAIN_MENU_MOVE_SPACE_ROBOT).add(en);
                } else {
                    // If the group doesn't exist, create a new list, add the entity, and put it in the map.
                    List<Entity> entityList = new ArrayList<>();
                    entityList.add(en);
                    entityGroupMap.put(Constant.MAIN_MENU_MOVE_SPACE_ROBOT, entityList);
                }
            }

            // Repeat the same process as above for entities that are instances of SpaceRover.
            if (en instanceof SpaceRover) {
                if (entityGroupMap.containsKey(Constant.MAIN_MENU_MOVE_SPACE_ROVER)) {
                    entityGroupMap.get(Constant.MAIN_MENU_MOVE_SPACE_ROVER).add(en);
                } else {
                    List<Entity> entityList = new ArrayList<>();
                    entityList.add(en);
                    entityGroupMap.put(Constant.MAIN_MENU_MOVE_SPACE_ROVER, entityList);
                }
            }

            // Repeat the same process for entities that are instances of EntityMartianAnimal.
            if (en instanceof EntityMartianAnimal) {
                if (entityGroupMap.containsKey(Constant.MAIN_MENU_MOVE_MARTIAN_ANIMALS)) {
                    entityGroupMap.get(Constant.MAIN_MENU_MOVE_MARTIAN_ANIMALS).add(en);
                } else {
                    List<Entity> entityList = new ArrayList<>();
                    entityList.add(en);
                    entityGroupMap.put(Constant.MAIN_MENU_MOVE_MARTIAN_ANIMALS, entityList);
                }
            }
        }
    }
}


    /**
     * print habitability status
     * @param plantMap
     */
    private void displayHabitabilityScores(Map<String, Entity> plantMap) {
        System.out.println("Habitability Status\n======================");

        if (plantMap == null || plantMap.size() <= 0) {
            System.out.println("No Record found.");
            return;
        }
        int totalScore=0;
        for (Map.Entry<String, Entity> entry : plantMap.entrySet()) {
            totalScore += entry.getValue().getSumScore();
            //System.out.println(entry.getKey() + " = " + entry.getValue().getQty()+",score:"+entry.getValue().getSumScore());
            System.out.println(entry.getKey() + " = " + entry.getValue().getQty());
        }
        totalScore += actionScore;
        System.out.println("\nTotal Habitability Score: " + totalScore );
    }

    /**
     * move Space Robot/ Space Rover
     *
     * @param reader BufferedReader instance for reading user input
     * @return next command from user
     */
    private String doMoveSpace(BufferedReader reader, MoveEnum move) throws IOException {
        if (!entityGroupMap.containsKey(move.getKeyNum())) {
            if (move.getKeyNum().equals(Constant.MAIN_MENU_MOVE_MARTIAN_ANIMALS)) {
                System.out.println("No martial animal found to move.");
            }else{
                System.out.println(String.format("No %s found to move.", move.getDesc()));
            }
            System.out.print(Constant.PROMPT_MAIN_MENU);
            return Constant.MENU_DIRECTION_MOVE_BACK_TO_MAIN_MENU;
        }
        List<Entity> entityList = entityGroupMap.get(move.getKeyNum());
        String inCmd = null;
        Entity entity = null;
        String entiyNm = null;
        do {
            System.out.println(String.format(move.getPrompt(), entityList.size()));
            for (int i = 0; i < entityList.size(); i++) {
                entity = entityList.get(i);
                if (entity.getKey().equalsIgnoreCase(Mars.MartianJeebie.getKey()) || entity.getKey().equalsIgnoreCase(Mars.MartianHeebie.getKey())) {
                    entiyNm = entity.getName().replace("MARTIAN", "");
                }else{
                    entiyNm = move.getDesc();
                }
                System.out.println(String.format("[%s] for %s at position (%s, %s)"
                        , String.valueOf(i + 1), entiyNm
                        , String.valueOf(entity.getX())
                        , String.valueOf(entity.getY())));
            }
            System.out.print("> ");
            inCmd = reader.readLine();
        } while (inCmd == null || !MarsUtils.isValidDigit(inCmd) || Integer.valueOf(inCmd)<1 || Integer.valueOf(inCmd) > entityList.size());

        Entity sltEntity = entityList.get(Integer.valueOf(inCmd) - 1);
        System.out.print(String.format(Constant.PROMPT_DIRECTION_SPACE, move.getDesc().replace(" ","")));
        inCmd = reader.readLine();

        while (MarsUtils.isValidMoveActionKey(inCmd)
                && !Constant.MENU_DIRECTION_MOVE_BACK_TO_MAIN_MENU.equalsIgnoreCase(inCmd)) {
            Location locTo = MarsUtils.getMoveLocation(sltEntity, inCmd);
            if (Mars.WELLNO.getKey().equals(entities[locTo.y()][locTo.x()].getKey())) {//reach the border
                System.out.println("Invalid Location, Boundary reached.");
                System.out.println();
            }else{
                MoveEvent event = MoveEventFactory.getMoveEvent(move.getKeyNum());
                String rlt = event.move(this, entities, sltEntity, locTo, reader, inCmd);
                refreshGroupEntities(entities);
                //no more move for dead martian animal, return to the last menu
                if (Constant.MENU_DIRECTION_MOVE_BACK_TO_MAIN_MENU.equals(rlt)) {
                    MarsUtils.displayMap(entities);
                    break;
                }
            }
            MarsUtils.displayMap(entities);
            System.out.print(String.format(Constant.PROMPT_DIRECTION_SPACE, move.getDesc().replace(" ","")));
            inCmd = reader.readLine();
        }
        System.out.print(Constant.PROMPT_MAIN_MENU);
        return inCmd;
    }

    /**
    * Executes the checkout process for a vehicle.
    * @param sc BufferedReader for user input.
    * @return Returns an empty string after processing.
    * @throws IOException
    */
    private String doExit(BufferedReader sc) throws IOException {
        String inCmd = null;
        System.out.print("Enter a filename for saving Martian Land Map\n> ");
        inCmd = sc.readLine();

        if (inCmd == null || inCmd.trim().length() <= 0) {
            System.out.print("Cannot create file for Martian Land Map.\n"
                    + "Terminating the mission for now. See you next time.\n");
            return null;
        }
        //save log file
        boolean status = MarsUtils.saveCurrentData(entities, inCmd);
        if (status) {
            System.out.println("Terminating the mission for now. See you next time.");
        }
        return null;
    }

    private String doPrintOldHabyStats() {
        try {
            MarsUtils.readHabitabilityDataFromLogFile();
            System.out.print(Constant.PROMPT_MAIN_MENU);
            return "";
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * @param sc       BufferedReader for user input.
     * @param arrs
     * @return Returns an empty string after processing.
     */
    private String doPrintCurrHabyStats(BufferedReader sc, Entity[][] arrs) {
        Map<String, Entity> marsMap = MarsUtils.getHabyScoresEntityMap(arrs);
        displayHabitabilityScores(marsMap);
        System.out.print(Constant.PROMPT_MAIN_MENU);
        return "";
    }

    public void addActionScore(int actionScore) {
        this.actionScore += actionScore;
    }

    public int getActionScor() {
        return this.actionScore;
    }

    /*
     *  Displays the welcome text.
     */
    private void displayMessage() {
        System.out.println("         __\n" +
                " _(\\    |@@|\n" +
                "(__/\\__ \\--/ __\n" +
                "   \\___|----|  |   __\n" +
                "       \\ }{ /\\ )_ / _\\\n" +
                "       /\\__/\\ \\__O (_COMP90041\n" +
                "      (--/\\--)    \\__/\n" +
                "      _)(  )(_\n" +
                "     `---''---`");
        System.out.println(
                " /$$      /$$ /$$                    /$$                           /$$      /$$                              \n" +
                        "| $$$    /$$$|__/                   |__/                          | $$$    /$$$                              \n" +
                        "| $$$$  /$$$$ /$$  /$$$$$$$ /$$$$$$$ /$$  /$$$$$$  /$$$$$$$       | $$$$  /$$$$  /$$$$$$   /$$$$$$   /$$$$$$$\n" +
                        "| $$ $$/$$ $$| $$ /$$_____//$$_____/| $$ /$$__  $$| $$__  $$      | $$ $$/$$ $$ |____  $$ /$$__  $$ /$$_____/\n" +
                        "| $$  $$$| $$| $$|  $$$$$$|  $$$$$$ | $$| $$  \\ $$| $$  \\ $$      | $$  $$$| $$  /$$$$$$$| $$  \\__/|  $$$$$$ \n" +
                        "| $$\\  $ | $$| $$ \\____  $$\\____  $$| $$| $$  | $$| $$  | $$      | $$\\  $ | $$ /$$__  $$| $$       \\____  $$\n" +
                        "| $$ \\/  | $$| $$ /$$$$$$$//$$$$$$$/| $$|  $$$$$$/| $$  | $$      | $$ \\/  | $$|  $$$$$$$| $$       /$$$$$$$/\n" +
                        "|__/     |__/|__/|_______/|_______/ |__/ \\______/ |__/  |__/      |__/     |__/ \\_______/|__/      |_______/ ");

        System.out.println();
    }
}
