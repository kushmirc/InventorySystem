package mirchandani.inventorysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    void onActionAddPart(ActionEvent event) throws IOException {
        //get the stage from the event's source widget
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionDeletePart(ActionEvent event) {
        System.out.println("Delete Part button clicked!");
    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) {
        System.out.println("Delete Product button clicked!");
    }

    @FXML
    void onActionExit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onActionModifyPart(ActionEvent event) {
        System.out.println("Modify Part button clicked!");
    }

    @FXML
    void onActionModifyProduct(ActionEvent event) {
        System.out.println("Modify Product button clicked!");
    }


    //public boolean search(int id) {}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        partsTableView.setItems(Inventory.getAllParts());

        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCostPerUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));


        productsTableView.setItems(Inventory.getAllProducts());

        productsProductIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productsProductNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productsInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productsPriceCostPerUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }


}