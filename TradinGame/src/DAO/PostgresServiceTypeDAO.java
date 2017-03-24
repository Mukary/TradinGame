package DAO;

import models.ServiceType;
import util.Util;

import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by mukary on 23/03/2017.
 */
public class PostgresServiceTypeDAO extends AbstractDAO<ServiceType>{

    @Override

    public int create(ServiceType obj) throws SQLException {
        int res = 0;
        try{
            PreparedStatement stmt = connect.prepareStatement("INSERT INTO \"ServiceType\"(label, description) VALUES (?, ?)");
            stmt.setString(1, obj.getLabel());
            stmt.setString(2, obj.getDescription());
            res = stmt.executeUpdate();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public int delete(ServiceType obj) throws SQLException {
        int res = 0;
        try{
            PreparedStatement stmt = connect.prepareStatement("DELETE FROM \"ServiceType\" WHERE label = ?");
            stmt.setString(1, obj.getLabel());
            res = stmt.executeUpdate();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public int update(ServiceType obj) throws SQLException {
        int res = 0;
        try{
            PreparedStatement stmt = connect.prepareStatement("UPDATE \"ServiceType\" SET (label = ?, description = ?) WHERE label = ?");
            stmt.setString(1, obj.getLabel());
            stmt.setString(2, obj.getDescription());
            stmt.setString(3, obj.getLabel());
            res = stmt.executeUpdate();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public ServiceType find(int id) throws SQLException {
        PreparedStatement stmt = connect.prepareStatement("SELECT * FROM \"ServiceType\" WHERE id = ?");
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            String label = rs.getString("label");
            String description = rs.getString("description");
            rs.close();
            stmt.close();
            return new ServiceType(label, description);
        }
        else
            return null;
    }

    public ServiceType find(String label) throws SQLException{
        PreparedStatement stmt = connect.prepareStatement("SELECT * FROM \"ServiceType\" WHERE label = ?");
        stmt.setString(1, label);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            label = rs.getString("label");
            String description = rs.getString("description");
            rs.close();
            stmt.close();
            return new ServiceType(label, description);
        }
        else
            return null;
    }

    public ArrayList<ServiceType> getAllByGameType(String gameTypeLabel) throws SQLException{
        PreparedStatement stmt = connect.prepareStatement("SELECT s.label, s.description FROM \"ServiceType\" s JOIN \"Compatibility\" c on c.gameTypeLabel = s.label WHERE  c.gameTypeLabel = ?");
        ArrayList<ServiceType> serviceTypes = new ArrayList<ServiceType>();
        stmt.setString(1, gameTypeLabel);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            String label = rs.getString("label");
            String description = rs.getString("description");
            serviceTypes.add(new ServiceType(label, description));
        }
        return serviceTypes;
    }

    @Override
    public ArrayList<ServiceType> getAll() throws SQLException {
        ArrayList<ServiceType> serviceTypes = new ArrayList<ServiceType>();
        PreparedStatement stmt = connect.prepareStatement("SELECT * FROM \"ServiceType\" ");
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            String label = rs.getString("label");
            String description = rs.getString("description");
            serviceTypes.add(new ServiceType(label, description));
        }
        rs.close();
        stmt.close();
        return serviceTypes;
    }


}