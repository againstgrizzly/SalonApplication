package Model;

import java.sql.*;

public class Appointment {
	private String appt_id;
	private Time appt_time;
	private String notes;
	private String client_id;
	private String employee_id;
	private int duration;
	
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getAppt_id() {
		return appt_id;
	}
	public void setAppt_id(String appt_id) {
		this.appt_id = appt_id;
	}
	public Time getAppt_time() {
		return appt_time;
	}
	public void setAppt_time(Time appt_time) {
		this.appt_time = appt_time;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	
	
}
