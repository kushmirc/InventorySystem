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

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 400);
        stage.setTitle("");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        InHouse part1 = new InHouse(1, "flipper", 8.00, 23, 5, 50, 1);
        InHouse part2 = new InHouse(2, "bumper", 15.00, 16, 3, 25, 2);
        Outsourced part3 = new Outsourced(3, "ramp", 35.00, 8, 2, 15, RampsRUs);

        Product product1 = new Product(1000, "The Addams Family", 3000, 6, 2, 10);
        Product product2 = new Product(1001, "Terminator 2", 2000, 3, 1, 5);
        Product product3 = new Product(1002, "Who Dunnit", 2500, 4, 3, 12);

        launch();
    }
}