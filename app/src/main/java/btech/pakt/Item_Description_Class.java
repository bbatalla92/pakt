package btech.pakt;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by Brennan on 9/7/2015.
 */
public class Item_Description_Class implements Serializable {

    String title;
    String description;
    int pricePerDay;
    int safeDeposit;
    int image;

    public Item_Description_Class(String title,
                                  String description,
                                  int pricePerDay,
                                  int safeDeposit,
                                  int image
    ){

        this.title = title;
        this.description = description;
        this.pricePerDay = pricePerDay;
        this.safeDeposit = safeDeposit;
        this.image = image;

    }

    public Item_Description_Class(String title,
                                  int image
    ){

        this.title = title;
        this.image = image;

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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
