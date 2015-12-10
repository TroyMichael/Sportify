package at.fhv.itb13.sportify.server.communication.remote.ejb;

import at.fhv.itb13.sportify.server.application.controller.MatchController;
import at.fhv.itb13.sportify.shared.communication.dtos.MatchDTO;
import at.fhv.itb13.sportify.shared.communication.enums.RightName;
import at.fhv.itb13.sportify.shared.communication.exceptions.NotAuthorizedException;
import at.fhv.itb13.sportify.shared.communication.remote.ejb.MatchRemote;
import at.fhv.itb13.sportify.shared.communication.remote.ejb.SessionRemote;

import javax.ejb.Stateful;

@Stateful
public class MatchBean implements MatchRemote {

    private SessionRemote _session;
    private MatchController _matchController;

    public MatchBean() {
        _matchController = new MatchController();
    }

    public void setSession(SessionRemote session) {
        _session = session;
    }

    @Override
    public void create(MatchDTO matchDto) throws NotAuthorizedException {
        _session.authorize(RightName.TOURNAMENT_MODIFY);
        _matchController.create(matchDto);
    }

    @Override
    public void update(MatchDTO matchDTO) throws NotAuthorizedException {
        _session.authorize(RightName.TOURNAMENT_MODIFY);
        _matchController.updateMatch(matchDTO);
    }
}
