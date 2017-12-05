package gui;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import persistence.Articles;
import persistence.User;
import services.BasicOpsRemote;
import services.CommentServicesRemote;
import services.UserManagementRemote;

public class FirstFrame {

	public static void main(String[] args) throws NamingException {
		Context context = new InitialContext();

		BasicOpsRemote basicsRemote = (BasicOpsRemote) context
				.lookup("pi-ifr-ear/pi-ifr-ejb/BasicOps!services.BasicOpsRemote");

		CommentServicesRemote commentRemote = (CommentServicesRemote) context
				.lookup("pi-ifr-ear/pi-ifr-ejb/CommentServices!services.CommentServicesRemote");
		UserManagementRemote userRemote = (UserManagementRemote) context
				.lookup("pi-ifr-ear/pi-ifr-ejb/UserManagement!services.UserManagementRemote");
		// Articles a = new Articles("test", "test", "test");
		// basicsRemote.saveOrUpdateArticle(a);
		User u = userRemote.findUserById(1);
		Articles a = basicsRemote.findArticlesById(21);
		commentRemote.addComment(u, a, "Salut");
		System.out.println(commentRemote.findCommentByArticle(a));
	}

}
