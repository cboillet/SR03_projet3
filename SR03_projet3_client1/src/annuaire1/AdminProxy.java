package annuaire1;

public class AdminProxy implements annuaire1.Admin {
  private String _endpoint = null;
  private annuaire1.Admin admin = null;
  
  public AdminProxy() {
    _initAdminProxy();
  }
  
  public AdminProxy(String endpoint) {
    _endpoint = endpoint;
    _initAdminProxy();
  }
  
  private void _initAdminProxy() {
    try {
      admin = (new annuaire1.AdminServiceLocator()).getAdmin();
      if (admin != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)admin)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)admin)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (admin != null)
      ((javax.xml.rpc.Stub)admin)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public annuaire1.Admin getAdmin() {
    if (admin == null)
      _initAdminProxy();
    return admin;
  }
  
  public void supprimerCategorie(long categorieID) throws java.rmi.RemoteException{
    if (admin == null)
      _initAdminProxy();
    admin.supprimerCategorie(categorieID);
  }
  
  public void modifierCategorie(long categorieID, java.lang.String newName) throws java.rmi.RemoteException{
    if (admin == null)
      _initAdminProxy();
    admin.modifierCategorie(categorieID, newName);
  }
  
  public void creerCategorie(java.lang.String name) throws java.rmi.RemoteException{
    if (admin == null)
      _initAdminProxy();
    admin.creerCategorie(name);
  }
  
  public java.lang.String listCategories() throws java.rmi.RemoteException{
    if (admin == null)
      _initAdminProxy();
    return admin.listCategories();
  }
  
  public java.lang.String listAnnoncesParVille(java.lang.String ville) throws java.rmi.RemoteException{
    if (admin == null)
      _initAdminProxy();
    return admin.listAnnoncesParVille(ville);
  }
  
  public java.lang.String listAnnoncesParCategorie(long categorieID) throws java.rmi.RemoteException{
    if (admin == null)
      _initAdminProxy();
    return admin.listAnnoncesParCategorie(categorieID);
  }
  
  public void creerAnnonce(java.lang.String name, long telephone, long categorieID, long numero, java.lang.String rue, long codePostal, java.lang.String ville) throws java.rmi.RemoteException{
    if (admin == null)
      _initAdminProxy();
    admin.creerAnnonce(name, telephone, categorieID, numero, rue, codePostal, ville);
  }
  
  public void supprimerAnnonce(long annonceID) throws java.rmi.RemoteException{
    if (admin == null)
      _initAdminProxy();
    admin.supprimerAnnonce(annonceID);
  }
  
  public void modifierAnnonce(long idAnnonce, java.lang.String name, long telephone, long categorieID, long numero, java.lang.String rue, long codePostal, java.lang.String ville) throws java.rmi.RemoteException{
    if (admin == null)
      _initAdminProxy();
    admin.modifierAnnonce(idAnnonce, name, telephone, categorieID, numero, rue, codePostal, ville);
  }
  
  public java.lang.String getCategorie(long categorieID) throws java.rmi.RemoteException{
    if (admin == null)
      _initAdminProxy();
    return admin.getCategorie(categorieID);
  }
  
  public java.lang.String getAnnonce(long annonceID) throws java.rmi.RemoteException{
    if (admin == null)
      _initAdminProxy();
    return admin.getAnnonce(annonceID);
  }
  
  public java.lang.String listAnnonces() throws java.rmi.RemoteException{
    if (admin == null)
      _initAdminProxy();
    return admin.listAnnonces();
  }
  
  
}