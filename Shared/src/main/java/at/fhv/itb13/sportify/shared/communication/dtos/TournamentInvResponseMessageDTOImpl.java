package at.fhv.itb13.sportify.shared.communication.dtos;

/**
 * Created by Caroline on 28.11.2015.
 */
public class TournamentInvResponseMessageDTOImpl extends  DTOImpl implements  TournamentInvResponseMessageDTO {

  private SimpleTournamentDTO _tournamentDTO;

    private Boolean _accepted;

    public TournamentInvResponseMessageDTOImpl(){}

    public TournamentInvResponseMessageDTOImpl(SimpleTournamentDTO tournamentDTO, Boolean accepted){
        _tournamentDTO = tournamentDTO;
        _accepted = accepted;
    }



    @Override
    public SimpleTournamentDTO getSimpleTournamentDTO() {
        return _tournamentDTO;
    }

    @Override
    public void setSimpleTournamentDTO(SimpleTournamentDTO tournamentDTO) {
        _tournamentDTO = tournamentDTO;
    }

    @Override
    public Boolean isAccepted() {
        return _accepted;
    }

    @Override
    public void accept(Boolean b) {
        _accepted = b;
    }
}
