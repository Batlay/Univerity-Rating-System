package Client.StudentController;

import Server.Command;
import Client.DBUtilis;
import Client.Entity.Student;
import Client.Entity.StudentO;
import Client.Entity.Subject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static Client.LoginController.username;
import static Client.LoginController.connectionTCP;

public class StudentHomescreen implements Initializable {

    @FXML
    private Button btn_logout;
    @FXML
    private AnchorPane news_anchor;
    @FXML
    private AnchorPane rating_anchor;
    @FXML
    private AnchorPane group_anchor;
    @FXML
    private AnchorPane subject_anchor;
    @FXML
    private TableView<Student> rate_tableview;
    @FXML
    private TableView<Student> group_tableview;
    @FXML
    private TableView<Subject> subject_tableview;
    @FXML
    private TableColumn<Student, String>  stid_column;
    @FXML
    private TableColumn<Student, Double>  avg_column;
    @FXML
    private TableColumn<Student, String>  name_column;
    @FXML
    private TableColumn<Student, String>  lastname_column;
    @FXML
    private TableColumn<Student, String>  middlename_column;
    @FXML
    private TableColumn<Student, String>  role_column;
    @FXML
    private TableColumn<Student, String>  phone_column;
    @FXML
    private TableColumn<Student, String>  email_column;
    @FXML
    private TableColumn<Subject, String>  discipline_column;
    @FXML
    private TableColumn<Subject, String>  hours_column;
    @FXML
    private TableColumn<Subject, String>  form_column;
    @FXML
    private TableColumn<Subject, String>  teacher_column;
    @FXML
    private TableColumn<Subject, String>  namesub_column;
    @FXML
    private ComboBox<String> year;
    @FXML
    private ComboBox<String> faculty;
    @FXML
    private ComboBox<String> spec;
    @FXML
    Text tName;
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
    private final  ObservableList<Subject> sub = FXCollections.observableArrayList();
    private final  ObservableList<String> string_faculty = FXCollections.observableArrayList();
    private final  ObservableList<String> string_year = FXCollections.observableArrayList();
    private final  ObservableList<String> string_spec = FXCollections.observableArrayList();

    @FXML
    private void rating() throws ParseException {
        try {
            initDataDate1();
            initDataDate2();
            initDataDate3();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    @FXML
    private void rating_title() {
        rate_tableview.getItems().clear();
        news_anchor.setVisible(false);
        rating_anchor.setVisible(true);
        group_anchor.setVisible(false);
        subject_anchor.setVisible(false);
        string_spec.clear();
        select_faculty();
        select_year();
    }


    @FXML
    private void group_title() {
        news_anchor.setVisible(false);
        rating_anchor.setVisible(false);
        subject_anchor.setVisible(false);
        group_anchor.setVisible(true);
        initDataGroup();

        String selectStmt ="SELECT first_name, last_name, middle_name, role, phone_number, email FROM student WHERE id_group ='"+tName.getText()+"'";

        data.clear();
        connectionTCP.writeUtf(selectStmt);
        connectionTCP.writeObject(Command.READGROUP);
        connectionTCP.flush();
        List<StudentO> group  = (List<StudentO>) connectionTCP.readObject();
        for (StudentO studentO : group) {
            Student e = new Student(studentO);
            data.add(e);
        }
        group_tableview.setItems(data);
    }


    public void initDataGroup() {
        String selectStmt = "SELECT id_group FROM student WHERE id_student = '" + username + "'";
        connectionTCP.writeUtf(selectStmt);
        connectionTCP.writeObject(Command.SELECTGROUP);
        connectionTCP.flush();
        String group = (String) connectionTCP.readObject();
        tName.setText(group);
    }

    public void initDataDate1() throws IOException {
        String selectStmt = "SELECT date FROM kt WHERE kt_id=1";
        initCalendarDate(selectStmt, date1);
    }
    public void initDataDate2() throws IOException {
        String selectStmt = "SELECT date FROM kt WHERE kt_id=2";
        initCalendarDate(selectStmt, date2);
    }
    public void initDataDate3() throws IOException {
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
        DBUtilis.changeScene(event,"student_cabinet.fxml", "Личный кабинет", username);
    }


    @FXML
    private void subjects_title()  {
        news_anchor.setVisible(false);
        rating_anchor.setVisible(false);
        group_anchor.setVisible(false);
        subject_anchor.setVisible(true);

        String selectStmt ="SELECT study_plan.code_subject,study_plan.hours,study_plan.form, teacher.name,subject.subject_name\n" +
                "FROM mark1 \n" +
                "JOIN student ON mark1.id_student=student.id_student\n" +
                "JOIN study_plan ON mark1.id_study_plan=study_plan.id_study_plan\n" +
                "JOIN teacher ON study_plan.id_teacher=teacher.id_teacher\n" +
                "JOIN subject ON study_plan.code_subject=subject.code_subject\n" +
                "WHERE student.id_student ='"+ username+"'";

        sub.clear();
        connectionTCP.writeUtf(selectStmt);
        connectionTCP.writeObject(Command.READSUB);
        connectionTCP.flush();
        StudentCabinet.mark_list(sub, subject_tableview);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        stid_column.setCellValueFactory(cellData -> cellData.getValue().stIdProperty());
        avg_column.setCellValueFactory(cellData -> cellData.getValue().avgProperty().asObject());
        name_column.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastname_column.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        middlename_column.setCellValueFactory(cellData -> cellData.getValue().middleNameProperty());
        role_column.setCellValueFactory(cellData -> cellData.getValue().roleProperty());
        phone_column.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
        email_column.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        discipline_column.setCellValueFactory(cellData -> cellData.getValue().disciplineProperty());
        hours_column.setCellValueFactory(cellData -> cellData.getValue().hoursProperty());
        form_column.setCellValueFactory(cellData -> cellData.getValue().formProperty());
        teacher_column.setCellValueFactory(cellData -> cellData.getValue().teacherProperty());
        namesub_column.setCellValueFactory(cellData -> cellData.getValue().nameProperty());



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
        group_anchor.setVisible(false);
        subject_anchor.setVisible(false);
    }
}
