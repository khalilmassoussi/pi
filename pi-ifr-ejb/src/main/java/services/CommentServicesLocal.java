package services;

import java.util.List;

import javax.ejb.Local;

import persistence.Articles;
import persistence.Comment;
import persistence.User;

@Local
public interface CommentServicesLocal {
	void addComment(User user, Articles articles, String content);

	List<Comment> findCommentByUser(User u);

	List<Comment> findCommentByArticle(Articles a);
}
