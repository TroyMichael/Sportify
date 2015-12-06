import javax.faces.context.FacesContext;
import java.io.IOException;

/**
 * Created by mod on 12/3/15.
 */
public class NavBean {

    public void moveToTournament(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("alltournament.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void moveToOverview(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("overview.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
