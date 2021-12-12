package Client.StudentController;

import Server.Command;
import Client.DBUtilis;
import Client.Entity.Subject;
import Client.Entity.SubjectO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;


import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import static Client.LoginController.*;
import static Client.LoginController.connectionTCP;

public class StudentCabinet implements Initializable {

    @FXML
    private Button btn_logout;
    @FXML
    private Button location_btn;
    @FXML
    private Button mark_btn;
    @FXML
    private ImageView locate;
    @FXML
    private ImageView mark_img;


    @FXML
    private AnchorPane location_anchor;
    @FXML
    private AnchorPane mark_anchor;

    @FXML
    private TableView<Subject> mark_tableview;
    @FXML
    private TableColumn<Subject, Integer>  mark1_column;
    @FXML
    private TableColumn<Subject, Double>  avg_column;
    @FXML
    private TableColumn<Subject, String>  subject_column;
    @FXML
    private TableColumn<Subject, Integer>  mark2_column;
    @FXML
    private TableColumn<Subject, Integer>  mark3_column;
    @FXML
    private TableColumn<Subject, Integer>  mark4_column;
    @FXML
    private TableColumn<Subject, Integer>  mark5_column;
    @FXML
    private TableColumn<Subject, Integer>  mark6_column;
    @FXML
    Text email_text;
    @FXML
    TextField name_text;
    @FXML
    TextField surname_text;
    @FXML
    TextField id_text;
    @FXML
    TextField email_TextField;
    @FXML
    TextField old_password;
    @FXML
    PasswordField new_password;
    @FXML
    PasswordField new1_password;
    @FXML
    Text kt1;
    @FXML
    Text kt2;
    @FXML
    Text kt3;
    @FXML
    Text sem;
    @FXML
    Text date1;
    @FXML
    Text date2;
    @FXML
    Text date3;

    private final  ObservableList<Subject> sub = FXCollections.observableArrayList();

    @FXML
    private void load() throws ParseException {
        mark_anchor.setVisible(true);

        Date now = new Date();
        Date d1 =new SimpleDateFormat("yyyy-MM-dd").parse(date1.getText());
        Date d2 =new SimpleDateFormat("yyyy-MM-dd").parse(date2.getText());
        Date d3 =new SimpleDateFormat("yyyy-MM-dd").parse(date3.getText());

        String selectStmt2 = """
                SELECT subject.code_subject,mark1.mark1,mark1.mark2,mark2.mark3,mark2.mark4,mark3.mark5,mark3.mark6, ROUND(((mark1.mark1+mark1.mark2+mark2.mark3+mark2.mark4+mark3.mark5+mark3.mark6 )/6),0) as mark_avg
                                                                FROM mark1
                                                                   JOIN mark2 ON mark1.id_study_plan=mark2.id_study_plan
                                                                 JOIN mark3 ON mark1.id_study_plan=mark3.id_study_plan
                                                                 JOIN student ON mark1.id_student=student.id_student\s
                                                                 JOIN groupa ON student.id_group=groupa.id_group\s
                                                               JOIN spec ON groupa.code_spec=spec.code_spec
                                                               JOIN faculty ON spec.code_faculty=faculty.code_faculty
                                                                 JOIN year ON groupa.code_year=year.code_year\s
                                                                  JOIN study_plan ON mark1.id_study_plan=study_plan.id_study_plan
                                                                  JOIN subject ON study_plan.code_subject=subject.code_subject
                                                                  WHERE student.id_student='91430002'
                                                                GROUP BY  study_plan.id_study_plan""";
        String selectStmt1 = """
                SELECT subject.code_subject,mark1.mark1,mark1.mark2,mark2.mark3,mark2.mark4,mark3.mark5,mark3.mark6, AVG(ROUND(((mark1.mark1+mark1.mark2+mark2.mark3+mark2.mark4)/4),2)) as mark_avg
                                                                FROM mark1
                                                                   JOIN mark2 ON mark1.id_study_plan=mark2.id_study_plan
                                                                 JOIN mark3 ON mark1.id_study_plan=mark3.id_study_plan
                                                                 JOIN student ON mark1.id_student=student.id_student\s
                                                                 JOIN groupa ON student.id_group=groupa.id_group\s
                                                               JOIN spec ON groupa.code_spec=spec.code_spec
                                                               JOIN faculty ON spec.code_faculty=faculty.code_faculty
                                                                 JOIN year ON groupa.code_year=year.code_year\s
                                                                  JOIN study_plan ON mark1.id_study_plan=study_plan.id_study_plan
                                                                  JOIN subject ON study_plan.code_subject=subject.code_subject
                                                                  WHERE student.id_student='91430002'
                                                                GROUP BY  study_plan.id_study_plan""";
        String selectStmt = """
                SELECT subject.code_subject,mark1.mark1,mark1.mark2,mark2.mark3,mark2.mark4,mark3.mark5,mark3.mark6, AVG(ROUND(((mark1.mark1+mark1.mark2)/2),2)) as mark_avg
                                                                FROM mark1
                                                                   JOIN mark2 ON mark1.id_study_plan=mark2.id_study_plan
                                                                 JOIN mark3 ON mark1.id_study_plan=mark3.id_study_plan
                                                                 JOIN student ON mark1.id_student=student.id_student\s
                                                                 JOIN groupa ON student.id_group=groupa.id_group\s
                                                               JOIN spec ON groupa.code_spec=spec.code_spec
                                                               JOIN faculty ON spec.code_faculty=faculty.code_faculty
                                                                 JOIN year ON groupa.code_year=year.code_year\s
                                                                  JOIN study_plan ON mark1.id_study_plan=study_plan.id_study_plan
                                                                  JOIN subject ON study_plan.code_subject=subject.code_subject
                                                                  WHERE student.id_student='91430002'
                                                                GROUP BY  study_plan.id_study_plan""";
        sub.clear();

        if( now.after(d1) && now.before(d2)) {
            marks(selectStmt);
            kt2.setText("N/A");
            kt3.setText("N/A");
            sem.setText("N/A");
        } else if (now.after(d2) && now.before(d3)) {
            marks(selectStmt1);
            avg_kt2();
            kt3.setText("N/A");
            sem.setText("N/A");
        } else if (now.after(d3)){
            marks(selectStmt2);
            avg_kt2();
            avg_kt3();
            sem();
        }}

    private void marks(String selectStmt) {
        connectionTCP.writeUtf(selectStmt);
        connectionTCP.writeObject(Command.READMARK);
        mark_list(sub, mark_tableview);
        avg_kt1();
    }

    static void mark_list(ObservableList<Subject> sub, TableView<Subject> mark_tableview) {
        List<SubjectO> subjects  = (List<SubjectO>) connectionTCP.readObject();
        for (SubjectO subject : subjects) {
            Subject w = new Subject(subject);
            sub.add(w);
        }
        mark_tableview.setItems(sub);
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


    public void initDataStudent() {
        String selectStmt = "SELECT * FROM student WHERE id_student = '" + username + "'";

        connectionTCP.writeUtf(selectStmt);
        connectionTCP.writeObject(Command.STUDENTINFO);
        connectionTCP.flush();
        String input = (String) connectionTCP.readObject();
        StringTokenizer st = new StringTokenizer(input);
                String name = st.nextToken();
                String name1 = st.nextToken();
                String name2 = st.nextToken();
                String lname = st.nextToken();
                String id = st.nextToken();
                String email = st.nextToken();
                name_text.setText(name+" "+name1+" "+name2);
                surname_text.setText(lname);
                id_text.setText(id);
                email_text.setText(email);
    }

    public void initDataEmail() {
        String selectStmt = "SELECT email FROM student WHERE id_student = '" + username + "'";
        connectionTCP.writeUtf(selectStmt);
        connectionTCP.writeObject(Command.READEMAIL);
        connectionTCP.flush();
        String email= (String) connectionTCP.readObject();
        email_text.setText(email);
    }

    public void save_password() {
        String updateStmt = "UPDATE student SET password ='" + new_password.getText() + "' WHERE id_student =" + username;
        if (old_password.getText().equals(password) && !new_password.getText().equals("")&& new_password.getText().matches("^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{8,20}$") && new_password.getText().equals(new1_password.getText())) {

            connectionTCP.writeUtf(updateStmt);
            connectionTCP.writeObject(Command.UPDATE);
            password = new_password.getText();

            old_password.clear();
            new_password.clear();
            new1_password.clear();

            NotificationType notificationType = NotificationType.SUCCESS;
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Пароль успешно изменён");
            tray.setNotificationType(notificationType);
            tray.showAndDismiss(Duration.millis(3000));
        }
        else {
            NotificationType notificationType = NotificationType.NOTICE;
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Проверьте введённые данные");
            tray.setMessage("Кол-во символов: 8-20. Наличие одной цифры\n и одной буквы с нижним индексом!");
            tray.setNotificationType(notificationType);
            tray.showAndDismiss(Duration.millis(3000));
        }
    }

    public void save_email() {
        String updateStmt = "UPDATE student SET email ='"  + email_TextField.getText() +"' WHERE id_student =" + username;
        if (email_TextField.getText().matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}")) {

            connectionTCP.writeUtf(updateStmt);
            connectionTCP.writeObject(Command.UPDATE);
            email_TextField.clear();

            NotificationType notificationType = NotificationType.SUCCESS;
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Почта изменена");
            tray.setNotificationType(notificationType);
            tray.showAndDismiss(Duration.millis(3000));
            initDataEmail();
        }
        else {
            NotificationType notificationType = NotificationType.NOTICE;
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Проверьте введённые вами данные");
            tray.setNotificationType(notificationType);
            tray.showAndDismiss(Duration.millis(3000));
        }
    }

    public void avg_kt1() {
        String selectStmt = "SELECT AVG(ROUND(((mark1.mark1+mark1.mark2)/2),2)) as mark_avg\n" +
                "                                                FROM mark1\n" +
                "                                                   JOIN mark2 ON mark1.id_study_plan=mark2.id_study_plan\n" +
                "                                                 JOIN mark3 ON mark1.id_study_plan=mark3.id_study_plan\n" +
                "                                                 JOIN student ON mark1.id_student=student.id_student \n" +
                "                                                 JOIN groupa ON student.id_group=groupa.id_group \n" +
                "                                               JOIN spec ON groupa.code_spec=spec.code_spec\n" +
                "                                               JOIN faculty ON spec.code_faculty=faculty.code_faculty\n" +
                "                                                 JOIN year ON groupa.code_year=year.code_year \n" +
                "                                                  JOIN study_plan ON mark1.id_study_plan=study_plan.id_study_plan\n" +
                "                                                  JOIN subject ON study_plan.code_subject=subject.code_subject\n" +
                "                                                  WHERE student.id_student='"+username+"'\n" +
                "                                                GROUP BY  student.id_student";
        kt_marks(selectStmt,kt1);
    }

    public void avg_kt2() {
        String selectStmt = "SELECT AVG(ROUND(((mark1.mark1+mark1.mark2+mark2.mark3+mark2.mark4)/4),2)) as mark_avg\n" +
                "                                                FROM mark1\n" +
                "                                                   JOIN mark2 ON mark1.id_study_plan=mark2.id_study_plan\n" +
                "                                                 JOIN mark3 ON mark1.id_study_plan=mark3.id_study_plan\n" +
                "                                                 JOIN student ON mark1.id_student=student.id_student \n" +
                "                                                 JOIN groupa ON student.id_group=groupa.id_group \n" +
                "                                               JOIN spec ON groupa.code_spec=spec.code_spec\n" +
                "                                               JOIN faculty ON spec.code_faculty=faculty.code_faculty\n" +
                "                                                 JOIN year ON groupa.code_year=year.code_year \n" +
                "                                                  JOIN study_plan ON mark1.id_study_plan=study_plan.id_study_plan\n" +
                "                                                  JOIN subject ON study_plan.code_subject=subject.code_subject\n" +
                "                                                  WHERE student.id_student='"+username+"'\n" +
                "                                                GROUP BY  student.id_student";
        kt_marks(selectStmt,kt2);
    }

    public void avg_kt3() {
        String selectStmt = "SELECT AVG(ROUND(((mark1.mark1+mark1.mark2+mark2.mark3+mark2.mark4+mark3.mark5+mark3.mark6)/6),2)) as mark_avg\n" +
                "                                                FROM mark1\n" +
                "                                                   JOIN mark2 ON mark1.id_study_plan=mark2.id_study_plan\n" +
                "                                                 JOIN mark3 ON mark1.id_study_plan=mark3.id_study_plan\n" +
                "                                                 JOIN student ON mark1.id_student=student.id_student \n" +
                "                                                 JOIN groupa ON student.id_group=groupa.id_group \n" +
                "                                               JOIN spec ON groupa.code_spec=spec.code_spec\n" +
                "                                               JOIN faculty ON spec.code_faculty=faculty.code_faculty\n" +
                "                                                 JOIN year ON groupa.code_year=year.code_year \n" +
                "                                                  JOIN study_plan ON mark1.id_study_plan=study_plan.id_study_plan\n" +
                "                                                  JOIN subject ON study_plan.code_subject=subject.code_subject\n" +
                "                                                  WHERE student.id_student='"+username+"'\n" +
                "                                                GROUP BY  student.id_student";
        kt_marks(selectStmt,kt3);
    }

    public void sem() {
        String selectStmt = """
                SELECT subject.code_subject,mark1.mark1,mark1.mark2,mark2.mark3,mark2.mark4,mark3.mark5,mark3.mark6, AVG(ROUND(((mark1.mark1+mark1.mark2+mark2.mark3+mark2.mark4+mark3.mark5+mark3.mark6 )/6),0)) as mark_avg
                                                                                FROM mark1
                                                                                   JOIN mark2 ON mark1.id_study_plan=mark2.id_study_plan
                                                                                JOIN mark3 ON mark1.id_study_plan=mark3.id_study_plan
                                                                                 JOIN student ON mark1.id_student=student.id_student\s
                                                                                 JOIN groupa ON student.id_group=groupa.id_group\s
                                                                              JOIN spec ON groupa.code_spec=spec.code_spec
                                                                               JOIN faculty ON spec.code_faculty=faculty.code_faculty
                                                                                JOIN year ON groupa.code_year=year.code_year\s
                                                                                  JOIN study_plan ON mark1.id_study_plan=study_plan.id_study_plan
                                                                                  JOIN subject ON study_plan.code_subject=subject.code_subject
                                                                                  WHERE student.id_student='91430002'
                                                                                GROUP BY  student.id_student""";
       kt_marks(selectStmt,sem);
    }

    private void kt_marks(String selectStmt, Text kt) {

        connectionTCP.writeUtf(selectStmt);
        connectionTCP.writeObject(Command.AVG_KT);
        connectionTCP.flush();
        String avg_kt = (String) connectionTCP.readObject();
        kt.setText(avg_kt);
    }

    @FXML
    private void back(){
        mark_anchor.setVisible(false);
    }
    public void location() {
        location_anchor.setVisible(true);
    }
    public void gotit() {
        location_anchor.setVisible(false);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initDataStudent();
        name_text.setDisable(true);
        surname_text.setDisable(true);
        id_text.setDisable(true);
        subject_column.setCellValueFactory(cellData -> cellData.getValue().disciplineProperty());
        avg_column.setCellValueFactory(cellData -> cellData.getValue().avgProperty().asObject());
        mark1_column.setCellValueFactory(cellData -> cellData.getValue().mark1Property().asObject());
        mark3_column.setCellValueFactory(cellData -> cellData.getValue().mark3Property().asObject());
        mark4_column.setCellValueFactory(cellData -> cellData.getValue().mark4Property().asObject());
        mark5_column.setCellValueFactory(cellData -> cellData.getValue().mark5Property().asObject());
        mark6_column.setCellValueFactory(cellData -> cellData.getValue().mark6Property().asObject());
        mark2_column.setCellValueFactory(cellData -> cellData.getValue().mark2Property().asObject());
        initDataDate1();
        initDataDate2();
        initDataDate3();
        location_btn.setGraphic(locate);
        mark_btn.setGraphic(mark_img);

        btn_logout.setOnAction(event -> DBUtilis.changeScene(event,"student_homescreen.fxml", "Главная страница", username));
    }
}
