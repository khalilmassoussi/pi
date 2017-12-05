package persistence;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity

public class Comment implements Serializable {
	@EmbeddedId
	private CommentId commentId;
	private String content;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "articleId", referencedColumnName = "id", insertable = false, updatable = false)
	private Articles article;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "userId", referencedColumnName = "id", insertable = false, updatable = false)
	private User user;

	private static final long serialVersionUID = 1L;

	public Comment() {
		super();
	}

	public Comment(String content, Articles article, User user) {
		super();
		this.commentId = new CommentId(article.getId(), user.getId(), Calendar.getInstance().getTime());
		this.content = content;
		this.article = article;
		this.user = user;
	}

	public CommentId getCommentId() {
		return commentId;
	}

	public void setCommentId(CommentId commentId) {
		this.commentId = commentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Articles getArticle() {
		return article;
	}

	public void setArticle(Articles article) {
		this.article = article;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
