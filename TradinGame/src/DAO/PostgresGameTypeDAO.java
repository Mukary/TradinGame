package DAO;

import models.GameType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by mukary on 23/03/2017.
 */
public class PostgresGameTypeDAO extends AbstractDAO<GameType>{
    @Override

    public int create(GameType obj) throws SQLException {
        int res = 0;
        try{
            PreparedStatement stmt = connect.prepareStatement("INSERT INTO \"GameType\"(label) VALUES (?)");
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
    public int delete(GameType obj) throws SQLException {
        int res = 0;
        try{
            PreparedStatement stmt = connect.prepareStatement("DELETE FROM \"GameType\" WHERE label = ?");
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
    public int update(GameType obj) throws SQLException {
        int res = 0;
        try{
            PreparedStatement stmt = connect.prepareStatement("UPDATE \"GameType\" SET (label = ?) WHERE label = ?");
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
    public GameType find(int id) throws SQLException {
        PreparedStatement stmt = connect.prepareStatement("SELECT * FROM \"GameType\" WHERE id = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            String label = rs.getString("label");
            rs.close();
            stmt.close();
            return new GameType(label);
        }
        else
            return null;
    }

    public GameType find(String label) throws SQLException{
        PreparedStatement stmt = connect.prepareStatement("SELECT * FROM \"GameType\" WHERE label = ?");
        stmt.setString(1, label);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            label = rs.getString("label");
            rs.close();
            stmt.close();
            return new GameType(label);
        }
        else
            return null;
    }

    @Override
    public ArrayList<GameType> getAll() throws SQLException {
        ArrayList<GameType> gameTypes = new ArrayList<GameType>();
        PreparedStatement stmt = connect.prepareStatement("SELECT * FROM \"GameType\" ");
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            String label = rs.getString("label");
            gameTypes.add(new GameType(label));
        }
        rs.close();
        stmt.close();
        return gameTypes;
    }

    public ArrayList<GameType> getAllByServiceType(String serviceType) throws SQLException {
        ArrayList<GameType> gameTypes = new ArrayList<GameType>();
        PreparedStatement stmt = connect.prepareStatement("SELECT g.label FROM \"GameType\" g JOIN \"Compatibility\" c on c.gameTypeLabel = g.label WHERE  c.ServiceTypeLabel = ?");
        stmt.setString(1, serviceType);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            String label = rs.getString("label");
            gameTypes.add(new GameType(label));
        }
        rs.close();
        stmt.close();
        return gameTypes;
    }
}
