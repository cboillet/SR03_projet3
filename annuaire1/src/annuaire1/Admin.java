package annuaire1;

import beans.Annonce;
import beans.Categorie;
import dao.AnnonceDao;
import dao.CategorieDao;
import dao.DAOFactory;

public class Admin {
	public static final String CONF_DAO_FACTORY = "daofactory";
	private CategorieDao categorieDao;
	private AnnonceDao annonceDao;
	
	public void Admin(){
		this.categorieDao = DAOFactory.getInstance().getCategorieDao();
	}
	
	/*Categorie*/
	public void creerCategorie(String name){
		categorieDao = DAOFactory.getInstance().getCategorieDao();
		Categorie categorie = new Categorie();
		categorie.setName(name);
		System.out.println("nome de la categorie ajoutée" + categorie.getName());
		categorieDao.creer(categorie);
	}
	public void modifierCategorie(String oldName, String newName){
		categorieDao = DAOFactory.getInstance().getCategorieDao();
		Categorie categorie = categorieDao.findCategorie(oldName);
		categorie.setName(newName);
		categorieDao.modify(categorie);
	}
	
	public void supprimerCategorie(){}
	
	/*Annonces*/
	public void creerAnnonce(String nameCategorie, String nameAnnonce){
		categorieDao = DAOFactory.getInstance().getCategorieDao();
		annonceDao = DAOFactory.getInstance().getAnnonceDao();
		Categorie categorie = categorieDao.findCategorie(nameCategorie);
		Annonce annonce = new Annonce();
		annonce.setName(nameAnnonce);
		annonceDao.creer(categorie.getId(), annonce);		
	}
	public void supprimerAnnonce(){}
	public void modifierAnnonce(){}
	
	/*Affichage*/
	public String afficherAdresse(){
		return "";
	}
	public String afficherNom(String nomAnnonce){
		return "";
	}
	public String getCategorie(String gategorieName){
		return "";
	}
	public String listCategorie(){
		return "";
	}
}
