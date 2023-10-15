module com.example.silmarilcursor {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.silmarilcursor to javafx.fxml;
    exports com.example.silmarilcursor;
}