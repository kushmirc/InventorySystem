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

    int id;

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
        loadedPart = part;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //id = Inventory.getAllParts().size() + 1;
        /*InHouse newPart = new InHouse(1, "coolpart", 1,3,1,4,1);
        newPart.setId(id++);*/
        //partIDTxt.setText(String.valueOf(id));
        //id = id + 1;
        // System.out.println(id);

        if(loadedPart instanceof InHouse) {
            InHouse inh = (InHouse) loadedPart;
            partMachineIDTxt.setText(String.valueOf(inh.getMachineId()));
            partInHouseRBtn.fire();
            partMachineIDLbl.setText("Machine ID");
        }
        else {
            Outsourced op = (Outsourced) loadedPart;
            partMachineIDTxt.setText(op.getCompanyName());
            partOutsourcedRBtn.fire();
            partMachineIDLbl.setText("Company Name");
        }

        partIDTxt.setText(String.valueOf(loadedPart.getId()));
        partNameTxt.setText(String.valueOf(loadedPart.getName()));
        partInvTxt.setText(String.valueOf(loadedPart.getStock()));
        partPriceCostTxt.setText(String.valueOf(loadedPart.getPrice()));
        partMaxTxt.setText(String.valueOf(loadedPart.getMax()));
        partMinTxt.setText(String.valueOf(loadedPart.getMin()));
        //partOutsourcedRBtn.fire();


       /* try {
            partMachineIDLbl.setText(String.valueOf(loadedPart.getMachineId));
        }
        catch(Exception err) {
            partMachineIDLbl.setText(loadedPart.getCompanyName);
        }*/

    }



}