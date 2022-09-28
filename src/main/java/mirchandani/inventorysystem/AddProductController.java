package mirchandani.inventorysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {

    Stage stage;
    Parent scene;
    @FXML
    private TableColumn<?, ?> inventoryLevelCol1;

    @FXML
    private TableColumn<?, ?> inventoryLevelCol2;

    @FXML
    private TableColumn<?, ?> partIDCol1;

    @FXML
    private TableView<?> partIDTableView1;

    @FXML
    private TableView<?> partIDTableView2;

    @FXML
    private TableColumn<?, ?> partNameCol1;

    @FXML
    private TableColumn<?, ?> partNameCol2;

    @FXML
    private TableColumn<?, ?> priceCostPerUnitCol1;

    @FXML
    private TableColumn<?, ?> priceCostPerUnitCol2;

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
    void onActionAddPart(ActionEvent event) {

    }

    @FXML
    void onActionDisplayMainScreen(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionRemovePart(ActionEvent event) {

    }

    @FXML
    void onActionSaveProduct(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("System initialized");
    }


}