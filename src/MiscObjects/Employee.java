package MiscObjects;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;
import java.util.*;

public class Employee {
    private String employee_id;
    private String username;
    private String f_name;
    private String l_name;
    private long phone;
    private String email;
    private Date date_of_birth;

    public Employee() {
        setEmployee_id(employee_id);
        setUsername(username);
        setF_name(f_name);
        setL_name(l_name);
        setEmail(email);
        setPhone(phone);
        setDate_of_birth(date_of_birth);
    }

    @Override
    public String toString() {
        String info = "Employee ID: " + employee_id + "\n" +
                "Username: " + username + "\n" +
                "First Name: " + f_name + "\n" +
                "Last Name: " + l_name + "\n" +
                "Phone: " + phone + "\n" +
                "Email: " + email + "\n" +
                "Date Of Birth: " + date_of_birth + "\n";
        return info;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    private StringProperty firstName;
    public void setFirstName(String value) { firstNameProperty().set(value); }
    public String getFirstName() { return firstNameProperty().get(); }
    public StringProperty firstNameProperty() {
        if (firstName == null) firstName = new SimpleStringProperty(this, "firstName");
        return firstName;
    }

}
