package btech.pakt;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brennan on 9/7/2015.
 */
public class Item_Description_Class implements Serializable {

    String title;
    String description;
    int pricePerDay;
    int safeDeposit;
    ArrayList images = new ArrayList<>();

    public Item_Description_Class(String title,
                                  String description,
                                  int pricePerDay,
                                  int safeDeposit,
                                  ArrayList images
    ){

        this.title = title;
        this.description = description;
        this.pricePerDay = pricePerDay;
        this.safeDeposit = safeDeposit;
        this.images = images;

    }

    public Item_Description_Class(String title,
                                  ArrayList images
    ){

        this.title = title;
        this.images = images;

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

    public ArrayList getImage() {
        return images;
    }

    public void setImage(ArrayList image) {
        this.images = image;
    }

}
