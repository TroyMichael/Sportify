package at.fhv.itb13.sportify.shared.communication.dtos;

import javax.jms.Message;
import java.io.Serializable;

/**
 * Created by Caroline on 28.11.2015.
 */
public interface TournamentInvitationMessageDTO extends DTO, Serializable {

    SimpleTournamentDTO getSimpleTournament();

    void setSimpleTournament(SimpleTournamentDTO tournament);

    UserDTO getSender();

    void setSender(UserDTO name);
}
