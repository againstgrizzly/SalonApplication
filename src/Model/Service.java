package Model;

public class Service {
	private String service_id;
	private String name;
	private String description;
	private double price;
	
	public Service(String name, String description, double price){
		setName(name);
		setDescription(description);
		setPrice(price);
	}
	
	public String getService_id() {
		return service_id;
	}
	public void setService_id(String service_id) {
		this.service_id = service_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

}
