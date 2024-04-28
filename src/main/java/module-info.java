module org.example.els {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.dlsc.formsfx;
    requires javafx.web;
    requires com.opencsv;
    requires jlayer;
    requires java.sql;
    requires javafx.media;
    opens org.example.els to javafx.fxml;
    exports org.example.els;
    exports googleTranslate;
    opens googleTranslate to javafx.fxml;

}