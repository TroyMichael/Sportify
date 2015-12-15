import at.fhv.itb13.sportify.shared.communication.dtos.*;
import at.fhv.itb13.sportify.shared.communication.exceptions.NotAuthorizedException;
import at.fhv.itb13.sportify.shared.communication.remote.ejb.MatchRemote;
import at.fhv.itb13.sportify.shared.communication.remote.ejb.SessionRemote;
import at.fhv.itb13.sportify.shared.communication.remote.ejb.TournamentRemote;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mod on 12/9/15.
 *
 */
@ManagedBean(name = "controllerbean", eager = true)
@SessionScoped
public class ControllerBean {

    private String _username;
    private String _password;
    private TournamentDTO _currentTournament;
    private ArrayList<TournamentDTO> _tournaments = new ArrayList<>();

    private HashMap<TournamentDTO, ArrayList<Match>> _tournamentMatch = new HashMap<>();

    @EJB
    private SessionRemote _sessionRemote;


    public ArrayList<TournamentDTO> getTournaments(){
        if(_tournaments.size() == 0){
            _tournaments.addAll(_sessionRemote.getTournamentRemote().getAllTournaments());
        }
        return _tournaments;
    }

    public ArrayList<MatchDTO> getMatches(TournamentDTO tournamentDTO){
        ArrayList<MatchDTO> result = new ArrayList<>();
        if(tournamentDTO != null){
            result.addAll(tournamentDTO.getMatches());
        }
        return result;
    }

    public String authenticate(){
        UserDTO userDTO = new UserDTOImpl();
        userDTO.setName(_username);
        userDTO.setPassword(_password);
        if(_sessionRemote.login(userDTO)) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("overview.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void setPassword(String password){
        _password = password;
    }
    public void setUsername(String username){
        _username = username;
    }

    public String getUsername(){
        return _username;
    }

    public String getPassword() {
        return _password;
    }

    public String processWithTournament(TournamentDTO tournamentDTO) {
        //set "canEdit" of all employees to false
        try {
            _currentTournament = tournamentDTO;
            FacesContext.getCurrentInstance().getExternalContext().redirect("tournamentoverview.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    * TODO: edit match false;
    */
    public String saveMatches(){
        for (Match match : _tournamentMatch.get(_currentTournament)){
               MatchDTO matchdto = convertToDTO(match);
                match.setEditable(false);
            try {
                _sessionRemote.getMatchRemote().update(matchdto);
            } catch (NotAuthorizedException e) {
                e.printStackTrace();
            }


        }
        return "displayAllTournaments.xhtml";
    }
    private Match convertToMatch(MatchDTO matchDTO){
        Match match = new Match();
        match.setId(matchDTO.getId());
        match.setVersion(matchDTO.getVersion());
        match.set_duration(matchDTO.getDuration());
        match.set_start(matchDTO.getStart());

        match.set_tournamentId(matchDTO.getTournamentId());
        match.set_matchStatus(matchDTO.getMatchStatus());

        match.set_team1(matchDTO.getTeam1());
        match.set_team2(matchDTO.getTeam2());

        match.setScore1(match.get_team1().getPoints());
        match.setScore2(match.get_team2().getPoints());

        return match;
    }
    private MatchDTO convertToDTO(Match match){
        MatchDTO matchDTO = new MatchDTOImpl();
        matchDTO.setId(match.getId());
        matchDTO.setVersion(match.getVersion());
        matchDTO.setDuration(match.get_duration());
        matchDTO.setStart(match.get_start());
        matchDTO.setTournamentId(match.get_tournamentId());
        matchDTO.setMatchStatus(match.get_matchStatus());
        matchDTO.setTeam1(match.get_team1());
        matchDTO.setTeam2(match.get_team2());
        matchDTO.getTeam1().setPoints(match.getScore1());
        matchDTO.getTeam2().setPoints(match.getScore2());

        return matchDTO;
    }
    public TournamentDTO get_currentTournament() {
        return _currentTournament;
    }

    public ArrayList<Match> getMatches() {
        ArrayList<Match> matches = new ArrayList<>();
        if(_tournamentMatch.containsKey(_currentTournament)){
            return _tournamentMatch.get(_currentTournament);
        } else {
            if(_currentTournament != null) {

                for (MatchDTO matchDTO : _currentTournament.getMatches()) {
                    matches.add(convertToMatch(matchDTO));
                }
            }
            _tournamentMatch.put(_currentTournament,matches);
            return _tournamentMatch.get(_currentTournament);
        }
    }
    public String editAction(Match match) {
        match.setEditable(true);
        return null;
    }
}
