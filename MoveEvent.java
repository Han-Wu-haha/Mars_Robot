import entities.Entity;
import vo.Location;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Han Wu, whw10@student.unimelb.edu.au, 1468664
 */
public interface MoveEvent {
    public String move(MarsHabitatApplication app, Entity[][] entities, Entity sltEntity, Location locTo,
                       BufferedReader reader, String inCmd) throws IOException;

}
