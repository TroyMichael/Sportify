package at.fhv.itb13.sportify.server.communication.remote;

import at.fhv.itb13.sportify.server.application.controller.MatchController;
import at.fhv.itb13.sportify.server.model.UserRight;
import at.fhv.itb13.sportify.shared.communication.dtos.MatchDTO;
import at.fhv.itb13.sportify.shared.communication.enums.RightName;
import at.fhv.itb13.sportify.shared.communication.remote.MatchRemote;
import at.fhv.itb13.sportify.shared.communication.exceptions.NotAuthorizedException;
import at.fhv.itb13.sportify.shared.communication.remote.Session;

import java.rmi.RemoteException;

public class MatchServant extends SessionServant implements MatchRemote {

    private MatchController _matchController;

    protected MatchServant(Session session) throws RemoteException {
        super(session);
        _matchController = new MatchController();
    }

    @Override
    public void create(MatchDTO matchDto) throws RemoteException, NotAuthorizedException {
        authorize(RightName.TOURNAMENT_MODIFY);
        _matchController.create(matchDto);
    }

    @Override
    public void update(MatchDTO matchDTO) throws RemoteException, NotAuthorizedException {
        authorize(RightName.TOURNAMENT_MODIFY);
        _matchController.updateMatch(matchDTO);
    }
}
