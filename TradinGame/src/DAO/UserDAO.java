package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.User;

public class UserDAO extends AbstractDAO<User>{

	@Override
	public void create(User obj) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User obj) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(User obj) throws SQLException {
		// TODO Auto-generated method stub

	}
	
	@Override
	public User find(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public User find(String nickname) throws SQLException {
		// TODO Auto-generated method stub
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
	public ArrayList<User> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
