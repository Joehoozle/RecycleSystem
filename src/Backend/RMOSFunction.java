package Backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

/**
 * Created by dantedg on 3/14/2017.
 */
public class RMOSFunction extends Observable {
    ArrayList<RCMFunction> RCMList;
    ArrayList<RecyclableItem> activeRecyclableItems;
    HashMap<String, USMoney> recyclableItemPrices;

    public RMOSFunction(ArrayList<RCMFunction> RCMList) {
        this.RCMList = RCMList;
        activeRecyclableItems = new ArrayList<RecyclableItem>();
        recyclableItemPrices = new HashMap<String, USMoney>();
    }

    //////////RCM Manipulation\\\\\\\\\\\\\\
    private void updateRCMList() {

    }

    public int getRCMNumber() {
        return RCMList.size();
    }

    public String getRCMId(int i) {
        return RCMList.get(i).getID();
    }

    public String getRCMName(int i) {
        return RCMList.get(i).toString();
    }

    public void addRCM(RCMFunction rcm) {
        RCMList.add(rcm);
    }

    public String getRCMLocation(int i) {
        return RCMList.get(i).getLocation();
    }

    public String getRCMID(int i) {
        return RCMList.get(i).getID();
    }

    public String getRCMCapacity(int i) {
        return Double.toString(RCMList.get(i).getCapacity());
    }

    public void editRCMCapacity(int i, double capacity) {
        RCMList.get(i).setCapacity(capacity);
    }

    public String getRCMWeight(int i) {
        return Double.toString(RCMList.get(i).getWeight());
    }

    public USMoney getRCMMoney(int i) {
        return RCMList.get(i).getCurrentMoney();
    }

    public void editRCMMoney(USMoney money, int i) {
        RCMList.get(i).setCurrentMoney(money);
    }

    public void empty(int i) {
        RCMList.get(i).empty();
    }

    public void activateRCM(int i) {
        RCMList.get(i).setIsActive(true);
    }

    public void deactivateRCM(int i) {
        RCMList.get(i).setIsActive(false);
    }

    /////////////Item Manipulation\\\\\\\\\\\\\\\
    public int getActiveItemNumber() {
        return activeRecyclableItems.size();
    }

    public String getActiveItemName(int i) {
        return activeRecyclableItems.get(i).getMaterialType();
    }

    public String getItemNameByIndex(int i) {
        return activeRecyclableItems.get(i).getMaterialType();
    }

    public void addItem(String string, USMoney cost) {
        activeRecyclableItems.add(new RecyclableItem(string));
        recyclableItemPrices.put(string, cost);
        for (int i = 0; i < RCMList.size(); i++) {
            RCMList.get(i).addItem(new RecyclableItem(string), cost);
        }
    }

    public void deleteItem(int i) {
        for (int j = 0; j < RCMList.size(); j++) {
            RCMList.get(j).removeItem(activeRecyclableItems.get(i), i);
        }
        activeRecyclableItems.remove(i);
    }

    public USMoney getItemPrice(int i) {
        return recyclableItemPrices.get(getItemNameByIndex(i));
    }

    public void editItemPrice(int i, USMoney price) {
        recyclableItemPrices.put(activeRecyclableItems.get(i).getMaterialType(), price);
        for (int j = 0; j < RCMList.size(); j++) {
            RCMList.get(j).setRecyclableItemPrices(activeRecyclableItems.get(i).getMaterialType(), price);
        }
    }

    public int getNumberOfItems(int i) {
        return RCMList.get(i).getNumItems();
    }

    public int fetchItemNumbers(String key, int RCMIndex) {
        return RCMList.get(RCMIndex).fetchItemCount(key);
    }

    public String smallestTimestamp() {
        if (RCMList.get(0).getTimeStamp().compareTo(RCMList.get(1).getTimeStamp()) < 0) {
            return RCMList.get(1).getID() + ": " + RCMList.get(0).getTimeStamp();
        } else if (RCMList.get(0).getTimeStamp().compareTo(RCMList.get(1).getTimeStamp()) >= 0) {
            return RCMList.get(0).getID();
        } else {
            return "error";
        }
    }

    public String mostRecycles() {
        if (RCMList.get(0).getRecycleCounter() > RCMList.get(1).getRecycleCounter()) {
            return RCMList.get(0).getID() + ": " + RCMList.get(0).getRecycleCounter();
        } else if (RCMList.get(0).getRecycleCounter() <= RCMList.get(1).getRecycleCounter()) {
            return RCMList.get(1).getID() + ": " + RCMList.get(1).getRecycleCounter();
        } else {
            return "error";
        }
    }
}
