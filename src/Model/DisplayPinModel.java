package Model;

import MiscObjects.Queries;

/**
 * Created by Ggastaldo8134 on 3/13/2017.
 */
public class DisplayPinModel {
    Queries queries = new Queries();

    public String resetPin(String pin, String username){
        String returnPin = queries.resetPIN(pin, username);
        return returnPin;
    }
    public boolean isInfoValid(String username, String dateOfBirth){
        boolean validInput = queries.returnPIN(username, dateOfBirth);
        return validInput;
    }
}