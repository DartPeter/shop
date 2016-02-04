/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author Peter
 */
public class Good {
    private int id;
    private int makerId;
    private String maker;
    private int catId;
    private String model;
    private double price;
    private boolean isAvailable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMakerId() {
        return makerId;
    }

    public void setMakerId(int makerId) {
        this.makerId = makerId;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Good() {
    }

    public Good(int id, int makerId, String maker, int catId, String model, double price, boolean isAvailable) {
        this.id = id;
        this.makerId = makerId;
        this.maker = maker;
        this.catId = catId;
        this.model = model;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return maker + " " + model + " " + price + " грн. " + (isAvailable?'+':'-');
    }
}
