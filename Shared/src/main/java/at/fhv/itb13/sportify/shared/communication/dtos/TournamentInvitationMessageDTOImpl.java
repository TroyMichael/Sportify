package at.fhv.itb13.sportify.shared.communication.dtos;

/**
 * Created by Caroline on 28.11.2015.
 */
public class TournamentInvitationMessageDTOImpl extends DTOImpl implements TournamentInvitationMessageDTO {

    private TournamentDTO _tournamentDTO;
    private UserDTO _sender;

    public TournamentInvitationMessageDTOImpl(){}

    public TournamentInvitationMessageDTOImpl(TournamentDTO tournamentDTO, UserDTO responseQueueName){
        _tournamentDTO = tournamentDTO;
        _sender = responseQueueName;
    }



    @Override
    public TournamentDTO getTournament() {
        return _tournamentDTO;
    }

    @Override
    public void setTournament(TournamentDTO tournament) {
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
