import java.util.Date;

/**
 * Created by mod on 12/6/15.
 */
public class Tournament {
    String name;
    String location;
    String sport;
    Date start;

    public Tournament(String name, String location, String sport, Date start) {
        this.name = name;
        this.location = location;
        this.sport = sport;
        this.start = start;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }
    public String process(){
        System.out.println("DOIT");
        return null;
    }
}
