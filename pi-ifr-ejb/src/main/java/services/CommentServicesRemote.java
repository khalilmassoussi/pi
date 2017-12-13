package services;

import java.util.List;

import javax.ejb.Remote;

import persistence.Articles;
import persistence.Comment;
import persistence.CommentId;
import persistence.User;

@Remote
public interface CommentServicesRemote {
	void addComment(User user, Articles articles, String content);

	List<Comment> findCommentByUser(User u);

	List<Comment> findCommentByArticle(Articles a);

	Articles findMostActiveArticle();

	int reactionNumber(Articles a);

	void deleteComment(Comment c);

	Comment findComment(CommentId c);

	List<Comment> myReactions(User u);
}
