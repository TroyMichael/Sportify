package at.fhv.itb13.sportify.application;

import at.fhv.itb13.sportify.application.Exception.PersonNotFoundException;
import org.junit.Test;

/**
 * Created by mod on 10/27/15.
 */
public class PersonControllerTest {
    @Test
    public void getPerson(){
        try{

        }catch(PersonNotFoundException pnfe){
            pnfe.printStackTrace();
        }
    }
}
