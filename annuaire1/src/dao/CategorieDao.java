package dao;

import java.util.List;

import beans.Categorie;

public interface CategorieDao {
	void creer( Categorie categorie) throws DAOException;
	void modify( Categorie categorie) throws DAOException;
	void delete( Long categorieID) throws DAOException;
	Categorie findCategorie( Long idCategorie) throws DAOException;
	List<Categorie> listerCategorie() throws DAOException;
}
