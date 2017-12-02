package services;

import java.util.List;

import javax.ejb.Local;

import persistence.Camp;
import persistence.History;
import persistence.Refugee;
import persistence.User;

@Local
public interface AssignmentServiceLocal {
	void addcamp(Camp c);
	Camp findCampById(int i);

	void assignRefugeeToCamp(Refugee r, Camp c);
	 List<History> findUserCamps(Refugee u);
	 List leavingDateNull(Refugee r );
void leavingDate(History h);
}
