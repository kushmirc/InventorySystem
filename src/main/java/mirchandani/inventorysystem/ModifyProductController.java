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
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


/** Class ModifyProductController controls ModifyProduct.fxml. It allows users to modify the product name, inventory level,
 * price, and max and min inventory allowed. The user can add associated parts to the product using the part, and
 * associated parts tables and buttons. The product ID cannot be changed by the user.
 * @author Kush Mirchandani*/
public class ModifyProductController implements Initializable {

    /** declares a stage variable */
    Stage stage;

    /** declares a scene variable */
    Parent scene;


    @FXML
    private TableColumn<Part, Integer> inventoryLevelCol1;

    @FXML
    private TableColumn<Part, Integer> inventoryLevelCol2;

    @FXML
    private TableColumn<Part, Integer> partIDCol1;

    @FXML
    private TableColumn<Part, Integer> partIDCol2;

    @FXML
    private TableView<Part> partsTableView1;

    @FXML
    private TableView<Part> partsTableView2;

    @FXML
    private TableColumn<Part, String> partNameCol1;

    @FXML
    private TableColumn<Part, String> partNameCol2;

    @FXML
    private TableColumn<Part, Double> priceCostPerUnitCol1;

    @FXML
    private TableColumn<Part, Double> priceCostPerUnitCol2;

    @FXML
    private TextField productIDTxt;

    @FXML
    private TextField productInvTxt;

    @FXML
    private TextField productMaxTxt;

    @FXML
    private TextField productMinTxt;

    @FXML
    private TextField productNameTxt;

    @FXML
    private TextField productPriceTxt;

    @FXML
    private TextField productSearchTxt;

    @FXML
    private Label partSearchExLbl;

    @FXML
    private Label associatedPartExLbl;

    @FXML
    private Label productNameExLbl;

    @FXML
    private Label productInvExLbl;

    @FXML
    private Label productPriceCostExLbl;

    @FXML
    private Label productMaxExLbl;

    @FXML
    private Label productMinExLbl;

    @FXML
    private Label partExLbl;

    /** Searches for matching parts.
     * Searches through allParts and displays those parts whose ID or Name contains the character(s) keyed in by the user to the search
     * field in the Parts pane. Displays an error message if the character(s) aren't contained in any parts.
     * @param event the item on the GUI that triggers the action */
    @FXML
    public void onActionLookUpPart(KeyEvent event) {
        partSearchExLbl.setText("");
        ObservableList<Part> searchedParts = FXCollections.observableArrayList(); //= Inventory.lookupPart(partsSearchTxt.getText());

        try {
            int searchedPartId = Integer.parseInt(productSearchTxt.getText());
            if(!(Inventory.lookupPart(searchedPartId) == null)){
            Part part = Inventory.lookupPart(searchedPartId);
            searchedParts.add(part); }
        }
        catch(NumberFormatException err){
            searchedParts = Inventory.lookupPart(productSearchTxt.getText());
        }

        if (searchedParts.size() == 0) {partSearchExLbl.setText("Part not found");}

        partsTableView1.setItems(searchedParts);
    }

    /** Add button clicked.
     * Adds the part selected in the top parts pane to the lower associated parts pane by calling the addToAssociatedPartsTable method.
     * If no part is selected, an error message is displayed.
     * @param event the item on the GUI that triggers the action */
    @FXML
    public void onActionAddPart(ActionEvent event) {
        partExLbl.setText("");

        if(partsTableView1.getSelectionModel().getSelectedItem() == null) {
            partExLbl.setText("Please select a part");
        }
    addToAssociatedPartsTable( partsTableView1.getSelectionModel().getSelectedItem());
    }

    /** Remove Associated Part button clicked.
     * Removes the part selected in the lower associated parts pane by calling the deleteAssociatedPart method in the Product class.
     * If no part is selected, an error message is displayed. A confirmation dialogue is displayed when the button is clicked.
     * @param event the item on the GUI that triggers the action */
    @FXML
    public void onActionRemoveAssociatedPart(ActionEvent event) {

        associatedPartExLbl.setText("");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Associated parts");
        alert.setHeaderText("Remove");
        alert.setContentText("Are you sure you want to remove this associated part?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Inventory.loadedProduct.deleteAssociatedPart(partsTableView2.getSelectionModel().getSelectedItem());
            associatedPartExLbl.setText("Associated part removed");
        } else {
            associatedPartExLbl.setText("Associated part not removed");
        }

    }

    /** Cancel button clicked.
     * Exits the Add Product screen and opens the Main Screen.
     * @param event the item on the GUI that triggers the action */
    @FXML
    public void onActionDisplayMainScreen(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Save button clicked.
     * Saves the modified product information for the product selected in the Main Screen.
     * Closes the Modify Product screen and opens the Main Screen when clicked. Displays error messages and stops running if any
     * of the input fields are blank when clicked. Displays error messages and stops running if min is greater than max, or
     * if inventory isn't between min and max.
     * @param event the item on the GUI that triggers the action */
    @FXML
    public void onActionSaveProduct(ActionEvent event) throws IOException {

        //Clear exception message fields
        productNameExLbl.setText("");
        productInvExLbl.setText("");
        productPriceCostExLbl.setText("");
        productMaxExLbl.setText("");
        productMinExLbl.setText("");

        //Check all input fields for exceptions and print applicable messages:
        if (productNameTxt.getText() == "") {
            productNameExLbl.setText("No data in name field");
        }

        try {
            Integer.parseInt(productInvTxt.getText());
        } catch (NumberFormatException e) {
            productInvExLbl.setText("Inventory is not an integer");
        }

        try {
            Double.parseDouble(productPriceTxt.getText());
        } catch (NumberFormatException e) {
            productPriceCostExLbl.setText("Price is not a double");
        }

        try {
            Integer.parseInt(productMaxTxt.getText());
        } catch (NumberFormatException e) {
            productMaxExLbl.setText("Max is not an integer");
        }

        try {
            Integer.parseInt(productMinTxt.getText());
        } catch (NumberFormatException e) {
            productMinExLbl.setText("Min is not an integer");
        }

        //After printing applicable error messages, check all input fields for exceptions again and return; out of the method if any exceptions exist so the program doesn't crash:
        if (productNameTxt.getText() == "") {
            return;
        }

        try {
            Integer.parseInt(productInvTxt.getText());
        } catch (NumberFormatException e) {
            return;
        }

        try {
            Double.parseDouble(productPriceTxt.getText());
        } catch (NumberFormatException e) {
            return;
        }

        try {
            Integer.parseInt(productMaxTxt.getText());
        } catch (NumberFormatException e) {
            return;
        }

        try {
            Integer.parseInt(productMinTxt.getText());
        } catch (NumberFormatException e) {
            return;
        }

        //If there are no errors for blank text fields, then check the logic on the Inv, Max & Min fields:
        if (Integer.parseInt(productMinTxt.getText()) > Integer.parseInt(productMaxTxt.getText())) {
            productMinExLbl.setText("Min must be less than Max");
        } else if (Integer.parseInt(productInvTxt.getText()) > Integer.parseInt(productMaxTxt.getText()) || Integer.parseInt(productInvTxt.getText()) < Integer.parseInt(productMinTxt.getText())) {
            productInvExLbl.setText("Inv must be between Min and Max");
        }

        //After printing applicable error messages, check Inventory fields for logical errors again and return; out of the method if any exceptions exist so the program doesn't crash:
        if (Integer.parseInt(productMinTxt.getText()) > Integer.parseInt(productMaxTxt.getText())) {
            return;
        } else if (Integer.parseInt(productInvTxt.getText()) > Integer.parseInt(productMaxTxt.getText()) || Integer.parseInt(productInvTxt.getText()) < Integer.parseInt(productMinTxt.getText())) {
            return; }

        //If there are no errors, proceed with modifying the product:

        Inventory.loadedProduct.setName(productNameTxt.getText());
        Inventory.loadedProduct.setStock(Integer.parseInt(productInvTxt.getText()));
        Inventory.loadedProduct.setPrice(Double.parseDouble(productPriceTxt.getText()));
        Inventory.loadedProduct.setMax(Integer.parseInt(productMaxTxt.getText()));
        Inventory.loadedProduct.setMin(Integer.parseInt(productMinTxt.getText()));
        Inventory.loadedProduct.setAssociatedParts(Inventory.loadedProduct.getAllAssociatedParts());

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Add a selected part to the Associated Parts (lower) table.
     * Adds the part selected in the top parts pane to the lower associated parts pane by calling the addAssociatedPart method in the Product class.
     * If no part is selected, an error message is displayed.
     * @param part the part to add */
    public void addToAssociatedPartsTable(Part part) {
        part = partsTableView1.getSelectionModel().getSelectedItem();
        if (part == null)
            System.out.println("Please select a part!");
        else
            Inventory.loadedProduct.addAssociatedPart(part);

        partsTableView2.setItems(Inventory.loadedProduct.getAllAssociatedParts());
        partIDCol2.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol2.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryLevelCol2.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceCostPerUnitCol2.setCellValueFactory(new PropertyValueFactory<>("price"));
    }


    /** This is the initialize method.
     * This is the first method that gets called when the scene is set to the Modify Product Screen.
     * Populates the text input fields with the values for the product that was selected on the Main Screen.
     * @param url the location of ModifyProduct.fxml
     * @param resourceBundle the name of ModifyProduct.fxml*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        productIDTxt.setText(String.valueOf(Inventory.loadedProduct.getId()));
        productNameTxt.setText(String.valueOf(Inventory.loadedProduct.getName()));
        productInvTxt.setText(String.valueOf(Inventory.loadedProduct.getStock()));
        productPriceTxt.setText(String.valueOf(Inventory.loadedProduct.getPrice()));
        productMaxTxt.setText(String.valueOf(Inventory.loadedProduct.getMax()));
        productMinTxt.setText(String.valueOf(Inventory.loadedProduct.getMin()));


        partsTableView1.setItems(Inventory.getAllParts());

        partIDCol1.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol1.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryLevelCol1.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceCostPerUnitCol1.setCellValueFactory(new PropertyValueFactory<>("price"));

        partExLbl.setText("");

        partsTableView2.setItems(Inventory.loadedProduct.getAllAssociatedParts());

        partIDCol2.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol2.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryLevelCol2.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceCostPerUnitCol2.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

}