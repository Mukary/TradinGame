package database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.SynchronousQueue;

/**
 * Utility class that initializes the JDBC driver. It reads a file to get the logins. It is a singleton.
 * @author MAX
 *
 */
public class Database {
	
	private static String FICHIER_PROPERTIES;
    private static final String PROPERTY_URL             = "url";
    private static final String PROPERTY_DRIVER          = "driver";
    private static final String PROPERTY_USERNAME 		 = "nomutilisateur";
    private static final String PROPERTY_PASSWORD   	 = "motdepasse";
    public static Connection conn;
    private static Database db;
    
    /**
     * Static constant representing Postgresql RDBMS
     */
    public static final int POSTGRESQL = 0;
    /**
     * Initialize the JDBC driver
     */
    private Database()
    {
    	Properties properties = new Properties();
		String url;
		String driver;
		String nomUtilisateur;
		String motDePasse;

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );
		try {
			properties.load( fichierProperties );		
			url = properties.getProperty( PROPERTY_URL );
	        driver = properties.getProperty( PROPERTY_DRIVER );
	        nomUtilisateur = properties.getProperty( PROPERTY_USERNAME);
	        motDePasse = properties.getProperty(PROPERTY_PASSWORD);
	        Class.forName(driver);
	        conn = DriverManager.getConnection(url, nomUtilisateur, motDePasse);
		} catch (IOException e) {
			System.out.println("Erreur : fichier de configuration introuvable.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Erreur lors de la connexion � la base de donn�es.");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Driver introuvable");
			e.printStackTrace();
		}
		        
    }
    
    /**
     * Applies the singleton pattern. Checks if there is already a connection in the database. If not, creates the connection.
     * @return the instance of the database.
     */
    public static Database getInstance(){
    	if(db==null){
    		db = new Database();
    		return db;
    	}
    	return db;
    	
    }
    
    /**
     * Sets the RDBMS used by the software
     * @param sgbd_type RDBMS to be used. It is desirable to use Database static constants.
     */
    public static void setSgbd(int sgbd_type){
    	switch(sgbd_type){
    	case POSTGRESQL:
    		FICHIER_PROPERTIES = "./database/database.properties";
    	}
    }
    
    /**
     * 
     * @return The connection to the database
     */
    public Connection getConnexion()
    {
    	return conn;
    }

}