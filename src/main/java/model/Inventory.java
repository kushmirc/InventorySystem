package model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Class Inventory is used to perform a number of actions for managing parts and products.
 * It contains observable lists of all part and product objects created by users. It contains
 * methods for adding parts and products to the lists, for searching (lookup) for parts and products
 * included in the lists, for modifying (update) part and product fields, for deleting parts and procuts
 * from the lists, and for retrieving (getting) all parts and products in the lists.
 * @author Kush Mirchandani*/
public class Inventory {

    /** declares a Part variable so parts can be updated */
    public static Part loadedPart;

    /** declares a partId variable so parts can be searched for (lookup) and so parts can be modified (update) */
    public static int partId;

    /** declares a Product variable so products can be updated */
    public static Product loadedProduct;

    /** declares a productId variable so products can be searched for (lookup) and so products can be modified (update) */
    public static int productId;

    //int index;


    /** declare and initialize observable list for parts */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    /** This method adds part objects to the allParts observable list
     * @param newPart the part to add */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /** This method iterates through every part in the all parts list and adds parts whose name fields contain the character(s)
     * input by the user to the namedParts list.
     * @param partName the character(s) to search for
     * @return the parts containing the character(s) searched for */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> namedParts = FXCollections.observableArrayList();

        for (Part part : allParts) {
            if (part.getName().contains(partName)) {
                namedParts.add(part);
            }
        }
        return namedParts;
    }

    /** This method iterates through every part in the all parts list and adds parts whose ID fields contain the integer
     * input by the user to the namedParts list.
     * @param partId the integer to search for
     * @return the parts containing the integer searched for */
    public static Part lookupPart(int partId) {

        for (Part part : allParts)
            if (part.getId() == partId) {
                return part;
            }
        return null;
    }

    /** This method takes in a part and its index and assigns them to public static varialbes defined in this class.
     * @param index the index of the part passed in
     * @param selectedPart the part passed in */
    public static void updatePart(int index, Part selectedPart) {
        partId = index;
        loadedPart = selectedPart;
    }

    /** This method removes a part object the allParts observable list
     * @param selectedPart the part to delete (remove)
     * @return removes the selected part */
    public static boolean deletePart(Part selectedPart) {
        if (selectedPart == null)
            return false;
        else
            return getAllParts().remove(selectedPart);
    }


    /** This method gets all part objects that have been created.
     * @return the observable list of all parts */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }


    /** declare and initialize observable list for products */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /** This method adds product objects to the allParts observable list
     * @param newProduct the product to add */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /** This method iterates through every product in the all products list and adds products whose name fields contain the character(s)
     * input by the user to the namedProducts list.
     * @param productName the character(s) to search for
     * @return the products containing the character(s) searched for */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> namedProducts = FXCollections.observableArrayList();

        for (Product product : allProducts) {
            if (product.getName().contains(productName)) {
                namedProducts.add(product);
            }
        }
        return namedProducts;
    }

    /** This method iterates through every product in the all product list and adds products whose ID fields contain the integer
     * input by the user to the namedProducts list.
     * @param productId the integer to search for
     * @return the product containing the integer searched for */
    public static Product lookupProduct(int productId){

        for (Product product : allProducts)
            if (product.getId() == productId) {
                return product;}
        return null;
    }

    /** This method takes in a product and its index and assigns them to public static varialbes defined in this class.
     * @param index the index of the product passed in
     * @param selectedProduct the product passed in */
    public static void updateProduct(int index, Product selectedProduct) {
        productId = index;
        loadedProduct = selectedProduct;
    }

    /** This method removes a product object the allProducts observable list
     * @param selectedProduct the product to delete (remove)
     * @return removes the selected product */
    public static boolean deleteProduct(Product selectedProduct) {
        if (selectedProduct == null)
            return false;
        else
            return getAllProducts().remove(selectedProduct);
    }

    /** This method gets all product objects that have been created.
     * @return the observable list of all products */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    }

