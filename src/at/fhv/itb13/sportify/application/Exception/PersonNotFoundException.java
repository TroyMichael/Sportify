package at.fhv.itb13.sportify.application.Exception;

/**
 * Created by mod on 10/27/15.
 */
public class PersonNotFoundException extends Throwable {
    public PersonNotFoundException() {
        super("Person cant be found!");
    }
}
