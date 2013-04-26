package in.jugchennai.javamoney.jpa.tools;

public class Cube {

    private String date;
    private String rate;
    private String currency;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "cube [date=" + date + ", rate=" + rate + ", currency="
                + currency + "]";
    }
}
