package annuaire1;

import java.util.List;

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
	public void modifierCategorie(Long categorieID, String newName){
		categorieDao = DAOFactory.getInstance().getCategorieDao();
		Categorie categorie = categorieDao.findCategorie(categorieID);
		categorie.setName(newName);
		System.out.println("Numero de la categorie" + categorie.getId());
		categorieDao.modify(categorie);
	}
	
	public void supprimerCategorie(Long categorieID){
		categorieDao = DAOFactory.getInstance().getCategorieDao();
		categorieDao.delete(categorieID);
	}
	
	/*Annonces*/
	public void creerAnnonce(Long categorieID, String nameAnnonce){
		categorieDao = DAOFactory.getInstance().getCategorieDao();
		annonceDao = DAOFactory.getInstance().getAnnonceDao();
		Categorie categorie = categorieDao.findCategorie(categorieID);
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
		
		if(name != null && !name.isEmpty()){annonce.setName(name);}
		if(telephone != null){annonce.setTelephone(telephone);}
		if(numero != null){adresse.setNumero(numero);}
		if(rue != null && !rue.isEmpty()){ System.out.println("Test rue"); adresse.setRue(rue);}
		if(codePostal!=null){adresse.setCodePostal(codePostal);}
		if(ville != null && !ville.isEmpty()){adresse.setVille(ville);}
		annonce.setAdresse(adresse);
		annonceDao.modify(annonce);
	}
	
	/*Affichage classement par ville*/
	public String afficherAdresse(String ville){
		annonceDao = DAOFactory.getInstance().getAnnonceDao();
		List<Annonce> liste = annonceDao.listerAnnonceVille(ville);
		String resultat = "";
		Adresse adresse;
		for(Annonce annonce : liste){
			adresse = annonce.getAdresse();
			resultat += "<annonce>"
					 + "<id>" + annonce.getId() + "</id>"
					 + "<nom>" + annonce.getName() + "</nom>"
					 + "<telephone>" + annonce.getTelephone() + "</telephone>" 
					 + "<adresse>" 
					 	+ "<numero>" + adresse.getNumero() + "</numero>"
					 	+ "<rue>" + adresse.getRue() + "</rue>"
					 	+ "<codepostal>" + adresse.getCodePostal() + "</codepostal>"
					 	+ "<ville>" + adresse.getVille() + "</ville>"
					 + "</adresse>"
					 + "</annonce>"; 
		}
		return resultat;
	}
	public String listAnnonces(Long categorieID){
		annonceDao = DAOFactory.getInstance().getAnnonceDao();
		List<Annonce> liste = annonceDao.listerAnnonce(categorieID);
		String resultat = "";
		Adresse adresse;
		for(Annonce annonce : liste){
			adresse = annonce.getAdresse();
			resultat += "<annonce>"
					 + "<id>" + annonce.getId() + "</id>"
					 + "<nom>" + annonce.getName() + "</nom>"
					 + "<telephone>" + annonce.getTelephone() + "</telephone>" 
					 + "<adresse>" 
					 	+ "<numero>" + adresse.getNumero() + "</numero>"
					 	+ "<rue>" + adresse.getRue() + "</rue>"
					 	+ "<codepostal>" + adresse.getCodePostal() + "</codepostal>"
					 	+ "<ville>" + adresse.getVille() + "</ville>"
					 + "</adresse>"
					 + "</annonce>"; 
		}
		
		return resultat;
	}
	public String afficherAnnonce(Long annonceID){
		String resultat;
		annonceDao = DAOFactory.getInstance().getAnnonceDao();
		Annonce annonce = annonceDao.findAnnonce(annonceID);
		Adresse adresse = annonce.getAdresse();
		resultat="<annonce>"
				 + "<id>" + annonce.getId() + "</id>"
				 + "<nom>" + annonce.getName() + "</nom>"
				 + "<telephone>" + annonce.getTelephone() + "</telephone>" 
				 + "<adresse>" 
				 	+ "<numero>" + adresse.getNumero() + "</numero>"
				 	+ "<rue>" + adresse.getRue() + "</rue>"
				 	+ "<codepostal>" + adresse.getCodePostal() + "</codepostal>"
				 	+ "<ville>" + adresse.getVille() + "</ville>"
				 + "</adresse>"
				 + "</annonce>"; 
		return resultat;
	}
	public String afficherCategorie(Long categorieID){
		categorieDao = DAOFactory.getInstance().getCategorieDao();
		Categorie categorie = categorieDao.findCategorie(categorieID);

		return "<categorie>"
		 + "<id>" + categorie.getId() + "</id>"
		 + "<nom>" + categorie.getName() + "</nom>"
		 + "</categorie>"; 
	}
	public String listCategories(){
		categorieDao = DAOFactory.getInstance().getCategorieDao();
		List<Categorie> liste = categorieDao.listerCategorie();
		String resultat="";
		for(Categorie categorie : liste){
			resultat += "<categorie>"
					 + "<id>" + categorie.getId() + "</id>"
					 + "<nom>" + categorie.getName() + "</nom>"
					 + "</categorie>"; 
		}
		return resultat;
	}
}
