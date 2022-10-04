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
public class ModifyPartController implements Initializable {

    Stage stage;
    Parent scene;

    int id;

    private static Part selectedPart = null;

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

        //id =Part.getId() + 1;
        //id = Integer.parseInt(partIDTxt.getText());

        String name = partNameTxt.getText();
        int stock = Integer.parseInt(partInvTxt.getText());
        double price = Double.parseDouble(partPriceCostTxt.getText());
        int max = Integer.parseInt(partMaxTxt.getText());
        int min = Integer.parseInt(partMinTxt.getText());
        //boolean inHouse;
        //int machineId = Integer.parseInt(partMachineIDTxt.getText());

       /* if(partInHouseRBtn.isSelected())
            inHouse = true;
        else
            inHouse = false;*/
        if(partInHouseRBtn.isSelected()) {
            int machineId = Integer.parseInt(partMachineIDTxt.getText());
            InHouse newpart = new InHouse(id, name, price, stock, min, max, machineId);
            Inventory.addPart(newpart);
        }
        else {
            String companyName = partMachineIDTxt.getText();
            Outsourced newpart = new Outsourced(id, name, price, stock, min, max, companyName);
            Inventory.addPart((newpart));
        }

        //id = id + 1;
        //System.out.println(id);

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public static void loadPart(Part part) {
       /* int idInt = part.getId();
        String idString = String.valueOf(idInt);
        System.out.println(idString);
        partIDTxt.setText(idString);*/
        selectedPart = part;

       /* partIDTxt.setText(String.valueOf(part.getId()));
        partNameTxt.setText(String.valueOf(part.getName()));
        partInvTxt.setText(String.valueOf(part.getStock()));
        partPriceCostTxt.setText(String.valueOf(part.getPrice()));
        partMaxTxt.setText(String.valueOf(part.getMax()));
        partMinTxt.setText(String.valueOf(part.getMin()));*/
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //id = Inventory.getAllParts().size() + 1;
        /*InHouse newPart = new InHouse(1, "coolpart", 1,3,1,4,1);
        newPart.setId(id++);*/
        //partIDTxt.setText(String.valueOf(id));
        //id = id + 1;
        // System.out.println(id);

        partIDTxt.setText(String.valueOf(selectedPart.getId()));
        partNameTxt.setText(String.valueOf(selectedPart.getName()));
        partInvTxt.setText(String.valueOf(selectedPart.getStock()));
        partPriceCostTxt.setText(String.valueOf(selectedPart.getPrice()));
        partMaxTxt.setText(String.valueOf(selectedPart.getMax()));
        partMinTxt.setText(String.valueOf(selectedPart.getMin()));
        partOutsourcedRBtn.fire();

       /* try {
            partMachineIDLbl.setText(String.valueOf(selectedPart.getMachineId));
        }
        catch(Exception err) {
            partMachineIDLbl.setText(selectedPart.getCompanyName);
        }*/

    }



}