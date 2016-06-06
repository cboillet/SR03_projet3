package dao;

import beans.Annonce;

public interface AnnonceDao {
	void creer( Long idCategorie, Annonce annonce) throws DAOException;
}
