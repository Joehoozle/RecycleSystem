
/**
 * Created by dantedg on 1/29/2017.
 */
public class USMoney {
    private int dollars;
    private int cents;

    public USMoney() {}

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
     * USMoney object and places these new values in the
     * current USMoney object while returning void
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
     * This method adds a USMoney object to the current USMoney object
     * and returns a new USMoney object with the added values
     *
     * @author Dante Dalla Gasperina
     * @version 1.0
     * @date 2017-01-29
     */
    public USMoney add(USMoney money) {
        int newDollars = this.dollars + money.dollars;
        int newCents;
        // convert cents to dollars if more than 99 cents
        if(this.cents + money.cents > 99) {
            newDollars += (this.cents + money.cents)/100;
            newCents = (this.cents + money.cents) % 100;
        } else {
            newCents = this.cents + money.cents;
        }
        USMoney newMoney = new USMoney(newDollars, newCents);
        return newMoney;
    }

    @Override
    public String toString() {
        String string = "$" + Integer.toString(dollars) + "." + Integer.toString(cents);
        return string;
    }
}
