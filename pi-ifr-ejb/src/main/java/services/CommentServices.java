package services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import persistence.Articles;
import persistence.Comment;
import persistence.CommentId;
import persistence.User;

/**
 * Session Bean implementation class CommentServices
 */
@Stateless
public class CommentServices implements CommentServicesRemote, CommentServicesLocal {
	@PersistenceContext
	private EntityManager entityManager;
	@EJB
	BasicOpsLocal basiclocal;

	/**
	 * Default constructor.
	 */
	public CommentServices() {
		// TODO Auto-generated constructor stub
	}

	public void addComment(User user, Articles articles, String content) {
		Comment comment = new Comment(content, entityManager.merge(articles), user);
		entityManager.merge(comment);

	}

	public void deleteComment(Comment c) {
		entityManager.remove(entityManager.merge(c));

	}

	@Override
	public Comment findComment(CommentId c) {
		return entityManager.find(Comment.class, c);

	}

	public List<Comment> findCommentByUser(User u) {
		String jpql = "SELECT u FROM Comment u WHERE u.user=:user";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("user", u);
		return query.getResultList();
	}

	public List<Comment> findCommentByArticle(Articles a) {
		String jpql = "SELECT u FROM Comment u WHERE u.article=:a";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("a", a);
		return query.getResultList();
	}

	public Articles findMostActiveArticle() {
		Articles ar = new Articles();
		int i = 0;
		List<Articles> articles = basiclocal.findallArticles();
		for (Articles a : articles) {

			if (i < findCommentByArticle(a).size()) {
				i = findCommentByArticle(a).size();
				ar = a;
			}

		}
		// String jpql = "SELECT c.a,count(c) FROM Comment c inner join c.article a
		// GROUP BY c.article ";
		// Query query = entityManager.createQuery(jpql);

		return (Articles) ar;

	}

	public int reactionNumber(Articles a) {
		return findCommentByArticle(a).size();

	}

	public List<Comment> myReactions(User u) {
		String jpql = "SELECT u FROM Comment u WHERE u.user=:a";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("a", u);
		return query.getResultList();

	}
}
