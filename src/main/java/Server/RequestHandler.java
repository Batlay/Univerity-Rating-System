package Server;

import Client.Entity.StudentO;
import Client.Entity.SubjectO;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class RequestHandler implements Runnable {
    private final ConnectionTCP connectionTCP;
    private ArrayList<ConnectionTCP> clients;


    public RequestHandler(Socket socket) {
        connectionTCP = new ConnectionTCP(socket);//сокет соединения с клиентом
    }

    @Override
    public void run() {
        StudentFunctions server_execute = new StudentFunctions();
        while (true) {
            String aaa = null;
            try {
                aaa = connectionTCP.readUtf();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Command command = (Command) connectionTCP.readObject();

            System.out.println(command);
            switch (command) {
                case READ -> {
                    List<StudentO> students = null;
                    try {
                        System.out.println(aaa);
                        students = server_execute.getStudentRate(aaa);
                    } catch (SQLException | ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }

                    connectionTCP.writeObject(students);
                }
                case ADMINSTUDENTS -> {
                    List<StudentO> students = null;
                    try {
                        System.out.println(aaa);
                        students = server_execute.getAdminStudents(aaa);
                    } catch (SQLException | ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }

                    connectionTCP.writeObject(students);
                }
                case ADMINTEACHER -> {
                    List<StudentO> students = null;
                    try {
                        System.out.println(aaa);
                        students = server_execute.getAdminTeacher(aaa);
                    } catch (SQLException | ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }

                    connectionTCP.writeObject(students);
                }
                case READSUB -> {
                    List<SubjectO> subject = null;
                    try {
                        System.out.println(aaa);
                        subject = server_execute.getStudentSubject(aaa);
                    } catch (SQLException | ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }

                    connectionTCP.writeObject(subject);
                }
                case READGROUP -> {
                    List<StudentO> student = null;
                    try {
                        System.out.println(aaa);
                        student = server_execute.getStudentGroup(aaa);
                    } catch (SQLException | IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    connectionTCP.writeObject(student);
                }
                case READMARK -> {
                    List<SubjectO> subject = null;
                    try {
                        System.out.println(aaa);
                        subject = server_execute.getStudentMark(aaa);
                    } catch (SQLException | ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }

                    connectionTCP.writeObject(subject);
                }
                case TEACHERMARK -> {
                    List<SubjectO> mark = null;
                    try {
                        System.out.println(aaa);
                        mark = server_execute.getTeacherMark(aaa);
                    } catch (SQLException | ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }

                    connectionTCP.writeObject(mark);
                }
                case TEACHERTABLE -> {
                    String marks = null;
                    try {
                        System.out.println(aaa);
                        marks = server_execute.getTeacherTable(aaa);
                    } catch (SQLException | ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }

                    connectionTCP.writeObject(marks);
                }
                case READID -> {
                    String marks = null;
                    try {
                        System.out.println(aaa);
                        marks = server_execute.getID(aaa);
                    } catch (SQLException | ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }

                    connectionTCP.writeObject(marks);
                }
                case TEACHERINFO -> {
                    String info = null;
                    try {
                        System.out.println(aaa);
                        info = server_execute.getTeacherInfo(aaa);
                    } catch (SQLException | ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }

                    connectionTCP.writeObject(info);
                }
                case TEACHERSUBJECTS -> {
                    List<SubjectO> subjects = null;
                    try {
                        System.out.println(aaa);
                        subjects = server_execute.getTeacherSubjects(aaa);
                    } catch (SQLException | ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }

                    connectionTCP.writeObject(subjects);
                }
                case READSPEC -> {
                    List<String> faculty = null;
                    try {
                        System.out.println(aaa);
                        faculty = server_execute.getSpec(aaa);
                    } catch (SQLException | ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }

                    connectionTCP.writeObject(faculty);
                }
                case READYEAR -> {
                    List<String> year = null;
                    try {
                        System.out.println(aaa);
                        year = server_execute.getYear(aaa);
                    } catch (SQLException | ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }

                    connectionTCP.writeObject(year);
                }
                case READFACULTY -> {
                    List<String> faculty = null;
                    try {
                        System.out.println(aaa);
                        faculty = server_execute.getFaculty(aaa);
                    } catch (SQLException | ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }

                    connectionTCP.writeObject(faculty);
                }
                case READDATE -> {
                    String date = null;
                    try {
                        System.out.println(aaa);
                        date = server_execute.getDate(aaa);
                    } catch (SQLException | ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }

                    connectionTCP.writeObject(date);
                }
                case SELECTGROUP -> {
                    String group = null;
                    try {
                        System.out.println(aaa);
                        group = server_execute.getGroup(aaa);
                    } catch (SQLException | ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }

                    connectionTCP.writeObject(group);
                }
                case READGROUPCB -> {
                    List<String> group = null;
                    try {
                        System.out.println(aaa);
                        group = server_execute.getGroupCB(aaa);
                    } catch (SQLException | ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }

                    connectionTCP.writeObject(group);
                }
                case READSUBJECTCB -> {
                    List<String> subject = null;
                    try {
                        System.out.println(aaa);
                        subject = server_execute.getSubject(aaa);
                    } catch (SQLException | ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }

                    connectionTCP.writeObject(subject);
                }
                case AVG_KT -> {
                    String avg = null;
                    try {
                        System.out.println(aaa);
                        avg = server_execute.getAvgKt(aaa);
                    } catch (SQLException | ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }

                    connectionTCP.writeObject(avg);
                }
                case STUDENTINFO -> {
                    String st_info = null;
                    try {
                        System.out.println(aaa);
                        st_info = server_execute.getStudentInfo(aaa);
                    } catch (SQLException | ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }

                    connectionTCP.writeObject(st_info);
                }
                case READEMAIL -> {
                    String email = null;
                    try {
                        System.out.println(aaa);
                        email = server_execute.getEmail(aaa);
                    } catch (SQLException | ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }

                    connectionTCP.writeObject(email);
                }
                case READSTUDYPLAN -> {
                    String sp = null;
                    try {
                        System.out.println(aaa);
                        sp = server_execute.getStudyPlan(aaa);
                    } catch (SQLException | ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }

                    connectionTCP.writeObject(sp);
                }
                case LOGINTEACHER -> {
                    boolean data;
                    StringTokenizer st = new StringTokenizer(aaa);
                    String username = st.nextToken();
                    String password = st.nextToken();
                    System.out.println(username + password);
                    data = server_execute.logTeacher(username, password);
                    connectionTCP.writeObject(data);

                }
                case LOGINADMIN -> {
                    boolean data;
                    StringTokenizer st = new StringTokenizer(aaa);
                    String username = st.nextToken();
                    String password = st.nextToken();
                    System.out.println(username + password);
                    data = server_execute.logAdmin(username, password);
                    connectionTCP.writeObject(data);
                }
                case LOGINSTUDENT -> {
                    boolean data;
                    StringTokenizer st = new StringTokenizer(aaa);
                    String username = st.nextToken();
                    String password = st.nextToken();
                    System.out.println(username + password);
                    data = server_execute.logStudent(username, password);
                    connectionTCP.writeObject(data);
                }
                case UPDATE -> {
                    try {
                        server_execute.update(aaa);
                    } catch (SQLException | ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }
                }
                case EXIT -> {
                    connectionTCP.close();
                    return;
                }
            }
        }
    }
}
