package mirchandani.inventorysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {

    @FXML
    private TableColumn<?, ?> partIDCol;

    @FXML
    private TableColumn<?, ?> partInventoryLevelCol;

    @FXML
    private TableColumn<?, ?> partNameCol;

    @FXML
    private TableColumn<?, ?> partPriceCostPerUnitCol;

    @FXML
    private TextField partsSearchTxt;

    @FXML
    private TableView<?> partsTableView;

    @FXML
    private TableColumn<?, ?> productsInventoryLevelCol;

    @FXML
    private TableColumn<?, ?> productsPriceCostPerUnitCol;

    @FXML
    private TableColumn<?, ?> productsProductIDCol;

    @FXML
    private TableColumn<?, ?> productsProductNameCol;

    @FXML
    private TextField productsSearchTxt;

    @FXML
    private TableView<?> productsTableView;

    @FXML
    void onActionAddPart(ActionEvent event) {
        System.out.println("Add Part button clicked!");
    }

    @FXML
    void onActionAddProduct(ActionEvent event) {
        System.out.println("Add Product button clicked!");
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
        System.out.println("Exit button clicked!");
    }

    @FXML
    void onActionModifyPart(ActionEvent event) {
        System.out.println("Modify Part button clicked!");
    }

    @FXML
    void onActionModifyProduct(ActionEvent event) {
        System.out.println("Modify Product button clicked!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("System initialized");
    }


}