package bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import persistence.Articles;
import persistence.Comment;
import services.BasicOpsLocal;
import services.CommentServicesLocal;
import services.UserManagementLocal;

@ManagedBean
@ViewScoped
public class ArticleBean {
	@EJB
	BasicOpsLocal basicOpsLocal;
	@EJB
	CommentServicesLocal commentLocal;
	@EJB
	UserManagementLocal userLocal;
	private boolean showComment = false;
	@ManagedProperty(value = "#{identity}")
	private Identity identity;
	private List<Articles> articles = new ArrayList<>();
	private boolean showCreate = false;
	private Articles a = new Articles();
	private Comment comment = new Comment();
	private String content;
	private List<Comment> comments = new ArrayList<>();

	@PostConstruct
	public void init() {
		articles = basicOpsLocal.findallArticles();

	}

	public void doShowComment() {
		showComment = true;
	}

	public void doSelect() {
		showCreate = true;
		comments = commentLocal.findCommentByArticle(a);
	}

	public void doCancel() {
		showCreate = false;
		init();
	}

	public void doCreateArticle() {

		// Articles a = basicOpsLocal.findArticlesById(a.getId());
		a.setCreatedAt(Calendar.getInstance().getTime());
		basicOpsLocal.saveOrUpdateArticle(a);
		showCreate = false;
		init();
		a = new Articles();
	}

	public void doAddComment() {

		commentLocal.addComment(identity.getUser(), a, comment.getContent());
	}

	public void doDeleteArticle() {
		basicOpsLocal.deleteArticle(a);
		doCancel();
	}

	public List<Articles> getArticles() {
		return articles;
	}

	public void setArticles(List<Articles> articles) {
		this.articles = articles;
	}

	public boolean isShowCreate() {
		return showCreate;
	}

	public void setShowCreate(boolean showCreate) {
		this.showCreate = showCreate;
	}

	public Articles getA() {
		return a;
	}

	public void setA(Articles a) {
		this.a = a;
	}

	public boolean isShowComment() {
		return showComment;
	}

	public void setShowComment(boolean showComment) {
		this.showComment = showComment;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public void info() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PrimeFaces Rocks."));
    }
     
    public void warn() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Watch out for PrimeFaces."));
    }
     
    public void error() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Contact admin."));
    }
     
    public void fatal() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "System Error"));
    }
}
