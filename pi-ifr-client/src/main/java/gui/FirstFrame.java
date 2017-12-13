package gui;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import persistence.Articles;
import persistence.Comment;
import persistence.CommentId;
import persistence.User;
import services.BasicOpsRemote;
import services.CommentServicesRemote;
import services.UserManagementRemote;

public class FirstFrame {

	public static void main(String[] args) throws NamingException, ParseException {
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
		commentRemote.addComment(u, a, "ccc");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		
		System.out.println(commentRemote.findMostActiveArticle()+"  "+ commentRemote.reactionNumber(commentRemote.findMostActiveArticle()));
//		String date1 = "04/12/2017 14:56:43";
//		CommentId cid = new CommentId(21, 1, simpleDateFormat.parse(date1));
//		Comment c = commentRemote.findComment(cid);
//		System.out.println(c.getCommentId().getUserId()+" "+simpleDateFormat.parse(date1));
//		try {
//			commentRemote.deleteComment(c);
//		}catch(Exception e){
//			System.out.println("tahche");
//		}
	}

}
