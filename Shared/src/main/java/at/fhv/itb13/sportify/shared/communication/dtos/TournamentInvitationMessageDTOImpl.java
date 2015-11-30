package at.fhv.itb13.sportify.shared.communication.dtos;

/**
 * Created by Caroline on 28.11.2015.
 */
public class TournamentInvitationMessageDTOImpl extends DTOImpl implements TournamentInvitationMessageDTO {

    private SimpleTournamentDTO _tournamentDTO;
    private UserDTO _sender;

    public TournamentInvitationMessageDTOImpl(){}

    public TournamentInvitationMessageDTOImpl(SimpleTournamentDTO tournamentDTO, UserDTO responseQueueName){
        _tournamentDTO = tournamentDTO;
        _sender = responseQueueName;
    }



    @Override
    public SimpleTournamentDTO getSimpleTournament() {
        return _tournamentDTO;
    }

    @Override
    public void setSimpleTournament(SimpleTournamentDTO tournament) {
        _tournamentDTO = tournament;
    }

    @Override
    public UserDTO getSender() {
        return _sender;
    }

    @Override
    public void setSender(UserDTO name) {
        _sender = name;
    }
}
