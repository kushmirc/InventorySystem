package mirchandani.inventorysystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.InHouse;
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
        deletePart((Part) partsTableView.getSelectionModel().getSelectedItem());
    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) {

        deleteProduct((Product) productsTableView.getSelectionModel().getSelectedItem());
    }

    @FXML
    void onActionExit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onActionModifyPart(ActionEvent event) throws IOException {

        Part selectedPart = partsTableView.getSelectionModel().getSelectedItem();

        ModifyPartController.loadPart(selectedPart);

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("ModifyPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionModifyProduct(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("ModifyProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionLoopUpPart(KeyEvent event) {
        ObservableList<Part> searchedParts = FXCollections.observableArrayList(); //= Inventory.lookupPart(partsSearchTxt.getText());

        try {
            int searchedPartId = Integer.parseInt(partsSearchTxt.getText());
            Part part = Inventory.lookupPart(searchedPartId);
            searchedParts.add(part);
        }
        catch(NumberFormatException err){
            searchedParts = Inventory.lookupPart(partsSearchTxt.getText());

        }
      /*  if(searchedParts.size() == 0) {
            int searchedPartId = Integer.parseInt(partsSearchTxt.getText());
            Part part = Inventory.lookupPart(searchedPartId);
            searchedParts.add(part);
        }*/

        partsTableView.setItems(searchedParts);
    }

    @FXML
    void onActionLoopUpProduct(KeyEvent event) {
        ObservableList<Product> searchedProducts = FXCollections.observableArrayList();

        try {
            int searchedProductId = Integer.parseInt(productsSearchTxt.getText());
            Product product = Inventory.lookupProduct(searchedProductId);
            searchedProducts.add(product);
        }
        catch(NumberFormatException err){
            searchedProducts = Inventory.lookupProduct(productsSearchTxt.getText());

        }
        productsTableView.setItems(searchedProducts);
    }

 /*   public boolean search(int id) {

        for(Part part : Inventory.getAllParts()) {
            if (part.getId() == id)
                return true;
        }
        return false;
    }*/

 /*   public boolean update (int id, Part part) {
        int index = -1;

        for(Part inHouse : Inventory.getAllParts()) {
            index++;

            if (inHouse.getId() == id) {
            Inventory.getAllParts().set(index, part);
            return true;
            }
        }
        return false;
    }*/

    public boolean deletePart(Part selectedPart) {
        selectedPart = (Part) partsTableView.getSelectionModel().getSelectedItem();
        if (selectedPart == null)
            return false;
        else
            return Inventory.getAllParts().remove(selectedPart);
    }

    public boolean deleteProduct(Product selectedProduct) {
        selectedProduct = (Product) productsTableView.getSelectionModel().getSelectedItem();
        if (selectedProduct == null)
            return false;
        else
            return Inventory.getAllProducts().remove(selectedProduct);
    }

   /* public boolean delete(int id) {
        for(Part part : Inventory.getAllParts()) {
            if (part.getId() == id)
                return Inventory.getAllParts().remove(part);
        }
        return false;
    }

    public Part selectPart(int id) {
        for(Part part: Inventory.getAllParts()) {
            if(part.getId() == id)
                return part;
        }
        return null;
    }

    public ObservableList<Part> filter(String name) {
        if(!(Inventory.getAllFilteredParts().isEmpty()))
            Inventory.getAllFilteredParts().clear();

      for(Part part: Inventory.getAllParts()) {
          if (part.getName().contains(name))
              Inventory.getAllFilteredParts().add(part);
      }
      if(Inventory.getAllFilteredParts().isEmpty())
          return Inventory.getAllParts();
      else
          return Inventory.getAllFilteredParts();
    }*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        partsTableView.setItems(Inventory.getAllParts());
        //partsTableView.setItems(filter("x"));

        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCostPerUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));


        productsTableView.setItems(Inventory.getAllProducts());

        productsProductIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productsProductNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productsInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productsPriceCostPerUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        /*if(search(3))
            System.out.println("Match!");
        else
            System.out.println("No match!");

        if (update(3, new InHouse(3, "spinner", 18.00, 7, 2, 20, 4)))
            System.out.println("Update successful!");
        else
            System.out.println("Update failed!");

        if(delete(3))
            System.out.println("Deleted!");
        else
            System.out.println("No match!");*/

        //partsTableView.getSelectionModel().select(selectPart(2));

        //Loading part data
        //Part selectedPart = (Part) partsTableView.getSelectionModel().getSelectedItem();

        /*int myint = 10;
        String mystring = String.valueOf(myint);
        System.out.println(mystring);*/



    }


}