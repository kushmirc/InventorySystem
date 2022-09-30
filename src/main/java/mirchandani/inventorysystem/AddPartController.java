package mirchandani.inventorysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author Kush Mirchandani
 */
public class AddPartController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private RadioButton partInHouseRBtn;
    @FXML
    private RadioButton partOutsourcedRBtn;
    @FXML
    private TextField partIDTxt;
    @FXML
    private TextField partNameTxt;

    @FXML
    private TextField partInvTxt;
    @FXML
    private TextField partPriceCostTxt;

    @FXML
    private TextField partMaxTxt;

    @FXML
    private TextField partMinTxt;

    @FXML
    private TextField partMachineIDTxt;

    @FXML
    void onActionDisplayMainScreen(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionSavePart(ActionEvent event) {

        int id = Integer.parseInt(partIDTxt.getText());
        String name = partNameTxt.getText();
        int stock = Integer.parseInt(partInvTxt.getText());
        double price = Double.parseDouble(partPriceCostTxt.getText());
        int max = Integer.parseInt(partMaxTxt.getText());
        int min = Integer.parseInt(partMinTxt.getText());
        boolean inHouse;
        int machineId = Integer.parseInt(partMachineIDTxt.getText());

        if(partInHouseRBtn.isSelected())
            inHouse = true;
        else
            inHouse = false;

        Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineId));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("System initialized");
    }


}