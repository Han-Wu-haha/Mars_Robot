package entities;

/**
 * @author Han Wu, whw10@student.unimelb.edu.au, 1468664
 */
public class Dog extends EntityEarthAnimal {

    public Dog(String key, String name) {
        super(key,name);
        this.health = 10;
    }

}
