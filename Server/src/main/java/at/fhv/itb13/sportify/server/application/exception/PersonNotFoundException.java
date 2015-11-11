package at.fhv.itb13.sportify.server.application.exception;

public class PersonNotFoundException extends Throwable {
    public PersonNotFoundException() {
        super("Person can't be found!");
    }
}
