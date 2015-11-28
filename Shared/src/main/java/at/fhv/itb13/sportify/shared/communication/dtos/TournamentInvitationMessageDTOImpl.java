package at.fhv.itb13.sportify.shared.communication.dtos;

/**
 * Created by Caroline on 28.11.2015.
 */
public class TournamentInvitationMessageDTOImpl extends DTOImpl implements TournamentInvitationMessageDTO {

    private TournamentDTO _tournamentDTO;
    private String _responseQueueName;

    public TournamentInvitationMessageDTOImpl(){}

    public TournamentInvitationMessageDTOImpl(TournamentDTO tournamentDTO, String responseQueueName){
        _tournamentDTO = tournamentDTO;
        _responseQueueName = responseQueueName;
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
    public String getResponseQueueName() {
        return _responseQueueName;
    }

    @Override
    public void setResponseQueueName(String name) {
        _responseQueueName = name;
    }
}
