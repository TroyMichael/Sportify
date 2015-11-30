package at.fhv.itb13.sportify.shared.communication.dtos;

import javax.jms.Message;
import java.io.Serializable;

/**
 * Created by Caroline on 28.11.2015.
 */
public interface TournamentInvitationMessageDTO extends DTO, Serializable {

    TournamentDTO getTournament();

    void setTournament(TournamentDTO tournament);

    UserDTO getSender();

    void setSender(UserDTO name);
}
