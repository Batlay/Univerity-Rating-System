package Client.TeacherController;

import Server.Command;
import Client.DBUtilis;
import Client.Entity.Subject;
import Client.Entity.SubjectO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import static Client.LoginController.*;
import static Client.LoginController.connectionTCP;

public class TeacherCabinet implements Initializable {

    @FXML
    private Button btn_logout;
    @FXML
    Text name;
    @FXML
    Text id;
    @FXML
    Text date1;
    @FXML
    Text date2;
    @FXML
    Text date3;
    @FXML
    Text email_text;
    @FXML
    TextField name_text;
    @FXML
    TextField email_TextField;
    @FXML
    TextField old_password;
    @FXML
    TextField mark1;
    @FXML
    TextField mark2;
    @FXML
    TextField mark3;
    @FXML
    TextField mark4;
    @FXML
    TextField mark5;
    @FXML
    TextField mark6;
    @FXML
    PasswordField new_password;
    @FXML
    PasswordField new1_password;
    @FXML
    Text kt1;
    @FXML
    Text sp;
    @FXML
    Text kt2;
    @FXML
    Text kt3;
    @FXML
    Text sem;
    @FXML
    TextField role_text;
    @FXML
    TextField phone_text;
    @FXML
    ComboBox group_combobox;
    @FXML
    ComboBox subj;

    @FXML
    private TableColumn<Subject, String>  stid_column;
    @FXML
    private TableColumn<Subject, Double>  avg_column;
    @FXML
    private TableColumn<Subject, String>  name_column;
    @FXML
    private TableColumn<Subject, String>  lastname_column;
    @FXML
    private TableColumn<Subject, Integer>  mark1_column;
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
    private TableView<Subject> group_tableview;
    @FXML
    private AnchorPane mark_anchor;

    private final  ObservableList<Subject> sub = FXCollections.observableArrayList();
    private final  ObservableList<String> string_group = FXCollections.observableArrayList();
    private final  ObservableList<String> string_subject = FXCollections.observableArrayList();


    public void study_plan() {
            String selectStmt = "SELECT id_study_plan\n" +
                    "from study_plan\n" +
                    "JOIN teacher ON study_plan.id_teacher=teacher.id_teacher\n" +
                    "where id_group='"+group_combobox.getValue()+"' AND teacher.username='"+username+"' AND code_subject='"+subj.getValue()+"'";
            connectionTCP.writeUtf(selectStmt);
            connectionTCP.writeObject(Command.READSTUDYPLAN);
            connectionTCP.flush();
            String stud_plan= (String) connectionTCP.readObject();
            sp.setText(stud_plan);
    }

    public void select_group() {
            String selectStmt = "SELECT study_plan.id_group\n" +
                    "                FROM study_plan \n" +
                    "                JOIN teacher ON study_plan.id_teacher=teacher.id_teacher\n" +
                    "\t\t\t\tWHERE teacher.username ='"+username+"'\n" +
                    "                group by study_plan.id_group";
            string_group.clear();
            connectionTCP.writeUtf(selectStmt);
            connectionTCP.writeObject(Command.READGROUPCB);
            connectionTCP.flush();
            List<String> facul  = (List<String>) connectionTCP.readObject();
            string_group.addAll(facul);
            group_combobox.setItems(string_group);

    }
    public void select_subject() {

            String selectStmt = "SELECT study_plan.code_subject\n" +
                    "                FROM study_plan \n" +
                    "                JOIN teacher ON study_plan.id_teacher=teacher.id_teacher\n" +
                    "\t\t\t\tWHERE teacher.username ='"+username+"'\n" +
                    "                group by study_plan.code_subject";
            string_subject.clear();
            connectionTCP.writeUtf(selectStmt);
            connectionTCP.writeObject(Command.READSUBJECTCB);
            connectionTCP.flush();
            List<String> subject  = (List<String>) connectionTCP.readObject();
            string_subject.addAll(subject);
            subj.setItems(string_subject);
    }

    @FXML
    private void load_group() throws ParseException {
        mark_anchor.setVisible(false);
        Date now = new Date();
        Date d1=new SimpleDateFormat("yyyy-MM-dd").parse(date1.getText());
        Date d2 =new SimpleDateFormat("yyyy-MM-dd").parse(date2.getText());
        Date d3 =new SimpleDateFormat("yyyy-MM-dd").parse(date3.getText());
        String selectStmt = "SELECT student.last_name,student.first_name, mark1.mark1, mark1.mark2, mark2.mark3,mark2.mark4,mark3.mark5,mark3.mark6, ROUND(((mark1.mark1+mark1.mark2)/2),0) as mark_avg, student.id_student\n" +
                "            FROM mark1\n" +
                "                JOIN mark2 ON mark1.id_study_plan=mark2.id_study_plan\n" +
                "                JOIN mark3 ON mark1.id_study_plan=mark3.id_study_plan\n" +
                "                JOIN student ON mark1.id_student=student.id_student\n" +
                "                JOIN groupa ON student.id_group=groupa.id_group\n" +
                "                JOIN study_plan ON mark1.id_study_plan=study_plan.id_study_plan\n" +
                "            WHERE groupa.id_group='"+group_combobox.getValue()+"' AND study_plan.code_subject='"+subj.getValue()+"'\n" +
                "            GROUP BY  study_plan.id_study_plan";

        String selectStmt1 = "SELECT student.last_name,student.first_name, mark1.mark1, mark1.mark2, mark2.mark3,mark2.mark4,mark3.mark5,mark3.mark6, ROUND(((mark1.mark1+mark1.mark2+mark2.mark3+mark2.mark4)/4),0) as mark_avg, student.id_student\n" +
        "                     FROM mark1\n" +
                "               JOIN mark2 ON mark1.id_study_plan=mark2.id_study_plan\n" +
                "               JOIN mark3 ON mark1.id_study_plan=mark3.id_study_plan\n" +
                "               JOIN student ON mark1.id_student=student.id_student" +
                "               JOIN groupa ON student.id_group=groupa.id_group\n" +
                "               JOIN study_plan ON mark1.id_study_plan=study_plan.id_study_plan\n" +
                "             WHERE groupa.id_group='"+group_combobox.getValue()+"' AND study_plan.code_subject='"+subj.getValue()+"'\n" +
                "             GROUP BY  study_plan.id_study_plan";

        String selectStmt2 = "SELECT student.last_name,student.first_name, mark1.mark1, mark1.mark2, mark2.mark3,mark2.mark4,mark3.mark5,mark3.mark6, ROUND(((mark1.mark1+mark1.mark2+mark2.mark3+mark2.mark4+mark3.mark5+mark3.mark6)/6),0) as mark_avg,student.id_student\n" +
        "                     FROM mark1\n" +
                "               JOIN mark2 ON mark1.id_study_plan=mark2.id_study_plan\n" +
                "               JOIN mark3 ON mark1.id_study_plan=mark3.id_study_plan\n" +
                "               JOIN student ON mark1.id_student=student.id_student\n" +
                "               JOIN groupa ON student.id_group=groupa.id_group\n" +
                "               JOIN study_plan ON mark1.id_study_plan=study_plan.id_study_plan\n" +
                "             WHERE groupa.id_group='"+group_combobox.getValue()+"' AND study_plan.code_subject='"+subj.getValue()+"'\n" +
                "             GROUP BY  study_plan.id_study_plan";

        sub.clear();
        if( now.after(d1) && now.before(d2)) {
            connectionTCP.writeUtf(selectStmt);
            connectionTCP.writeObject(Command.TEACHERMARK);
            mark_list(sub, group_tableview);
        } else if (now.after(d2) && now.before(d3)) {
            connectionTCP.writeUtf(selectStmt1);
            connectionTCP.writeObject(Command.TEACHERMARK);
            mark_list(sub, group_tableview);
        } else if (now.after(d3)){
            connectionTCP.writeUtf(selectStmt2);
            connectionTCP.writeObject(Command.TEACHERMARK);
            mark_list(sub, group_tableview);
        }
        study_plan();
    }

    static void mark_list(ObservableList<Subject> sub, TableView<Subject> mark_tableview) {
        List<SubjectO> subjects  = (List<SubjectO>) connectionTCP.readObject();
        for (SubjectO subject : subjects) {
            Subject w = new Subject(subject);
            sub.add(w);
        }
        mark_tableview.setItems(sub);
    }

    @FXML
    private void select() {
        name.setText(group_tableview.getSelectionModel().getSelectedItem().getFirstName());
        id.setText(group_tableview.getSelectionModel().getSelectedItem().getSt_Id());
        mark_anchor.setVisible(true);
        String selectStmt = "SELECT mark1.mark1,mark1.mark2, mark2.mark3, mark2.mark4, mark3.mark5, mark3.mark6\n" +
                "FROM mark1\n" +
                "JOIN mark2 ON mark1.id_study_plan=mark2.id_study_plan\n" +
                "JOIN mark3 ON mark1.id_study_plan=mark3.id_study_plan\n" +
                "JOIN study_plan ON mark1.id_study_plan=study_plan.id_study_plan\n" +
                "WHERE mark1.id_student='"+id.getText()+"' AND study_plan.id_study_plan='"+sp.getText() +"'\n" +
                "group by mark1.id_student\n";

        connectionTCP.writeUtf(selectStmt);
        connectionTCP.writeObject(Command.TEACHERTABLE);
        connectionTCP.flush();

        String input = (String) connectionTCP.readObject();
        StringTokenizer st = new StringTokenizer(input);
                String m1 = st.nextToken();
                String m2 = st.nextToken();
                String m3 = st.nextToken();
                String m4 = st.nextToken();
                String m5 = st.nextToken();
                String m6 = st.nextToken();
                mark1.setText(m1);
                mark2.setText(m2);
                mark3.setText(m3);
                mark4.setText(m4);
                mark5.setText(m5);
                mark6.setText(m6);
    }

    @FXML
    private void save() throws ParseException {
        String updateStmt =
                "UPDATE mark1\n" +
                        "SET mark1='"+mark1.getText()+"', mark2='"+mark2.getText()+"'\n" +
                        "WHERE id_study_plan='"+sp.getText()+"';\n";
        String updateStmt1 = "UPDATE mark2\n"  +
                "             SET mark3='"+mark3.getText()+"', mark4='"+mark4.getText()+"'\n" +
                "             WHERE id_study_plan='"+sp.getText()+"'";
        String updateStmt2 = "UPDATE mark3\n"  +
                "             SET mark5='"+mark5.getText()+"', mark6='"+mark6.getText()+"'\n" +
                "             WHERE id_study_plan='"+sp.getText()+"'";

        connectionTCP.writeUtf(updateStmt);
        connectionTCP.writeObject(Command.UPDATE);
        connectionTCP.writeUtf(updateStmt1);
        connectionTCP.writeObject(Command.UPDATE);
        connectionTCP.writeUtf(updateStmt2);
        connectionTCP.writeObject(Command.UPDATE);
        mark_anchor.setVisible(false);
        load_group();
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


    public void initDataEmail() {
        String selectStmt = "SELECT email FROM teacher WHERE username = '" + username + "'";
        connectionTCP.writeUtf(selectStmt);
        connectionTCP.writeObject(Command.READEMAIL);
        connectionTCP.flush();
        String email= (String) connectionTCP.readObject();
        email_text.setText(email);
    }

    public void initDataTeacher() {
        String selectStmt = "SELECT name,role,phone,email FROM teacher WHERE username = '" + username + "'";

        connectionTCP.writeUtf(selectStmt);
        connectionTCP.writeObject(Command.TEACHERINFO);
        connectionTCP.flush();

                String input = (String) connectionTCP.readObject();
                StringTokenizer st = new StringTokenizer(input);
                String name = st.nextToken();
                String name1 = st.nextToken();
                String name2 = st.nextToken();
                String role = st.nextToken();
                String phone = st.nextToken();
                String email = st.nextToken();
                name_text.setText(name+" "+name1+" "+name2);
                role_text.setText(role);
                phone_text.setText(phone);
                email_text.setText(email);
            }

    public void save_password() {
        String updateStmt = "UPDATE teacher SET password ='" + new_password.getText() + "' WHERE username ='" + username+"'";
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
        String updateStmt = "UPDATE teacher SET email ='"  + email_TextField.getText() +"' WHERE username ='" + username+"'";
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stid_column.setCellValueFactory(cellData -> cellData.getValue().stIdProperty());
        avg_column.setCellValueFactory(cellData -> cellData.getValue().avgProperty().asObject());
        name_column.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastname_column.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        mark1_column.setCellValueFactory(cellData -> cellData.getValue().mark1Property().asObject());
        mark3_column.setCellValueFactory(cellData -> cellData.getValue().mark3Property().asObject());
        mark4_column.setCellValueFactory(cellData -> cellData.getValue().mark4Property().asObject());
        mark5_column.setCellValueFactory(cellData -> cellData.getValue().mark5Property().asObject());
        mark6_column.setCellValueFactory(cellData -> cellData.getValue().mark6Property().asObject());
        mark2_column.setCellValueFactory(cellData -> cellData.getValue().mark2Property().asObject());
        select_group();
        select_subject();
        initDataDate1();
        initDataDate2();
        initDataDate3();
        initDataTeacher();

        btn_logout.setOnAction(event -> DBUtilis.changeScene(event,"teacher_homescreen.fxml", "Главная страница", username));
    }
}

