import constant.Constant;
/**
 * @author Han Wu, whw10@student.unimelb.edu.au, 1468664
 */
public class MoveEventFactory {
    public static MoveEvent getMoveEvent(String code) {
        if (Constant.MAIN_MENU_MOVE_SPACE_ROBOT.equals(code)) {
            return new SpaceRobotMoveEvent();
        }
        if (Constant.MAIN_MENU_MOVE_SPACE_ROVER.equals(code)) {
            return new SpaceRoverMoveEvent();
        }
        if (Constant.MAIN_MENU_MOVE_MARTIAN_ANIMALS.equals(code)) {
            return new MartianAnimalMoveEvent();
        }
        return null;
    }
}
