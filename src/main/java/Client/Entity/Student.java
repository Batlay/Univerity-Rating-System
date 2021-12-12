package Client.Entity;
import javafx.beans.property.*;

import java.io.Serializable;


public class Student implements Serializable {
    //Объявить столбы таблицы
    private transient StringProperty st_id;
    private transient DoubleProperty avg;
    private transient IntegerProperty user_id;
    private transient StringProperty username;
    private transient StringProperty role;
    private transient StringProperty password;
    private transient StringProperty first_name;
    private transient StringProperty last_name;
    private transient StringProperty middle_name;
    private transient StringProperty email;
    private transient StringProperty address;
    private transient StringProperty spec;
    private transient StringProperty phone_number;
    private transient StringProperty name;
    private transient IntegerProperty group;

    //Constructor
    public Student(StudentO studentO)  {
        this.user_id = new SimpleIntegerProperty();
        this.username = new SimpleStringProperty(studentO.getUsername());
        this.role = new SimpleStringProperty(studentO.getRole());
        this.password = new SimpleStringProperty(studentO.getPassword());
        this.first_name = new SimpleStringProperty(studentO.getFirst_name());
        this.last_name = new SimpleStringProperty(studentO.getLast_name());
        this.middle_name = new SimpleStringProperty(studentO.getMiddle_name());
        this.email = new SimpleStringProperty(studentO.getEmail());
        this.phone_number = new SimpleStringProperty(studentO.getPhone_number());
        this.st_id = new SimpleStringProperty(studentO.getSt_id());
        this.avg = new SimpleDoubleProperty(studentO.getAvg());
        this.group = new SimpleIntegerProperty(studentO.getGroup());
        this.address = new SimpleStringProperty(studentO.getAddress());
        this.spec = new SimpleStringProperty(studentO.getSpec());
        this.name = new SimpleStringProperty(studentO.getName());
    }
    //employee_id
    public String getSt_Id() {
        return st_id.get();
    }
    public void setSt_id(String st_id){
        this.st_id.set(String.valueOf(st_id));
    }
    public StringProperty stIdProperty(){
        return st_id;
    }

    public void setGroup(int group){
        this.group.set(group);
    }
    public IntegerProperty groupProperty(){
        return group;
    }
    //first_name

    public void setAvg(double avg){
        this.avg.set(avg);
    }
    public DoubleProperty avgProperty() {
        return avg;
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getSpec() {
        return spec.get();
    }

    public StringProperty specProperty() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec.set(spec);
    }

    public String getRole () {
        return role.get();
    }
    public void setRole(String role){
        this.role.set(role);
    }
    public StringProperty roleProperty() {
        return role;
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

    public String getMiddleName () {
        return middle_name.get();
    }
    public void setMiddle_name(String middle_name){
        this.middle_name.set(middle_name);
    }
    public StringProperty middleNameProperty() {
        return middle_name;
    }

    //email
    public String getEmail () {
        return email.get();
    }
    public void setEmail (String email){
        this.email.set(email);
    }
    public StringProperty emailProperty() {
        return email;
    }

    public int getEmployeeId() {
        return user_id.get();
    }
    public void setEmployeeId(int employeeId){
        this.user_id.set(employeeId);
    }
    public IntegerProperty employeeIdProperty(){
        return user_id;
    }
    //first_name

    public void setUsername(String userName){
        this.username.set(userName);
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty UsernameProperty() {
        return username;
    }

    public void setPassword(String password){this.password.set(password);}
    public StringProperty PasswordProperty() {return password;}


    public String getPhoneNumber () {
        return phone_number.get();
    }
    public void setPhoneNumber (String phoneNumber){
        this.phone_number.set(phoneNumber);
    }
    public StringProperty phoneNumberProperty() {
        return phone_number;
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }
}
