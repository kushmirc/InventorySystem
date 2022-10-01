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
    //Declare and initialize filtered observable list for parts:
   /* private static ObservableList<Part> filteredParts = FXCollections.observableArrayList();
    public static ObservableList<Part> getAllFilteredParts() {
        return filteredParts;
    }*/

    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> namedParts = FXCollections.observableArrayList();

        for(Part part : allParts)
            if(part.getName().contains(partName)){
                namedParts.add(part);
            }
        return namedParts;

    }

    public static Part lookupPart(int partId) {

        for(Part part : allParts)
            if(part.getId() == partId){
                return part;}
        return null;
    }

    //Declare and initialize observable list for products:
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    //Create the addProduct method to add product objects to the product observable list:
    public static void addProduct(Product newProduct) {allProducts.add(newProduct);}
    //Create getAllProducts method to populate all products into the observable list of products:
    public static ObservableList<Product> getAllProducts() { return allProducts;}

    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> namedProducts = FXCollections.observableArrayList();

        for(Product product : allProducts)
            if(product.getName().contains(productName)){
                namedProducts.add(product);
            }
        return namedProducts;

    }

    public static Product lookupProduct(int productId) {

        for(Product product : allProducts)
            if(product.getId() == productId){
                return product;}
        return null;
    }

}

