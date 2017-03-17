import java.lang.reflect.Array;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

/**
 * Created by dantedg on 3/14/2017.
 */
public class RMOSFunction extends Observable{
    ArrayList<RCMFunction> RCMList;
    ArrayList<RecyclableItem> activeRecyclableItems;
    HashMap<String,USMoney> recyclableItemPrices;

    public RMOSFunction(ArrayList<RCMFunction> RCMList) {
        this.RCMList = RCMList;
        activeRecyclableItems = new ArrayList<RecyclableItem>();
        recyclableItemPrices = new HashMap<String, USMoney>();
    }
    /////////////Database Statistics\\\\\\\\\\\\\
    public void setupStatistics() {
        Connection con = null;
        try {
            String URL = "jdbc:sqlite:C:\\Users\\dantedg\\Documents\\ClassFiles\\ObjectOriented\\RecycleSystem\\RCMdata";
            con = DriverManager.getConnection(URL);
            System.out.print("Got connected!");
            String query = "CREATE TABLE IF NOT EXISTS RCMTransactions (" +
                    "id INTEGER PRIMARY KEY," +
                    "RCMId INTEGER," +
                    "sale TEXT," +
                    "material TEXT," +
                    "weight REAL," +
                    "timeStamp DateTime DEFAULT CURRENT_TIMESTAMP," +
                    "emptyFlag INTEGER );";
            Statement statement = con.createStatement();
            statement.execute(query);
            System.out.println("Table was created alright");
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    System.out.print("Closing");
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public String getAllEntries() {
        Connection con = null;
        ResultSet result = null;
        String ID ="";
        try {
            String URL = "jdbc:sqlite:C:\\Users\\dantedg\\Documents\\ClassFiles\\ObjectOriented\\RecycleSystem\\RCMdata";
            con = DriverManager.getConnection(URL);
            System.out.print("Got connected!");
            String query = "SELECT * FROM RCMTransactions;";
            Statement statement = con.createStatement();
            result = statement.executeQuery(query);
            System.out.println("Table was created alright");
            if (result.next()) {
                ID = result.getString("sale");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    System.out.print("Closing");
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return ID;
    }

//    public String getMostRecent

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

    public void editRCMCapacity(int i,double capacity) {
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
        recyclableItemPrices.put(string,cost);
        notifyObservers(activeRecyclableItems);
    }

    public void deleteItem(int i) {
        activeRecyclableItems.remove(i);
        notifyObservers(activeRecyclableItems);
    }

    public USMoney getItemPrice(int i) {
        return recyclableItemPrices.get(getItemNameByIndex(i));
    }

    public void editItemPrice(int i, USMoney price) {
        recyclableItemPrices.put(activeRecyclableItems.get(i).getMaterialType(),price);
        for(int j = 0; j < RCMList.size(); j++){
            RCMList.get(j).setRecyclableItemPrices(activeRecyclableItems.get(i).toString(), price);
        }
        notifyObservers(activeRecyclableItems);
    }

    public String getNumberOfItems(int i) {
        return Integer.toString(RCMList.get(i).getNumItems());
    }
}
