package at.fhv.itb13.sportify.shared.communication.remote.ejb;

import at.fhv.itb13.sportify.shared.communication.dtos.SimpleSportDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.SportDTO;

import javax.ejb.Local;
import java.util.List;

@Local
public interface SportRemote {
    List<SportDTO> getSports();

    List<SimpleSportDTO> getAllSimpleSports();
}
