package persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable

@SuppressWarnings("serial")
public class HistoryId implements Serializable {
	private int refugeeId;
	private int campId;
	private Date joiningDate;
	
	public HistoryId(int refugeeId, int campId,Date date) {
		super();
		this.refugeeId = refugeeId;
		this.campId = campId;
		this.joiningDate=date;
	}


	public Date getDate() {
		return joiningDate;
	}


	public void setDate(Date date) {
		this.joiningDate = date;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + campId;
		result = prime * result + refugeeId;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HistoryId other = (HistoryId) obj;
		if (campId != other.campId)
			return false;
		if (refugeeId != other.refugeeId)
			return false;
		return true;
	}


	public int getRefugeeId() {
		return refugeeId;
	}


	public void setRefugeeId(int refugeeId) {
		this.refugeeId = refugeeId;
	}


	public int getCampId() {
		return campId;
	}


	public void setCampId(int campId) {
		this.campId = campId;
	}


	public HistoryId() {
		
	}


	
	
	
}
