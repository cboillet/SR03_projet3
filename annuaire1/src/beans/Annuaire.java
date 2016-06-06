package beans;

import java.util.List;

public class Annuaire {
	private List<Categorie> categories;

	public void addCategorie(String name){
		Categorie categorie = new Categorie();
		this.categories.add(categorie);
	}
	public Categorie getCategorie(int id){
		Categorie c = null;
		for (Categorie categorie : categories) {
	        if ( categorie.getId() == id ) {
	            c = categorie;
	        }
	    }
		return c;
	}
	public Categorie getCategorie(String name){
		Categorie c = null;
		for (Categorie categorie : categories) {
	        if ( categorie.getName() == name ) {
	            c = categorie;
	        }
	    }
		return c;
	}
	public void removeCategorie(int id){
		categories.remove(getCategorie(id));
	}
}
