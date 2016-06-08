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
	
	public Admin(){
		this.categorieDao = DAOFactory.getInstance().getCategorieDao();
		this.annonceDao = DAOFactory.getInstance().getAnnonceDao();
	}
	
	/*********** Catégories ***********/
	public void creerCategorie(String name){
		Categorie categorie = new Categorie();
		categorie.setName(name);
		categorieDao.creer(categorie);
	}
	public void modifierCategorie(Long categorieID, String newName){
		Categorie categorie = new Categorie();
		categorie.setId(categorieID);
		categorie.setName(newName);
		categorieDao.modify(categorie);
	}
	public void supprimerCategorie(Long categorieID){
		categorieDao.delete(categorieID);
	}
	//liste les catégories
	public String listCategories(){
		List<Categorie> liste = categorieDao.listerCategorie();
		String resultat="<liste>";
		for(Categorie categorie : liste){
			resultat += "<categorie>"
					 + "<id>" + categorie.getId() + "</id>"
					 + "<nom>" + categorie.getName() + "</nom>"
					 + "</categorie>"; 
		}
		resultat += "</liste>";
		return resultat;
	}
	//récupère une catégorie par son ID
	public String getCategorie(Long categorieID){
		Categorie categorie = categorieDao.findCategorie(categorieID);
		return "<categorie>"
		 + "<id>" + categorie.getId() + "</id>"
		 + "<nom>" + categorie.getName() + "</nom>"
		 + "</categorie>"; 
	}
	
	
	/*********** Annonces ***********/
	public void creerAnnonce(String name, String telephone, Long categorieID, Long numero, String rue, String codePostal, String ville){
		Annonce annonce = new Annonce();
		Adresse adresse = new Adresse();
		annonce.setName(name);
		annonce.setTelephone(telephone);
		annonce.setId_categorie(categorieID);
		adresse.setNumero(numero);
		adresse.setRue(rue);
		adresse.setCodePostal(codePostal);
		adresse.setVille(ville);
		annonce.setAdresse(adresse);
		annonceDao.creer(annonce);
	}
	public void modifierAnnonce(Long idAnnonce, String name, String telephone, Long categorieID, Long numero, String rue, String codePostal, String ville){
		Annonce annonce = annonceDao.findAnnonce(idAnnonce);
		Adresse adresse = annonce.getAdresse();
		annonce.setName(name);
		annonce.setTelephone(telephone);
		annonce.setId_categorie(categorieID);
		adresse.setNumero(numero);
		adresse.setRue(rue);
		adresse.setCodePostal(codePostal);
		adresse.setVille(ville);
		annonce.setAdresse(adresse);
		annonceDao.modify(annonce);
	}
	public void supprimerAnnonce(Long annonceID){
		annonceDao.delete(annonceID);
	}
	//liste les annonces
	public String listAnnonces(){
		List<Annonce> liste = annonceDao.listerAnnonce();
		String resultat="<liste>";
		Adresse adresse;
		for(Annonce annonce : liste){
			adresse = annonce.getAdresse();
			resultat += "<annonce>"
					 + "<id>" + annonce.getId() + "</id>"
					 + "<nom>" + annonce.getName() + "</nom>"
					 + "<cat>" + annonce.getId_categorie() + "</cat>"
					 + "<telephone>" + annonce.getTelephone() + "</telephone>" 
					 + "<adresse>" 
					 	+ "<numero>" + adresse.getNumero() + "</numero>"
					 	+ "<rue>" + adresse.getRue() + "</rue>"
					 	+ "<codepostal>" + adresse.getCodePostal() + "</codepostal>"
					 	+ "<ville>" + adresse.getVille() + "</ville>"
					 + "</adresse>"
					 + "</annonce>"; 
		}
		resultat += "</liste>";
		return resultat;
	}
	//liste les annonces par ville
	public String listAnnoncesParNom(String nom){
		List<Annonce> liste = annonceDao.listerAnnonceNom(nom);
		String resultat = "<liste>";
		Adresse adresse;
		for(Annonce annonce : liste){
			adresse = annonce.getAdresse();
			resultat += "<annonce>"
					 + "<id>" + annonce.getId() + "</id>"
					 + "<nom>" + annonce.getName() + "</nom>"
					 + "<cat>" + annonce.getId_categorie() + "</cat>"
					 + "<telephone>" + annonce.getTelephone() + "</telephone>" 
					 + "<adresse>" 
					 	+ "<numero>" + adresse.getNumero() + "</numero>"
					 	+ "<rue>" + adresse.getRue() + "</rue>"
					 	+ "<codepostal>" + adresse.getCodePostal() + "</codepostal>"
					 	+ "<ville>" + adresse.getVille() + "</ville>"
					 + "</adresse>"
					 + "</annonce>"; 
		}
		resultat += "</liste>";
		return resultat;
	}
	//liste les annonces par ville
	public String listAnnoncesParVille(String ville){
		List<Annonce> liste = annonceDao.listerAnnonceVille(ville);
		String resultat = "<liste>";
		Adresse adresse;
		for(Annonce annonce : liste){
			adresse = annonce.getAdresse();
			resultat += "<annonce>"
					 + "<id>" + annonce.getId() + "</id>"
					 + "<nom>" + annonce.getName() + "</nom>"
					 + "<cat>" + annonce.getId_categorie() + "</cat>"
					 + "<telephone>" + annonce.getTelephone() + "</telephone>" 
					 + "<adresse>" 
					 	+ "<numero>" + adresse.getNumero() + "</numero>"
					 	+ "<rue>" + adresse.getRue() + "</rue>"
					 	+ "<codepostal>" + adresse.getCodePostal() + "</codepostal>"
					 	+ "<ville>" + adresse.getVille() + "</ville>"
					 + "</adresse>"
					 + "</annonce>"; 
		}
		resultat += "</liste>";
		return resultat;
	}
	//liste les annonces par catégorie
	public String listAnnoncesParCategorie(Long categorieID){
		List<Annonce> liste = annonceDao.listerAnnonceCat(categorieID);
		String resultat = "<liste>";
		Adresse adresse;
		for(Annonce annonce : liste){
			adresse = annonce.getAdresse();
			resultat += "<annonce>"
					 + "<id>" + annonce.getId() + "</id>"
					 + "<nom>" + annonce.getName() + "</nom>"
					 + "<cat>" + annonce.getId_categorie() + "</cat>"
					 + "<telephone>" + annonce.getTelephone() + "</telephone>" 
					 + "<adresse>" 
					 	+ "<numero>" + adresse.getNumero() + "</numero>"
					 	+ "<rue>" + adresse.getRue() + "</rue>"
					 	+ "<codepostal>" + adresse.getCodePostal() + "</codepostal>"
					 	+ "<ville>" + adresse.getVille() + "</ville>"
					 + "</adresse>"
					 + "</annonce>"; 
		}
		resultat += "</liste>";
		return resultat;
	}
	//récupère une annonce par son ID
	public String getAnnonce(Long annonceID){
		String resultat;
		Annonce annonce = annonceDao.findAnnonce(annonceID);
		Adresse adresse = annonce.getAdresse();
		resultat="<annonce>"
				 + "<id>" + annonce.getId() + "</id>"
				 + "<nom>" + annonce.getName() + "</nom>"
				 + "<cat>" + annonce.getId_categorie() + "</cat>"
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
}
