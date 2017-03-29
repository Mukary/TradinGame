package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		int res = 0;
		
		PreparedStatement stmt = connect.prepareStatement("DELETE FROM \"Report\" WHERE \"reportID\" = ?");
		stmt.setInt(1, obj.getReportID());
		res = stmt.executeUpdate();		
		return res;
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
		ArrayList<Report> allReports = new ArrayList<Report>();
		PreparedStatement stmt = connect.prepareStatement("SELECT * FROM \"Report\"");
		ResultSet resultSet = stmt.executeQuery();
		while(resultSet.next()){
			int reportID = resultSet.getInt("reportID");
			String topic = resultSet.getString("reportTopic");
			String description = resultSet.getString("reportDescription");
			String userNickname = resultSet.getString("userNickname");
			System.out.println(reportID);
			int serviceID = resultSet.getInt("serviceID");
			
			Report report = new Report(reportID, topic, description, serviceID, userNickname);
			allReports.add(report);
			
		}
		resultSet.close();
		stmt.close();
		return allReports;
	}

}
