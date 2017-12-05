package bean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import persistence.Admin;
import persistence.Refugee;
import persistence.User;
import services.UserManagementLocal;

@ManagedBean
@SessionScoped
public class Identity {
	private User user = new User();
	private boolean loggedIn = false;

	@EJB
	private UserManagementLocal userLocal;

	public String doLogin() {
		String navigateTo = "";
		User userLoggedIn = userLocal.login1(user.getUsername(), user.getPassword());
		if (userLoggedIn != null) {
			user = userLoggedIn;
			loggedIn = true;
			if (userLoggedIn instanceof Refugee) {
				navigateTo = "/pages/articlesUser?faces-redirect=true";
			} else if (userLoggedIn instanceof Admin) {
				navigateTo = "/pages/articles?faces-redirect=true";
			} else if (userLoggedIn instanceof User) {
				navigateTo = "/pages/articlesUser?faces-redirect=true";
			}
		} else {
			navigateTo = "/horror?faces-redirect=true";
		}

		return navigateTo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

}
