package dao;

import beans.Categorie;

public interface CategorieDao {
	void creer( Categorie categorie) throws DAOException;
	void modify( Categorie categorie) throws DAOException;
	Categorie findCategorie( String cateogireName) throws DAOException;
}
