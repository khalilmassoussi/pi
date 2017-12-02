package persistence;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class History implements Serializable {
	
	private Date leavingDate;
	
	@EmbeddedId
	private HistoryId historyId;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "refugeeId", referencedColumnName = "id", insertable = false, updatable = false )
	private Refugee refugee;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "campId", referencedColumnName = "id", insertable = false, updatable = false)
	private Camp camp;

	private static final long serialVersionUID = 1L;

	

	public History() {
		super();
	}

	

	public History(Refugee refugee, Camp camp) {
		super();
		this.historyId = new HistoryId(refugee.getId(),camp.getId(),Calendar.getInstance().getTime());
		this.refugee = refugee;
		this.camp = camp;
	}

	

	public Date getLeavingDate() {
		return leavingDate;
	}

	public void setLeavingDate(Date leavingDate) {
		this.leavingDate = leavingDate;
	}

	public HistoryId getHistoryId() {
		return historyId;
	}

	public void setHistoryId(HistoryId historyId) {
		this.historyId = historyId;
	}

	public Refugee getRefugee() {
		return refugee;
	}

	public void setRefugee(Refugee refugee) {
		this.refugee = refugee;
	}

	public Camp getCamp() {
		return camp;
	}

	public void setCamp(Camp camp) {
		this.camp = camp;
	}

}
