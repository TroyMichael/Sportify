import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.util.*;

/**
 * Created by mod on 12/3/15.
 */
@ManagedBean(name = "tournamentbean", eager = true)
@SessionScoped
public class TournamentBean implements Serializable {
    private Tournament current = null;
    private ArrayList<Tournament> tournaments = new ArrayList<>(Arrays.asList(
            new Tournament("Ultra Brutal Street Fight","Hard","Streetfight",new java.util.Date()),
            new Tournament("Ultra Brutal Street Fight2","Hard","Streetfight",new java.util.Date()),
            new Tournament("Ultra Brutal Street Fight3","Hard","Streetfight",new java.util.Date())

            ));

    public ArrayList<Tournament> getTournaments() {
        return tournaments;
    }

    public void setTournaments(ArrayList<Tournament> matches) {
        this.tournaments = tournaments;
    }

    public String processWithTournament(Tournament tournament) {
        //set "canEdit" of all employees to false
        try {
            current = tournament;
            FacesContext.getCurrentInstance().getExternalContext().redirect("tournamentoverview.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Tournament getCurrent() {
        return current;
    }

    public void setCurrent(Tournament current) {
        this.current = current;
    }
}
