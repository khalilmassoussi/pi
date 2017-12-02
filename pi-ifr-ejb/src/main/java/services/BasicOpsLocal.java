package services;

import java.util.List;

import javax.ejb.Local;

import persistence.Camp;
import persistence.Refugee;

@Local
public interface BasicOpsLocal {
List<Camp> findCamps();
Boolean userInCamp(int id,Camp c);
Long numberOfRefugees(Camp c);
Long numberOfMales(Camp c);
Long numberOfFemales(Camp c);
List refugeesInCamp(Camp c);
Long countUsersBySexe(String s);
void updateUser(Refugee user);

}
