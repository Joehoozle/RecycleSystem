package Backend;

import java.util.Random;

/**
 * Created by dantedg on 3/11/2017.
 */
public class RecyclableItem {
    private String materialType;
    private double weight;

    public RecyclableItem(String materialType) {
        this.materialType = materialType;
        setWeight();
    }

    public String getMaterialType() {
        return materialType;
    }

    public double getWeight() {
        return weight;
    }

    private void setWeight() {
        Random r = new Random();
        this.weight = r.nextDouble() * 5.0;
    }
}
