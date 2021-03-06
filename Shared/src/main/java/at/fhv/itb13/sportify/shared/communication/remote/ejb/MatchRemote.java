package at.fhv.itb13.sportify.shared.communication.remote.ejb;

import at.fhv.itb13.sportify.shared.communication.dtos.MatchDTO;
import at.fhv.itb13.sportify.shared.communication.exceptions.NotAuthorizedException;

import javax.ejb.Local;
import javax.ejb.Remote;

@Remote
public interface MatchRemote {

    void setSession(SessionRemote session);

    void create(MatchDTO matchDto) throws NotAuthorizedException;

    void update(MatchDTO matchDTO) throws NotAuthorizedException;
}
