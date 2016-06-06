package annuaire1;

public class TestAdmin {
	private static Admin admin;
	
	public TestAdmin(){
		
	}
	
	public static void main(String[] args){
		admin = new Admin();
		admin.creerAnnonce("Hebergement","Maison de campagne");
		admin.modifierAnnonce((long)2, null, null, null, null, null, null);	
		
	}
}
