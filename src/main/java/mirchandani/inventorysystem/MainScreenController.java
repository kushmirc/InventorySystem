package mirchandani.inventorysystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.ArrayList;


/** Class MainScreenController controls MainScreen.fxml.  It's the first Scene that opens
 * when the program is initialized. It displays table views of all parts and all products,
 * and allows a user to search for, add, modify, or delete parts or products.
 * @author Kush Mirchandani*/
public class MainScreenController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TableColumn<Part, Integer> partIDCol;

    @FXML
    private TableColumn<Part, Integer> partInventoryLevelCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Double> partPriceCostPerUnitCol;

    @FXML
    private TextField partsSearchTxt;

    @FXML
    private TableView<Part> partsTableView;

    @FXML
    private TableColumn<Product, Integer> productsInventoryLevelCol;

    @FXML
    private TableColumn<Product, Double> productsPriceCostPerUnitCol;

    @FXML
    private TableColumn<Product, Integer> productsProductIDCol;

    @FXML
    private TableColumn<Product, String> productsProductNameCol;

    @FXML
    private TextField productsSearchTxt;

    @FXML
    private TableView<Product> productsTableView;

    @FXML
    private Label partsExLbl;

    @FXML
    private Label productsExLbl;

    @FXML
    private Label partSearchExLbl;

    @FXML
    private Label productSearchExLbl;

    /** Add button clicked in Parts pane.
     * Opens the Add Part window when the Add button is clicked on the Main Screen's Parts pane.
     * @param event the item on the GUI that triggers the action */
    @FXML
    void onActionAddPart(ActionEvent event) throws IOException {
        //get the stage from the event's source widget
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Add button clicked in Products pane.
     * Opens the Add Product window when the Add button is clicked on the Main Screen's Products pane.
     * @param event the item on the GUI that triggers the action */
    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Delete button clicked in Parts pane.
     * Deletes the selected part when the Delete button is clicked on the Main Screen's Parts pane.
     * @param event the item on the GUI that triggers the action */
    @FXML
    void onActionDeletePart(ActionEvent event) {

        partsExLbl.setText("");

        if(partsTableView.getSelectionModel().getSelectedItem() == null) {
            partsExLbl.setText("Please select a part");
            return;}

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Parts");
        alert.setHeaderText("Delete");
        alert.setContentText("Are you sure you want to delete this part?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Inventory.deletePart(partsTableView.getSelectionModel().getSelectedItem());
            partsExLbl.setText("Part deleted");
        } else {
            partsExLbl.setText("Part not deleted");
        }

    }

    /** Delete button clicked in Products pane.
     * Deletes the selected product when the Delete button is clicked on the Main Screen's Products pane.
     * @param event the item on the GUI that triggers the action */
    @FXML
    void onActionDeleteProduct(ActionEvent event) {

        productsExLbl.setText("");

        if(productsTableView.getSelectionModel().getSelectedItem() == null) {
            productsExLbl.setText("Please select a product");
            return;}

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Products");
        alert.setHeaderText("Delete");
        alert.setContentText("Are you sure you want to delete this product?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if(productsTableView.getSelectionModel().getSelectedItem().getAllAssociatedParts().size() >0 ) {
                productsExLbl.setText("This product has parts");
                return;}
            Inventory.deleteProduct(productsTableView.getSelectionModel().getSelectedItem());
            productsExLbl.setText("Product deleted");
        } else {
            productsExLbl.setText("Product not deleted");
        }
    }

    /** Exit button clicked.
     * Closes the stage and exits the program.
     * @param event the item on the GUI that triggers the action */
    @FXML
    void onActionExit(ActionEvent event) {
        System.exit(0);
    }

    /** Modify button clicked in Parts pane.
     * Opens the Modify Part window when the Modify button is clicked on the Main Screen's Parts pane.
     * @param event the item on the GUI that triggers the action */
    @FXML
    void onActionModifyPart(ActionEvent event) throws IOException {

        if(partsTableView.getSelectionModel().getSelectedItem() == null) {
            partsExLbl.setText("Please select a part");
            return;}

        //Part selectedPart = partsTableView.getSelectionModel().getSelectedItem();
        Part loadedPart = partsTableView.getSelectionModel().getSelectedItem();

        ObservableList<Part> allParts = Inventory.getAllParts();
        int partId = allParts.indexOf(loadedPart);

        //ModifyPartController.loadPart(selectedPart);
        Inventory.updatePart(partId, loadedPart);

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("ModifyPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** Modify button clicked in Products pane.
     * Opens the Modify Product window when the Modify button is clicked on the Main Screen's Products pane.
     * @param event the item on the GUI that triggers the action */
    @FXML
    void onActionModifyProduct(ActionEvent event) throws IOException {

        if(productsTableView.getSelectionModel().getSelectedItem() == null) {
            productsExLbl.setText("Please select a product");
            return;}

        //Product selectedProduct = productsTableView.getSelectionModel().getSelectedItem();
        Product loadedProduct = productsTableView.getSelectionModel().getSelectedItem();

        ObservableList<Product> allProducts = Inventory.getAllProducts();
        int productId = allProducts.indexOf(loadedProduct);

        //ModifyProductController.loadProduct(selectedProduct);
        Inventory.updateProduct(productId, loadedProduct);

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("ModifyProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Searches for matching parts.
     * Searches through allParts and displays those parts whose ID or Name contains the character(s) keyed in by the user to the search
     * field in the Parts pane. Displays an error message if the character(s) aren't contained in any parts.
     * @param event the item on the GUI that triggers the action */
    @FXML
    void onActionLoopUpPart(KeyEvent event) {
        partSearchExLbl.setText("");
        ObservableList<Part> searchedParts = FXCollections.observableArrayList(); //= Inventory.lookupPart(partsSearchTxt.getText());


        try {
            int searchedPartId = Integer.parseInt(partsSearchTxt.getText());
            if(!(Inventory.lookupPart(searchedPartId) == null)){
                Part part = Inventory.lookupPart(searchedPartId);
                searchedParts.add(part);}
        }
        catch(NumberFormatException err){
            searchedParts = Inventory.lookupPart(partsSearchTxt.getText());
        }

        partsTableView.setItems(searchedParts);

        if (searchedParts.size() == 0) {partSearchExLbl.setText("Part not found");}

    }

    /** Searches for matching products.
     * Searches through allProducts and displays those products whose ID or Name contains the character(s) keyed in by the user to the search
     * field in the Products pane. Displays an error message if the character(s) aren't contained in any products.
     * @param event the item on the GUI that triggers the action */
    @FXML
    void onActionLoopUpProduct(KeyEvent event) {
        productSearchExLbl.setText("");
        ObservableList<Product> searchedProducts = FXCollections.observableArrayList();

        try {
            int searchedProductId = Integer.parseInt(productsSearchTxt.getText());
            if(!(Inventory.lookupProduct(searchedProductId) == null)) {
                Product product = Inventory.lookupProduct(searchedProductId);
                searchedProducts.add(product);}
        }
        catch(NumberFormatException err){
            searchedProducts = Inventory.lookupProduct(productsSearchTxt.getText());
        }

        productsTableView.setItems(searchedProducts);
        if (searchedProducts.size() == 0) {productSearchExLbl.setText("Product not found");}
    }


    /** This is the initialize method.
     * This is the first method that gets called when the scene is set to the Main Screen.
     * @param url the location of MainScreen.fxml
     * @param resourceBundle the name of MainScreen.fxml*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        partsTableView.setItems(Inventory.getAllParts());
        //partsTableView.setItems(filter("x"));

        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCostPerUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));


        productsTableView.setItems(Inventory.getAllProducts());

        productsProductIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productsProductNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productsInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productsPriceCostPerUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        /*if(search(3))
            System.out.println("Match!");
        else
            System.out.println("No match!");

        if (update(3, new InHouse(3, "spinner", 18.00, 7, 2, 20, 4)))
            System.out.println("Update successful!");
        else
            System.out.println("Update failed!");

        if(delete(3))
            System.out.println("Deleted!");
        else
            System.out.println("No match!");*/

        //partsTableView.getSelectionModel().select(selectPart(2));

        //Loading part data
        //Part selectedPart = (Part) partsTableView.getSelectionModel().getSelectedItem();

        /*int myint = 10;
        String mystring = String.valueOf(myint);
        System.out.println(mystring);*/



    }


}