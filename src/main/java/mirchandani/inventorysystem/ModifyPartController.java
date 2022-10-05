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
import model.Outsourced;
import model.Part;
import model.InHouse;
import model.Outsourced;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author Kush Mirchandani
 */
public class ModifyPartController implements Initializable {

    Stage stage;
    Parent scene;

    private static Part loadedPart;

    @FXML
    private RadioButton partInHouseRBtn;
    @FXML
    private RadioButton partOutsourcedRBtn;
    @FXML
    public Label partMachineIDLbl;
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

    public void onInHouse(ActionEvent actionEvent) {
        partMachineIDLbl.setText("Machine ID");
    }

    public void onOutsourced(ActionEvent actionEvent) {
        partMachineIDLbl.setText("Company Name");
    }

    @FXML
    void onActionDisplayMainScreen(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionSavePart(ActionEvent event) throws IOException {

        loadedPart.setName(partNameTxt.getText());
        loadedPart.setStock(Integer.parseInt(partInvTxt.getText()));
        loadedPart.setPrice(Double.parseDouble(partPriceCostTxt.getText()));
        loadedPart.setMax(Integer.parseInt(partMaxTxt.getText()));
        loadedPart.setMin(Integer.parseInt(partMinTxt.getText()));

        if(partInHouseRBtn.isSelected()) {
            InHouse inh = (InHouse) loadedPart;
            inh.setMachineId(Integer.parseInt(partMachineIDTxt.getText()));
        } else {
            Outsourced op = (Outsourced) loadedPart;
            op.setCompanyName(partMachineIDTxt.getText());
        }

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public static void loadPart(Part part) {
        loadedPart = part;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        if(loadedPart instanceof InHouse) {
            InHouse in = (InHouse) loadedPart;
            partMachineIDTxt.setText(String.valueOf(in.getMachineId()));
            partInHouseRBtn.setSelected(true);
            partInHouseRBtn.setDisable(true);
            partOutsourcedRBtn.setDisable(true);
            partMachineIDLbl.setText("Machine ID");
        }
        else {
            Outsourced op = (Outsourced) loadedPart;
            partMachineIDTxt.setText(op.getCompanyName());
            partOutsourcedRBtn.setSelected(true);
            partInHouseRBtn.setDisable(true);
            partOutsourcedRBtn.setDisable(true);
            partMachineIDLbl.setText("Company Name");
        }

        partIDTxt.setText(String.valueOf(loadedPart.getId()));
        partNameTxt.setText(String.valueOf(loadedPart.getName()));
        partInvTxt.setText(String.valueOf(loadedPart.getStock()));
        partPriceCostTxt.setText(String.valueOf(loadedPart.getPrice()));
        partMaxTxt.setText(String.valueOf(loadedPart.getMax()));
        partMinTxt.setText(String.valueOf(loadedPart.getMin()));

        /*if(loadedPart instanceof InHouse)
             System.out.println("loadedPart is still an InHouse object");
          else if(loadedPart instanceof Outsourced)
             System.out.println("loadedPart is Outsourced");
          else
             System.out.println("loadedPart is a Part again now");*/

    }



}