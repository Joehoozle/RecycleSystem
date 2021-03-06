package Backend;

/**
 * Created by dantedg on 1/29/2017.
 *
 * This class is a helper class that deals with Money logic and visualization
 */
public class USMoney {
    private int dollars;
    private int cents;

    //constructor
    public USMoney(int dollars, int cents) {
        // convert cents to dollars if more than 99 cents
        if(cents > 99) {
            dollars += cents/100;
            cents %= 100;
        }
        this.dollars = dollars;
        this.cents = cents;
    }

    public int getDollars() {
        return dollars;
    }

    public void setDollars(int dollars) {
        this.dollars = dollars;
    }

    public int getCents() {
        return cents;
    }

    public void setCents(int cents) {
        // convert cents to dollars if more than 99 cents
        if(cents > 99) {
            this.dollars += (cents/100);
            this.cents = (cents % 100);
        } else {
            this.cents = cents;
        }
    }

    /**
     * This method adds two integers dollars and cents to the current
     * Backend.USMoney object and places these new values in the
     * current Backend.USMoney object while returning void
     *
     * @author Dante Dalla Gasperina
     * @version 1.0
     * @date 2017-01-29
     */
    public void addTo(int dollars, int cents) {
        this.dollars += dollars;
        // convert cents to dollars if more than 99 cents
        if(this.cents + cents > 99) {
            this.dollars += (this.cents + cents)/100;
            this.cents = (this.cents + cents) % 100;
        } else {
            this.cents += cents;
        }
    }

    /**
     * This method adds a Backend.USMoney object to the current Backend.USMoney object
     * and returns a new Backend.USMoney object with the added values
     *
     * @author Dante Dalla Gasperina
     * @version 1.0
     * @date 2017-01-29
     */
    public void add(USMoney money) {
        int newDollars = this.dollars + money.dollars;
        int newCents;
        // convert cents to dollars if more than 99 cents
        if(this.cents + money.cents > 99) {
            newDollars += (this.cents + money.cents)/100;
            newCents = (this.cents + money.cents) % 100;
        } else {
            newCents = this.cents + money.cents;
        }
        this.dollars = newDollars;
        this.cents = newCents;
    }

    @Override
    public String toString() {
        String string;
        if(cents < 10)
            string = "$" + Integer.toString(dollars) + ".0" + Integer.toString(cents);
        else
            string = "$" + Integer.toString(dollars) + "." + Integer.toString(cents);
        return string;
    }

    public double getDouble(){
        return dollars + ((double) cents / 100);
    }


    //this is a special method that takes in the weight of an object and returns a USMoney object representing the multiplication
    public USMoney calculateCost(double weight){
        double dollarPart = weight * (double) (dollars * 100);
        double centPart = weight * (double) cents;
        int cost = (int) Math.round(dollarPart) + (int) Math.round(centPart);
        return new USMoney(0, cost);
    }

    //compares two USMoney objects
    public int compareTo(USMoney money2){
        return (cents + dollars * 100) - (money2.getCents() + money2.getDollars() * 100);
    }
}
