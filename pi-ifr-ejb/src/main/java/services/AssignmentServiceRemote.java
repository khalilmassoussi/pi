package services;

import java.util.List;

import javax.ejb.Remote;

import persistence.Camp;
import persistence.History;
import persistence.Refugee;
import persistence.User;

@Remote
public interface AssignmentServiceRemote {
void addcamp(Camp c);
	void assignRefugeeToCamp(Refugee r, Camp c);
Camp findCampById(int i);
 List <History>findUserCamps(Refugee u);
 List leavingDateNull(Refugee r);
 void leavingDate(History h);


}
