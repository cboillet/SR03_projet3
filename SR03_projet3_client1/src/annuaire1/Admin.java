/**
 * Admin.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package annuaire1;

public interface Admin extends java.rmi.Remote {
    public void supprimerCategorie(long categorieID) throws java.rmi.RemoteException;
    public void modifierCategorie(long categorieID, java.lang.String newName) throws java.rmi.RemoteException;
    public java.lang.String listAnnoncesParVille(java.lang.String ville) throws java.rmi.RemoteException;
    public java.lang.String listAnnoncesParCategorie(long categorieID) throws java.rmi.RemoteException;
    public void creerCategorie(java.lang.String name) throws java.rmi.RemoteException;
    public void creerAnnonce(java.lang.String name, long telephone, long categorieID, long numero, java.lang.String rue, long codePostal, java.lang.String ville) throws java.rmi.RemoteException;
    public void supprimerAnnonce(long annonceID) throws java.rmi.RemoteException;
    public void modifierAnnonce(long idAnnonce, java.lang.String name, long telephone, long categorieID, long numero, java.lang.String rue, long codePostal, java.lang.String ville) throws java.rmi.RemoteException;
    public java.lang.String listCategories() throws java.rmi.RemoteException;
    public java.lang.String getCategorie(long categorieID) throws java.rmi.RemoteException;
    public java.lang.String getAnnonce(long annonceID) throws java.rmi.RemoteException;
}
