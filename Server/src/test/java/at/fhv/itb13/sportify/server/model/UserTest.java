package at.fhv.itb13.sportify.server.model;

import junit.framework.TestCase;

/**
 * Created by KYUSS on 11.11.2015.
 */
public class UserTest extends TestCase {

    public void testLogin() throws Exception {
        User soap = new User("sha9939", "sontheei");
        assertTrue(soap.login());
    }
}