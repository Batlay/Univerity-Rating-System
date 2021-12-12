package Client.Entity;

import java.io.Serializable;

public class StudentO implements Serializable {

    private String st_id;
    private double avg;
    private int group;
    private String address;
    private String spec;
    private String password;
    private String first_name;
    private String last_name;
    private String middle_name;
    private String role;
    private String email;
    private String phone_number;
    private String name;
    private String username;


    public StudentO(String st_id,
                    double avg) {
        this.st_id = st_id;
        this.avg = avg;

    }
    public StudentO(String first_name,
                    String last_name, String middle_name, String role,  String phone_number, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name=middle_name;
        this.role = role;
        this.email = email;
        this.phone_number=phone_number;

    }
    public StudentO(String st_id,
                    String first_name,
                    String last_name,
                    String middle_name,
                    int group,
                    String spec,
                    String email,
                    String phone_number,
                    String password,
                    String address) {
        this.st_id = st_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name=middle_name;
        this.group = group;
        this.spec = spec;
        this.email = email;
        this.phone_number=phone_number;
        this.password = password;
        this.address = address;

    }

    public StudentO(String st_id,
                    String name,
                    String username,
                    String role,
                    String email,
                    String phone_number,
                    String password,
                    String address) {
        this.st_id = st_id;
        this.name = name;
        this.username = username;
        this.role=role;
        this.email = email;
        this.phone_number=phone_number;
        this.password = password;
        this.address = address;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getSt_id() {
        return st_id;
    }

    public void setSt_id(String st_id) {
        this.st_id = st_id;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }
}
