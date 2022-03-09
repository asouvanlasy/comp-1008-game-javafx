module com.example.gamejavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gamejavafx to javafx.fxml;
    exports com.example.gamejavafx;
}