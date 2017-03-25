package DAO;

import models.Service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by mukary on 23/03/2017.
 */
public class PostgresServiceDAO extends AbstractDAO<Service>{

    @Override

    public int create(Service obj) throws SQLException {
        int res = 0;
        try{
            PreparedStatement stmt = connect.prepareStatement("INSERT INTO \"Service\"(description, \"expirationDate\", \"sellerNickname\", \"serviceTypeLabel\", \"gameName\", \"consumerNickname\") VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, obj.getDescription());
            stmt.setDate(2, obj.getExpirationDate());
            stmt.setString(3, obj.getSellerNickname());
            stmt.setString(4, obj.getServiceTypeLabel());
            stmt.setString(5, obj.getGameName());
            stmt.setString(6, obj.getConsumerNickname());
            res = stmt.executeUpdate();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public int delete(Service obj) throws SQLException {
        int res = 0;
        try{
            PreparedStatement stmt = connect.prepareStatement("DELETE FROM \"Service\" WHERE idService = ?");
            stmt.setInt(1, obj.getIdService());
            res = stmt.executeUpdate();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public int update(Service obj) throws SQLException {
        int res = 0;
        try{
            PreparedStatement stmt = connect.prepareStatement("UPDATE \"Service\" SET (idService = ?, description = ?, expirationDate = ?, sellerNickname = ?, serviceTypeLabel = ?, gameName = ?, consumerNickname = ?) WHERE idService = ?");
            stmt.setInt(1, obj.getIdService());
            stmt.setString(2, obj.getDescription());
            stmt.setDate(3, obj.getExpirationDate());
            stmt.setString(4, obj.getSellerNickname());
            stmt.setString(5, obj.getServiceTypeLabel());
            stmt.setString(6, obj.getGameName());
            stmt.setString(7, obj.getConsumerNickname());
            stmt.setInt(8, obj.getIdService());
            res = stmt.executeUpdate();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public Service find(int id) throws SQLException {
        PreparedStatement stmt = connect.prepareStatement("SELECT * FROM \"Service\" WHERE idService = ?");
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            int idService = rs.getInt("idService");
            String description = rs.getString("description");
            Date expirationDate = rs.getDate("expirationDate");
            String sellerNickname = rs.getString("sellerNickName");
            String serviceTypeLabel = rs.getString("serviceTypeLabel");
            String gameName = rs.getString("gameName");
            String consumerNickname = rs.getString("consumerNickname");
            rs.close();
            stmt.close();
            return new Service(idService, description, expirationDate, sellerNickname, serviceTypeLabel, gameName, consumerNickname);
        }
        else
            return null;
    }

    @Override
    public ArrayList<Service> getAll() throws SQLException {
        ArrayList<Service> services = new ArrayList<Service>();
        PreparedStatement stmt = connect.prepareStatement("SELECT * FROM \"Service\" ");
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            int idService = rs.getInt("idService");
            String description = rs.getString("description");
            Date expirationDate = rs.getDate("expirationDate");
            String sellerNickname = rs.getString("sellerNickName");
            String serviceTypeLabel = rs.getString("serviceTypeLabel");
            String gameName = rs.getString("gameName");
            String consumerNickname = rs.getString("consumerNickname");
            services.add(new Service(idService, description, expirationDate, sellerNickname, serviceTypeLabel, gameName, consumerNickname));
        }
        rs.close();
        stmt.close();
        return services;
    }
}
