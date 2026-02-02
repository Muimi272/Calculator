module com.muimi.calculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.muimi.calculator to javafx.fxml;
    exports com.muimi.calculator;
}