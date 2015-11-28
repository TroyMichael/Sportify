package at.fhv.itb13.sportify.shared.communication.dtos;

/**
 * Created by Caroline on 28.11.2015.
 */
public class TournamentInvResponseMessageDTOImpl extends  DTOImpl implements  TournamentInvResponseMessageDTO {

  private TournamentDTO _tournamentDTO;

    private Boolean _accepted;

    public TournamentInvResponseMessageDTOImpl(){}

    public TournamentInvResponseMessageDTOImpl(TournamentDTO tournamentDTO, Boolean accepted){
        _tournamentDTO = tournamentDTO;
        _accepted = accepted;
    }



    @Override
    public TournamentDTO getTournamentDTO() {
        return _tournamentDTO;
    }

    @Override
    public void setTournamentDTO(TournamentDTO tournamentDTO) {
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
