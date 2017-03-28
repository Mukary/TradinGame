package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import DAO.AbstractDAO;
import models.Report;


public class PostgresReportDAO extends AbstractDAO<Report> {

	@Override
	public int create(Report obj) throws SQLException {
		int res = 0;		
		PreparedStatement stmt = connect.prepareStatement("INSERT INTO \"Report\"(\"reportTopic\", \"reportDescription\", \"serviceID\", \"userNickname\") VALUES (?, ?, ?, ?)");
		stmt.setString(1, obj.getTopic());
		stmt.setString(2, obj.getDescription());
		stmt.setInt(3, obj.getServiceID());
		stmt.setString(4, obj.getUserNickname());		
		res = stmt.executeUpdate();	        
		return res;
	}

	@Override
	public int delete(Report obj) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Report obj) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Report find(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Report> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
