package dao;

import static dao.DAOUtilitaire.fermeturesSilencieuses;
import static dao.DAOUtilitaire.getRequetePreparee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import beans.Categorie;

public class CategorieDaoImpl implements CategorieDao {
	private static final String SQL_INSERT = "INSERT INTO categorie (name) VALUES (?)";
	private static final String SQL_FIND_CATEGORIE = "SELECT * FROM categorie WHERE categorie.id=?";	
	private static final String SQL_MODIFY = "UPDATE categorie SET categorie.name=? WHERE categorie.id=?";	
	private static final String SQL_DELETE = "DELETE FROM categorie WHERE categorie.id=?";	 
	private static final String SQL_SELECT_ALL = "SELECT * FROM categorie";
	private DAOFactory daoFactory;
	
	CategorieDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
	
	@Override 
	public void creer(Categorie categorie) throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = (Connection) daoFactory.getConnection();
            preparedStatement = getRequetePreparee( connexion, SQL_INSERT, true, categorie.getName());
            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création de la question, aucune ligne ajoutée dans la table." );
            }
            
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
                categorie.setId( valeursAutoGenerees.getLong( 1 ) );
            } else {
                throw new DAOException( "Échec de la création de la categorie en base, aucun ID auto-généré retourné." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
	}
	
	@Override
	public void modify( Categorie categorie) throws DAOException{
		Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = (Connection) daoFactory.getConnection();
            preparedStatement = getRequetePreparee( connexion, SQL_MODIFY, false, categorie.getName(), categorie.getId() );
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
	public void delete( Long categorieID) throws DAOException{
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = (Connection) daoFactory.getConnection();
            preparedStatement = getRequetePreparee( connexion, SQL_DELETE, false, categorieID );
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
	public Categorie findCategorie( Long categorieID) throws DAOException{
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Categorie categorie = new Categorie();
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = (Connection) daoFactory.getConnection();
            preparedStatement = getRequetePreparee( connexion, SQL_FIND_CATEGORIE, false, categorieID );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while ( resultSet.next() ) {
                categorie.setId(resultSet.getLong("id"));
                categorie.setName(resultSet.getString("name"));
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
		return categorie;
	}
	
	@Override
	public List<Categorie> listerCategorie() throws DAOException{
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Categorie> liste = new ArrayList<Categorie>();

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = (Connection) daoFactory.getConnection();
            preparedStatement = getRequetePreparee( connexion, SQL_SELECT_ALL, false );
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
	
	 
    private static Categorie map( ResultSet resultSet ) throws SQLException {
    	Categorie categorie = new Categorie();
    	categorie.setId(resultSet.getLong( "id" ));
    	categorie.setName(resultSet.getString( "name" ));
        return categorie;
    }
}
