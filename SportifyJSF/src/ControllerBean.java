//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import at.fhv.itb13.sportify.shared.communication.dtos.MatchDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.MatchDTOImpl;
import at.fhv.itb13.sportify.shared.communication.dtos.SimpleSportDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TournamentDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.UserDTOImpl;
import at.fhv.itb13.sportify.shared.communication.exceptions.NotAuthorizedException;
import at.fhv.itb13.sportify.shared.communication.remote.ejb.SessionRemote;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(
        name = "controllerbean",
        eager = true
)
@SessionScoped
public class ControllerBean {
    private String _username;
    private String _password;
    private TournamentDTO _currentTournament;
    private ArrayList<TournamentDTO> _tournaments = new ArrayList();
    private HashMap<String, String> _sportidsport = new HashMap();
    private HashMap<TournamentDTO, ArrayList<Match>> _tournamentMatch = new HashMap();
    @EJB
    private SessionRemote _sessionRemote;

    public ControllerBean() {
    }

    public ArrayList<TournamentDTO> getTournaments() {
        if(this._tournaments.size() == 0) {
            this._tournaments.addAll(this._sessionRemote.getTournamentRemote().getAllTournaments());
        }

        return this._tournaments;
    }

    public ArrayList<MatchDTO> getMatches(TournamentDTO tournamentDTO) {
        ArrayList result = new ArrayList();
        if(tournamentDTO != null) {
            result.addAll(tournamentDTO.getMatches());
        }

        return result;
    }

    public String authenticate() {
        UserDTOImpl userDTO = new UserDTOImpl();
        userDTO.setName(this._username);
        userDTO.setPassword(this._password);
        if(this._sessionRemote.login(userDTO)) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("overview.xhtml");
            } catch (IOException var3) {
                var3.printStackTrace();
            }
        }

        return null;
    }

    public void setPassword(String password) {
        this._password = password;
    }

    public void setUsername(String username) {
        this._username = username;
    }

    public String getUsername() {
        return this._username;
    }

    public String getPassword() {
        return this._password;
    }

    public String processWithTournament(TournamentDTO tournamentDTO) {
        try {
            this._currentTournament = tournamentDTO;
            FacesContext.getCurrentInstance().getExternalContext().redirect("tournamentoverview.xhtml");
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        return null;
    }

    public String saveMatches() {
        Iterator var1 = ((ArrayList)this._tournamentMatch.get(this._currentTournament)).iterator();

        while(var1.hasNext()) {
            Match match = (Match)var1.next();
            MatchDTO matchdto = this.convertToDTO(match);
            match.setEditable(false);

            try {
                this._sessionRemote.getMatchRemote().update(matchdto);
            } catch (NotAuthorizedException var5) {
                var5.printStackTrace();
            }
        }

        return "displayAllTournaments.xhtml";
    }

    private Match convertToMatch(MatchDTO matchDTO) {
        Match match = new Match();
        match.setId(matchDTO.getId());
        match.setVersion(matchDTO.getVersion().intValue());
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

    private MatchDTO convertToDTO(Match match) {
        MatchDTOImpl matchDTO = new MatchDTOImpl();
        matchDTO.setId(match.getId());
        matchDTO.setVersion(Integer.valueOf(match.getVersion()));
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
        return this._currentTournament;
    }

    public ArrayList<Match> getMatches() {
        ArrayList matches = new ArrayList();
        if(this._tournamentMatch.containsKey(this._currentTournament)) {
            return (ArrayList)this._tournamentMatch.get(this._currentTournament);
        } else {
            if(this._currentTournament != null) {
                Iterator var2 = this._currentTournament.getMatches().iterator();

                while(var2.hasNext()) {
                    MatchDTO matchDTO = (MatchDTO)var2.next();
                    matches.add(this.convertToMatch(matchDTO));
                }
            }

            this._tournamentMatch.put(this._currentTournament, matches);
            return (ArrayList)this._tournamentMatch.get(this._currentTournament);
        }
    }

    public String getSportByID(String id) {
        if(this._sportidsport.size() == 0) {
            List sports = this._sessionRemote.getSportRemote().getAllSimpleSports();
            Iterator var3 = sports.iterator();

            while(var3.hasNext()) {
                SimpleSportDTO temp = (SimpleSportDTO)var3.next();
                this._sportidsport.put(temp.getId(), temp.getName());
            }
        }

        return this._sportidsport.containsKey(id)?(String)this._sportidsport.get(id):"";
    }

    public String editAction(Match match) {
        match.setEditable(true);
        return null;
    }
}
