import at.fhv.itb13.sportify.shared.communication.dtos.TournamentDTO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;

/**
 * Created by mod on 12/9/15.
 */
@ManagedBean
@SessionScoped
public class ControllerBean {
    private ArrayList<TournamentDTO> tournaments = new ArrayList<>();


    public ArrayList<TournamentDTO> getTournaments(){
        if(tournaments.size() == 0){

        }
        return tournaments;
    }
}
