package mirchandani.inventorysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddPartController implements Initializable {

    @FXML
    private TextField partIDTxt;

    @FXML
    private RadioButton partInHouseRBtn;

    @FXML
    private TextField partInvTxt;

    @FXML
    private TextField partMachineIDTxt;

    @FXML
    private TextField partMaxTxt;

    @FXML
    private TextField partMinTxt;

    @FXML
    private TextField partNameTxt;

    @FXML
    private RadioButton partOutsourcedRBtn;

    @FXML
    private TextField partPriceCostTxt;

    @FXML
    void onActionDisplayMainScreen(ActionEvent event) {

    }

    @FXML
    void onActionSavePart(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("System initialized");
    }


}