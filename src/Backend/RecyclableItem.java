package Backend;

import java.util.Random;

/**
 * Created by dantedg on 3/11/2017.
 *
 * This class is a helper class that represents a recyclable item
 */
public class RecyclableItem {
    private String materialType;
    private double weight;

    //constructor
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

    //creates a randomly generated weight from 0 to 5 pounds
    private void setWeight() {
        Random r = new Random();
        this.weight = r.nextDouble() * 5.0;
    }
}
