module org.example.p14_obat053 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires java.desktop;

    opens org.example.p14_obat053 to javafx.fxml;
    exports org.example.p14_obat053;
}