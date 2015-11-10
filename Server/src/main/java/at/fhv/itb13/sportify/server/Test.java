package at.fhv.itb13.sportify.server;

import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.database.DBFacadeImpl;
import at.fhv.itb13.sportify.server.model.Person;
import at.fhv.itb13.sportify.server.model.Roster;
import org.hibernate.HibernateException;

public class Test {

    public static void main(String[] args) {

        // Person 1464a0fd-23f8-49f1-999d-f806ed0b84ac
        // erstellen - funktioniert!

        // Team 51edb89d-3750-4793-a9f5-60e728948f61
        // erstellen - funktioniert!
        // setDepartment - funktioniert!
        // setPersons - funktioniert!
        // setRosters - fail!

        // Department 500c9b78-5d8f-4fa8-968f-c98d4cc7f2c8
        // erstellen - funktioniert!
        // setSports - funktioniert!

        // Roster 97b5833a-e57f-44a5-99a5-2057631d0354 - fertig!
        // erstellen - funktioniert!
        // setTeam - funktioniert!
        // setPersons - funktioniert!

        // Sport 0c70872f-12c4-4e6a-b389-9e8dbd76f3c8
        // erstellen - funktioniert!
        // setDepartment - funktioniert!

        DBFacade facade = new DBFacadeImpl();
        try {
            facade.beginTransaction();
            //Department d = facade.get(Department.class, "500c9b78-5d8f-4fa8-968f-c98d4cc7f2c8");
            Person p = facade.get(Person.class, "1464a0fd-23f8-49f1-999d-f806ed0b84ac");
            Roster r = facade.get(Roster.class, "97b5833a-e57f-44a5-99a5-2057631d0354");
            //Team t = facade.get(Team.class, "51edb89d-3750-4793-a9f5-60e728948f61");
            facade.createOrUpdate(r);
            facade.commitTransaction();
        } catch (HibernateException e) {
            facade.rollbackTransaction();
            e.printStackTrace();
        }
    }
}
