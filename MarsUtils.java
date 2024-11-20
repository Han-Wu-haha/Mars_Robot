import constant.Constant;
import entities.Entity;
import entities.EntityEarthAnimal;
import entities.EntityPlant;
import entities.Mineral;
import enums.Mars;
import exception.UnknownEntityException;
import vo.Location;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.*;

/**
 * @author Han Wu, whw10@student.unimelb.edu.au, 1468664
 */

public class MarsUtils {
    private static final List<String> MOVE_KEYS = Arrays.asList(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8"});

    /**
     * [1] to move north. [2] to move west. [3] to move east. [4] to move south. [5] to move north-west. [6] to move south-west. [7] to move
     * north-east. [8] to move south-east. [0] to go back to main menu
     *
     * @param sltEntity
     * @param inCmd
     * @return
     */
    public static Location getMoveLocation(Entity sltEntity, String inCmd) {
        int x = sltEntity.getX();
        int y = sltEntity.getY();
        switch (inCmd) {
            case Constant.MENU_DIRECTION_MOVE_NORTH:
                y = y - 1;
                break;
            case Constant.MENU_DIRECTION_MOVE_WEST:
                x = x - 1;
                break;
            case Constant.MENU_DIRECTION_MOVE_EAST:
                x = x + 1;
                break;
            case Constant.MENU_DIRECTION_MOVE_SOUTH:
                y = y + 1;
                break;
            case Constant.MENU_DIRECTION_MOVE_NORTH_WEST:
                x = x - 1;
                y = y - 1;
                break;
            case Constant.MENU_DIRECTION_MOVE_SOUTH_WEST:
                x = x - 1;
                y = y + 1;
                break;
            case Constant.MENU_DIRECTION_MOVE_NORTH_EAST:
                x = x + 1;
                y = y - 1;
                break;
            case Constant.MENU_DIRECTION_MOVE_SOUTH_EAST:
                x = x + 1;
                y = y + 1;
                break;
            case Constant.MENU_DIRECTION_MOVE_BACK_TO_MAIN_MENU:
                return null;
            default:
                return null;

        }
        return new Location(x, y);
    }

    /**
     * Checks if the given input command consists of valid digits.
     *
     * @param inCmd The input command to validate.
     * @return true if the input consists only of digits, false otherwise.
     */
    public static boolean isValidDigit(String inCmd) {
        if (inCmd == null || inCmd.trim().length() <= 0) {
            return false;
        }
        char[] chars = inCmd.toCharArray();
        for (char c : chars) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * [1] to move north. [2] to move west. [3] to move east. [4] to move south. [5] to move north-west. [6] to move south-west. [7] to move
     * north-east. [8] to move south-east. [0] to go back to main menu
     *
     * @param key The key input by the user.
     * @return true if the key is valid, false otherwise.
     */
    public static boolean isValidMoveActionKey(String key) {
        return MOVE_KEYS.contains(key.toLowerCase());
    }

    /**
     * initialize map and save it in the array
     *
     * @param mapList
     * @return
     */
    public static Entity[][] initArrayEntity(List<String> mapList) {
        Entity[][] arrs = new Entity[mapList.size()][mapList.get(0).length()];
        for (int i = 0; i < mapList.size(); i++) {
            for (int j = 0; j < mapList.get(i).length(); j++) {
                arrs[i][j] = instanceEntity(String.valueOf(mapList.get(i).charAt(j)));
                arrs[i][j].setX(j);
                arrs[i][j].setY(i);
            }
        }
        return arrs;
    }

    /**
    * Dynamically creates an instance of an Entity object based on a given symbol.
    * It utilizes reflection to instantiate objects whose types are not known at compile time.
    *
    * @param symbol The symbol that is used to identify the type of Entity to be instantiated.
    * @return An instance of the corresponding Entity, or null if the process fails.
    * @throws UnknownEntityException If the symbol does not correspond to any known entity.
    */
    public static Entity instanceEntity(String symbol) {
        // Initialize the entity reference to null, to be returned in case of failure.
        Entity entity = null;

        // Attempt to retrieve a Mars object using the provided symbol.
        Mars mars = Mars.getMarsByKey(symbol);
        
        // If no Mars object is associated with the symbol, throw an exception.
        if (mars == null) {
            throw new UnknownEntityException("unknownEntity:" + symbol);
        }

        try {
            // Dynamically locate the class for the entity using the class name stored in Mars object.
            Class<?> aClass = Class.forName("entities." + mars.getEntityName());
            
            // Retrieve the constructor of the entity class that takes two strings as parameters.
            Constructor<?> constructor = aClass.getDeclaredConstructor(String.class, String.class);
            
            // Make the constructor accessible in case it's defined as private.
            constructor.setAccessible(true);
            
            // Create a new instance of the Entity using the constructor and parameters from the Mars object.
            entity = (Entity) constructor.newInstance(mars.getKey(), mars.getEntityName().toUpperCase());
        } catch (Exception ex) {
            // Print the stack trace for any exception that occurs during instantiation.
            ex.printStackTrace();
        }
        
        // Return the newly created Entity instance or null if an error occurred during instantiation.
        return entity;
    }


    /**
    * Attempts to save the current state of a 2D array of entities to a file.
    * The file name is derived from a command string provided as input.
    * It checks for the existence of the file and creates it if necessary.
    * Then it writes the key of each entity in the array to the file.
    * If any I/O operations fail, it returns false to indicate the failure.
    *
    * @param entities 2D array of Entity objects representing the current state.
    * @param inCmd    The command string that determines the file name for saving the data.
    * @return true if the data was successfully saved, false otherwise.
    */
    public static boolean saveCurrentData(Entity[][] entities, String inCmd) {
        boolean status = true;
        String property = System.getProperty("user.dir");
        File file = new File(property + File.separator + inCmd);

        // Check if the file exists, create a new one if it doesn't.
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                status = false;
                System.out.println("Cannot create file for Martian Land Map.\n" +
                        "Terminating the mission for now. See you next time. \n");
                return status;
            }
        }

        // StringBuilder to accumulate the keys of the entities.
        StringBuilder sb = new StringBuilder();
        for (Entity[] entity : entities) {
            for (Entity item : entity) {
                sb.append(item.getKey());
            }
            sb.append("\n");
        }

        // Write the accumulated keys to the file.
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file, false));
            bw.write(sb.toString().substring(0, sb.length() - 1)); // Exclude the last newline.
            bw.flush();
        } catch (IOException ex) {
            status = false;
            System.out.println("Cannot write Martian Land Map to the file.\n" +
                    "Terminating the mission for now. See you next time. \n");
        } finally {
            // Ensure resources are freed.
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return status;
    }

    /**
    * Writes habitability data to a log file. This includes aggregating scores from entities
    * and appending the calculated total score to the log file for tracking over time.
    *
    * @param app   The application instance containing action scores to be logged.
    * @param arrs  2D array of Entity objects from which to derive habitability data.
    */
    public static void writeHabitabilityDatatoLogFile(MarsHabitatApplication app, Entity[][] arrs) {
        // Mapping of habitability scores by entity name.
        Map<String, Entity> marsMap = getHabyScoresEntityMap(arrs);
        String property = System.getProperty("user.dir");
        File file = new File(property + File.separator + "resources/habitability.log");

        // Prepare data for writing to the log.
        StringBuilder sb = new StringBuilder();
        int totalScore = 0;
        sb.append("==START==\n");
        for (Map.Entry<String, Entity> entry : marsMap.entrySet()) {
            totalScore += entry.getValue().getSumScore();
            sb.append(entry.getKey()).append("=").append(entry.getValue().getQty()).append("\n");
        }
        sb.append("SCORE=" + (totalScore + app.getActionScor())).append("\n");
        sb.append("==END==\n");

        // Write data to the log file.
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file, true));
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException ex) {
            System.out.println("Cannot write Martian Land Map to the file.\n" +
                    "Terminating the mission for now. See you next time.\n");
        } finally {
            // Ensure resources are freed.
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
    * Reads habitability data from a log file and prints it out to the console.
    * The method structures the printed output by distinct program runs.
    *
    * @throws IOException If the file cannot be read, an exception is thrown.
    */
    public static void readHabitabilityDataFromLogFile() throws IOException {
        // List to hold habitability data from each 'run' within the log.
        List<String> tentList = new ArrayList<>();
        String property = System.getProperty("user.dir");
        File file = new File(property + File.separator + "resources/habitability.log");

        // Map to hold all 'runs' of data, each associated with an incremental index.
        Map<Integer, List<String>> tentMap = new HashMap<>();
        int index = 0;
        BufferedReader br = null;
        try {
            if (!file.exists()) {
                throw new FileNotFoundException(" File Not Found, aborting mission.");
            }
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().length() <= 0) {
                    continue;
                }
                if (line.indexOf("==START==") > -1) {
                    index++;
                    tentList = new ArrayList<>();
                    tentMap.put(index, tentList);
                    continue;
                }
                if (!line.startsWith("==END==")) {
                    tentMap.get(index).add(line);
                }
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            // Ensure resources are freed.
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Print out each run of habitability data with its corresponding score.
        for (Map.Entry<Integer, List<String>> entry : tentMap.entrySet()) {
            System.out.println(String.format("Program Run :%s \n" +
                    "Habitability Status \n" +
                    "======================", entry.getKey()));
            for (int i = 0; i < entry.getValue().size(); i++) {
                if (i == entry.getValue().size() - 1) {
                    System.out.println(String.format("\nTotal Habitability Score: %s", entry.getValue().get(i).split("=")[1]));
                } else {
                    System.out.println(entry.getValue().get(i));
                }
            }
        }
    }

    /**
    * Processes a 2D array of entities and returns a map with entity names as keys and entities as values.
    * The entities are scored and counted based on their type and occurrences within the array.
    *
    * @param arrs 2D array of Entity objects to be processed for habitability scores.
    * @return A map with entity names as keys and corresponding Entity objects with updated scores and quantities.
    */
    public static Map<String, Entity> getHabyScoresEntityMap(Entity[][] arrs) {
        Map<String, Entity> marsMap = new HashMap<>();
        for (int i = 0; i < arrs.length; i++) {
            for (int k = 0; k < arrs[i].length; k++) {
                Entity en = (Entity) arrs[i][k].clone();
                if (en instanceof Mineral || en instanceof EntityPlant || en instanceof EntityEarthAnimal) {
                    if (marsMap.containsKey(en.getName())) {
                        marsMap.get(en.getName()).setQty(marsMap.get(en.getName()).getQty() + 1);
                        marsMap.put(en.getName(), marsMap.get(en.getName()));
                    } else {
                        en.setQty(1);
                        marsMap.put(en.getName(), en);
                    }
                }
            }
        }
        return marsMap;
    }


    /**
     * print map only
     *
     * @param arrs
     */
    public static void displayMap(Entity[][] arrs) {
        System.out.println("Here is a layout of Martian land.\n");
        for (int i = 0; i < arrs.length; i++) {
            for (int k = 0; k < arrs[i].length; k++) {
                Entity en = (Entity) arrs[i][k].clone();
                System.out.print(en.getKey());
            }
            System.out.println();
        }
        System.out.println();
    }
}


