package at.fhv.itb13.sportify.server.application.exception;

/**
 * Created by mod on 10/27/15.
 */
public class PersonNotFoundException extends Throwable {
    public PersonNotFoundException() {
        super("Person can't be found!");
    }
}