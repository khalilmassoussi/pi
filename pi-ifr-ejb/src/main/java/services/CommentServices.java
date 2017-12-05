package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import persistence.Articles;
import persistence.Comment;
import persistence.User;

/**
 * Session Bean implementation class CommentServices
 */
@Stateless
public class CommentServices implements CommentServicesRemote, CommentServicesLocal {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public CommentServices() {
		// TODO Auto-generated constructor stub
	}

	public void addComment(User user, Articles articles, String content) {
		Comment comment = new Comment(content, articles, user);
		entityManager.merge(comment);

	}
	public List<Comment> findCommentByUser(User u){
		String jpql = "SELECT u FROM Comment u WHERE u.user=:user";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("user",u);
		return query.getResultList();
	}

	public List<Comment> findCommentByArticle(Articles a){
		String jpql = "SELECT u FROM Comment u WHERE u.article=:a";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("a",a);
		return query.getResultList();
	}
}
