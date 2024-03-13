module org.example.els {
    requires javafx.controls;
    requires javafx.fxml;
    //requires javafx.web;
    requires com.dlsc.formsfx;

    opens org.example.els to javafx.fxml;
    exports org.example.els;

}