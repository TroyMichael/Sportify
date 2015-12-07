import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by mod on 12/6/15.
 */
@ManagedBean(name = "matchbean", eager = true)
@SessionScoped
public class MatchBean implements Serializable {
    private String team1 = "Simons Team";
    private String team2 = "Allstars";
    private int score1 = 0;
    private int score2 = 0;

    private ArrayList<Match> matches = new ArrayList<>(Arrays.asList(
            new Match("Niklas", "Gegner", 100, 0),
            new Match("RND", "RND2", 10, 10)
    ));

    public ArrayList<Match> getMatches() {
        return matches;
    }

    public void setMatches(ArrayList<Match> matches) {
        this.matches = matches;
    }

    public String saveMatch(){
        //set "canEdit" of all employees to false
        for (Match employee : matches){
            employee.setCanEdit(false);
        }
        return null;
    }
    public String editMatch(Match employee){
        employee.setCanEdit(true);
        return null;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public int getScore1() {
        return score1;
    }

    public void setScore1(int score1) {
        this.score1 = score1;
    }

    public int getScore2() {
        return score2;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }
}
