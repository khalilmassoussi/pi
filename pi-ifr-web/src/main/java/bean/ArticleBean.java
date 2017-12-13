package bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

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
	private boolean showView = false;
	private Articles a = new Articles();
	private Articles article = new Articles();
	private Comment comment = new Comment();
	private String content;
	private List<Comment> comments = new ArrayList<>();
	private String description;
	private boolean commsupp = false;
	public List<Comment> myReactions = new ArrayList<>();

	private Articles mostActive;
	private int reactions;
	File uploads = new File(System.getProperty("jboss.server.data.dir"), "uploads");

	@PostConstruct
	public void init() {

		List<Articles> articles1 = basicOpsLocal.findallArticles();
		for (Articles a : articles1) {
			a.setDescription(stripTags(a.getDescription()));
			articles.add(a);
		}
		mostActive = commentLocal.findMostActiveArticle();
		reactions = commentLocal.reactionNumber(mostActive);
		description = stripTags(mostActive.getDescription());

	}

	public void doShowView(Articles artcl) {

		System.out.println("caoucaou");
		showView = true;
		a = artcl;
		comments = commentLocal.findCommentByArticle(a);

	}

	public void doShowComment() {
		showComment = true;
	}

	public void doSelect() {
		showCreate = true;
	}

	public void doCancel() {
		showCreate = false;
		showView = false;
		init();
	}

	public void doCreateArticle() {

		a.setPicture(file.getFileName());
		// Articles a = basicOpsLocal.findArticlesById(a.getId());
		a.setCreatedAt(Calendar.getInstance().getTime());
		basicOpsLocal.saveOrUpdateArticle(a);
		showCreate = false;
		init();
		a = new Articles();
	}

	public void doAddComment() {
		System.out.println("cc");
		commentLocal.addComment(identity.getUser(), a, comment.getContent());
		System.out.println("cc");

		comments = commentLocal.findCommentByArticle(a);
		System.out.println("cc");
		showView = true;
	}

	public void doDeleteComment(Comment c) {
		commentLocal.deleteComment(c);
		doShowView(a);
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

	public boolean isShowView() {
		return showView;
	}

	public void setShowView(boolean showView) {
		this.showView = showView;
	}

	private UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void handleFileUpload(FileUploadEvent event) throws IOException {
		System.out.println("cc");

		a.setPicture(event.getFile().getFileName());

		file = event.getFile();
		System.out.println(file.getFileName());
		File fi = new File(uploads, file.getFileName());

		try (InputStream input = file.getInputstream()) {
			Files.copy(input, fi.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setImage(StreamedContent image) {
	}

	public StreamedContent getImage() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			// So, we're rendering the view. Return a stub StreamedContent so that it will
			// generate right URL.
			return new DefaultStreamedContent();
		} else {
			// So, browser is requesting the image. Return a real StreamedContent with the
			// image bytes.
			String filename = a.getPicture();
			System.out.println(filename);
			return new DefaultStreamedContent(new FileInputStream(new File("/uploads/", filename)));
		}
	}

	public Articles getMostActive() {
		return mostActive;
	}

	public void setMostActive(Articles mostActive) {
		this.mostActive = mostActive;
	}

	public int getReactions() {
		return reactions;
	}

	public void setReactions(int reactions) {
		this.reactions = reactions;
	}

	public static String stripTags(String input) {
		return input.replaceAll("\\<.*?>", "");
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isCommsupp() {
		return commsupp;
	}

	public void setCommsupp(boolean commsupp) {
		this.commsupp = commsupp;
	}

	public int reactionNumber(Articles r) {

		return commentLocal.reactionNumber(r);
	}

	public List<Comment> getMyReactions() {
		return myReactions;
	}

	public void setMyReactions(List<Comment> myReactions) {
		this.myReactions = myReactions;
	}

	public void doShowMyReactions() {

		myReactions = commentLocal.findCommentByUser(identity.getUser());
		for (Comment comm : myReactions) {

			System.out.println(comm.getArticle().getTitle());
		}
		System.out.println(identity.getUser());
		System.out.println("cc");

	}

	public Articles getArticle() {
		return article;
	}

	public void setArticle(Articles article) {
		this.article = article;
	}

}
