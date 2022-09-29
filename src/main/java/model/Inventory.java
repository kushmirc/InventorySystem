package model;




import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Kush Mirchandani
 */
public class Inventory {
//Declare and initialize observable list for parts:
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
//Create the addPart method to add part objects to the part observable list:
    public static void addPart(Part newPart) {allParts.add(newPart);}
    //Create getAllParts method to populate all parts into the observable list of parts:
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    //Declare and initialize observable list for products:
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    //Create the addProduct method to add product objects to the product observable list:
    public static void addProduct(Product newProduct) {allProducts.add(newProduct);}
    //Create getAllProducts method to populate all products into the observable list of products:
    public static ObservableList<Product> getAllProducts() { return allProducts;}
}
