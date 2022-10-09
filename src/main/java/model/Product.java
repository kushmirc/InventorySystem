package model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import mirchandani.inventorysystem.AddPartController;

/** Class Product is used to create product instances.
 * It contains getters and setters for each instance variable, as well as methods for
 * adding, deleting, and getting associated parts for a product.
 * @author Kush Mirchandani*/
public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();


    /** This is the Product constructor method.
     * This method is used to create Product instances.
     * @param id the id of the new Product
     * @param name the name of the new Product
     * @param price the price of the new Product
     * @param stock the stock of the new Product
     * @param min the minimum stock of the new Product
     * @param max the maximum stock of the new Product */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    //Getters and setters:
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }


    /**
     * @param associatedParts the associated parts to set
     */
    public void setAssociatedParts(ObservableList<Part> associatedParts) {
        this.associatedParts = associatedParts;
    }

    /**
     * @param part the part to set
     */
    public void addAssociatedPart(Part part) {
        this.associatedParts.add(part);
    }

    /**
     * @param selectedAssociatedPart the selected associated part to remove
     * @return the removal action
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        if (selectedAssociatedPart == null)
            return false;
        else
            return this.associatedParts.remove(selectedAssociatedPart);
    }

    /**
     * @return the observable list of parts
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

}
