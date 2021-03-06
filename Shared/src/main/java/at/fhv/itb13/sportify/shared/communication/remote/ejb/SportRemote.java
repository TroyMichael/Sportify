package at.fhv.itb13.sportify.shared.communication.remote.ejb;

import at.fhv.itb13.sportify.shared.communication.dtos.SimpleSportDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.SportDTO;

import javax.ejb.Local;
import javax.ejb.Remote;
import java.util.List;
@Remote
public interface SportRemote {

    void setSession(SessionRemote session);

    List<SportDTO> getSports();

    List<SimpleSportDTO> getAllSimpleSports();
}
