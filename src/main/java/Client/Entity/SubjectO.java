package Client.Entity;

import java.io.Serializable;

public class SubjectO implements Serializable {

    private String discipline;
    private String hours;
    private String form;
    private String teacher;
    private String name;
    private String group;
    private int mark1;
    private int mark2;
    private int mark3;
    private int mark4;
    private int mark5;
    private int mark6;
    private double avg;
    private int semester;
    private String first_name;
    private String last_name;
    private String st_id;

    public SubjectO(String discipline,  // когда клиент присылает информацию
                    String hours,
                    String form,
                    String teacher,
                    String name) {
        this.discipline = discipline;
        this.hours = hours;
        this.form = form;
        this.teacher = teacher;
        this.name = name ;

    }

    public SubjectO(String discipline,  // когда клиент присылает информацию
                    int mark1,
                    int mark2,
                    int mark3,
                    int mark4,
                    int mark5,
                    int mark6,
                    double avg) {
        this.discipline = discipline;
        this.mark1 = mark1;
        this.mark2 = mark2;
        this.mark3 = mark3;
        this.mark4 = mark4;
        this.mark5 = mark5;
        this.mark6 = mark6;
        this.avg = avg;
    }

    public SubjectO(String first_name,  // когда клиент присылает информацию
                    String last_name,
                    int mark1,
                    int mark2,
                    int mark3,
                    int mark4,
                    int mark5,
                    int mark6,
                    double avg,
                    String st_id) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.mark1 = mark1;
        this.mark2 = mark2;
        this.mark3 = mark3;
        this.mark4 = mark4;
        this.mark5 = mark5;
        this.mark6 = mark6;
        this.avg = avg;
        this.st_id = st_id;
    }

    public SubjectO(String group,  // когда клиент присылает информацию
                    int semester,
                    String discipline,
                    String name) {
        this.group = group;
        this.semester = semester;
        this.discipline = discipline;
        this.name = name;

    }

    public String getSt_id() {
        return st_id;
    }

    public void setSt_id(String st_id) {
        this.st_id = st_id;
    }


    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getMark1() {
        return mark1;
    }

    public void setMark1(int mark1) {
        this.mark1 = mark1;
    }

    public int getMark2() {
        return mark2;
    }

    public void setMark2(int mark2) {
        this.mark2 = mark2;
    }

    public int getMark3() {
        return mark3;
    }

    public void setMark3(int mark3) {
        this.mark3 = mark3;
    }

    public int getMark4() {
        return mark4;
    }

    public void setMark4(int mark4) {
        this.mark4 = mark4;
    }

    public int getMark5() {
        return mark5;
    }

    public void setMark5(int mark5) {
        this.mark5 = mark5;
    }

    public int getMark6() {
        return mark6;
    }

    public void setMark6(int mark6) {
        this.mark6 = mark6;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}
