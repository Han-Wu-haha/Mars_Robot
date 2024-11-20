import constant.Constant;
import enums.Mars;
import entities.Dog;
import entities.Entity;
import entities.EntityEarthAnimal;
import entities.EntityPlant;
import vo.Location;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Handles the movement events for Martian animals within the Mars Habitat simulation.
 * This event can involve moving to an empty location, engaging in a fight with a dog,
 * consuming plants or interacting with earth animals.
 * @author Han Wu, whw10@student.unimelb.edu.au, 1468664
 */
public class MartianAnimalMoveEvent extends AbstractMoveEvent implements MoveEvent {

    /**
     * Executes a move event for a selected entity within the entities grid.
     * 
     * @param app        The MarsHabitatApplication instance managing the state of the application.
     * @param entities   The current grid of entities.
     * @param sltEntity  The selected entity that is moving.
     * @param locTo      The destination location for the move.
     * @param reader     The BufferedReader to handle user input, if necessary.
     * @param inCmd      The input command triggering this event.
     * @return           A String indicating the outcome of the move, or null if the move is within the grid.
     * @throws IOException If an input or output exception occurs.
     */
    @Override
    public String move(MarsHabitatApplication app, Entity[][] entities, Entity sltEntity, Location locTo,
                       BufferedReader reader, String inCmd) throws IOException {
        // Get the entity at the destination location.
        Entity nextEntity = entities[locTo.y()][locTo.x()];
        String nextStr = nextEntity.getKey();

        // If the location is empty, perform the swap.
        if (Mars.DOT.getKey().equals(nextStr)) {
            swapPosition(entities, sltEntity, locTo);
            MarsUtils.writeHabitabilityDatatoLogFile(app, entities);
        } 
        // If the next entity is a Dog, engage in a fight.
        else if (nextEntity instanceof Dog) {
            System.out.println("Martian animal and Dog has entered a fight");
            int fightTimes = Math.min(nextEntity.getHealth(), sltEntity.getHealth()) / 2 + 1;

            // Loop over the fight sequence.
            for (int i = 0; i < fightTimes; i++) {
                // Martian Animal attacks the dog.
                nextEntity.setHealth(nextEntity.getHealth() - 2);
                System.out.println(String.format("Martian Animal attacked dog. Health of dog reduced by 2, Present Health: %s",
                        nextEntity.getHealth()));

                // Check if the dog has died.
                if (nextEntity.getHealth() <= 0) {
                    System.out.println("Dog died\n");
                    removePosition(entities, locTo);
                    break;
                }

                // Dog retaliates.
                sltEntity.setHealth(sltEntity.getHealth() - 2);
                System.out.println(String.format("Dog attacked Martian Animal. Martian Animal's health reduced by 2, Present Health: %s",
                        sltEntity.getHealth()));

                // Check if the Martian Animal has died.
                if (sltEntity.getHealth() <= 0) {
                    System.out.println("Martian Animal died\n");
                    app.addActionScore(7);
                    slefDestructPosition(entities, sltEntity);
                    return Constant.MENU_DIRECTION_MOVE_BACK_TO_MAIN_MENU;
                }
            }
            MarsUtils.writeHabitabilityDatatoLogFile(app, entities);
        } 
        // If the next entity is a plant or an earth animal, the Martian animal will consume it.
        else if (nextEntity instanceof EntityPlant || nextEntity instanceof EntityEarthAnimal) {
            if (nextEntity instanceof EntityEarthAnimal) {
                System.out.println("The cattle are killed by the martian animals.");
            } else {
                System.out.println("The plantation are eaten by the martian animals.");
            }
            sltEntity.setHealth(sltEntity.getHealth() + 2);
            collectPosition(entities, sltEntity, locTo);
            MarsUtils.writeHabitabilityDatatoLogFile(app, entities);
        } 
        // If the destination location is not valid for movement.
        else {
            System.out.println("You cannot move to this location.");
        }
        return null;
    }
}

