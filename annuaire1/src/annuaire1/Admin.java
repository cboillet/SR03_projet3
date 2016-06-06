package annuaire1;

import beans.Adresse;
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
		System.out.println("Numero de la categorie" + categorie.getId());
		categorieDao.modify(categorie);
	}
	
	public void supprimerCategorie(String categorieName){
		categorieDao = DAOFactory.getInstance().getCategorieDao();
		categorieDao.delete(categorieName);
	}
	
	/*Annonces*/
	public void creerAnnonce(String nameCategorie, String nameAnnonce){
		categorieDao = DAOFactory.getInstance().getCategorieDao();
		annonceDao = DAOFactory.getInstance().getAnnonceDao();
		Categorie categorie = categorieDao.findCategorie(nameCategorie);
		Annonce annonce = new Annonce();
		annonce.setName(nameAnnonce);
		annonceDao.creer(categorie.getId(), annonce);		
	}
	public void supprimerAnnonce(String annonceName){
		annonceDao = DAOFactory.getInstance().getAnnonceDao();
		annonceDao.delete(annonceName);
	}
	
	public void modifierAnnonce(Long idAnnonce, String name, Long telephone, Long numero, String rue, Long codePostal, String ville){
		annonceDao = DAOFactory.getInstance().getAnnonceDao();
		Annonce annonce = annonceDao.findAnnonce(idAnnonce);
		Adresse adresse = annonce.getAdresse();
		System.out.println("Annonce à modifier" + annonce.getName());
		
		if(!name.equals(null)){annonce.setName(name);}
		if(telephone != null){annonce.setTelephone(telephone);}
		if(numero != null){adresse.setNumero(numero);}
		if(!rue.equals(null)){adresse.setRue(rue);}
		if(codePostal!=null){adresse.setCodePostal(codePostal);}
		if(!ville.equals(null)){adresse.setVille(ville);}
	
	}
	
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
