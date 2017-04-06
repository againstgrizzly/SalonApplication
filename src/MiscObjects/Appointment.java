package MiscObjects;

import java.sql.*;
import java.util.List;

/*
Edited by Brannon 4/5/17
Added/changed variables for start_time, end_time
and added a List<Service> to hold all the services
being performed for an appointment
 */

public class Appointment {

	private String appt_id;
	private String client_id;
	private String employee_id;
	private Date date;
	private Time start_time;
	private Time end_time;
	private int duration;
	private String notes;
	private List<Service> services;

	public String getAppt_id() {
		return appt_id;
	}

	public void setAppt_id(String appt_id){
		this.appt_id = appt_id;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getStart_time() {
		return start_time;
	}

	public void setStart_time(Time start_time) {
		this.start_time = start_time;
	}

	public Time getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Time end_time) {
		this.end_time = end_time;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}
}
