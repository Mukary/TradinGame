package DAO;

import models.GameType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Data Access Object managing the GameType table in the dabatase
 * Created by mukary on 23/03/2017.
 */
public class PostgresGameTypeDAO extends AbstractDAO<GameType>{
    
	/**
     * Inserts a game type in the database.
     * @param obj the game type to be inserted
     * @return 1 if the query succeeded, 0 otherwise
     * @throw SQLException if the query fails
     */
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

    /**
     * Deletes a game type in the database.
     * @param obj the game type to be deleted
     * @return 1 if the query succeeded, 0 otherwise
     * @throw SQLException if the query fails
     */
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

    /**
     * Updates a game type in the database.
     * @param obj the game type to be updated
     * @return 1 if the query succeeded, 0 otherwise
     * @throw SQLException if the query fails
     */
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

    /**
     * Finds a game type in the database given its label.
     * @param the game type label to find
     * @return 1 the GameType object if it was found, null otherwise
     * @throw SQLException if the query fails
     */
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

    /**
     * Gets all the game types contained in the database.
     * @return an ArrayList of GameType objects
     * @throw SQLException if the query fails
     */
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
    
    /**
     * Finds all the game types concerned by a service type
     * @param serviceType the concerned service type
     * @return an ArrayList of GameType
     * @throws SQLException if the query fails
     */
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
