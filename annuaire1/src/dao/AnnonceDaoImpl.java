package dao;

import beans.Adresse;
import beans.Annonce;
import beans.Categorie;

import static dao.DAOUtilitaire.fermeturesSilencieuses;
import static dao.DAOUtilitaire.getRequetePreparee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import beans.Annonce;

public class AnnonceDaoImpl implements AnnonceDao {
	private static final String SQL_INSERT = "INSERT INTO annonce (fk_id_categorie, name) VALUES (?,?)";
	private static final String SQL_DELETE = "DELETE FROM annonce WHERE annonce.name=?";
	private static final String SQL_FIND_ANNONCE = "SELECT * FROM annonce WHERE annonce.id=?";	
	private static final String SQL_MODIFY = "UPDATE annonce SET annonce.name=?, annonce.telephone=?, annonce.numero=?, annonce.rue=?, annonce.codepostal=?, annonce.ville=? WHERE annonce.id=?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM annonce WHERE annonce.fk_id_categorie=?";	
	private static final String SQL_SELECT_VILLE = "SELECT * FROM annonce WHERE annonce.ville=? ORDER BY ville ASC";	
	private DAOFactory daoFactory;
	
	AnnonceDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
	
	@Override
	public void creer( Long idCategorie, Annonce annonce) throws DAOException{
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = (Connection) daoFactory.getConnection();
            preparedStatement = getRequetePreparee( connexion, SQL_INSERT, true, idCategorie, annonce.getName());
            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création de la question, aucune ligne ajoutée dans la table." );
            }
            
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
                annonce.setId( valeursAutoGenerees.getLong( 1 ) );
            } else {
                throw new DAOException( "Échec de la création de la question en base, aucun ID auto-généré retourné." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
	}
	
	@Override
	public void delete( String annonceName) throws DAOException{
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = (Connection) daoFactory.getConnection();
            preparedStatement = getRequetePreparee( connexion, SQL_DELETE, false, annonceName );
            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la suppression de la question, aucune ligne supprimée de la table." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }
	}
	
	@Override
	public void modify(Annonce annonce) throws DAOException{
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        Adresse adresse = annonce.getAdresse();
        //UPDATE annonce SET annonce.name=?, annonce.telephone=?, annonce.numero=?, annonce.rue=?, annonce.codepostal=?, annonce.ville=? WHERE annonce.id=?
        
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = (Connection) daoFactory.getConnection();
            preparedStatement = getRequetePreparee( connexion, SQL_MODIFY, false, annonce.getName(), annonce.getTelephone(), adresse.getNumero(), adresse.getRue(), adresse.getCodePostal(), adresse.getVille(), annonce.getId() );
            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la mise à jour de la categorie, aucune ligne modifiée dans la table." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }		
	}
	
	@Override
	public 	Annonce findAnnonce( Long idAnnonce) throws DAOException{
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Annonce annonce = new Annonce();
        Adresse adresse = new Adresse();
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = (Connection) daoFactory.getConnection();
            preparedStatement = getRequetePreparee( connexion, SQL_FIND_ANNONCE, false, idAnnonce );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while ( resultSet.next() ) {
                annonce.setId(resultSet.getLong("id"));
                annonce.setName(resultSet.getString("name"));
                annonce.setTelephone((long)resultSet.getInt("telephone"));
                adresse.setNumero((long)resultSet.getInt("numero"));
                adresse.setRue(resultSet.getString("rue"));
                adresse.setCodePostal((long)resultSet.getInt("codepostal"));
                adresse.setVille(resultSet.getString("ville"));
                annonce.setAdresse(adresse);
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
		return annonce;
	}
	
	@Override
	public List<Annonce> listerAnnonce(Long idCategorie) throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Annonce> liste = new ArrayList<Annonce>();

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = (Connection) daoFactory.getConnection();
            preparedStatement = getRequetePreparee( connexion, SQL_SELECT_ALL, false, idCategorie );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while ( resultSet.next() ) {
                liste.add(map( resultSet ));
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
		return liste;	
	}
	
	@Override
	public List<Annonce> listerAnnonceVille(String ville) throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Annonce> liste = new ArrayList<Annonce>();

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = (Connection) daoFactory.getConnection();
            preparedStatement = getRequetePreparee( connexion, SQL_SELECT_VILLE, false, ville );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while ( resultSet.next() ) {
                liste.add(map( resultSet ));
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
		return liste;	
	}
	
	  private static Annonce map( ResultSet resultSet ) throws SQLException {
	    	Annonce annonce = new Annonce();
	    	Adresse adresse = new Adresse();
	    	annonce.setId(resultSet.getLong( "id" ));
	    	annonce.setName(resultSet.getString( "name" ));
	    	annonce.setTelephone(resultSet.getLong( "telephone" ));
	    	adresse.setNumero(resultSet.getLong("numero"));
	    	adresse.setRue(resultSet.getString("rue"));
	    	adresse.setCodePostal(resultSet.getLong("codepostal"));
	    	adresse.setVille(resultSet.getString("ville"));
	    	annonce.setAdresse(adresse);
	        return annonce;
	    }
}
