package Backend;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Class RCMFunction
 * Created by dantedg on 3/14/2017.
 *
 * This class does all the backend work for the RCM
 */
public class RCMFunction{
    private boolean full;
    private double capacity;
    private double weight;
    private USMoney maxMoney;
    private USMoney currentMoney;
    private String location;
    private String ID;
    private int numItems;
    private boolean isActivated; //depicts whether RCM is on or off
    private ArrayList<RecyclableItem> activeRecyclableItems; //list of items that can be recycled
    private HashMap<String, USMoney> recyclableItemPrices; //hash of prices to items
    private HashMap<String, Integer> itemCounts; //count of each distinct item that has been recycled
    private int recycleCounter; //total count of items that have been recycled
    private Date timeStamp; //timestamp to depict last time emptied

    //main constructor
    public RCMFunction(String location, String ID, int capacity) {
        full = false;
        this.location = location;
        this.ID = ID;
        recycleCounter = 0;
        this.capacity = capacity;
        weight = 0;
        numItems = 0;
        this.maxMoney = new USMoney(200, 0);
        currentMoney = maxMoney;
        isActivated = true;
        activeRecyclableItems = new ArrayList<RecyclableItem>();
        recyclableItemPrices = new HashMap<String, USMoney>();
        itemCounts = new HashMap<String,Integer>();
        timeStamp = new Date();
    }

    /////////Getters and Setters\\\\\\\\\

    public Date getTimeStamp() {
        return timeStamp;
    }

    public int getRecycleCounter() {
        return recycleCounter;
    }

    public boolean isFull(){
        return full;
    }

    public void setFull(boolean b){
        full = b;
    }

    public int getNumItems() {
        return numItems;
    }

    //number of available items that can be recycled
    public int availableItems() {
        return activeRecyclableItems.size();
    }

    public String getItemNameByIndex(int i) {
        return activeRecyclableItems.get(i).getMaterialType();
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

    public void addWeight(double itemWeight) {
        this.weight += itemWeight;
        this.capacity -= itemWeight;
    }

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

    //sets an items price
    public void setRecyclableItemPrices(String key, USMoney price){
        recyclableItemPrices.get(key).setDollars(price.getDollars());
        recyclableItemPrices.get(key).setCents(price.getCents());
    }

    //sets an RCM to active or not active
    public void setIsActive(boolean b) {
        this.isActivated = b;
    }

    //checks whether the RCM is active or not
    public boolean isActivated() {
        return isActivated;
    }

    public HashMap<String, USMoney> getRecyclableItemPrices() { return recyclableItemPrices; }

    ////////////////////// OPERATION METHODS //////////////////////

    //empties the RCM of all items and sets weight and numItems to 0
    public void empty(){
        weight = 0;
        numItems = 0;
        itemCounts.clear();
        capacity = 500;
        timeStamp = new Date();
    }

    //What gets called when an item gets recycled
    public USMoney recycle(ArrayList<RecyclableItem> recyclables) {
        double tmpWeight;
        double sub;
        double sumWeight = 0;
        int sessionCounter = 0;
        USMoney coupon = new USMoney(0,0);
        USMoney tmpCost;
        USMoney sumCost = new USMoney(0, 0);
        recycleCounter++;
        for(RecyclableItem tmp : recyclables){
            tmpWeight = tmp.getWeight();
            tmpCost = recyclableItemPrices.get(tmp.getMaterialType()).calculateCost(tmpWeight);
            sub = currentMoney.getDollars() - tmpCost.getDouble();
            if(sub < 0.0){
                sumWeight += tmpWeight;
                coupon.add(tmpCost);
                sessionCounter++;
                continue;
            }
            sumWeight += tmpWeight;
            sumCost.addTo(tmpCost.getDollars(), tmpCost.getCents());
            sessionCounter++;
            currentMoney.setDollars(0);
            sub *= 100.0;
            currentMoney.setCents((int)sub);
        }
        if(coupon.getDollars() == 0 && coupon.getCents() == 0) {
            JOptionPane.showMessageDialog(null,
                    sumWeight + " lb. and " + sessionCounter + " item recycling success! Dispensing " + sumCost.toString() + "!");
        }
        else{
            JOptionPane.showMessageDialog(null,
                    sumWeight + " lb. and " + sessionCounter + " item recycling success! Dispensing " + sumCost.toString() + " and " +
                            "printing voucher for " + coupon.toString() + "!");
        }
        return sumCost;
    }

    //adds an item to the RCM's list of accepted item
    public void addItem(RecyclableItem x, USMoney value) {
        activeRecyclableItems.add(x);
        recyclableItemPrices.put(x.getMaterialType(),value);
    }

    //removes an item from the RCM's list of accepted items
    public void removeItem(RecyclableItem item, int index) {
        recyclableItemPrices.remove(item.getMaterialType());
        activeRecyclableItems.remove(index);
    }

    //increments the count for total items and the specific item in the item count hash
    public void incrementItemCount(String key) {
        numItems++;
        if (!itemCounts.containsKey(key)) {
            itemCounts.put(key, 1);
        } else {
            int count = itemCounts.get(key);
            itemCounts.put(key, (count + 1));
        }
    }

    //fetches the item count for a specific item in the item count hash
    public int fetchItemCount(String key) {
        if(itemCounts.containsKey(key)) {
            return itemCounts.get(key);
        } else {
            return -1;
        }
    }
}
