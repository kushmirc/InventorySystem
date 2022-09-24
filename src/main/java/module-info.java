module mirchandani.inventorysystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens mirchandani.inventorysystem to javafx.fxml;
    exports mirchandani.inventorysystem;
}