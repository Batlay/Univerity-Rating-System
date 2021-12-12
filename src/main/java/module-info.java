module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires java.sql.rowset;
    requires mysql.connector.java;
    requires TrayNotification;

    opens Client to javafx.fxml;
    exports Client;
    exports Server;
    opens Server to javafx.fxml;
    exports Client.Entity;
    opens Client.Entity to javafx.fxml;
    exports Client.AdminController;
    opens Client.AdminController to javafx.fxml;
    exports Client.TeacherController;
    opens Client.TeacherController to javafx.fxml;
    exports Client.StudentController;
    opens Client.StudentController to javafx.fxml;
}