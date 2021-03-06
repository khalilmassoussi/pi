package services;

import java.util.List;

import javax.ejb.Remote;

import persistence.Articles;
import persistence.Camp;
import persistence.Refugee;

@Remote
public interface BasicOpsRemote {
	List<Camp> findCamps();
Boolean userInCamp(int id,Camp c);
Long numberOfRefugees(Camp c);
Long numberOfMales(Camp c);
Long numberOfFemales(Camp c);
List refugeesInCamp(Camp c);
Long countUsersBySexe(String s);
void updateUser(Refugee user);
void saveOrUpdateArticle(Articles article);
List<Articles> findallArticles();
Articles findArticlesById(int id);
void deleteArticle(Articles article);


}
