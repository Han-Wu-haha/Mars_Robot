import enums.Mars;
import entities.Dot;
import entities.Entity;
import vo.Location;

/**
 * Provides an abstract foundation for movement-related events within the game system.
 * This class captures the essential mechanisms and operations needed to facilitate 
 * the various movement behaviors of entities, such as swapping positions, collection,
 * removal, and instantiation. It is designed to be extended by specific event handlers 
 * that further detail or customize these behaviors as per game rules and scenarios.
 * 
 * @author Han Wu, whw10@student.unimelb.edu.au, 1468664
 */

public abstract class AbstractMoveEvent {

    /**
     * Swaps the positions of two entities.
     *
     * @param entities  The 2D array containing all entities.
     * @param sltEntity The selected entity to be moved.
     * @param locTo     The target location to move the selected entity to.
     */
    protected void swapPosition(Entity[][] entities, Entity sltEntity, Location locTo) {
        Entity tempTo = (Entity) entities[locTo.y()][locTo.x()].clone();
        Entity tempFrom = (Entity) sltEntity.clone();

        sltEntity.setX(locTo.x());
        sltEntity.setY(locTo.y());
        entities[locTo.y()][locTo.x()] = sltEntity;

        tempTo.setX(tempFrom.getX());
        tempTo.setY(tempFrom.getY());
        entities[tempFrom.getY()][tempFrom.getX()] = tempTo;
    }

    /**
     * Moves the selected entity to the target location and replaces
     * the target entity with a Dot.
     *
     * @param entities  The 2D array containing all entities.
     * @param sltEntity The selected entity to be moved.
     * @param locTo     The target location to move the selected entity to.
     */
    protected void collectPosition(Entity[][] entities, Entity sltEntity, Location locTo) {
        Entity tempFrom = (Entity) sltEntity.clone();

        sltEntity.setX(locTo.x());
        sltEntity.setY(locTo.y());
        entities[locTo.y()][locTo.x()] = sltEntity;

        Entity entity = new Dot(Mars.DOT.getKey(), Mars.DOT.getEntityName().toUpperCase());
        entity.setX(sltEntity.getX());
        entity.setY(sltEntity.getY());
        entities[tempFrom.getY()][tempFrom.getX()] = entity;
    }

    /**
     * Removes the entity at the target location and replaces it with a Dot.
     *
     * @param entities The 2D array containing all entities.
     * @param locTo    The target location.
     */
    protected void removePosition(Entity[][] entities, Location locTo) {
        Entity entity = new Dot(Mars.DOT.getKey(), Mars.DOT.getEntityName().toUpperCase());
        entity.setX(locTo.x());
        entity.setY(locTo.y());
        entities[locTo.y()][locTo.x()] = entity;
    }

    /**
     * Removes the selected entity from its current position and replaces it with a Dot.
     *
     * @param entities  The 2D array containing all entities.
     * @param sltEntity The selected entity to be removed.
     */
    protected void selfDestructPosition(Entity[][] entities, Entity sltEntity) {
        Entity entity = new Dot(Mars.DOT.getKey(), Mars.DOT.getEntityName().toUpperCase());
        entity.setX(sltEntity.getX());
        entity.setY(sltEntity.getY());
        entities[sltEntity.getY()][sltEntity.getX()] = entity;
        sltEntity = null;
    }

    /**
     * Creates and places a new entity instance based on given symbols at the specified location.
     *
     * @param entities The 2D array containing all entities.
     * @param locTo    The target location.
     * @param symbols  Symbols representing the entity to be instantiated.
     */
    protected void planInstance(Entity[][] entities, Location locTo, String symbols) {
        entities[locTo.y()][locTo.x()] = MarsUtils.instanceEntity(symbols);
    }
}

