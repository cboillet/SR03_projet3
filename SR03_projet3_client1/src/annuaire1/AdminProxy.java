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
  
  public void supprimerAnnonce() throws java.rmi.RemoteException{
    if (admin == null)
      _initAdminProxy();
    admin.supprimerAnnonce();
  }
  
  public java.lang.String afficherAdresse() throws java.rmi.RemoteException{
    if (admin == null)
      _initAdminProxy();
    return admin.afficherAdresse();
  }
  
  public void modifierAnnonce() throws java.rmi.RemoteException{
    if (admin == null)
      _initAdminProxy();
    admin.modifierAnnonce();
  }
  
  public java.lang.String afficherNom(java.lang.String nomAnnonce) throws java.rmi.RemoteException{
    if (admin == null)
      _initAdminProxy();
    return admin.afficherNom(nomAnnonce);
  }
  
  public java.lang.String getCategorie(java.lang.String gategorieName) throws java.rmi.RemoteException{
    if (admin == null)
      _initAdminProxy();
    return admin.getCategorie(gategorieName);
  }
  
  public java.lang.String listCategorie() throws java.rmi.RemoteException{
    if (admin == null)
      _initAdminProxy();
    return admin.listCategorie();
  }
  
  public void creerCategorie(java.lang.String name) throws java.rmi.RemoteException{
    if (admin == null)
      _initAdminProxy();
    admin.creerCategorie(name);
  }
  
  public void supprimerCategorie() throws java.rmi.RemoteException{
    if (admin == null)
      _initAdminProxy();
    admin.supprimerCategorie();
  }
  
  public void creerAnnonce(java.lang.String nameCategorie, java.lang.String nameAnnonce) throws java.rmi.RemoteException{
    if (admin == null)
      _initAdminProxy();
    admin.creerAnnonce(nameCategorie, nameAnnonce);
  }
  
  public void modifierCategorie(java.lang.String oldName, java.lang.String newName) throws java.rmi.RemoteException{
    if (admin == null)
      _initAdminProxy();
    admin.modifierCategorie(oldName, newName);
  }
  
  
}