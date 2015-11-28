package at.fhv.itb13.sportify.shared.communication.dtos;

import java.io.Serializable;

/**
 * Created by Caroline on 28.11.2015.
 */
public interface TournamentInvitationMessageDTO extends DTO, Serializable {

    TournamentDTO getTournament();

    void setTournament(TournamentDTO tournament);

    String getResponseQueueName();

    void setResponseQueueName(String name);
}
