package model;

/** Class Outsourced is a child of the Parts class.
 * It is used to create instances of Outsourced parts, and contains the companyName instance field,
 * which is unique to this class.
 * @author Kush Mirchandani*/
public class Outsourced extends Part{

    /** declares a companyName variable to be used for Outsourced parts*/
    private String companyName;


    /** This is the Outsourced constructor method.
     * This method is used to create instances of Outsourced parts.
     * @param id the id of the new Outsourced part
     * @param name the name of the new Outsourced part
     * @param price the price of the new Outsourced part
     * @param stock the stock of the new Outsourced part
     * @param min the minimum stock of the new Outsourced part
     * @param max the maximum stock of the new Outsourced part
     * @param companyName the company name of the new Outsourced part*/
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /** @return the companyName */
    public String getCompanyName() {
        return companyName;
    }

    /** @param companyName  the company name to set */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
