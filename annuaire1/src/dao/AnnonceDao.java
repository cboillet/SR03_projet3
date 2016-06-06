package dao;

import beans.Annonce;
import beans.Categorie;

public interface AnnonceDao {
	void creer( Long idCategorie, Annonce annonce) throws DAOException;
	void delete( String annonceName) throws DAOException;
	void modify(Long idAnnonce, String name, int telephone, int numero, String rue, int codePostal, String ville) throws DAOException;
	Annonce findAnnonce( Long idAnnonce) throws DAOException;
}
