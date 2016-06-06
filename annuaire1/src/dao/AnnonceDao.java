package dao;

import java.util.List;

import beans.Annonce;
import beans.Categorie;

public interface AnnonceDao {
	void creer( Long idCategorie, Annonce annonce) throws DAOException;
	void delete( String annonceName) throws DAOException;
	void modify(Annonce annonce) throws DAOException;
	Annonce findAnnonce( Long idAnnonce) throws DAOException;
	List<Annonce> listerAnnonce( Long idCategorie) throws DAOException;
	List<Annonce> listerAnnonceVille( String ville) throws DAOException;
}
