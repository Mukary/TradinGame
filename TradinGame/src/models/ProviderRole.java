package models;

/**
 * Created by trade on 24/03/17.
 */
public class ProviderRole extends UserRole {

    public int postService(Service service){
        return 1;
    }

    public int editService(Service service){
        return 1;
    }

    public int deleteService(Service service){
        return 1;
    }

}
