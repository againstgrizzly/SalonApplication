package Model;

import MiscObjects.Queries;

/**
 * Created by Gio on 4/6/2017.
 */
public class DisplayUsernameModel {
    Queries queries = new Queries();

    public Boolean isInfoValid(String firstname, String lastname, String dateOfBirth){
        Boolean returnUsername = queries.UsernameDisplayAttempt(firstname, lastname, dateOfBirth);
        return returnUsername;
    }
}
