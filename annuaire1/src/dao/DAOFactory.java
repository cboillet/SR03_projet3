package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOFactory {
	 private static final String FICHIER_PROPERTIES       = "/dao/dao.properties";
	 private static final String PROPERTY_URL             = "url";
	 private static final String PROPERTY_DRIVER          = "driver";
	 private static final String PROPERTY_NOM_UTILISATEUR = "login";
	 private static final String PROPERTY_MOT_DE_PASSE    = "mdp";
	 
	 private String              url; //certainement à enlever
     private String              username;
     private String              password;

	    /* Création d'un objet DAOFactory (méthode private) */
	    DAOFactory( String url, String username, String password ) {
	        this.url = url;
	        this.username = username;
	        this.password = password;
	    }

	 /*
	     * Méthode chargée de récupérer les informations de connexion à la base de
	     * données, charger le driver JDBC et retourner une instance de la Factory
	     */
	 public static DAOFactory getInstance() throws DAOConfigurationException {
		 Properties properties = new Properties();
		 String url;
	     String driver;
	     String nomUtilisateur;
	     String motDePasse;
	     
	     url = "jdbc:mysql://tuxa.sme.utc:3306/sr03p004";//properties.getProperty( PROPERTY_URL );
	     driver = "com.mysql.jdbc.Driver";//properties.getProperty( PROPERTY_DRIVER );
	     nomUtilisateur = "sr03p004";//properties.getProperty( PROPERTY_NOM_UTILISATEUR );
	     motDePasse = "EvLjGbN2"; //properties.getProperty( PROPERTY_MOT_DE_PASSE );
        
	     try {
	            Class.forName( driver );
	        } catch ( ClassNotFoundException e ) {
	            throw new DAOConfigurationException( "Le driver est introuvable dans le classpath.", e );
	        }

	        DAOFactory instance = new DAOFactory( url, nomUtilisateur, motDePasse );
	        return instance;
	    }

	    /* Méthode chargée de fournir une connexion à la base de données */
	    Connection getConnection() throws SQLException {
	        return DriverManager.getConnection( url, username, password );
	    }

	    /* Méthodes de récupération de l'implémentation des différents DAO */
	    public AnnonceDao getAnnonceDao() {
	        return new AnnonceDaoImpl( this );
	    }
	    public CategorieDao getCategorieDao() {
	        return new CategorieDaoImpl( this );
	    }
	     
}
