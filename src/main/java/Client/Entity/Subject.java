package Client.Entity;
import javafx.beans.property.*;

import java.io.Serializable;


public class Subject implements Serializable {
    private final StringProperty discipline;
    private final StringProperty hours;
    private final StringProperty form;
    private final StringProperty teacher;
    private final StringProperty name;
    private final StringProperty group;
    private final IntegerProperty mark1;
    private final IntegerProperty mark2;
    private final IntegerProperty mark3;
    private final IntegerProperty mark4;
    private final IntegerProperty mark5;
    private final IntegerProperty mark6;
    private final DoubleProperty avg;
    private final IntegerProperty semester;
    private final transient StringProperty first_name;
    private final transient StringProperty last_name;
    private final transient StringProperty st_id;


    //Конструктор
    public Subject(SubjectO subjectO) {
        this.discipline = new SimpleStringProperty(subjectO.getDiscipline());
        this.hours = new SimpleStringProperty(subjectO.getHours());
        this.form = new SimpleStringProperty(subjectO.getForm());
        this.teacher= new SimpleStringProperty(subjectO.getTeacher());
        this.name = new SimpleStringProperty(subjectO.getName());
        this.avg = new SimpleDoubleProperty(subjectO.getAvg());
        this.group = new SimpleStringProperty(subjectO.getGroup());
        this.mark1 = new SimpleIntegerProperty(subjectO.getMark1());
        this.mark2 = new SimpleIntegerProperty(subjectO.getMark2());
        this.mark3 = new SimpleIntegerProperty(subjectO.getMark3());
        this.mark4 = new SimpleIntegerProperty(subjectO.getMark4());
        this.mark5 = new SimpleIntegerProperty(subjectO.getMark5());
        this.mark6 = new SimpleIntegerProperty(subjectO.getMark6());
        this.semester = new SimpleIntegerProperty(subjectO.getSemester());
        this.first_name = new SimpleStringProperty(subjectO.getFirst_name());
        this.last_name = new SimpleStringProperty(subjectO.getLast_name());
        this.st_id = new SimpleStringProperty(subjectO.getSt_id());
    }

    public String getSt_Id() {
        return st_id.get();
    }
    public void setSt_id(String st_id){
        this.st_id.set(String.valueOf(st_id));
    }
    public StringProperty stIdProperty(){
        return st_id;
    }

    public void setAvg(double avg){
        this.avg.set(avg);
    }
    public DoubleProperty avgProperty() {
        return avg;
    }

    public String getDiscipline () {
        return discipline.get();
    }
    public void setDiscipline(String discipline){
        this.discipline.set(discipline);
    }
    public StringProperty disciplineProperty() {
        return discipline;
    }

    public String getGroup () {
        return group.get();
    }
    public void setGroup(String group){
        this.group.set(group);
    }
    public StringProperty groupProperty() {
        return group;
    }

    public int getMark1() {
        return mark1.get();
    }
    public void setMark1(int mark1){
        this.mark1.set(mark1);
    }
    public IntegerProperty mark1Property(){
        return mark1;
    }

    public int getMark2() {
        return mark2.get();
    }
    public void setMark2(int mark2){
        this.mark2.set(mark2);
    }
    public IntegerProperty mark2Property(){
        return mark2;
    }

    public int getSemester() {
        return semester.get();
    }
    public void setSemester(int semester){
        this.semester.set(semester);
    }
    public IntegerProperty semesterProperty(){
        return semester;
    }

    public int getMark3() {
        return mark3.get();
    }
    public void setMark3(int mark3){
        this.mark3.set(mark3);
    }
    public IntegerProperty mark3Property(){
        return mark3;
    }

    public int getMark4() {
        return mark4.get();
    }
    public void setMark4(int mark4){
        this.mark4.set(mark4);
    }
    public IntegerProperty mark4Property(){
        return mark4;
    }

    public int getMark5() {
        return mark5.get();
    }
    public void setMark5(int mark5){
        this.mark5.set(mark5);
    }
    public IntegerProperty mark5Property(){
        return mark5;
    }

    public int getMark6() {
        return mark6.get();
    }
    public void setMark6(int mark6){
        this.mark6.set(mark6);
    }
    public IntegerProperty mark6Property(){
        return mark6;
    }


    public String getHours () {
        return hours.get();
    }
    public void setHours(String hours){
        this.hours.set(hours);
    }
    public StringProperty hoursProperty() {
        return hours;
    }


    public String getForm () {
        return form.get();
    }
    public void setForm(String form){
        this.form.set(form);
    }
    public StringProperty formProperty() {
        return form;
    }

    public String getTeacher () {
        return teacher.get();
    }
    public void setTeacher(String teacher){
        this.teacher.set(teacher);
    }
    public StringProperty teacherProperty() {
        return teacher;
    }

    public String getName () {
        return name.get();
    }
    public void setName(String name){
        this.name.set(name);
    }
    public StringProperty nameProperty() {
        return name;
    }

    public String getFirstName () {
        return first_name.get();
    }
    public void setFirstName(String firstName){
        this.first_name.set(firstName);
    }
    public StringProperty firstNameProperty() {
        return first_name;
    }
    //last_name
    public String getLastName () {
        return last_name.get();
    }
    public void setLastName(String lastName){
        this.last_name.set(lastName);
    }
    public StringProperty lastNameProperty() {
        return last_name;
    }
}



