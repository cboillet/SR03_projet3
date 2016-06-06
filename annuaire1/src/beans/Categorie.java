package beans;

import java.util.List;

public class Categorie {
	private Long id;
	private String name;
	private List<Annonce> annonces;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	public List<Annonce> getAnnonces() {
		return annonces;
	}
	public void setAnnonces(List<Annonce> annonces) {
		this.annonces = annonces;
	}
	public void addAnnonce(String name){
		Annonce annonce = new Annonce();
		this.annonces.add(annonce);
	}
	public Annonce getAnnonce(int id){
		Annonce a = null;
		for (Annonce annonce : annonces) {
	        if ( annonce.getId() == id ) {
	            a = annonce;
	        }
	    }
		return a;
	}
	public void removeAnnonce(int id){
		annonces.remove(getAnnonce(id));
	}
	
	
}
