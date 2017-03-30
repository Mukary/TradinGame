package DAO;

import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.User;
import util.Util;

public class PostgresUserDAO extends AbstractDAO<User>{
	
	@Override
	/**
	 * Inserts an User object in the database
	 * @param obj: the user object to be inserted
	 * @throws SQLException: When the query fails
	 */
	public int create(User obj) throws SQLException {
		int res = 0;
		try{
			PreparedStatement stmt = connect.prepareStatement("INSERT INTO \"user\"(nickname, firstname, lastname, country, city, address, is_admin, is_banned, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, obj.getNickname());
			stmt.setString(2, obj.getFirstname());
			stmt.setString(3, obj.getLastname());
			stmt.setString(4, obj.getCountry());
			stmt.setString(5, obj.getCity());
			stmt.setString(6, obj.getAddress());
			stmt.setBoolean(7, obj.isIsAdmin());
			stmt.setBoolean(8, obj.isIsBanned());
			stmt.setString(9, Util.sha1(obj.getPassword()));
			res = stmt.executeUpdate();
	   }catch(SQLException se){
	      se.printStackTrace();
	   }catch(Exception e){
	      e.printStackTrace();
	   }
		return res;
	}

	@Override
	/**
	 * Deletes an user in the database
	 * @param obj: The user to be deleted
	 * @throws SQLException: When the query fails
	 */
	public int delete(User obj) throws SQLException {
		int res = 0;
		try{
			PreparedStatement stmt = connect.prepareStatement("DELETE FROM \"user\" WHERE nickname = ?");
			stmt.setString(1, obj.getNickname());
			res = stmt.executeUpdate();
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}

	@Override
	/**
	 * Updates an user in the database
	 * @param obj: the user to be updated in the database
	 * @throws SQLException: When the query fails
	 */
	public int update(User obj) throws SQLException {
        int res = 0;
        try{
            PreparedStatement stmt = connect.prepareStatement("UPDATE \"user\" SET nickname = ?, firstname = ?, lastname = ?, country = ?, city = ?, address = ?, is_admin = ?, is_banned = ? WHERE nickname = ?");
            stmt.setString(1, obj.getNickname());
            stmt.setString(2, obj.getFirstname());
            stmt.setString(3, obj.getLastname());
            stmt.setString(4, obj.getCountry());
            stmt.setString(5, obj.getCity());
            stmt.setString(6, obj.getAddress());
            stmt.setBoolean(7, obj.isIsAdmin());
            stmt.setBoolean(8, obj.isIsBanned());

            stmt.setString(9, obj.getNickname());
            res = stmt.executeUpdate();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return res;

	}
	
	@Override
	public User find(int id) throws SQLException {
		PreparedStatement stmt = connect.prepareStatement("SELECT * FROM \"user\" WHERE id = ?");
		stmt.setInt(1, id);

		ResultSet rs = stmt.executeQuery();
		if(rs.next()){
			String nickname = rs.getString("nickname");
			String password = rs.getString("password");
			String firstname = rs.getString("firstname");
			String lastname = rs.getString("lastname");
			String country = rs.getString("country");
			String city = rs.getString("city");
			String address = rs.getString("address");
			Boolean isBanned = rs.getBoolean("is_banned");
			Boolean isAdmin = rs.getBoolean("is_admin");
			rs.close();
			stmt.close();
			return new User(nickname, firstname, lastname, password, country, city, address, isBanned, isAdmin);
		}
		else
			return null;
	}
	
	/**
	 * Finds an user in the database given the nickname and the password
	 * @param nickname nickname of the user to find
	 * @param password password NOT HASHED YET of the user to find
	 * @return the user object if it was found, null otherwise
	 * @throws SQLException
	 */
	public User find(String nickname, String password) throws SQLException{

		PreparedStatement stmt = connect.prepareStatement("SELECT * FROM \"user\" WHERE nickname = ? AND password = ?");
		stmt.setString(1, nickname);
		try {
			stmt.setString(2,  Util.sha1(password));

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		ResultSet rs = stmt.executeQuery();
		if(rs.next()){


			String firstname = rs.getString("firstname");
			String lastname = rs.getString("lastname");
			String country = rs.getString("country");
			String city = rs.getString("city");
			String address = rs.getString("address");
			Boolean isBanned = rs.getBoolean("is_banned");
			Boolean isAdmin = rs.getBoolean("is_admin");
			rs.close();
			stmt.close();
			return new User(nickname, firstname, lastname, password, country, city, address, isBanned, isAdmin);
		}
		else

			return null;
		
	}

	
	/**
	 * Finds an user in the database given his nickname
	 * @param nickname: The given nickname to use for the query
	 * @return the user object if the user was found, null otherwise
	 * @throws SQLException: When the query fails
	 */
	public User find(String nickname) throws SQLException {
		PreparedStatement stmt = connect.prepareStatement("SELECT * FROM \"user\" WHERE nickname = ?");
		stmt.setString(1, nickname);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()){
			String firstname = rs.getString("firstname");
			String lastname = rs.getString("lastname");
			String password = rs.getString("password");
			String country = rs.getString("country");
			String city = rs.getString("city");
			String address = rs.getString("address");
			Boolean isBanned = rs.getBoolean("is_banned");
			Boolean isAdmin = rs.getBoolean("is_admin");
			rs.close();
			stmt.close();
			return new User(nickname, firstname, lastname, password, country, city, address, isBanned, isAdmin);
		}
		else
			return null;
	}
	
	@Override
	/**
	 * Finds all the user in the database
	 * @return an ArrayList containing all the users
	 * @throws SQLException: When the query fails
	 */
	public ArrayList<User> getAll() throws SQLException {
        ArrayList<User> users = new ArrayList<User>();
	    PreparedStatement stmt = connect.prepareStatement("SELECT * FROM \"user\" ");
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            String nickname = rs.getString("nickname");
            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
            String password = rs.getString("password");
            String country = rs.getString("country");
            String city = rs.getString("city");
            String address = rs.getString("address");
            Boolean isBanned = rs.getBoolean("is_banned");
            Boolean isAdmin = rs.getBoolean("is_admin");
            users.add(new User(nickname, firstname, lastname, password, country, city, address, isBanned, isAdmin));
        }
        rs.close();
        stmt.close();
        return users;
    }
}
