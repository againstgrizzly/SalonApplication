package Model;

import MiscObjects.Queries;

/**
 * Created by Ggastaldo8134 on 3/13/2017.
 */
public class Reset2Model {

    Queries queries = new Queries();
    public boolean isInfoValid(String firstname, String lastname, String dateOfBirth){
        boolean validInput = queries.UsernameDisplayAttempt(firstname, lastname, dateOfBirth);
        return validInput;
    }
}
