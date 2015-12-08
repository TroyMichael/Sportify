package at.fhv.itb13.sportify.server.communication.remote.ejb;

import at.fhv.itb13.sportify.server.application.controller.MatchController;
import at.fhv.itb13.sportify.shared.communication.dtos.MatchDTO;
import at.fhv.itb13.sportify.shared.communication.enums.RightName;
import at.fhv.itb13.sportify.shared.communication.exceptions.NotAuthorizedException;
import at.fhv.itb13.sportify.shared.communication.remote.ejb.MatchRemote;
import at.fhv.itb13.sportify.shared.communication.remote.ejb.SessionLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class MatchBean implements MatchRemote {

    @EJB
    private SessionLocal _sessionBean;

    private MatchController _matchController;

    public MatchBean() {
        _matchController = new MatchController();
    }

    @Override
    public void create(MatchDTO matchDto) throws NotAuthorizedException {
        _sessionBean.authorize(RightName.TOURNAMENT_MODIFY);
        _matchController.create(matchDto);
    }

    @Override
    public void update(MatchDTO matchDTO) throws NotAuthorizedException {
        _sessionBean.authorize(RightName.TOURNAMENT_MODIFY);
        _matchController.updateMatch(matchDTO);
    }
}
