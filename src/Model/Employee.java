package Model;

import java.sql.Date;
import java.util.*;

public class Employee {
	private String employee_id;
	private String username;
	private String f_name;
	private String l_name;
	private double phone;
	private String email;
	private Date date_of_birth;
	private List<Service> emp_services;
	private List<Appointment> appointments;

	public Employee(){

	}
	
	public Employee(String f_name, String l_name, String email, int phone, Date date_of_birth){
		setF_name(f_name);
		setL_name(l_name);
		setEmail(email);
		setPhone(phone);
		setDate_of_birth(date_of_birth);
		//setUsername();
	}
	
	
	public List<Service> getEmp_services() {
		return emp_services;
	}
	public void setEmp_services(List<Service> emp_services) {
		this.emp_services = emp_services;
	}
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getPhone() {
		return phone;
	}
	public void setPhone(double phone) {
		this.phone = phone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
		/*
		 * Here I want to set the username to be auto generated
		 * by using some combination of the first and last name
		 * with maybe an integer.
		 */
//		String username;
//		username = f_name.substring(0,1) + l_name;
//		this.username = username;
	}
	public Date getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public List<Appointment> getAppointments() {
		return appointments;
	}
	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
}
