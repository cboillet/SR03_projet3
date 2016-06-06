/**
 * Admin.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package annuaire1;

public interface Admin extends java.rmi.Remote {
    public void creerAnnonce(java.lang.String nameCategorie, java.lang.String nameAnnonce) throws java.rmi.RemoteException;
    public void supprimerAnnonce() throws java.rmi.RemoteException;
    public java.lang.String afficherAdresse() throws java.rmi.RemoteException;
    public void modifierAnnonce() throws java.rmi.RemoteException;
    public java.lang.String afficherNom(java.lang.String nomAnnonce) throws java.rmi.RemoteException;
    public java.lang.String getCategorie(java.lang.String gategorieName) throws java.rmi.RemoteException;
    public java.lang.String listCategorie() throws java.rmi.RemoteException;
    public void creerCategorie(java.lang.String name) throws java.rmi.RemoteException;
    public void supprimerCategorie() throws java.rmi.RemoteException;
    public void modifierCategorie(java.lang.String oldName, java.lang.String newName) throws java.rmi.RemoteException;
}
