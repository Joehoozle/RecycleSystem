import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by dantedg on 3/15/2017.
 */
public abstract class RCMTransaction {
    //TODO: will this work for multiple transactions?

    public static void post(String sale, String id, double weight, String material, int emptyFlag) {
        Connection con = null;
        try {
            String URL = "jdbc:sqlite:C:\\Users\\dantedg\\Documents\\ClassFiles\\ObjectOriented\\RecycleSystem\\RCMdata";
            con = DriverManager.getConnection(URL);
            System.out.print("Got connected!");
            String query = "INSERT INTO RCMTransactions(RCMId, sale, material, weight, emptyFlag) " +
                    "VALUES( '" +
                    id + "', '" +
                    sale + "', '" +
                    material + "', " +
                    Double.toString(weight) + ", " +
                    Integer.toString(emptyFlag) + ");";
            Statement statement = con.createStatement();
            statement.execute(query);
            System.out.println("Row was inserted alright");
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
}
