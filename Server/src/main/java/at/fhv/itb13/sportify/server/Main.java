package at.fhv.itb13.sportify.server;

import at.fhv.itb13.sportify.server.application.controller.PersonController;
import at.fhv.itb13.sportify.server.communication.remote.ControllerFactoryImpl;
import at.fhv.itb13.sportify.server.communication.remote.PersonServant;
import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.database.DBFacadeImpl;
import at.fhv.itb13.sportify.server.database.PersonDAO;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTOImpl;
import at.fhv.itb13.sportify.shared.communication.remote.ControllerFactory;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Michael on 27.10.2015.
 */
public class Main {

    public static void main(String[] args) {
       /* try {
            PersonServant personServant = new PersonServant();
            ControllerFactory controllerFactory = ControllerFactoryImpl.getInstance();
            Naming.rebind("rmi://localhost:12345/ControllerFactory", controllerFactory);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }*/
        PersonDTO personDTO = new PersonDTOImpl(
                "Andrea",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                true
        );
        PersonController personController = PersonController.getInstance();
        List<PersonDTO> list = personController.searchPerson(personDTO);
        System.out.println(list.toString());

//        SportifyGUI.run(args);
//        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
//        DBFacade dbFacade = (DBFacade) context.getBean("dbFacade");
//        System.out.println(dbFacade);
    }
}
