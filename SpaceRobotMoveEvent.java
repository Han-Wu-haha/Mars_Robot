import constant.Constant;
import enums.CattleMenuEnum;
import enums.Mars;
import entities.Dog;
import entities.Entity;
import entities.EntityEarthAnimal;
import entities.EntityPlant;
import enums.PlantMenuEnum;
import enums.SpaceRobotMoveEnum;
import vo.Location;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Class responsible for handling the movement events of a Space Robot within the Mars habitat.
 * A Space Robot can perform various actions upon moving to a new location, such as swapping positions,
 * watering plants, or feeding animals, which may affect the habitat's habitability.
 * It also includes a command-driven submenu for additional actions like planting trees or rearing cattle.
 * The move event is processed based on the entity present at the target location.
 * @author Han Wu, whw10@student.unimelb.edu.au, 1468664
 */
public class SpaceRobotMoveEvent extends AbstractMoveEvent implements MoveEvent {

    /**
     * Executes a move event for a Space Robot within the entities grid.
     * 
     * @param app        The MarsHabitatApplication instance managing the state of the application.
     * @param entities   The current grid of entities.
     * @param sltEntity  The Space Robot entity that is moving.
     * @param locTo      The destination location for the move.
     * @param reader     The BufferedReader to handle user input for interactive actions.
     * @param inCmd      The input command triggering this event.
     * @return           A String indicating the outcome of the move, or null if the move is completed within the grid.
     * @throws IOException If an input or output exception occurs.
     */
    @Override
    public String move(MarsHabitatApplication app, Entity[][] entities, Entity sltEntity, Location locTo,
                       BufferedReader reader, String inCmd) throws IOException {
        Entity nextEntity = entities[locTo.y()][locTo.x()];
        String nextStr = nextEntity.getKey();

        // If the target location is empty, perform the swap and check for subsequent moves.
        if (Mars.dot().equals(nextStr)) {
            swapPosition(entities, sltEntity, locTo);
            // Check the left side of the new position for an empty space to move to.
            if (Mars.dot().equals(entities[locTo.y()][locTo.x() - 1].getKey())) {
                Location nextLoc = new Location(locTo.x() - 1, locTo.y());
                // Process a submenu for additional moves until a previous menu command is received.
                do {
                    inCmd = doMove(reader, entities, nextLoc);
                } while (!Constant.MENU_GO_PREVIOUS.equals(inCmd));
            }
            MarsUtils.writeHabitabilityDatatoLogFile(app, entities);
        } 
        // If the target location has a plant, offer to water it.
        else if (nextEntity instanceof EntityPlant) {
            // Interactive loop to handle user decision to water the plant or not.
            do {
                System.out.print(Constant.PROMPT_SPACE_MOVE_TO_PLANT_OCCUPIED);
                inCmd = reader.readLine();
                // If the user chooses to water the plant, increase habitability score.
                if ("Y".equals(inCmd)) {
                    System.out.println("You watered a plant. It will grow");
                } else if (!"N".equals(inCmd)) {
                    System.out.println("Invalid Command");
                }
            } while (!("Y".equals(inCmd) || "N".equals(inCmd)));
            MarsUtils.writeHabitabilityDatatoLogFile(app, entities);
        } 
        // If the target location has an earth animal, offer to feed it.
        else if (nextEntity instanceof EntityEarthAnimal) {
            // Interactive loop to handle user decision to feed the animal or not.
            do {
                System.out.print(Constant.PROMPT_SPACE_MOVE_TO_EARTHANIMAL_OCCUPIED);
                inCmd = reader.readLine();
                // If the user chooses to feed the animal, increase habitability score.
                if ("Y".equals(inCmd)) {
                    System.out.println("You have fed the cattle. It will grow");
                    app.addActionScore(2);
                    // If the animal is a dog, increase its health.
                    if (nextEntity instanceof Dog) {
                        nextEntity.setHealth(nextEntity.getHealth() + 1);
                    }
                } else if (!"N".equals(inCmd)) {
                    System.out.println("Invalid Command");
                }
            } while (!("Y".equals(inCmd) || "N".equals(inCmd)));
            MarsUtils.writeHabitabilityDatatoLogFile(app, entities);
        } 
        // If the target location is not valid for movement.
        else {
            System.out.println("You cannot move to this location.");
        }
        return null;
    }

    /**
     * Handles additional move commands provided by the user through a submenu.
     * This method is invoked when the Space Robot has additional options after moving to an empty location.
     * 
     * @param reader   The BufferedReader to handle user input.
     * @param entities The current grid of entities.
     * @param locTo    The location to which additional actions may apply.
     * @return         A String command that controls the flow of the submenu.
     * @throws IOException If an input or output exception occurs.
     */
    private String doMove(BufferedReader reader, Entity[][] entities, Location locTo)
            throws IOException {
        System.out.print(Constant.PROMPT_MOVE_SUB_MENU);
        String inCmd = reader.readLine();
        SpaceRobotMoveEnum menuEnum = null;
        
        // Plant tree command.
        if (Constant.MENU_PLAN_TREE.equals(inCmd)) {
            System.out.print(Constant.PROMPT_MENU_PLAN_TREE);
            inCmd = reader.readLine();
            menuEnum = PlantMenuEnum.getByKeyNum(inCmd);
        } 
        // Rear cattle command.
        else if (Constant.MENU_REAR_CATTLE.equals(inCmd)) {
            System.out.print(Constant.PROMPT_MENU_REAR_CATTLE);
            inCmd = reader.readLine();
            menuEnum = CattleMenuEnum.getByKeyNum(inCmd);
        }

        // Execute the action associated with the chosen command.
        if (menuEnum != null) {
            planInstance(entities, locTo, menuEnum.getMarsKey());
            System.out.println(menuEnum.getPrintMsg());
        } else if (Constant.MENU_GO_PREVIOUS.equalsIgnoreCase(inCmd)) {
            return Constant.MENU_GO_PREVIOUS;
        }
        
        // Default command to return to the previous menu.
        return Constant.MENU_GO_PREVIOUS;
    }
}

