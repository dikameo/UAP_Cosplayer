module main.uapkostum {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires junit;


    opens main.uapkostum to javafx.fxml;
    exports main.uapkostum;
}