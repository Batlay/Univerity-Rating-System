package Client.AdminController;

import Server.Command;
import Client.DBUtilis;
import Client.Entity.Student;
import Client.Entity.StudentO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static Client.LoginController.connectionTCP;
import static Client.LoginController.username;

public class AdminHomescreen implements Initializable {

    @FXML
    private Button btn_logout;
    @FXML
    private AnchorPane news_anchor;
    @FXML
    private AnchorPane rating_anchor;
    @FXML
    private TableView<Student> rate_tableview;
    @FXML
    private TableColumn<Student, String>  stid_column;
    @FXML
    private TableColumn<Student, Double>  avg_column;
    @FXML
    private ComboBox<String> year;
    @FXML
    private ComboBox<String> faculty;
    @FXML
    private ComboBox<String> spec;
    @FXML
    Text date1;
    @FXML
    Text date2;
    @FXML
    Text date3;

    public static String yea = null;
    public static String facult;
    public static String sp = null;

    private final  ObservableList<Student> data = FXCollections.observableArrayList();
    private final  ObservableList<String> string_faculty = FXCollections.observableArrayList();
    private final  ObservableList<String> string_year = FXCollections.observableArrayList();
    private final  ObservableList<String> string_spec = FXCollections.observableArrayList();

    @FXML
    private void rating() throws ParseException {
        initDataDate1();
        initDataDate2();
        initDataDate3();
        yea = year.getValue().trim();
        sp = spec.getValue().trim();
        facult = faculty.getValue().trim();

        Date now = new Date();
        Date mark1 = new SimpleDateFormat("yyyy-MM-dd").parse(date1.getText());
        Date mark2 = new SimpleDateFormat("yyyy-MM-dd").parse(date2.getText());
        Date mark3 = new SimpleDateFormat("yyyy-MM-dd").parse(date3.getText());

        String selectStmt = "SELECT student.id_student, AVG(ROUND(((mark1+mark2)/2),2)) as mark_avg\n" +
                "                 FROM mark1\n" +
                "                 JOIN student ON mark1.id_student=student.id_student \n" +
                "                 JOIN groupa ON student.id_group=groupa.id_group \n" +
                "                JOIN spec ON groupa.code_spec=spec.code_spec \n" +
                "                JOIN faculty ON spec.code_faculty=faculty.code_faculty \n" +
                "                 JOIN year ON groupa.code_year=year.code_year \n" +
                "                WHERE faculty.code_faculty='"+facult+"' AND spec.code_spec='"+sp+"' AND year.code_year='"+yea+"' \n" +
                "                 GROUP BY id_student";
        String selectStmt1 = "SELECT student.id_student, AVG(ROUND(((mark1.mark1+mark1.mark2+mark2.mark3+mark2.mark4)/4),2)) as mark_avg\n" +
                "                 FROM mark1\n" +
                "                 JOIN mark2 ON mark1.id_student=mark2.id_student \n" +
                "                 JOIN student ON mark1.id_student=student.id_student \n" +
                "                 JOIN groupa ON student.id_group=groupa.id_group \n" +
                "                JOIN spec ON groupa.code_spec=spec.code_spec \n" +
                "                JOIN faculty ON spec.code_faculty=faculty.code_faculty \n" +
                "                 JOIN year ON groupa.code_year=year.code_year \n" +
                "                  WHERE faculty.code_faculty='"+facult+"' AND spec.code_spec='"+sp+"' AND year.code_year='"+yea+"'\n" +
                "                 GROUP BY id_student";
        String selectStmt2 = "SELECT student.id_student, ROUND(AVG(ROUND(((mark1.mark1+mark1.mark2+mark2.mark3+mark2.mark4+mark3.mark5+mark3.mark6 )/6),0)),2) as mark_avg\n" +
                "                 FROM mark1\n" +
                "                 JOIN mark2 ON mark1.id_student=mark2.id_student \n" +
                "                 JOIN mark3 ON mark1.id_student=mark3.id_student \n" +
                "                 JOIN student ON mark1.id_student=student.id_student \n" +
                "                 JOIN groupa ON student.id_group=groupa.id_group \n" +
                "                JOIN spec ON groupa.code_spec=spec.code_spec \n" +
                "                JOIN faculty ON spec.code_faculty=faculty.code_faculty \n" +
                "                 JOIN year ON groupa.code_year=year.code_year \n" +
                "                  WHERE faculty.code_faculty='"+facult+"' AND spec.code_spec='"+sp+"' AND year.code_year='"+yea+"'\n" +
                "                 GROUP BY id_student;";

        data.clear();
        if( now.after(mark1) && now.before(mark2)) {
            connectionTCP.writeUtf(selectStmt);

        } else if (now.after(mark2) && now.before(mark3)) {
            connectionTCP.writeUtf(selectStmt1);

        } else if (now.after(mark3)){
            connectionTCP.writeUtf(selectStmt2);
        }
        connectionTCP.writeObject(Command.READ);
        connectionTCP.flush();

        List<StudentO> students  = (List<StudentO>) connectionTCP.readObject();
        for (StudentO student : students) {
            Student e = new Student(student);
            data.add(e);
        }

        rate_tableview.setItems(data);
    }

    public void select_year() {
        String selectStmt = "select code_year from year";
        string_year.clear();
        connectionTCP.writeUtf(selectStmt);
        connectionTCP.writeObject(Command.READYEAR);
        connectionTCP.flush();
        List<String> faculty  = (List<String>) connectionTCP.readObject();
        string_year.addAll(faculty);
        year.setItems(string_year);
    }

    @FXML
    private void faculty() {
        String selectStmt = "select code_spec from spec WHERE code_faculty='"+faculty.getValue()+"'";
        string_spec.clear();
        connectionTCP.writeUtf(selectStmt);
        connectionTCP.writeObject(Command.READSPEC);
        connectionTCP.flush();
        List<String> faculty  = (List<String>) connectionTCP.readObject();
        string_spec.addAll(faculty);
        spec.setItems(string_spec);
    }

    public void select_faculty() {
        String selectStmt = "select code_faculty from faculty";
        string_faculty.clear();
        connectionTCP.writeUtf(selectStmt);
        connectionTCP.writeObject(Command.READFACULTY);
        connectionTCP.flush();
        List<String> facul  = (List<String>) connectionTCP.readObject();
        string_faculty.addAll(facul);
        faculty.setItems(string_faculty);
    }

    @FXML
    private void rating_title() {
        rate_tableview.getItems().clear();
        news_anchor.setVisible(false);
        rating_anchor.setVisible(true);
        string_spec.clear();
        select_faculty();
        select_year();
    }


    public void initDataDate1() {
        String selectStmt = "SELECT date FROM kt WHERE kt_id=1";
        initCalendarDate(selectStmt, date1);
    }
    public void initDataDate2() {
        String selectStmt = "SELECT date FROM kt WHERE kt_id=2";
        initCalendarDate(selectStmt, date2);
    }
    public void initDataDate3() {
        String selectStmt = "SELECT date FROM kt WHERE kt_id=3";
        initCalendarDate(selectStmt, date3);
    }

    private void initCalendarDate(String selectStmt, Text date) {

        connectionTCP.writeUtf(selectStmt);
        connectionTCP.writeObject(Command.READDATE);
        connectionTCP.flush();
        String date_kt = (String) connectionTCP.readObject();
        date.setText(date_kt);
    }

    @FXML
    private void cabinet(ActionEvent event){
        DBUtilis.changeScene(event,"admin_cabinet.fxml", "Администратор", username);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        stid_column.setCellValueFactory(cellData -> cellData.getValue().stIdProperty());
        avg_column.setCellValueFactory(cellData -> cellData.getValue().avgProperty().asObject());
        initDataDate1();
        initDataDate2();
        initDataDate3();

        btn_logout.setOnAction(event -> {
            String a ="";
            connectionTCP.writeUtf(a);
            connectionTCP.writeObject(Command.EXIT);
            connectionTCP.close();
            DBUtilis.changeScene(event,"login.fxml", "Академия магии и волшебства", null);
        });
    }
    @FXML
    private void news() {
        news_anchor.setVisible(true);
        rating_anchor.setVisible(false);
    }
}
