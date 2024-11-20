package entities;

/**
 * @author Han Wu, whw10@student.unimelb.edu.au, 1468664
 */
public class Mineral extends EntityNonScore {
    private int qty       = 0;
    public Mineral(String key, String name) {
        super(key, name);
    }

    @Override
    public int getQty() {
        return qty;
    }

    @Override
    public void setQty(int qty) {
        this.qty = qty;
    }
}
