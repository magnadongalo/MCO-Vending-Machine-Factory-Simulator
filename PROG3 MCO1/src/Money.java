public class Money {
    private int thousand;
    private int fiveH;
    private int hund;
    private int fifty;
    private int twenty;
    private int ten;
    private int five;
    private int one;
    private float total;

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

    public int getThousand() {
        return this.thousand;
    }

    public int getFiveH() {
        return this.fiveH;
    }

    public int getHund() {
        return this.hund;
    }

    public int getFifty() {
        return this.fifty;
    }

    public int getTwenty() {
        return this.twenty;
    }

    public int getTen() {
        return this.ten;
    }

    public int getFive() {
        return this.five;
    }

    public int getOne() {
        return this.one;
    }

    public float getTotal() {
        return this.total;
    }

    public void setThousand(int thousand) {
        this.thousand = thousand;
        calculateTotal();
    }

    public void setFiveH(int fiveH) {
        this.fiveH = fiveH;
        calculateTotal();
    }

    public void setHund(int hund) {
        this.hund = hund;
        calculateTotal();
    }

    public void setFifty(int fifty) {
        this.fifty = fifty;
        calculateTotal();
    }

    public void setTwenty(int twenty) {
        this.twenty = twenty;
        calculateTotal();
    }

    public void setTen(int ten) {
        this.ten = ten;
        calculateTotal();
    }

    public void setFive(int five) {
        this.five = five;
        calculateTotal();
    }

    public void setOne(int one) {
        this.one = one;
        calculateTotal();
    }

    public float calculateTotal() {
        this.total = (float) (thousand * 1000 + fiveH * 500
                + hund * 100 + fifty * 50
                + twenty * 20 + ten * 10
                + five * 5 + one);

        return this.total;
    }
}
