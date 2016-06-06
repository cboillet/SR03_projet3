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
import beans.Question;

public class CategorieDaoImpl implements CategorieDao {
	private static final String SQL_INSERT = "INSERT INTO categorie (name) VALUES (?)";
	private static final String SQL_FIND_CATEGORIE = "SELECT * FROM categorie WHERE categorie.name=?";	
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
                throw new DAOException( "Échec de la création de la question en base, aucun ID auto-généré retourné." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
	}
	
	@Override
	public Categorie findCategorie( String categorieName) throws DAOException{
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Categorie categorie = new Categorie();
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = (Connection) daoFactory.getConnection();
            preparedStatement = getRequetePreparee( connexion, SQL_FIND_CATEGORIE, false, categorieName );
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
}
