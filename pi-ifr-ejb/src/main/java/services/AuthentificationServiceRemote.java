package services;

import java.time.LocalDate;

import javax.ejb.Remote;

@Remote

public interface AuthentificationServiceRemote {

	void login(String username, String Password);

	void register(String username, String firstname, String lastname, String email, String password,
			LocalDate birthdate, String country, String usertype,String sexe);
	Boolean existance(String username);

}
