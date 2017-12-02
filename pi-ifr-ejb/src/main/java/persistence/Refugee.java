package persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Refugee
 *
 */
@Entity

public class Refugee extends User implements Serializable {
	
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Skills>skills;
	

	private int hired;
	private int adopted;
	
	private static final long serialVersionUID = 1L;
	@ManyToOne
	private Camp camp;

	public Camp getCamp() {
		return camp;
	}
	public void setCamp(Camp camp) {
		this.camp = camp;
	}
	public Refugee(int hired , String username) {
		super(username);

		this.hired = hired;
	}
	public Refugee(String firstname, String lastname, String username, String email, Date birthdate, String password,
			String country) {
		super(username, firstname, lastname, email, birthdate, password, country);
	}
	
	public Refugee() {
		super();
	}   
	public List<Skills> getSkills() {
		return skills;
	}

	public void setSkills(List<Skills> skills) {
		this.skills = skills;
	}

	public int getHired() {
		return this.hired;
	}

	public void setHired(int hired) {
		this.hired = hired;
	}   
	public int getAdopted() {
		return this.adopted;
	}

	public void setAdopted(int adopted) {
		this.adopted = adopted;
	}
	public Refugee(String username, String firstname, String lastname, String email, Date birthdate, String password,
			String country, String sexe) {
		super(username, firstname, lastname, email, birthdate, password, country, sexe);
	}
   
}
