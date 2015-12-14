import at.fhv.itb13.sportify.shared.communication.dtos.MatchDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TournamentDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.UserDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.UserDTOImpl;
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

    @EJB
    private TournamentRemote _tournamentRemote;
    @EJB
    private SessionRemote _sessionRemote;
    @EJB
    private MatchRemote _matchRemote;

    public ArrayList<TournamentDTO> getTournaments(){
        if(_tournaments.size() == 0){
            _tournaments.addAll(_tournamentRemote.getAllTournaments());
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

    public boolean authenticate(){
        UserDTO userDTO = new UserDTOImpl();
        userDTO.setName(_username);
        userDTO.setPassword(_password);
        return _sessionRemote.login(userDTO);
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
        for (MatchDTO match : _currentTournament.getMatches()){
            try {
                _matchRemote.update(match);
            } catch (NotAuthorizedException e) {
                FacesMessage facesMessage = new FacesMessage();
                facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
                facesMessage.setDetail("Not Authorized");
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            }
        }
        return null;
    }

    public TournamentDTO get_currentTournament() {
        return _currentTournament;
    }

    public ArrayList<MatchDTO> getMatches(){
        ArrayList result = new ArrayList();
        result.addAll(_currentTournament.getMatches());
        return result;
    }
}