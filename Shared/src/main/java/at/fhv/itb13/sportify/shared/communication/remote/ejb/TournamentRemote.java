package at.fhv.itb13.sportify.shared.communication.remote.ejb;

import at.fhv.itb13.sportify.shared.communication.dtos.SimpleTournamentDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TournamentDTO;
import at.fhv.itb13.sportify.shared.communication.exceptions.NotAuthorizedException;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface TournamentRemote {
    void createTournament(TournamentDTO tournamentDTO) throws NotAuthorizedException;

    List<TournamentDTO> getAllTournaments();

    List<SimpleTournamentDTO> getAllSimpleTournaments();

    TournamentDTO getByID(String id);

    void updateTournament(TournamentDTO tournamentDTO) throws NotAuthorizedException;
}
