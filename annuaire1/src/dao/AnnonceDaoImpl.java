package dao;

public class AnnonceDaoImpl implements AnnonceDao {
	
	private DAOFactory daoFactory;
	
	AnnonceDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
}
