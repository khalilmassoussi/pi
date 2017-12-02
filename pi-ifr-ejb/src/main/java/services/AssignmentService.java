package services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import persistence.Camp;
import persistence.History;
import persistence.Refugee;
import persistence.User;

/**
 * Session Bean implementation class AssignmentService
 */
@Stateless
public class AssignmentService implements AssignmentServiceRemote, AssignmentServiceLocal {
	@EJB
	UserManagementLocal userManagement;
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public AssignmentService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void assignRefugeeToCamp(Refugee r, Camp c) {
		History history = new History(entityManager.merge(r), entityManager.merge(c));

		entityManager.persist(history);
	}

	@Override
	public void addcamp(Camp c) {
		entityManager.persist(c);
	}

	@Override
	public Camp findCampById(int i) {
		return entityManager.find(Camp.class, i);
	}

	@Override
	public List<History> findUserCamps(Refugee u) {
		Query query = entityManager.createQuery("select e from History e where e.refugee=:ref");
		query.setParameter("ref", u);
		return query.getResultList();
	}

	@Override
	public List leavingDateNull(Refugee r) {
		Query query = entityManager.createQuery("select e from History e where e.refugee=:ref and e.leavingDate IS null");
		query.setParameter("ref", r);
		return query.getResultList();
		
	}

	@Override
	public void leavingDate(History h) {
		entityManager.merge(h);
	}
}
