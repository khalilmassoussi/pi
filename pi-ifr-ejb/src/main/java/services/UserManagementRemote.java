package services;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import persistence.Admin;
import persistence.Refugee;
import persistence.User;

@Remote
@WebService

public interface UserManagementRemote {

	@WebMethod(operationName="addUser")
	@WebResult(name="adding")
	void addUser(User user);
	
	@WebMethod(operationName="updateUser")
    @WebResult( name="updating")
	void updateUser(@WebParam(name="u")User user);
	
	@WebMethod(operationName="deleteUserBy")
    @WebResult(name="deletingUserbyid")
	void deleteUserById(@WebParam(name="Id")int id);
	
	@WebMethod(operationName="deleteUser")
    @WebResult(name="deletingUser")
	void deleteUser(@WebParam(name="u")User user);
	
	@WebMethod(operationName="findUserById")
    @WebResult(name="findingUsers")
	User findUserById(@WebParam(name="id")int id);
	
	@WebMethod(operationName="findAllUsers")
    @WebResult(name="ListUsers")
	List<User> findAllUsers();
	// List login(String username,String password);
	
	@WebMethod(operationName="findByUsersType")
    @WebResult(name="ListUsers")
	List<User>findByType(@WebParam(name="type")Class type);
	 
	@WebMethod(operationName="findUser")
	    @WebResult(name="ListUsersName")
	User findUser(String username);
	
	@WebMethod(operationName="login")
    @WebResult(name="User")
	List login(@WebParam(name="username")String username,@WebParam(name="password") String password);
	
	@WebMethod(operationName="findAllRefugee")
    @WebResult(name="ListRefugee")
	List<Refugee> findAllRefugee();
	@WebMethod(operationName="addUser1")
    @WebResult(name="addingAdmin")
	void addUser1(Admin user);
	@WebMethod(operationName="login2")
    @WebResult(name="User2")
	User login1(@WebParam(name = "username") String username, @WebParam(name = "password") String password);

}
