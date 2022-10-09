package mirchandani.inventorysystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Product;

import java.io.IOException;

// Javadoc location: InventorySystem/javadoc

/** Class Main contains the main method, starts the stage for JavaFX, and initializes JavaFX.
 * FUTURE ENHANCEMENT: When a part is added to the associatedParts list for a product,
 * the inventory amount for that part should be reduced by 1. If reducing the inventory
 * would cause it to go below the Min, then an exception should be thrown, and the addition
 * of the part should be prevented.
 * @author Kush Mirchandani */
public class Main extends Application {

    /** The start method for the JavaFX stage.
     * This starts the JavaFX stage, and sets the first scene to MainScreen.fxml
     * @param stage the stage to start */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 400);
        stage.setTitle("");
        stage.setScene(scene);
        stage.show();
    }

    /** This is the main method.
     * This is the first method that gets called when the program runs, and it launches JavaFX.*/
    public static void main(String[] args) {

        InHouse part1 = new InHouse(7, "flipper", 8.00, 23, 5, 50, 1);
        InHouse part2 = new InHouse(8, "bumper", 15.00, 16, 3, 25, 2);
        Outsourced part3 = new Outsourced(9, "ramp", 35.00, 8, 2, 15, "RampsRUs");

        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);


        Product product1 = new Product(1007, "The Addams Family", 3000, 6, 2, 10);
        Product product2 = new Product(1008, "Terminator 2", 2000, 3, 1, 5);
        Product product3 = new Product(1009, "Who Dunnit", 2500, 4, 3, 12);

        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);


        launch();
    }
}