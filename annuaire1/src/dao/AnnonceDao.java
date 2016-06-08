package dao;

import java.util.List;

import beans.Annonce;

public interface AnnonceDao {
	void creer( Annonce annonce) throws DAOException;
	void delete( Long idAnnonce) throws DAOException;
	void modify( Annonce annonce ) throws DAOException;
	Annonce findAnnonce( Long idAnnonce ) throws DAOException;
	List<Annonce> listerAnnonce( Long idCategorie ) throws DAOException;
	List<Annonce> listerAnnonceVille( String ville ) throws DAOException;
}
