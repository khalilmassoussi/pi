package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import persistence.Articles;
import persistence.Camp;
import persistence.Refugee;

/**
 * Session Bean implementation class BasicOps
 */
@Stateless
public class BasicOps implements BasicOpsRemote, BasicOpsLocal {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public BasicOps() {

	}

	@Override
	public List<Camp> findCamps() {
		String jpql = "SELECT u FROM Camp u";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}

	@Override
	public Boolean userInCamp(int id, Camp c) {
		String jpql = "SELECT u FROM Refugee u WHERE u.camp=:c and u.id=:id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("c", c);
		query.setParameter("id", id);
		if (query.getResultList().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public Long numberOfRefugees(Camp c) {
		String jpql = "SELECT count(*) FROM Refugee u WHERE u.camp=:c";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("c", c);
		Long count = (Long) query.getSingleResult();
		return count;
	}

	@Override
	public Long numberOfMales(Camp c) {
		String jpql = "SELECT count(*) FROM Refugee u WHERE u.camp=:c and u.sexe=:Male";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("c", c);
		query.setParameter("Male", "Male");
		Long count = (Long) query.getSingleResult();
		return count;
	}

	@Override
	public Long numberOfFemales(Camp c) {
		String jpql = "SELECT count(*) FROM Refugee u WHERE u.camp=:c and u.sexe=:Female";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("c", c);
		query.setParameter("Female", "Female");
		Long count = (Long) query.getSingleResult();
		return count;
	}

	@Override
	public List refugeesInCamp(Camp c) {
		String jpql = "SELECT u FROM Refugee u WHERE u.camp=:c";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("c", c);
		return query.getResultList();
	}

	@Override
	public Long countUsersBySexe(String s) {
		if (s.equals("all")) {
			String jpql = "SELECT count(*) FROM User u ";
			Query query = entityManager.createQuery(jpql);
			return (Long) query.getSingleResult();
		} else {
			String jpql = "SELECT count(*) FROM User u WHERE u.sexe=:s ";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("s", s);
			return (Long) query.getSingleResult();
		}

	}

	@Override
	public void updateUser(Refugee user) {
		entityManager.merge(user);
	}

	@Override
	public void saveOrUpdateArticle(Articles article) {
		entityManager.merge(article);
	}

	public List<Articles> findallArticles() {

		String jpql = "SELECT u FROM Articles u";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();

	}
	public Articles findArticlesById(int id) {
		return entityManager.find(Articles.class, id);
		
		
	}
	@Override
	public void deleteArticle(Articles article) {
		entityManager.remove(entityManager.merge(article));
	}
}
