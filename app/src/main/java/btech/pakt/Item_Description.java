package btech.pakt;

/**
 * Created by Brennan on 9/7/2015.
 */
public class Item_Description {

    String title;
    String description;
    int pricePerDay;
    int safeDeposit;

    public Item_Description(String title,
                            String description,
                            int pricePerDay,
                            int safeDeposit

                            ){

        this.title = title;
        this.description = description;
        this.pricePerDay = pricePerDay;
        this.safeDeposit = safeDeposit;

    }


    public int getSafeDeposit() {
        return safeDeposit;
    }

    public void setSafeDeposit(int safeDeposit) {
        this.safeDeposit = safeDeposit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
}
