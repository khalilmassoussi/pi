package services;

import java.util.List;

import javax.ejb.Remote;

import persistence.Articles;
import persistence.Comment;
import persistence.User;

@Remote
public interface CommentServicesRemote {
	void addComment(User user, Articles articles, String content);

	List<Comment> findCommentByUser(User u);

	List<Comment> findCommentByArticle(Articles a);
}
