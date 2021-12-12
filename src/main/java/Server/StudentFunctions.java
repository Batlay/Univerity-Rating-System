package Server;

import Client.DBUtilis;
import Client.Entity.StudentO;
import Client.Entity.SubjectO;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class StudentFunctions {
    public List<StudentO> getStudentRate(String aaa) throws SQLException, ClassNotFoundException, IOException {

        List<StudentO> students = new ArrayList<>();
        try {
            ResultSet rs = DBUtilis.dbExecuteQuery(aaa);
            while (rs.next()) {

                String st_id = rs.getString("id_student");
                double avg= rs.getDouble("mark_avg");

                StudentO student = new StudentO(st_id, avg);
                students.add(student);
            }
        } catch (SQLException | ClassNotFoundException e){
            System.out.println("Error occurred while getting students information from DB.\n" + e);
            e.printStackTrace();
        }
        return students;
    }
    public boolean logTeacher(String username, String password){
        return DBUtilis.logInTeacher(username,password);
    }

    public boolean logAdmin(String username, String password){
        return DBUtilis.logInAdmin(username,password);
    }

    public boolean logStudent(String username, String password){
        return DBUtilis.logInStudent(username,password);
    }

    public List<StudentO> getAdminStudents(String aaa) throws SQLException, ClassNotFoundException, IOException {

        List<StudentO> students = new ArrayList<>();
        try {
            ResultSet rs = DBUtilis.dbExecuteQuery(aaa);
            while (rs.next()) {

                String st_id = rs.getString("id_student");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String middle_name = rs.getString("middle_name");
                int group = rs.getInt("id_group");
                String spec = rs.getString("code_spec");
                String email = rs.getString("email");
                String phone_number = rs.getString("phone_number");
                String password = rs.getString("password");
                String address = rs.getString("address");

                StudentO student = new StudentO(st_id, first_name,last_name,middle_name,group,spec,email,phone_number,password,address);
                students.add(student);
            }
        } catch (SQLException | ClassNotFoundException e){
            System.out.println("Error occurred while getting students information from DB.\n" + e);
            e.printStackTrace();
        }
        return students;
    }

    public List<StudentO> getAdminTeacher(String aaa) throws SQLException, ClassNotFoundException, IOException {

        List<StudentO> students = new ArrayList<>();
        try {
            ResultSet rs = DBUtilis.dbExecuteQuery(aaa);
            while (rs.next()) {

                String st_id = rs.getString("id_teacher");
                String name = rs.getString("name");
                String username = rs.getString("username");
                String role = rs.getString("role");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String password = rs.getString("password");
                String address = rs.getString("address");


                StudentO student = new StudentO(st_id, name,username,role,email,phone,password,address);
                students.add(student);
            }
        } catch (SQLException | ClassNotFoundException e){
            System.out.println("Error occurred while getting students information from DB.\n" + e);
            e.printStackTrace();
        }
        return students;
    }


    public List<SubjectO> getStudentSubject(String aaa) throws SQLException, ClassNotFoundException, IOException {

        List<SubjectO> subjects = new ArrayList<>();

        try {

            ResultSet rs = DBUtilis.dbExecuteQuery(aaa);

            while (rs.next()) {

                String discipline = rs.getString("code_subject");
                String hours = rs.getString("hours");
                String form =rs.getString("form");
                String teacher = rs.getString("name");
                String name = rs.getString("subject_name");

                SubjectO subject = new SubjectO(discipline,hours,form,teacher,name);
                subjects.add(subject);
            }
        } catch (SQLException | ClassNotFoundException e){
            System.out.println("Error occurred while getting students information from DB.\n" + e);
            e.printStackTrace();
        }

        return subjects;
    }

    public List<StudentO> getStudentGroup(String aaa) throws SQLException, ClassNotFoundException, IOException {

        List<StudentO> group = new ArrayList<>();

        try {
            ResultSet rs = DBUtilis.dbExecuteQuery(aaa);

            while (rs.next()) {

                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String middle_name = rs.getString("middle_name");
                String role = rs.getString("role");
                String phone_number = rs.getString("phone_number");
                String email = rs.getString("email");


                StudentO student = new StudentO(first_name,last_name,middle_name,role,phone_number,email);
                group.add(student);
            }
        } catch (SQLException | ClassNotFoundException e){
            System.out.println("Error occurred while getting students information from DB.\n" + e);
            e.printStackTrace();
        }

        return group;
    }


    public List<SubjectO> getStudentMark(String aaa) throws SQLException, ClassNotFoundException, IOException {

        List<SubjectO> marks = new ArrayList<>();

        try {

            ResultSet rs = DBUtilis.dbExecuteQuery(aaa);

            while (rs.next()) {

                String discipline = rs.getString("code_subject");
                int mark1 = rs.getInt("mark1");
                int mark2 =rs.getInt("mark2");
                int mark3 = rs.getInt("mark3");
                int mark4 = rs.getInt("mark4");
                int mark5 = rs.getInt("mark5");
                int mark6 = rs.getInt("mark6");
                double avg= rs.getDouble("mark_avg");


                SubjectO subject = new SubjectO(discipline,mark1,mark2,mark3,mark4,mark5,mark6,avg);
                marks.add(subject);
            }
        } catch (SQLException | ClassNotFoundException e){
            System.out.println("Error occurred while getting students information from DB.\n" + e);
            e.printStackTrace();
        }

        return marks;
    }

    public List<SubjectO> getTeacherMark(String aaa) throws SQLException, ClassNotFoundException, IOException {

        List<SubjectO> marks = new ArrayList<>();

        try {

            ResultSet rs = DBUtilis.dbExecuteQuery(aaa);

            while (rs.next()) {

                String name = rs.getString("last_name");
                String surname = rs.getString("first_name");
                int mark1 = rs.getInt("mark1");
                int mark2 =rs.getInt("mark2");
                int mark3 = rs.getInt("mark3");
                int mark4 = rs.getInt("mark4");
                int mark5 = rs.getInt("mark5");
                int mark6 = rs.getInt("mark6");
                double avg= rs.getDouble("mark_avg");
                String st_id = rs.getString("id_student");


                SubjectO subject = new SubjectO(name,surname,mark1,mark2,mark3,mark4,mark5,mark6,avg,st_id);
                marks.add(subject);
            }
        } catch (SQLException | ClassNotFoundException e){
            System.out.println("Error occurred while getting students information from DB.\n" + e);
            e.printStackTrace();
        }

        return marks;
    }

    public List<SubjectO> getTeacherSubjects(String aaa) throws SQLException, ClassNotFoundException, IOException {

        List<SubjectO> subjects = new ArrayList<>();

        try {

            ResultSet rs = DBUtilis.dbExecuteQuery(aaa);

            while (rs.next()) {

                String group = rs.getString("id_group");
                int semester = rs.getInt("id_semester");
                String code = rs.getString("code_subject");
                String name =rs.getString("subject_name");

                SubjectO subject = new SubjectO(group,semester,code,name);
                subjects.add(subject);
            }
        } catch (SQLException | ClassNotFoundException e){
            System.out.println("Error occurred while getting students information from DB.\n" + e);
            e.printStackTrace();
        }

        return subjects;
    }

    public String getDate(String selectStmt) throws SQLException, ClassNotFoundException, IOException {

        String date = null;
        try {
            DBUtilis.dbConnect();
            ResultSet rs = DBUtilis.dbExecuteQuery(selectStmt);
            while (rs.next()) {
                date = rs.getString("date");
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return date;
    }

    public List<String> getSpec(String aaa) throws SQLException, ClassNotFoundException, IOException {

        List<String> spec = new ArrayList<>();

        try {
            ResultSet rs = DBUtilis.dbExecuteQuery(aaa);

            while (rs.next()) {

                spec.add(rs.getString("code_spec"));

            }
        } catch (SQLException | ClassNotFoundException e){
            System.out.println("Error occurred while getting students information from DB.\n" + e);
            e.printStackTrace();
        }
        return spec;
    }

    public List<String> getYear(String aaa) throws SQLException, ClassNotFoundException, IOException {

        List<String> year = new ArrayList<>();
        try {
            ResultSet rs = DBUtilis.dbExecuteQuery(aaa);
            while (rs.next()) {
                year.add(rs.getString("code_year"));
            }
        } catch (SQLException | ClassNotFoundException e){
            System.out.println("Error occurred while getting students information from DB.\n" + e);
            e.printStackTrace();
        }
        return year;
    }

    public List<String> getFaculty(String aaa) throws SQLException, ClassNotFoundException, IOException {

        List<String> faculty = new ArrayList<>();
        try {
            ResultSet rs = DBUtilis.dbExecuteQuery(aaa);
            while (rs.next()) {
                faculty .add(rs.getString("code_faculty"));
            }
        } catch (SQLException | ClassNotFoundException e){
            System.out.println("Error occurred while getting students information from DB.\n" + e);
            e.printStackTrace();
        }
        return faculty ;
    }

    public List<String> getGroupCB(String aaa) throws SQLException, ClassNotFoundException, IOException {

        List<String> group = new ArrayList<>();
        try {
            ResultSet rs = DBUtilis.dbExecuteQuery(aaa);
            while (rs.next()) {
                group.add(rs.getString("id_group"));
            }
        } catch (SQLException | ClassNotFoundException e){
            System.out.println("Error occurred while getting students information from DB.\n" + e);
            e.printStackTrace();
        }
        return group ;
    }

    public List<String> getSubject(String aaa) throws SQLException, ClassNotFoundException, IOException {

        List<String> group = new ArrayList<>();
        try {
            ResultSet rs = DBUtilis.dbExecuteQuery(aaa);
            while (rs.next()) {
                group.add(rs.getString("code_subject"));
            }
        } catch (SQLException | ClassNotFoundException e){
            System.out.println("Error occurred while getting students information from DB.\n" + e);
            e.printStackTrace();
        }
        return group ;
    }


    public String getGroup(String selectStmt) throws SQLException, ClassNotFoundException, IOException {

        String group = null;
        try {
            DBUtilis.dbConnect();
            ResultSet rs = DBUtilis.dbExecuteQuery(selectStmt);
            while (rs.next()) {
                group = rs.getString("id_group");
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return group;
    }

    public String getStudentInfo(String selectStmt) throws SQLException, ClassNotFoundException, IOException {
        String st_info = null;
        try {
            DBUtilis.dbConnect();
            ResultSet rs = DBUtilis.dbExecuteQuery(selectStmt);
            while (rs.next()) {
                String name = rs.getString("last_name");
                String name1 = rs.getString("first_name");
                String name2 = rs.getString("middle_name");
                String lname = rs.getString("id_group");
                String id = rs.getString("id_student");
                String email = rs.getString("email");
                st_info = name + " " + name1 + " " + name2 + " " + lname + " " + id + " " + email + " ";
            }
        }
     catch (SQLException | ClassNotFoundException throwables) {
        throwables.printStackTrace();
    }
    return st_info;
    }

    public String getTeacherInfo(String selectStmt) throws SQLException, ClassNotFoundException, IOException {
        String t_info = null;
        try {
            DBUtilis.dbConnect();
            ResultSet rs = DBUtilis.dbExecuteQuery(selectStmt);
            while (rs.next()) {
                String name = rs.getString("name");
                String role = rs.getString("role");
                String phone = rs.getString("phone");
                String email = rs.getString("email");

                t_info = name + " " + role + " " + phone + " " + email + " ";
            }
        }
        catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return t_info;
    }

    public String getTeacherTable(String selectStmt) throws SQLException, ClassNotFoundException, IOException {
        String marks = null;
        try {
            DBUtilis.dbConnect();
            ResultSet rs = DBUtilis.dbExecuteQuery(selectStmt);
            while (rs.next()) {
                String mark1 = rs.getString("mark1");
                String mark2 = rs.getString("mark2");
                String mark3 = rs.getString("mark3");
                String mark4 = rs.getString("mark4");
                String mark5 = rs.getString("mark5");
                String mark6 = rs.getString("mark6");
                marks = mark1 + " " + mark2 + " " + mark3 + " " + mark4 + " " + mark5 + " " + mark6 + " ";
            }
        }
        catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return marks;
    }


    public String getEmail(String selectStmt) throws SQLException, ClassNotFoundException, IOException {

        String email = null;
        try {
            DBUtilis.dbConnect();
            ResultSet rs = DBUtilis.dbExecuteQuery(selectStmt);
            while (rs.next()) {
                email = rs.getString("email");
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return email;
    }

    public String getStudyPlan(String selectStmt) throws SQLException, ClassNotFoundException, IOException {

        String sp = null;
        try {
            DBUtilis.dbConnect();
            ResultSet rs = DBUtilis.dbExecuteQuery(selectStmt);
            while (rs.next()) {
                sp = rs.getString("id_study_plan");
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return sp;
    }

    public String getID(String selectStmt) throws SQLException, ClassNotFoundException, IOException {

        String sp = null;
        try {
            DBUtilis.dbConnect();
            ResultSet rs = DBUtilis.dbExecuteQuery(selectStmt);
            while (rs.next()) {
                sp = rs.getString("id_student");
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return sp;
    }

    public String getAvgKt(String selectStmt) throws SQLException, ClassNotFoundException, IOException {

        String kt = null;
        try {
            DBUtilis.dbConnect();
            ResultSet rs = DBUtilis.dbExecuteQuery(selectStmt);
            while (rs.next()) {
                kt = rs.getString("mark_avg");
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return kt;
    }

    public void update(String updateStmt) throws SQLException, ClassNotFoundException, IOException {
        try {
            DBUtilis.dbExecuteUpdate(updateStmt);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

}


