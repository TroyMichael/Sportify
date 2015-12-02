package at.fhv.itb13.sportify.shared.communication.dtos;

import java.io.Serializable;

/**
 * Created by Caroline on 28.11.2015.
 */
public interface TournamentInvitationMessageDTO extends DTO, Serializable {

    SimpleTournamentDTO getSimpleTournament();

    void setSimpleTournament(SimpleTournamentDTO tournament);

    String getSender();

    void setSender(String name);
}
