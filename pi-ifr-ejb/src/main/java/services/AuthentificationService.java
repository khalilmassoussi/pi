package services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import persistence.Refugee;
import persistence.User;

/**
 * Session Bean implementation class AuthentificationService
 */

@Stateless
// @WebService(name = "IfrPortType", portName = "IfrPort", serviceName =
// "IfrService", targetNamespace = "http://ws.ifr.tn", endpointInterface =
// "service.AuthentificationService")
public class AuthentificationService implements AuthentificationServiceRemote, AuthentificationServiceLocal {
	@EJB
	private UserManagementRemote userManagement;

	/**
	 * Default constructor.
	 */
	public AuthentificationService() {
	}

	@Override
	public void login(String username, String Password) {

	}

	@Override
	public void register(String username, String firstname, String lastname, String email, String password,
			LocalDate birthdate, String country, String usertype, String sexe) {
		Instant instant = birthdate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		if (usertype.equals("User")) {
			User u = new User(username, firstname, lastname, email, Date.from(instant), password, country, sexe);
			userManagement.addUser(u);
		} else if (usertype.equals("Refugee")) {
			Refugee u = new Refugee(firstname, lastname, username, email, Date.from(instant), password, country, sexe);
			userManagement.addUser(u);
		}

	}

	@Override
	public Boolean existance(String username) {
		List<User> a = userManagement.findAllUsers();
		Boolean b = false;
		for (User user : a) {
			if (username.equals(user.getUsername())) {
				b = true;

			}

		}
		return b;

	}
}