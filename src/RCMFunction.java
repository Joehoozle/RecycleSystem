
import java.util.ArrayList;

/**
 * Class RCMFunction
 * Created by dantedg on 3/14/2017.
 */
public class RCMFunction{
    private double capacity;
    private double weight;
    private USMoney maxMoney;
    private USMoney currentMoney;
    private String location;
    private String ID;
    private int numItems;

    public RCMFunction(String location, String ID, int capacity, USMoney maxMoney) {
        this.location = location;
        this.ID = ID;
        this.capacity = capacity;
        weight = 0;
        numItems = 0;
        currentMoney = maxMoney;
        this.maxMoney = maxMoney;
    }

    /////////Getters and Setters\\\\\\\\\
    public int getNumItems() {
        return numItems;
    }

    public void setNumItems(int numItems) {
        this.numItems = numItems;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public USMoney getMaxMoney(){ return maxMoney; }

    public USMoney getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(USMoney currentMoney) {
        this.currentMoney = currentMoney;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String place) {
        this.location = place;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "RCM: " + ID + " in " + location;
    }

    ////////////////////// OPERATION METHODS //////////////////////
    public void empty(){
        weight = 0;
    }

    public void refillMoney() { currentMoney = maxMoney; }

    //What gets called when an item gets recycled
    public void recycle(ArrayList<RecyclableItem> recyclables) {
        numItems++;
        for(RecyclableItem tmp : recyclables){
            //RCMTransaction.post();
        }
    }

    public void logTransaction(ArrayList<RecyclableItem> items, ArrayList<String> sales) {
        RecyclableItem item;
        String sale;
        for(int i=0;i<items.size();i++) {
            item = items.get(i);
            sale = sales.get(i);
            RCMTransaction.post("90",ID,item.getWeight(),item.getMaterialType(),0);
        }
    }
}
