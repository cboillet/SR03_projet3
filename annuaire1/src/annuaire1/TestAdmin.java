package annuaire1;

public class TestAdmin {
	private static Admin admin;
	
	public TestAdmin(){
		
	}
	
	public static void main(String[] args){
		admin = new Admin();
		String resultat= admin.listAnnoncesParVille("Compiegne");
		System.out.println(resultat);
	}
}
