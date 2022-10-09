package model;


/** Class Part is abstract and parent to InHouse and Outsourced.
 * It is used to declare the instance variables used by the child classes, InHouse and Outsourced.
 * It includes getters and setters for each of the variables.
 * @author Kush Mirchandani*/
public abstract class Part {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /** The constructor method for the Part class.
     * This constructor is not used to instantiate parts because this class is abstract, rather,
     * the constructor is called by the child classes as super when instantiating InHouse or Outsourced parts.
     * @param id the id of the new part
     * @param name the name of the new  part
     * @param price the price of the new part
     * @param stock the stock of the new part
     * @param min the minimum stock of the new part
     * @param max the maximum stock of the new part */
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

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

}