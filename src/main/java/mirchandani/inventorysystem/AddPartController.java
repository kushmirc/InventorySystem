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

    int id;

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

    @FXML
    private Label partNameExLbl;

    @FXML
    private Label partInvExLbl;

    @FXML
    private Label partPriceCostExLbl;

    @FXML
    private Label partMaxMinExLbl;

    @FXML
    private Label partMachineIdExLbl;

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

        //Check all input fields for exceptions and print applicable messages:
        if (partNameTxt.getText() == "") {
            partNameExLbl.setText("No data in name field");
        }

        try {
            Integer.parseInt(partInvTxt.getText());
        } catch (NumberFormatException e) {
            partInvExLbl.setText("Inventory is not an integer");
        }

        try {
            Double.parseDouble(partPriceCostTxt.getText());
        } catch (NumberFormatException e) {
            System.out.println("Price is not a double" + e.getMessage());
        }

        try {
            Integer.parseInt(partMaxTxt.getText());
        } catch (NumberFormatException e) {
            System.out.println("Max is not an integer" + e.getMessage());
        }

        try {
            Integer.parseInt(partMinTxt.getText());
        } catch (NumberFormatException e) {
            System.out.println("Min is not an integer" + e.getMessage());
        }

        if (partInHouseRBtn.isSelected()) {
            try {
                Integer.parseInt(partMachineIDTxt.getText());
            } catch (NumberFormatException e) {
                System.out.println("Machine ID is not an integer" + e.getMessage());
            }
        }

        if (partOutsourcedRBtn.isSelected()) {
            if (partMachineIDTxt.getText() == "") {
                System.out.println("Exception: No data in Company Name field");
            }
        }

        //After printing applicable error messages, check all input fields for exceptions again and return; out of the method if any exceptions exist so the program doesn't crash:
        if (partNameTxt.getText() == "") {
            return;
        }

        try {
            Integer.parseInt(partInvTxt.getText());
        } catch (NumberFormatException e) {
            return;
        }

        try {
            Double.parseDouble(partPriceCostTxt.getText());
        } catch (NumberFormatException e) {
            return;
        }

        try {
            Integer.parseInt(partMaxTxt.getText());
        } catch (NumberFormatException e) {
            return;
        }

        try {
            Integer.parseInt(partMinTxt.getText());
        } catch (NumberFormatException e) {
            return;
        }

        if (partInHouseRBtn.isSelected()) {
            try {
                Integer.parseInt(partMachineIDTxt.getText());
            } catch (NumberFormatException e) {
                return;
            }
        }

        if (partOutsourcedRBtn.isSelected()) {
            if (partMachineIDTxt.getText() == "") {
                return;
            }
        }

        //If there are no errors for blank text fields, then check the logic on the Inv, Max & Min fields:
        if (Integer.parseInt(partMinTxt.getText()) > Integer.parseInt(partMaxTxt.getText())) {
            System.out.println("Min must be less than Max");
        } else if (Integer.parseInt(partInvTxt.getText()) > Integer.parseInt(partMaxTxt.getText()) || Integer.parseInt(partInvTxt.getText()) < Integer.parseInt(partMinTxt.getText())) {
            System.out.println("Inv must be between Min and Max");
        }

        //After printing applicable error messages, check Inventory fields for logical errors again and return; out of the method if any exceptions exist so the program doesn't crash:
        if (Integer.parseInt(partMinTxt.getText()) > Integer.parseInt(partMaxTxt.getText())) {
            return;
        } else if (Integer.parseInt(partInvTxt.getText()) > Integer.parseInt(partMaxTxt.getText()) || Integer.parseInt(partInvTxt.getText()) < Integer.parseInt(partMinTxt.getText())) {
            return; }

            String name = partNameTxt.getText();
            int stock = Integer.parseInt(partInvTxt.getText());
            double price = Double.parseDouble(partPriceCostTxt.getText());
            int max = Integer.parseInt(partMaxTxt.getText());
            int min = Integer.parseInt(partMinTxt.getText());

            if (partInHouseRBtn.isSelected()) {
                int machineId = Integer.parseInt(partMachineIDTxt.getText());
                InHouse newpart = new InHouse(id, name, price, stock, min, max, machineId);
                Inventory.addPart(newpart);
            } else {
                String companyName = partMachineIDTxt.getText();
                Outsourced newpart = new Outsourced(id, name, price, stock, min, max, companyName);
                Inventory.addPart((newpart));
            }


            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();


        /*catch(NumberFormatException e) {
            System.out.println("Please enter valid values in text fields!");
            System.out.println("Exception: " + e);
            System.out.println("Exception: " + e.getMessage());
        }*/

        }


        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){

            id = Inventory.getAllParts().size() + 1;
        /*InHouse newPart = new InHouse(1, "coolpart", 1,3,1,4,1);
        newPart.setId(id++);*/
            //partIDTxt.setText(String.valueOf(id));
            //id = id + 1;
            // System.out.println(id);


        }



}