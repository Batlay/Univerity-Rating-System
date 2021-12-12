package Client.AdminController;

import Server.Command;
import Client.DBUtilis;
import Client.Entity.Student;
import Client.Entity.StudentO;
import Client.Entity.Subject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;

import static Client.LoginController.connectionTCP;
import static Client.LoginController.username;

public class AdminCabinet implements Initializable {

    @FXML
    private Button btn_logout;
    @FXML
    private Button btn_student;
    @FXML
    private Button btn_teacher;
    @FXML
    private SplitPane anchor_student;
    @FXML
    private SplitPane anchor_teacher;
    @FXML
    private Button btn_spec;
    @FXML
    private Button btn_group;
    @FXML
    private Button save_student_btn;
    @FXML
    private Button save_teacher_btn;
    @FXML
    private Button cancel_student_btn;
    @FXML
    private Button cancel_teacher_btn;
    @FXML
    private Button btn_submit;
    @FXML
    private Button add_teacher_btn;
    @FXML
    private Button add_student_btn1;
    @FXML
    private Button t_choose;
    @FXML
    private AnchorPane news_anchor;
    @FXML
    private AnchorPane rating_anchor;
    @FXML
    private AnchorPane group_anchor;
    @FXML
    private AnchorPane subject_anchor;
    @FXML
    private TableView<Student> students_tableview;
    @FXML
    private TableView<Student> teacher_tableview;
    @FXML
    private TableView<Subject> subject_tableview;
    @FXML
    private TableColumn<Student, String>  stid_column;
    @FXML
    private TableColumn<Student, String>  stid_column1;
    @FXML
    private TableColumn<Student, String>  address_column;
    @FXML
    private TableColumn<Student, String>  firstname_column;
    @FXML
    private TableColumn<Student, String>  lastname_column;
    @FXML
    private TableColumn<Student, String>  middlename_column;
    @FXML
    private TableColumn<Student, String>  password_column;
    @FXML
    private TableColumn<Student, String>  phone_column;
    @FXML
    private TableColumn<Student, String>  email_column;
    @FXML
    private TableColumn<Student, String>  password_column1;
    @FXML
    private TableColumn<Student, String>  phone_column1;
    @FXML
    private TableColumn<Student, String>  email_column1;
    @FXML
    private TableColumn<Student, String>  address_column1;
    @FXML
    private TableColumn<Student, String>  name_column;
    @FXML
    private TableColumn<Student, String>  username_column1;
    @FXML
    private TableColumn<Student, String>  role_column;
    @FXML
    private TableColumn<Student, String>  spec_column;
    @FXML
    private TableColumn<Student, Integer>  group_column;
    @FXML
    private TableColumn<Subject, String>  form_column;
    @FXML
    private TableColumn<Subject, String>  teacher_column;
    @FXML
    private TableColumn<Subject, String>  namesub_column;
    @FXML
    private ComboBox<String> group;
    @FXML
    private ComboBox<String> faculty;
    @FXML
    private ComboBox<String> spec;
    @FXML
    private ComboBox<String> group_combobox;
    @FXML
    private ComboBox<String> faculty_combobox;
    @FXML
    private ComboBox<String> spec_combobox;
    @FXML
    private ComboBox<String> t_group;
    @FXML
    private ComboBox<String> t_sub;
    @FXML
    private TextField id_textField;
    @FXML
    private TextField name_textField;
    @FXML
    private TextField lastname_textField;
    @FXML
    private TextField middlename_textField;
    @FXML
    private TextField email_textField;
    @FXML
    private TextField address_textField;
    @FXML
    private TextField phone_textField;
    @FXML
    private TextField password_textField;
    @FXML
    private TextField search_id_textField;
    @FXML
    private TextField search_id_textField1;
    @FXML
    private TextField t_id;
    @FXML
    private TextField t_role;
    @FXML
    private TextField t_email;
    @FXML
    private TextField t_password;
    @FXML
    private TextField t_phone;
    @FXML
    private TextField t_username;
    @FXML
    private TextField t_name;
    @FXML
    private TextField t_address;
    @FXML
    Text tName;
    @FXML
    Text date1;
    @FXML
    Text date2;
    @FXML
    Text date3;


    public static String gr;
    public static String facult;
    public static String sp;
    private final  ObservableList<Student> data_teacher = FXCollections.observableArrayList();
    private final  ObservableList<Student> data = FXCollections.observableArrayList();
    private final  ObservableList<Subject> sub = FXCollections.observableArrayList();
    private final  ObservableList<String> string_faculty = FXCollections.observableArrayList();
    private final  ObservableList<String> string_group = FXCollections.observableArrayList();
    private final  ObservableList<String> string_spec = FXCollections.observableArrayList();
    private final  ObservableList<String> string_faculty1 = FXCollections.observableArrayList();
    private final  ObservableList<String> string_group1 = FXCollections.observableArrayList();
    private final  ObservableList<String> string_spec1 = FXCollections.observableArrayList();
    private final  ObservableList<String> string_subject = FXCollections.observableArrayList();

    @FXML
    private void search_choice() {

        gr = group.getValue().trim();
        sp = spec.getValue().trim();
        facult = faculty.getValue().trim();


        String selectStmt = "SELECT student.id_student,student.last_name,student.first_name, student.middle_name,student.id_group,groupa.code_spec,student.email,student.phone_number,student.password,student.address\n" +
                "                               FROM student\n" +
                "                JOIN groupa ON student.id_group=groupa.id_group\n" +
                "                 JOIN spec ON groupa.code_spec=spec.code_spec\n" +
                "                 JOIN faculty ON spec.code_faculty=faculty.code_faculty\n" +
                "                 WHERE faculty.code_faculty='"+facult+"' AND spec.code_spec='"+sp+"' AND groupa.id_group='"+gr+"'";

        data.clear();
        connectionTCP.writeUtf(selectStmt);
        connectionTCP.writeObject(Command.ADMINSTUDENTS);
        connectionTCP.flush();

        List<StudentO> students  = (List<StudentO>) connectionTCP.readObject();
        for (StudentO student : students) {
            Student e = new Student(student);
            data.add(e);
        }
        students_tableview.setItems(data);
    }

    @FXML
    private void search_choice1() {


        String selectStmt = "SELECT id_teacher, name,username,role,email,phone,password,address\n" +
                "FROM teacher";
        data_teacher.clear();
        connectionTCP.writeUtf(selectStmt);
        connectionTCP.writeObject(Command.ADMINTEACHER);
        connectionTCP.flush();

        List<StudentO> students  = (List<StudentO>) connectionTCP.readObject();
        for (StudentO student : students) {
            Student e = new Student(student);
            data_teacher.add(e);
        }
        teacher_tableview.setItems(data_teacher);
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
    public void select_faculty1() {
        String selectStmt = "select code_faculty from faculty";
        string_faculty1.clear();
        connectionTCP.writeUtf(selectStmt);
        connectionTCP.writeObject(Command.READFACULTY);
        connectionTCP.flush();
        List<String> facul  = (List<String>) connectionTCP.readObject();
        string_faculty1.addAll(facul);
        faculty_combobox.setItems(string_faculty1);
    }
    public void select_group() {
        String selectStmt = "SELECT study_plan.id_group\n" +
                "                FROM study_plan \n" +
                "                JOIN teacher ON study_plan.id_teacher=teacher.id_teacher\n" +
                "\t\t\t\tWHERE teacher.username ='"+t_username.getText()+"'\n" +
                "                group by study_plan.id_group";
        string_group1.clear();
        connectionTCP.writeUtf(selectStmt);
        connectionTCP.writeObject(Command.READGROUPCB);
        connectionTCP.flush();
        List<String> facul  = (List<String>) connectionTCP.readObject();
        string_group1.addAll(facul);
        t_group.setItems(string_group1);
    }
    public void select_subject() {

        String selectStmt = "SELECT study_plan.code_subject\n" +
                "                FROM study_plan \n" +
                "                JOIN teacher ON study_plan.id_teacher=teacher.id_teacher\n" +
                "\t\t\t\tWHERE teacher.username ='"+t_username.getText()+"' AND id_group='"+t_group.getValue()+"'\n" +
                "                group by study_plan.code_subject";
        string_subject.clear();
        connectionTCP.writeUtf(selectStmt);
        connectionTCP.writeObject(Command.READSUBJECTCB);
        connectionTCP.flush();
        List<String> subject  = (List<String>) connectionTCP.readObject();
        string_subject.addAll(subject);
        t_sub.setItems(string_subject);
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
    private void faculty1() {
        String selectStmt = "select code_spec from spec WHERE code_faculty='"+faculty_combobox.getValue()+"'";
        string_spec.clear();
        connectionTCP.writeUtf(selectStmt);
        connectionTCP.writeObject(Command.READSPEC);
        connectionTCP.flush();
        List<String> faculty  = (List<String>) connectionTCP.readObject();
        string_spec.addAll(faculty);
        spec_combobox.setItems(string_spec);
    }

    @FXML
    private void choose_subject() {
       select_subject();
    }

    @FXML
    private void student() {
        anchor_student.setVisible(true);
        anchor_teacher.setVisible(false);
    }

    @FXML
    private void teacher() {
        anchor_student.setVisible(false);
        anchor_teacher.setVisible(true);
    }

    @FXML
    private void edit_student() {
        cancel_student_btn.setDisable(false);
        save_student_btn.setDisable(false);
        id_textField.setDisable(false);
        name_textField.setDisable(false);
        lastname_textField.setDisable(false);
        middlename_textField.setDisable(false);
        address_textField.setDisable(false);
        phone_textField.setDisable(false);
        email_textField.setDisable(false);
        password_textField.setDisable(false);
        id_textField.setText(students_tableview.getSelectionModel().getSelectedItem().getSt_Id());
        name_textField.setText(students_tableview.getSelectionModel().getSelectedItem().getFirstName());
        lastname_textField.setText(students_tableview.getSelectionModel().getSelectedItem().getLastName());
        middlename_textField.setText(students_tableview.getSelectionModel().getSelectedItem().getMiddleName());
        address_textField.setText(students_tableview.getSelectionModel().getSelectedItem().getAddress());
        phone_textField.setText(students_tableview.getSelectionModel().getSelectedItem().getPhoneNumber());
        email_textField.setText(students_tableview.getSelectionModel().getSelectedItem().getEmail());
        password_textField.setText(students_tableview.getSelectionModel().getSelectedItem().getPassword());
    }
    @FXML
    private void edit_teacher() {
        cancel_teacher_btn.setDisable(false);
        save_teacher_btn.setDisable(false);
        t_group.setDisable(false);
        t_sub.setDisable(false);
        t_choose.setDisable(false);
        t_id.setDisable(false);
        t_role.setDisable(false);
        t_address.setDisable(false);
        t_email.setDisable(false);
        t_name.setDisable(false);
        t_username.setDisable(false);
        t_phone.setDisable(false);
        t_password.setDisable(false);
        t_id.setText(teacher_tableview.getSelectionModel().getSelectedItem().getSt_Id());
        t_role.setText(teacher_tableview.getSelectionModel().getSelectedItem().getRole());
        t_address.setText(teacher_tableview.getSelectionModel().getSelectedItem().getAddress());
        t_email.setText(teacher_tableview.getSelectionModel().getSelectedItem().getEmail());
        t_name.setText(teacher_tableview.getSelectionModel().getSelectedItem().getName());
        t_username.setText(teacher_tableview.getSelectionModel().getSelectedItem().getUsername());
        t_phone.setText(teacher_tableview.getSelectionModel().getSelectedItem().getPhoneNumber());
        t_password.setText(teacher_tableview.getSelectionModel().getSelectedItem().getPassword());
        select_group();
    }

    @FXML
    private void save_student() {
        String updateStmt = "UPDATE student\n" +
                "SET last_name='"+lastname_textField.getText()+"', first_name='"+name_textField.getText()+"',middle_name='"+middlename_textField.getText()+"',id_student='"+id_textField.getText()+"',email='"+email_textField.getText()+"',phone_number='"+phone_textField.getText()+"',password = '"+password_textField.getText()+"', address='"+address_textField.getText()+"'\n" +
                "WHERE id_student='"+students_tableview.getSelectionModel().getSelectedItem().getSt_Id()+"'";

        connectionTCP.writeUtf(updateStmt);
        connectionTCP.writeObject(Command.UPDATE);
        t_group.setDisable(true);
        t_sub.setDisable(true);
        t_choose.setDisable(true);
        id_textField.setDisable(true);
        id_textField.clear();
        name_textField.setDisable(true);
        name_textField.clear();
        lastname_textField.setDisable(true);
        lastname_textField.clear();
        middlename_textField.setDisable(true);
        middlename_textField.clear();
        address_textField.setDisable(true);
        address_textField.clear();
        phone_textField.setDisable(true);
        phone_textField.clear();
        email_textField.setDisable(true);
        email_textField.clear();
        password_textField.setDisable(true);
        password_textField.clear();
        cancel_student_btn.setDisable(true);
        save_student_btn.setDisable(true);
        search_choice1();
    }
    @FXML
    private void save_teacher() {
        String updateStmt = "UPDATE teacher\n" +
                "SET name='"+t_name.getText()+"', password='"+t_password.getText()+"',phone='"+t_phone.getText()+"',address='"+t_address.getText()+"',email='"+t_email.getText()+"',username='"+t_username.getText()+"',role = '"+t_role.getText()+"', id_teacher='"+t_id.getText()+"'\n" +
                "WHERE id_teacher='"+teacher_tableview.getSelectionModel().getSelectedItem().getSt_Id()+"'";

        connectionTCP.writeUtf(updateStmt);
        connectionTCP.writeObject(Command.UPDATE);
        t_id.setDisable(true);
        t_id.clear();
        t_role.setDisable(true);
        t_role.clear();
        t_phone.setDisable(true);
        t_phone.clear();
        t_password.setDisable(true);
        t_password.clear();
        t_address.setDisable(true);
        t_address.clear();
        t_name.setDisable(true);
        t_name.clear();
        t_username.setDisable(true);
        t_username.clear();
        t_email.setDisable(true);
        t_email.clear();
        cancel_teacher_btn.setDisable(true);
        add_teacher_btn.setDisable(true);
        t_group.setDisable(true);
        t_group.getItems().clear();
        t_sub.setDisable(true);
        t_choose.setDisable(true);
        save_teacher_btn.setDisable(true);
        search_choice1();
    }

    @FXML
    private void search_id(){
        String selectStmt="SELECT student.id_student,student.last_name,student.first_name, student.middle_name,student.id_group,groupa.code_spec,student.email,student.phone_number,student.password,student.address\n" +
        "                               FROM student\n" +
                "                JOIN groupa ON student.id_group=groupa.id_group\n" +
                "                 WHERE id_student='"+search_id_textField.getText()+"'";

        data.clear();
        connectionTCP.writeUtf(selectStmt);
        connectionTCP.writeObject(Command.ADMINSTUDENTS);
        connectionTCP.flush();
        List<StudentO> students  = (List<StudentO>) connectionTCP.readObject();
        for (StudentO student : students) {
            Student e = new Student(student);
            data.add(e);
        }
        students_tableview.setItems(data);
    }
    @FXML
    private void search_id1(){
        String selectStmt="SELECT id_teacher, name,username,role,email,phone,password,address\n" +
        "FROM teacher\n" +
                "         WHERE id_teacher='"+search_id_textField1.getText()+"'";

        data_teacher.clear();
        connectionTCP.writeUtf(selectStmt);
        connectionTCP.writeObject(Command.ADMINTEACHER);
        connectionTCP.flush();
        List<StudentO> students  = (List<StudentO>) connectionTCP.readObject();
        for (StudentO student : students) {
            Student e = new Student(student);
            data_teacher.add(e);
        }
        teacher_tableview.setItems(data_teacher);
    }

    @FXML
    private void delete() {
        id_textField.setText(students_tableview.getSelectionModel().getSelectedItem().getSt_Id());
        String updateStmt = "DELETE FROM student WHERE id_student='"+id_textField.getText()+"';";
        connectionTCP.writeUtf(updateStmt);
        connectionTCP.writeObject(Command.UPDATE);
        id_textField.clear();
        search_choice();
    }

    @FXML
    private void delete_teacher() {
        t_id.setText(teacher_tableview.getSelectionModel().getSelectedItem().getSt_Id());
        String updateStmt = "DELETE FROM teacher WHERE id_teacher='"+t_id.getText()+"';";
        connectionTCP.writeUtf(updateStmt);
        connectionTCP.writeObject(Command.UPDATE);
        t_id.clear();
        search_choice1();
    }


    @FXML
    private void add_student() {
        select_faculty1();
        cancel_student_btn.setDisable(false);
        add_student_btn1.setDisable(false);
        save_student_btn.setDisable(true);
        btn_spec.setDisable(false);
        btn_group.setDisable(false);
        faculty_combobox.setDisable(false);
        spec_combobox.setDisable(false);
        group_combobox.setDisable(false);
        id_textField.setDisable(false);
        name_textField.setDisable(false);
        lastname_textField.setDisable(false);
        middlename_textField.setDisable(false);
        address_textField.setDisable(false);
        phone_textField.setDisable(false);
        email_textField.setDisable(false);
        password_textField.setDisable(false);
    }
    @FXML
    private void change_group(){
        select_faculty1();
        cancel_student_btn.setDisable(false);
        save_student_btn.setDisable(true);
        btn_spec.setDisable(false);
        btn_group.setDisable(false);
        faculty_combobox.setDisable(false);
        spec_combobox.setDisable(false);
        group_combobox.setDisable(false);
        btn_submit.setDisable(false);
        id_textField.setDisable(true);
        id_textField.clear();
        name_textField.setDisable(true);
        name_textField.clear();
        lastname_textField.setDisable(true);
        lastname_textField.clear();
        middlename_textField.setDisable(true);
        middlename_textField.clear();
        address_textField.setDisable(true);
        address_textField.clear();
        phone_textField.setDisable(true);
        phone_textField.clear();
        email_textField.setDisable(true);
        email_textField.clear();
        password_textField.setDisable(true);
        password_textField.clear();

    }
    @FXML
    private void submit(){
        String updateStmt = "UPDATE student\n" +
                "SET id_group='"+group_combobox.getValue()+"'\n" +
                "WHERE id_student='"+students_tableview.getSelectionModel().getSelectedItem().getSt_Id()+"'";

        connectionTCP.writeUtf(updateStmt);
        connectionTCP.writeObject(Command.UPDATE);
        cancel_student_btn.setDisable(true);
        save_student_btn.setDisable(true);
        btn_spec.setDisable(true);
        btn_group.setDisable(true);
        faculty_combobox.setDisable(true);
        spec_combobox.setDisable(true);
        group_combobox.setDisable(true);
        faculty_combobox.getItems().clear();
        spec_combobox.getItems().clear();
        group_combobox.getItems().clear();
        btn_submit.setDisable(true);
    }
    @FXML
    private void add_student1() {
        String updateStmt = "INSERT INTO student (id_student,first_name,last_name,middle_name,id_group,password,phone_number,email,address)\n" +
                "VALUES ('"+id_textField.getText()+"','"+name_textField.getText()+"','"+lastname_textField.getText()+"','"+middlename_textField.getText()+"','"+group_combobox.getValue().trim()+"','"+password_textField.getText()+"','"+phone_textField.getText()+"','"+email_textField.getText()+"','"+address_textField.getText()+"')";
        connectionTCP.writeUtf(updateStmt);
        connectionTCP.writeObject(Command.UPDATE);
        id_textField.setDisable(true);
        id_textField.clear();
        name_textField.setDisable(true);
        name_textField.clear();
        lastname_textField.setDisable(true);
        lastname_textField.clear();
        middlename_textField.setDisable(true);
        middlename_textField.clear();
        address_textField.setDisable(true);
        address_textField.clear();
        phone_textField.setDisable(true);
        phone_textField.clear();
        email_textField.setDisable(true);
        email_textField.clear();
        password_textField.setDisable(true);
        password_textField.clear();
        faculty_combobox.setDisable(true);
        spec_combobox.setDisable(true);
        group_combobox.setDisable(true);
        faculty_combobox.getItems().clear();
        spec_combobox.getItems().clear();
        group_combobox.getItems().clear();
        btn_spec.setDisable(true);
        btn_group.setDisable(true);
        cancel_student_btn.setDisable(true);
        add_student_btn1.setDisable(true);
        search_choice();
    }
    @FXML
    private void cancel_student(){
        id_textField.setDisable(true);
        id_textField.clear();
        name_textField.setDisable(true);
        name_textField.clear();
        lastname_textField.setDisable(true);
        lastname_textField.clear();
        middlename_textField.setDisable(true);
        middlename_textField.clear();
        address_textField.setDisable(true);
        address_textField.clear();
        phone_textField.setDisable(true);
        phone_textField.clear();
        email_textField.setDisable(true);
        email_textField.clear();
        password_textField.setDisable(true);
        password_textField.clear();
        faculty_combobox.setDisable(true);
        spec_combobox.setDisable(true);
        group_combobox.setDisable(true);
        faculty_combobox.getItems().clear();
        spec_combobox.getItems().clear();
        group_combobox.getItems().clear();
        btn_spec.setDisable(true);
        btn_group.setDisable(true);
        cancel_student_btn.setDisable(true);
        save_student_btn.setDisable(true);
        add_student_btn1.setDisable(true);
    }
    @FXML
    private void cancel_teacher(){
        add_teacher_btn.setDisable(true);
        save_teacher_btn.setDisable(true);
        t_id.setDisable(true);
        t_name.setDisable(true);
        t_email.setDisable(true);
        t_address.setDisable(true);
        t_password.setDisable(true);
        t_phone.setDisable(true);
        t_role.setDisable(true);
        t_username.setDisable(true);
        t_id.clear();
        t_name.clear();
        t_email.clear();
        t_address.clear();
        t_password.clear();
        t_phone.clear();
        t_role.clear();
        t_username.clear();
        t_group.getItems().clear();
        t_sub.getItems().clear();
        t_group.setDisable(true);
        t_sub.setDisable(true);
        t_choose.setDisable(true);
        cancel_teacher_btn.setDisable(true);
    }
    @FXML
    private void add_teacher() {
        add_teacher_btn.setDisable(false);
        save_teacher_btn.setDisable(true);
        t_id.setDisable(false);
        t_name.setDisable(false);
        t_email.setDisable(false);
        t_address.setDisable(false);
        t_password.setDisable(false);
        t_phone.setDisable(false);
        t_role.setDisable(false);
        t_username.setDisable(false);
        cancel_teacher_btn.setDisable(false);
    }
    @FXML
    private void add_teacher1() {
        String updateStmt = "INSERT INTO teacher (id_teacher,username,password,name,email,phone,role,address)\n" +
                "VALUES ('"+t_id.getText()+"','"+t_username.getText()+"','"+t_password.getText()+"','"+t_name.getText()+"','"+t_email.getText()+"','"+t_phone.getText()+"','"+t_role.getText()+"','"+t_address.getText()+"')";
        connectionTCP.writeUtf(updateStmt);
        connectionTCP.writeObject(Command.UPDATE);
        add_teacher_btn.setDisable(true);
        t_id.setDisable(true);
        t_name.setDisable(true);
        t_email.setDisable(true);
        t_address.setDisable(true);
        t_password.setDisable(true);
        t_phone.setDisable(true);
        t_role.setDisable(true);
        t_username.setDisable(true);
        t_id.clear();
        t_name.clear();
        t_email.clear();
        t_address.clear();
        t_password.clear();
        t_phone.clear();
        t_role.clear();
        t_username.clear();
        cancel_teacher_btn.setDisable(true);
        search_choice1();
    }


    @FXML
    private void speciality() {
        String selectStmt = "select id_group from groupa WHERE code_spec='"+spec.getValue()+"'";
        string_group.clear();
        connectionTCP.writeUtf(selectStmt);
        connectionTCP.writeObject(Command.READGROUPCB);
        connectionTCP.flush();
        List<String> faculty  = (List<String>) connectionTCP.readObject();
        string_group.addAll(faculty);
        group.setItems(string_group);
    }

    @FXML
    private void speciality1() {
        String selectStmt = "select id_group from groupa WHERE code_spec='"+spec_combobox.getValue()+"'";
        string_group1.clear();
        connectionTCP.writeUtf(selectStmt);
        connectionTCP.writeObject(Command.READGROUPCB);
        connectionTCP.flush();
        List<String> faculty  = (List<String>) connectionTCP.readObject();
        string_group1.addAll(faculty);
        group_combobox.setItems(string_group1);
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        stid_column.setCellValueFactory(cellData -> cellData.getValue().stIdProperty());
        stid_column1.setCellValueFactory(cellData -> cellData.getValue().stIdProperty());
        password_column.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
        password_column1.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
        firstname_column.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastname_column.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        name_column.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        username_column1.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        role_column.setCellValueFactory(cellData -> cellData.getValue().roleProperty());
        middlename_column.setCellValueFactory(cellData -> cellData.getValue().middleNameProperty());
        group_column.setCellValueFactory(cellData -> cellData.getValue().groupProperty().asObject());
        phone_column.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
        email_column.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        address_column.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        phone_column1.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
        email_column1.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        address_column1.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        spec_column.setCellValueFactory(cellData -> cellData.getValue().specProperty());
      /*  form_column.setCellValueFactory(cellData -> cellData.getValue().formProperty());
        teacher_column.setCellValueFactory(cellData -> cellData.getValue().teacherProperty());
        namesub_column.setCellValueFactory(cellData -> cellData.getValue().nameProperty());*/

        select_faculty();


        btn_logout.setOnAction(event -> DBUtilis.changeScene(event,"admin_homescreen.fxml", "Главная страница", null));
    }

}
