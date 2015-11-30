package at.fhv.itb13.sportify.shared.communication.dtos;

/**
 * Created by Caroline on 28.11.2015.
 */
public class TournamentInvitationMessageDTOImpl extends DTOImpl implements TournamentInvitationMessageDTO {

    private SimpleTournamentDTO _tournamentDTO;
    private String _sender;

    public TournamentInvitationMessageDTOImpl(){}

    public TournamentInvitationMessageDTOImpl(SimpleTournamentDTO tournamentDTO, String sender){
        _tournamentDTO = tournamentDTO;
        _sender = sender;
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
    public String getSender() {
        return _sender;
    }

    @Override
    public void setSender(String name) {
        _sender = name;
    }
}
