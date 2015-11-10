package at.fhv.itb13.sportify.server.communication.remote;

import at.fhv.itb13.sportify.server.application.controller.TeamController;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TeamDTO;
import at.fhv.itb13.sportify.shared.communication.remote.TeamRemote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by mod on 11/10/15.
 */
public class TeamServant extends UnicastRemoteObject implements TeamRemote {
    /**
     * TODO: new TEAMCONTROLLER anpassen
     */
    private TeamController _teamController;
    public TeamServant() throws RemoteException {
        super();
        _teamController = new TeamController();
    }

    /**
     * TODO: teamcontroller.create(); teamcontroller.update(); teamcontroller.addPerson();
     * @param teamDTO
     */
    @Override
    public void createTeam(TeamDTO teamDTO) {

    }

    @Override
    public void editTeam(TeamDTO TeamDTO) {

    }

    @Override
    public void addPersonToTeam(PersonDTO personDTO) {

    }
}
