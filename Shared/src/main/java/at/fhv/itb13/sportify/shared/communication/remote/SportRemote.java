package at.fhv.itb13.sportify.shared.communication.remote;

import at.fhv.itb13.sportify.shared.communication.dtos.SportDTO;

import java.util.List;

/**
 * Created by mod on 11/10/15.
 */
public interface SportRemote {
    List<SportDTO> getName();
}
