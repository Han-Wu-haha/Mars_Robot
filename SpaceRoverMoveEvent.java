import enums.Mars;
import entities.Entity;
import vo.Location;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Class for handling movement events of a Space Rover within the Mars habitat.
 * The Space Rover can move to an empty space, destroy rocks, or collect minerals,
 * which can affect the habitat's habitability score.
 * @author Han Wu, whw10@student.unimelb.edu.au, 1468664
 */
public class SpaceRoverMoveEvent extends AbstractMoveEvent implements MoveEvent {
    
    /**
     * Executes a move event for a Space Rover within the entities grid.
     * Depending on the entity at the destination, the rover may swap positions,
     * destroy a rock, or collect a mineral, updating the habitability score accordingly.
     * 
     * @param app        The MarsHabitatApplication instance managing the state of the application.
     * @param entities   The current grid of entities.
     * @param sltEntity  The Space Rover entity that is moving.
     * @param locTo      The destination location for the move.
     * @param reader     The BufferedReader to handle user input, if necessary.
     * @param inCmd      The input command triggering this event.
     * @return           A String indicating the outcome of the move, or null if the move is completed within the grid.
     * @throws IOException If an input or output exception occurs.
     */
    @Override
    public String move(MarsHabitatApplication app, Entity[][] entities, Entity sltEntity, Location locTo,
                       BufferedReader reader, String inCmd) throws IOException {
        // Get the key of the entity at the target location.
        String nextStr = entities[locTo.y()][locTo.x()].getKey();
        
        // If the location is empty, perform the swap.
        if (Mars.DOT.getKey().equals(nextStr)) {
            swapPosition(entities, sltEntity, locTo);
            MarsUtils.writeHabitabilityDatatoLogFile(app, entities);
        } 
        // If the location has a rock, remove it.
        else if (Mars.Rock.getKey().equals(nextStr)) {
            System.out.println("We found a plain rock, Rover will destroy it now.");
            app.addActionScore(1); // Increase habitability score for removing the rock.
            collectPosition(entities, sltEntity, locTo); // Collect or remove the rock.
            MarsUtils.writeHabitabilityDatatoLogFile(app, entities);
        } 
        // If the location has a mineral, collect it.
        else if (Mars.Mineral.getKey().equals(nextStr)) {
            System.out.println("We found a mineral, Rover will collect it now.");
            app.addActionScore(2); // Increase habitability score for collecting the mineral.
            collectPosition(entities, sltEntity, locTo); // Collect the mineral.
            MarsUtils.writeHabitabilityDatatoLogFile(app, entities);
        } 
        // If the destination location is not valid for movement.
        else {
            System.out.println("You cannot move to this location.");
        }
        return null;
    }
}

