package at.fhv.itb13.sportify.shared.communication.dtos;

import java.util.HashSet;

/**
 * Created by Michael on 01.12.2015.
 *
 */
public class ExternalDisplayTeamDTO extends DTOImpl implements DisplayTeamDTO {

    private String _name;
    private SimpleSportDTO _sport;
    private SimpleTournamentDTO _tournament;

    public ExternalDisplayTeamDTO(String name){
        _name = name;
    }

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public void setName(String name) {
        _name = name;
    }

    @Override
    public HashSet<SimplePersonDTO> getMembers() {
        return null;
    }

    @Override
    public void addMember(SimplePersonDTO personDTO) {

    }

    @Override
    public SimplePersonDTO getTrainer() {
        return null;
    }

    @Override
    public void setTrainer(SimplePersonDTO trainer) {

    }

    @Override
    public SimpleSportDTO getSport() {
        return _sport;
    }

    @Override
    public void setSport(SimpleSportDTO sport) {
        _sport = sport;
    }

    @Override
    public HashSet<SimpleTournamentDTO> getTournaments() {
        HashSet<SimpleTournamentDTO> tournament = new HashSet<>();
        tournament.add(_tournament);
        return tournament;
    }

    @Override
    public void addSimpleTournamentDTO(SimpleTournamentDTO simpleTournamentDTO) {
        _tournament = simpleTournamentDTO;
    }
}
