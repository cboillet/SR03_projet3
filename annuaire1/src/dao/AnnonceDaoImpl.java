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
	public void modify(Long idAnnonce, String name, int telephone, int numero, String rue, int codePostal, String ville) throws DAOException{}
	
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
}
