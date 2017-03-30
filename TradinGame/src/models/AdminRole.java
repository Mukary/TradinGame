package models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by trade on 24/03/17.
 */
public class AdminRole extends UserRole{

    public void banUser(User user){
    	user.setIsBanned(true);
    	return;
    }

    public void unBanUser(User user){
    	user.setIsBanned(false);
    	return;
    }

    public int createGame(Game game){
        return 1;
    }

    public int editGame(Game game){
        return 1;
    }

    public int deleteGame(Game game){
        return 1;
    }

    public int createServiceType(ServiceType serviceType){
        return 1;
    }

    public int editServiceType(ServiceType serviceType){
        return 1;
    }

    public int deleteServiceType(ServiceType serviceType){
        return 1;
    }

    public int registerUser(User user){
        return 1;
    }

    public int resetUser(User user){
        return 1;
    }

    public int deleteReport(Report report){
        return 1;
    }

   /* public int deleteRequest(Request request){
        return 1;
   }
   *
   * TODO : rajouter le modele Request*/


}
