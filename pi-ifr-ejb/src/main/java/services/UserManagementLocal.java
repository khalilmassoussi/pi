package services;

import java.util.List;

import javax.ejb.Local;
import javax.jws.WebParam;

import persistence.Admin;
import persistence.Refugee;
import persistence.User;

@Local
public interface UserManagementLocal {
	void addUser(User user);

	void updateUser(User user);

	void deleteUserById(int id);

	void deleteUser(User user);

	User findUserById(int id);

	List<User> findAllUsers();

	// List login(String username,String password);
	List<User> findByType(Class type);

	User findUser(String username);

	List login(String username, String password);

	List<Refugee> findAllRefugee();

	void addUser1(Admin user);

	User login1(@WebParam(name = "username") String username, @WebParam(name = "password") String password);

}
