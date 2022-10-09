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

/** Class AddPartController controls AddPart.fxml. The user can select radio buttons
 * to create an in-house or outsourced part. It allows users to create a part name, inventory level,
 * price, max and min inventory allowed, and a machine Id or company name depending on the selected part type.
 * The part ID is incremented and auto-generated, and cannot be changed by the user.
 * @author Kush Mirchandani*/
public class AddPartController implements Initializable {

    /** declares a stage variable */
    Stage stage;

    /** declares a scene variable */
    Parent scene;

    /** declares an id variable so that part IDs can be incremented */
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
    private Label partMaxExLbl;

    @FXML
    private Label partMinExLbl;

    @FXML
    private Label partMachineIdExLbl;


    /** In-House radio button clicked.
     * Sets the partMachineIDLbl to "Machine ID" when clicked.
     * @param actionEvent the item on the GUI that triggers the action */
    public void onInHouse(ActionEvent actionEvent) {
        partMachineIDLbl.setText("Machine ID");
    }

    /** Outsourced radio button clicked.
     * Sets the partMachineIDLbl to "Company Name" when clicked.
     * @param actionEvent the item on the GUI that triggers the action */
    public void onOutsourced(ActionEvent actionEvent) {
        partMachineIDLbl.setText("Company Name");
    }

    /** Cancel button clicked.
     * Exits the Add Part screen and opens the Main Screen.
     * @param event the item on the GUI that triggers the action */
    @FXML
    public void onActionDisplayMainScreen(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Save button clicked.
     * Saves the new In-House or Outsourced part, by calling the addPart method in the Inventory class.
     * Closes the Add Part screen and opens the Main Screen when clicked. Displays error messages and stops running if any
     * of the input fields are blank when clicked. Displays error messages and stops running if min is greater than max, or
     * if inventory isn't between min and max.
     * @param event the item on the GUI that triggers the action */
    @FXML
    public void onActionSavePart(ActionEvent event) throws IOException {

        //Clear exception message fields
        partNameExLbl.setText("");
        partInvExLbl.setText("");
        partPriceCostExLbl.setText("");
        partMaxExLbl.setText("");
        partMinExLbl.setText("");
        partMachineIdExLbl.setText("");

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
            partPriceCostExLbl.setText("Price is not a double");
        }

        try {
            Integer.parseInt(partMaxTxt.getText());
        } catch (NumberFormatException e) {
            partMaxExLbl.setText("Max is not an integer");
        }

        try {
            Integer.parseInt(partMinTxt.getText());
        } catch (NumberFormatException e) {
            partMinExLbl.setText("Min is not an integer");
        }

        if (partInHouseRBtn.isSelected()) {
            try {
                Integer.parseInt(partMachineIDTxt.getText());
            } catch (NumberFormatException e) {
                partMachineIdExLbl.setText("Machine ID is not an integer");
            }
        }

        if (partOutsourcedRBtn.isSelected()) {
            if (partMachineIDTxt.getText() == "") {
                partMachineIdExLbl.setText("Exception: No data in Company Name field");
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
            partMinExLbl.setText("Min must be less than Max");
        } else if (Integer.parseInt(partInvTxt.getText()) > Integer.parseInt(partMaxTxt.getText()) || Integer.parseInt(partInvTxt.getText()) < Integer.parseInt(partMinTxt.getText())) {
            partInvExLbl.setText("Inv must be between Min and Max");
        }

        //After printing applicable error messages, check Inventory fields for logical errors again and return; out of the method if any exceptions exist so the program doesn't crash:
        if (Integer.parseInt(partMinTxt.getText()) > Integer.parseInt(partMaxTxt.getText())) {
            return;
        } else if (Integer.parseInt(partInvTxt.getText()) > Integer.parseInt(partMaxTxt.getText()) || Integer.parseInt(partInvTxt.getText()) < Integer.parseInt(partMinTxt.getText())) {
            return; }

        //If there are no errors, proceed with adding the part:
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
        }

     /** This is the initialize method.
     * This is the first method that gets called when the scene is set to the Add Part Screen.
     * initializes the id variable and increments it by making it one larger than the number of parts in the allParts list.
     * @param url the location of AddPart.fxml
     * @param resourceBundle the name of AddPart.fxml*/
        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){

            id = Inventory.getAllParts().size() + 1;
        }
}