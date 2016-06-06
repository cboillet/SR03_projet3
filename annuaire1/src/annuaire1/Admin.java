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
	public void modifierCategorie(){}
	/*Annonces*/
	public void ajouterAnnonce(String nameCategorie, String nameAnnonce){
		categorieDao = DAOFactory.getInstance().getCategorieDao();
		Categorie categorie = categorieDao.findCategorie(nameCategorie);
		Annonce annonce = new Annonce();
		annonce.setName(nameAnnonce);
		
	}
	public void supprimerAnnonce(){}
	public void modifierAnnonce(){}
	/*Affichage*/
	public void afficherAdresse(){}
	
}
