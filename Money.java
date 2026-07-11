/**
 * Represents an amount of money as a balance of Philippine peso
 * denominations.
 * @author Gutierrez, Jonathan Jr
 * @author Maullon, Edriel Lexine
 */
public class Money {
    /** Number of 1000 peso bills */
    private int thousand;
 
    /** Number of 500 peso bills */
    private int fiveH;
 
    /** Number of 100 peso bills */
    private int hund;
 
    /** Number of 50 peso bills */
    private int fifty;
 
    /** Number of 20 peso coins */
    private int twenty;
 
    /** Number of 10 peso coins */
    private int ten;
 
    /** Number of 5 peso coins */
    private int five;
 
    /** Number of 1 piso coins */
    private int one;
 
    /** The total value of all the peso denominations */
    private float total;
 
    /**
     * Constructs a new Money instance with all denomination counts
     * and the total set to zero.
     */
    public Money() {
        thousand = 0;
        fiveH = 0;
        hund = 0;
        fifty = 0;
        twenty = 0;
        ten = 0;
        five = 0;
        one = 0;
        total = 0.0f;
    }
 
    /**
     * Returns the number of 1000 peso bills.
     *
     * @return the count of 1000 peso bills
     */
    public int getThousand() {
        return thousand;
    }
 
    /**
     * Returns the number of 500 peso bills.
     *
     * @return the count of 500 peso bills
     */
    public int getFiveH() {
        return fiveH;
    }
 
    /**
     * Returns the number of 100 peso bills.
     *
     * @return the count of 100 peso bills
     */
    public int getHund() {
        return hund;
    }
 
    /**
     * Returns the number of 50 peso bills.
     *
     * @return the count of 50 peso bills
     */
    public int getFifty() {
        return fifty;
    }
 
    /**
     * Returns the number of 20 peso coins.
     *
     * @return the count of 20 peso coins
     */
    public int getTwenty() {
        return twenty;
    }
 
    /**
     * Returns the number of 10 peso coins.
     *
     * @return the count of 10 peso coins
     */
    public int getTen() {
        return ten;
    }
 
    /**
     * Returns the number of 5 peso coins.
     *
     * @return the count of 5 peso coins
     */
    public int getFive() {
        return five;
    }
 
    /**
     * Returns the number of 1 piso coins.
     *
     * @return the count of 1 piso coins
     */
    public int getOne() {
        return one;
    }
 
    /**
     * Returns the total monetary value of the Money
     *
     * @return the total value of Money instance
     */
    public float getTotal() {
        return total;
    }
 
    /**
     * Sets the number of 1000 peso bills and recalculates the total.
     *
     * @param thousand the new count of 1000 peso bills
     */
    public void setThousand(int thousand) {
        this.thousand = thousand;
        calculateTotal();
    }
 
    /**
     * Sets the number of 500 peso bills and recalculates the total.
     *
     * @param fiveH the new count of 500 peso bills
     */
    public void setFiveH(int fiveH) {
        this.fiveH = fiveH;
        calculateTotal();
    }
 
    /**
     * Sets the number of 100 peso bills and recalculates the total.
     *
     * @param hund the new count of 100 peso bills
     */
    public void setHund(int hund) {
        this.hund = hund;
        calculateTotal();
    }
 
    /**
     * Sets the number of 50 peso bills and recalculates the total.
     *
     * @param fifty the new count of 50 peso bills
     */
    public void setFifty(int fifty) {
        this.fifty = fifty;
        calculateTotal();
    }
 
    /**
     * Sets the number of 20 peso coins and recalculates the total.
     *
     * @param twenty the new count of 20 peso coins
     */
    public void setTwenty(int twenty) {
        this.twenty = twenty;
        calculateTotal();
    }
 
    /**
     * Sets the number of 10 peso bills and recalculates the total.
     *
     * @param ten the new count of 10 peso coins
     */
    public void setTen(int ten) {
        this.ten = ten;
        calculateTotal();
    }
 
    /**
     * Sets the number of 5 peso coins and recalculates the total.
     *
     * @param five the new count of 5 peso coins
     */
    public void setFive(int five) {
        this.five = five;
        calculateTotal();
    }
 
    /**
     * Sets the number of 1 piso coins and recalculates the total.
     *
     * @param one the new count of 1 piso coins
     */
    public void setOne(int one) {
        this.one = one;
        calculateTotal();
    }
 
    /**
     * Calculates and updates the total monetary value based on the current
     * counts of each denomination.
     *
     * @return the recalculated total value
     */
    public float calculateTotal() {
        total = (float) (thousand * 1000 + fiveH * 500
                + hund * 100 + fifty * 50
                + twenty * 20 + ten * 10
                + five * 5 + one);
 
        return total;
    }
}
