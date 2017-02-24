import java.sql.Date;
import java.util.*;

public class Employee {
	private String employee_id;
	private String f_name;
	private String l_name;
	private String email;
	private int phone;
	private String username;
	private Date date_of_birth;
	private ArrayList<Service> emp_services;
	
	public Employee(String f_name, String l_name, String email,
			int phone, Date date_of_birth){
		setF_name(f_name);
		setL_name(l_name);
		setEmail(email);
		setPhone(phone);
		setDate_of_birth(date_of_birth);
		
		/*
		 * Here I want to set the username to be auto generated
		 * by using some combination of the first and last name
		 * with maybe an integer.
		 */
	}
	
	
	public ArrayList<Service> getEmp_services() {
		return emp_services;
	}
	public void setEmp_services(ArrayList<Service> emp_services) {
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
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	
	
	

}
