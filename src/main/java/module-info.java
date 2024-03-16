module org.example.els {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.dlsc.formsfx;
    requires javafx.web;
    opens org.example.els to javafx.fxml;
    exports org.example.els;

}