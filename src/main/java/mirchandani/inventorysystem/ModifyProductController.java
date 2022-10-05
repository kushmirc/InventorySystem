package mirchandani.inventorysystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 *
 * @author Kush Mirchandani
 */
public class ModifyProductController implements Initializable {

    Stage stage;
    Parent scene;

    //int id;

    Product newProduct;

    private static Product loadedProduct;

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
    void onActionLookUpPart(KeyEvent event) {
        ObservableList<Part> searchedParts = FXCollections.observableArrayList(); //= Inventory.lookupPart(partsSearchTxt.getText());

        try {
            int searchedPartId = Integer.parseInt(productSearchTxt.getText());
            Part part = Inventory.lookupPart(searchedPartId);
            searchedParts.add(part);
        }
        catch(NumberFormatException err){
            searchedParts = Inventory.lookupPart(productSearchTxt.getText());

        }
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
    loadedProduct.deleteAssociatedPart(partsTableView2.getSelectionModel().getSelectedItem());
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

        //newProduct.setId(id);
        loadedProduct.setName(productNameTxt.getText());
        loadedProduct.setStock(Integer.parseInt(productInvTxt.getText()));
        loadedProduct.setPrice(Double.parseDouble(productPriceTxt.getText()));
        loadedProduct.setMax(Integer.parseInt(productMaxTxt.getText()));
        loadedProduct.setMin(Integer.parseInt(productMinTxt.getText()));
        loadedProduct.setAssociatedParts(loadedProduct.getAllAssociatedParts());

        //Product newProduct = new Product(id, name, price, stock, min, max);
        //newProduct.setAssociatedParts(newProduct.getAssociatedParts());
        //Inventory.addProduct(newProduct);

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
            loadedProduct.addAssociatedPart(part);

        partsTableView2.setItems(loadedProduct.getAllAssociatedParts());
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
            loadedProduct.deleteAssociatedPart(selectedAssociatedPart);
    }

    public static void loadProduct(Product product){
        loadedProduct = product;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //id = Inventory.getAllProducts().size() + 1000;

        productIDTxt.setText(String.valueOf(loadedProduct.getId()));
        productNameTxt.setText(String.valueOf(loadedProduct.getName()));
        productInvTxt.setText(String.valueOf(loadedProduct.getStock()));
        productPriceTxt.setText(String.valueOf(loadedProduct.getPrice()));
        productMaxTxt.setText(String.valueOf(loadedProduct.getMax()));
        productMinTxt.setText(String.valueOf(loadedProduct.getMin()));


        partsTableView1.setItems(Inventory.getAllParts());

        partIDCol1.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol1.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryLevelCol1.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceCostPerUnitCol1.setCellValueFactory(new PropertyValueFactory<>("price"));


        partsTableView2.setItems(loadedProduct.getAllAssociatedParts());

        partIDCol2.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol2.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryLevelCol2.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceCostPerUnitCol2.setCellValueFactory(new PropertyValueFactory<>("price"));



        //newProduct = new Product(1, "", 1, 1, 1, 1);

    }






}