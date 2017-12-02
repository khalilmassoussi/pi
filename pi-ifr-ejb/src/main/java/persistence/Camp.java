package persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
/**
 * Entity implementation class for Entity: Camp
 *
 */
@Entity
public class Camp implements Serializable{
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private int capacity;
	private String location;
	
	private static final long serialVersionUID = 1L;
//	@OneToMany
//	  @JoinColumn(name="CAMP_ID", referencedColumnName="id")
//	  private List<Refugee> refugees;
//	public List<Refugee> getRefugees() {
//		return refugees;
//	}
//	public void setRefugees(List<Refugee> refugees) {
//		this.refugees = refugees;
//	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Camp( String name, int capacity, String location) {
		super();
		this.name = name;
		this.capacity = capacity;
		this.location = location;
	}
	@Override
	public String toString() {
		return "Camp [id=" + id + ", name=" + name + ", capacity=" + capacity + ", location=" + location + "]";
	}
	public Camp() {
		super();
		// TODO Auto-generated constructor stub
	}

}
