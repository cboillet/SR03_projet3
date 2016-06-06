package annuaire1;

public class TestAdmin {
	private static Admin admin;
	
	public TestAdmin(){
		
	}
	
	public static void main(String[] args){
		admin = new Admin();
		admin.modifierCategorie("Loisirs","Tourisme");
	}
}
