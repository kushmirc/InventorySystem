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



/**
 *
 * @author Kush Mirchandani
 */
public class AddProductController implements Initializable {

    Stage stage;
    Parent scene;

    int id;

    Product newProduct;

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
    void onActionLookUpPart(KeyEvent event) {
        partSearchExLbl.setText("");
        ObservableList<Part> searchedParts = FXCollections.observableArrayList(); //= Inventory.lookupPart(partsSearchTxt.getText());

        try {
            int searchedPartId = Integer.parseInt(productSearchTxt.getText());
            if(!(Inventory.lookupPart(searchedPartId) == null)) {
                Part part = Inventory.lookupPart(searchedPartId);
                searchedParts.add(part); }
        }
        catch(NumberFormatException err){
            searchedParts = Inventory.lookupPart(productSearchTxt.getText());
        }

        if (searchedParts.size() == 0) {partSearchExLbl.setText("Part not found");}

      /*  if(searchedParts.size() == 0) {
            int searchedPartId = Integer.parseInt(partsSearchTxt.getText());
            Part part = Inventory.lookupPart(searchedPartId);
            searchedParts.add(part);
        }*/

        partsTableView1.setItems(searchedParts);
    }

    @FXML
    void onActionAddPart(ActionEvent event) {
    addToAssociatedPartsTable((Part) partsTableView1.getSelectionModel().getSelectedItem());
    }
    @FXML
    void onActionRemoveAssociatedPart(ActionEvent event) {

        associatedPartExLbl.setText("");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Associated parts");
        alert.setHeaderText("Remove");
        alert.setContentText("Are you sure you want to remove this associated part?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            newProduct.deleteAssociatedPart(partsTableView2.getSelectionModel().getSelectedItem());
            associatedPartExLbl.setText("Associated part removed");
        } else {
            associatedPartExLbl.setText("Associated part not removed");
        }
    }

    @FXML
    void onActionDisplayMainScreen(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionSaveProduct(ActionEvent event) throws IOException {

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


        //int id = Integer.parseInt(productIDTxt.getText());
        /*String name = productNameTxt.getText();
        int stock = Integer.parseInt(productInvTxt.getText());
        double price = Double.parseDouble(productPriceTxt.getText());
        int max = Integer.parseInt(productMaxTxt.getText());
        int min = Integer.parseInt(productMinTxt.getText());*/

        //If there are no errors, proceed with adding the product   :
        newProduct.setId(id);
        newProduct.setName(productNameTxt.getText());
        newProduct.setStock(Integer.parseInt(productInvTxt.getText()));
        newProduct.setPrice(Double.parseDouble(productPriceTxt.getText()));
        newProduct.setMax(Integer.parseInt(productMaxTxt.getText()));
        newProduct.setMin(Integer.parseInt(productMinTxt.getText()));

        //Product newProduct = new Product(id, name, price, stock, min, max);
        //newProduct.setAssociatedParts(newProduct.getAssociatedParts());
        Inventory.addProduct(newProduct);

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();


    }

    public void addToAssociatedPartsTable(Part part) {
        part = partsTableView1.getSelectionModel().getSelectedItem();
        if (part == null)
            System.out.println("Please select a part!");
        else
            newProduct.addAssociatedPart(part);

        partsTableView2.setItems(newProduct.getAllAssociatedParts());
        partIDCol2.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol2.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryLevelCol2.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceCostPerUnitCol2.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    public void deleteFromAssociatedPartsTable(Part selectedAssociatedPart){
        selectedAssociatedPart = (Part) partsTableView2.getSelectionModel().getSelectedItem();
        if (selectedAssociatedPart == null)
            System.out.println("Please select a part!");
        else
            newProduct.deleteAssociatedPart(selectedAssociatedPart);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        id = Inventory.getAllProducts().size() + 1000;

        partsTableView1.setItems(Inventory.getAllParts());

        partIDCol1.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol1.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryLevelCol1.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceCostPerUnitCol1.setCellValueFactory(new PropertyValueFactory<>("price"));


        /*partsTableView2.setItems(Product.getAllAssociatedParts());

        partIDCol2.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol2.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryLevelCol2.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceCostPerUnitCol2.setCellValueFactory(new PropertyValueFactory<>("price"));*/

        newProduct = new Product(1, "", 1, 1, 1, 1);

    }






}