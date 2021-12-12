package Client;

import Server.Command;
import Server.ConnectionTCP;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private PasswordField txt_password;

    @FXML
    private TextField txt_username;

    @FXML
    private ChoiceBox cbUser;

    public static ConnectionTCP connectionTCP ;
    public static String username = null;
    public static String password = null;






    @FXML
    private void loginButtonClick(ActionEvent event) {
        boolean aa;
        username = txt_username.getText().trim();
        password = txt_password.getText();
        String userType = cbUser.getValue().toString().trim();
        switch (userType) {
            case "Admin" -> {
                connectionTCP.writeUtf(username+" "+password);
                connectionTCP.writeObject(Command.LOGINADMIN);
                aa = (boolean) connectionTCP.readObject();
                if (aa) {
                    DBUtilis.changeScene(event,"admin_homescreen.fxml", "Главная страница", username);
                    System.out.println("Добро пожаловать!");
                }
                else{
                    System.out.println("Авторизация не удалась.");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Проверьте введённые вами данные");
                    alert.show();
                }
            }
            case "Student" -> {
                connectionTCP.writeUtf(username+" "+password);
                connectionTCP.writeObject(Command.LOGINSTUDENT);
                aa = (boolean) connectionTCP.readObject();

                if (aa) {
                    DBUtilis.changeScene(event, "student_homescreen.fxml", "Главная страница", username);
                    System.out.println("Добро пожаловать!");
                }
                else{
                    System.out.println("Авторизация не удалась.");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Проверьте введённые вами данные");
                    alert.show();
                }
            }
            case "Teacher" -> {
                connectionTCP.writeUtf(username+" "+password);
                connectionTCP.writeObject(Command.LOGINTEACHER);
                aa = (boolean) connectionTCP.readObject();

                if (aa) {
                   DBUtilis.changeScene(event, "teacher_homescreen.fxml", "Главная страница", username);
                    System.out.println("Добро пожаловать!");
                }
                else{
                    System.out.println("Авторизация не удалась.");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Проверьте введённые вами данные");
                    alert.show();
                }
            }
        }
    }

    @FXML
    private void exit(){
        String a ="";
        connectionTCP.writeUtf(a);
        connectionTCP.writeObject(Command.EXIT);
        connectionTCP.close();
        System.exit(0);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            connectionTCP = new ConnectionTCP(new Socket("localhost", 3636));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

    }
}


