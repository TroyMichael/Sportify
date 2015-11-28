package at.fhv.itb13.sportify.shared.communication.dtos;

import java.io.Serializable;

/**
 * Created by Caroline on 28.11.2015.
 */
public interface TournamentInvResponseMessageDTO extends DTO, Serializable{

    TournamentDTO getTournamentDTO();

    void setTournamentDTO(TournamentDTO tournamentDTO);

    Boolean isAccepted();

    void accept(Boolean b);
}
