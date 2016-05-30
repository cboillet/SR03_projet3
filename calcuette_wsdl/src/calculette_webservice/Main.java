package calculette_webservice;

import java.rmi.RemoteException;

public class Main {
	private static CalculetteProxy calculetteProxy;
	
	public Main(){
	}
	
	public static void main(String[] args) {
				calculetteProxy = new CalculetteProxy();
				try{
					int addition = calculetteProxy.add(2, 3);
					int multiplication = calculetteProxy.mul(2, 3);
					int soustraction = calculetteProxy.sou(1, 2);
					
					System.out.println("résultats addition : " + addition);
					System.out.println("résultats multiplication : " + multiplication);
					System.out.println("résultats soustraction : " + soustraction);
				}
				catch(RemoteException e){
					e.printStackTrace();
				}
				
	}
}
