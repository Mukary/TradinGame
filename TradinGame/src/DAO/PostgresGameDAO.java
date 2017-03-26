package DAO;

import models.Game;
import models.ServiceType;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by mukary on 23/03/2017.
 */
public class PostgresGameDAO extends AbstractDAO<Game> {

    @Override

    public int create(Game obj) throws SQLException {
        int res = 0;
        try{
            PreparedStatement stmt = connect.prepareStatement("INSERT INTO \"Game\"(name, editor, \"releaseDate\", \"GameTypeLabel\") VALUES (?, ?, ?, ?)");
            stmt.setString(1, obj.getName());
            stmt.setString(2, obj.getEditor());
            stmt.setDate(3, obj.getReleaseDate());
            stmt.setString(4, obj.getGameTypeLabel());
            res = stmt.executeUpdate();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public int delete(Game obj) throws SQLException {
        int res = 0;
        try{
            PreparedStatement stmt = connect.prepareStatement("DELETE FROM \"Game\" WHERE name = ?");
            stmt.setString(1, obj.getName());
            res = stmt.executeUpdate();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public int update(Game obj) throws SQLException {
        int res = 0;
        try{
            PreparedStatement stmt = connect.prepareStatement("UPDATE \"Game\" SET (name = ?, editor = ?, releaseDate = ?, gameTypeLabel = ?) WHERE name = ?");
            stmt.setString(1, obj.getName());
            stmt.setString(2, obj.getEditor());
            stmt.setDate(3, obj.getReleaseDate());
            stmt.setString(4, obj.getGameTypeLabel());
            stmt.setString(5, obj.getName());
            res = stmt.executeUpdate();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public Game find(int id) throws SQLException {
        PreparedStatement stmt = connect.prepareStatement("SELECT * FROM \"Game\" WHERE id = ?");
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            String name = rs.getString("name");
            String editor = rs.getString("editor");
            Date releaseDate = rs.getDate("releaseDate");
            String gameTypeLabel = rs.getString("GameTypeLabel");
            rs.close();
            stmt.close();
            return new Game(name, editor, releaseDate, gameTypeLabel);
        }
        else
            return null;
    }

    public Game find(String name) throws SQLException{
        PreparedStatement stmt = connect.prepareStatement("SELECT * FROM \"Game\" WHERE name = ?");
        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            name = rs.getString("name");
            String editor = rs.getString("editor");
            Date releaseDate = rs.getDate("releaseDate");
            String gameTypeLabel = rs.getString("GameTypeLabel");
            rs.close();
            stmt.close();
            return new Game(name, editor, releaseDate, gameTypeLabel);
        }
        else
            return null;
    }

    @Override
    public ArrayList<Game> getAll() throws SQLException {
        ArrayList<Game> games = new ArrayList<Game>();
        PreparedStatement stmt = connect.prepareStatement("SELECT * FROM \"Game\" ");
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            String name = rs.getString("name");
            String editor = rs.getString("editor");
            Date releaseDate = rs.getDate("releaseDate");
            String gameTypeLabel = rs.getString("GameTypeLabel");
            games.add(new Game(name, editor, releaseDate, gameTypeLabel));
        }
        rs.close();
        stmt.close();
        return games;
    }
    
    /**
     * Returns all the game associated to a service type
     * @param serviceType The concerned service type
     * @return an ArrayList of Game objects
     * @throws SQLException when the query fails
     */
    public ArrayList<Game> getAllByServiceType(ServiceType serviceType) throws SQLException{
    	ArrayList<Game> games = new ArrayList<Game>();
    	PreparedStatement stmt = connect.prepareStatement("SELECT g.name as name, g.editor as editor, g.\"releaseDate\" as releaseDate, g.\"GameTypeLabel\" as GameTypeLabel FROM \"Game\" g,\"GameType\" gt, \"Compatibilities\" c "
    			+ "WHERE g.\"GameTypeLabel\" = gt.label AND c.\"ServiceTypeLabel\" = ?"
    			+ " AND c.\"GameTypeLabel\" = g.\"GameTypeLabel\";");
    	stmt.setString(1, serviceType.getLabel());
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            String name = rs.getString("name");
            String editor = rs.getString("editor");
            Date releaseDate = rs.getDate("releasedate");
            String gameTypeLabel = rs.getString("gametypelabel");
            games.add(new Game(name, editor, releaseDate, gameTypeLabel));
        }
        rs.close();
        stmt.close();
    	return games;
    }
    
    public boolean isCompatibleWithServiceType(Game game, ServiceType serviceType) throws SQLException{
    	PreparedStatement stmt = connect.prepareStatement("SELECT g.name as name, g.editor as editor, g.\"releaseDate\" as releaseDate, g.\"GameTypeLabel\" as GameTypeLabel FROM \"Game\" g,\"GameType\" gt, \"Compatibilities\" c "
    			+ "WHERE g.\"GameTypeLabel\" = gt.label AND c.\"ServiceTypeLabel\" = ?"
    			+ "AND g.name = ?"
    			+ " AND c.\"GameTypeLabel\" = g.\"GameTypeLabel\";");
    	stmt.setString(1, serviceType.getLabel());
    	stmt.setString(2, game.getName());
    	ResultSet rs = stmt.executeQuery();
    	return rs.next();
    }
}
