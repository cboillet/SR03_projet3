package dao;

import beans.Categorie;

public interface CategorieDao {
	void creer( Categorie categorie) throws DAOException;
	void modify( Categorie categorie) throws DAOException;
	void delete( String categorieName) throws DAOException;
	Categorie findCategorie( String categorieName) throws DAOException;
}
