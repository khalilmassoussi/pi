package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import persistence.Admin;
import persistence.Refugee;
import persistence.User;

/**
 * Session Bean implementation class UserManagement
 */
@Stateless
@WebService(name="UserServicePortType",
portName="UserService",
serviceName="UserService",
targetNamespace="http://UserServicePi.tn",
endpointInterface="services.UserManagementRemote"
	
		)
public class UserManagement implements UserManagementRemote, UserManagementLocal {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public UserManagement() {
	}

	@Override
	public void addUser(User user) {
		entityManager.persist(user);

	}

	@Override
	public void updateUser(User user) {
		entityManager.merge(user);
	}

	@Override
	public void deleteUserById(int id) {
		entityManager.remove(findUserById(id));
	}

	@Override
	public void deleteUser(User user) {
		entityManager.remove(user);
	}

	@Override
	public User findUserById(int id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public List<User> findAllUsers() {
		String jpql = "SELECT u FROM User u";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}

	@Override
	public List<User> findByType(Class type) {
		String jpql = "SELECT pe FROM User pe WHERE TYPE(pe)=:t";
		Query query = entityManager.createQuery(jpql);
query.setParameter("t", type);

		return query.getResultList();
	}

	@Override
	public User findUser(String username) {
		String jpql = "SELECT pe FROM User pe WHERE pe.username=:t";
		Query query = entityManager.createQuery(jpql);
query.setParameter("t", username);
		
		return (User) query.getSingleResult();
	}

	 @Override
	 public List login(String username, String password) {
	 Query query=entityManager.createQuery("select e from User e where e.username=:l and e.password=:p");
	 query.setParameter("l", username).setParameter("p", password);
	 return query.getResultList();
	 }

	// @Override
	// public ObservableList<User> displayAll() {
	// String jpql = "SELECT u FROM User u";
	// Query query = entityManager.createQuery(jpql);
	//
	//
	//// for(Object o : query.getResultList()) {
	//// list.add(query.getResultList().get(index))
	//// o.
	// return list;
	// }
	 @Override
		public List<Refugee> findAllRefugee() {
			String jpql = "SELECT u FROM Refugee u";
			Query query = entityManager.createQuery(jpql);
			return query.getResultList();
		}
	 @Override
		public void addUser1(Admin user) {
			entityManager.persist(user);

		}
	 @Override
	 public User login1(String username, String password) {
	 Query query=entityManager.createQuery("select e from User e where e.username=:l and e.password=:p");
	 query.setParameter("l", username).setParameter("p", password);
	 return (User) query.getSingleResult();
	 }
}
