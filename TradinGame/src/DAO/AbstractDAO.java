package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.Database;

/**
 * Data Access Object : Provides 4 functions according to the <strong>CRUD</strong> pattern.
 * @author Max Cabourg
 *
 * @param <T> Type of object 
 */
public abstract class AbstractDAO<T> {

  protected Connection connect;
  
  /**
   * Initializes the database.
   */
  public AbstractDAO()
  {
	  connect = Database.getInstance().getConnexion();
  }
   
  /**
  * Inserts a T object in the database
  * @param obj the object to insert in the database
  * @return 1 if the query succeeded, 0 otherwise
  * @throws SQLException
  */
  public abstract int create(T obj) throws SQLException;

  /**
  * Deletes a T object in the database
  * @param obj the object to delete
  * @return 1 if the query succeeded, 0 otherwise
  * @throws SQLException 
  */
  public abstract int delete(T obj) throws SQLException;

  /**
  * Updates a T object in the database
  * @param obj the object to update
  * @return 1 if the query succeeded, 0 otherwise
  * @throws SQLException 
  */
  public abstract int update(T obj) throws SQLException;

  /**
  * Finds an object in the database thanks to its ID
  * @param id id of the object
  * @throws SQLException 
  */
  public abstract T find(int id) throws SQLException;
  
  
  /**
   * Finds every row of the class concerned in the database
   * @return a list of every object stored in the database.
   * @throws SQLException 
   */
  public abstract ArrayList<T> getAll() throws SQLException;
}