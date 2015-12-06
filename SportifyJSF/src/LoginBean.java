import javax.faces.context.FacesContext;
import java.io.IOException;

/**
 * Created by mod on 12/3/15.
 */
public class LoginBean {
    private String username = "";
    private String password = "";
    public LoginBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void authentification(){
            if(authorize()){
            String uri = "overview.xhtml";
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private boolean authorize() {
        if (username.equals("test")) {
            return true;
        }
        return false;
    }
}
