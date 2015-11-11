package at.fhv.itb13.sportify.application.controller;

import at.fhv.itb13.sportify.server.application.controller.TeamController;
import at.fhv.itb13.sportify.shared.communication.dtos.TeamDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TeamDTOImpl;
import junit.framework.TestCase;

/**
 * Created by mod on 11/11/15.
 */
public class TeamControllerTest extends TestCase {

    public void createTeam() throws Exception {
        TeamController teamController = new TeamController();
        Exception exi = null;
        try {
            TeamDTO team = new TeamDTOImpl();
            team.setName("SuperSoccar");
            team.setSportId("0c70872f-12c4-4e6a-b389-9e8dbd76f3c8");
            team.addPersonId("0038a817-657b-4812-b807-3b428ab54ae5");
            teamController.create(team);
        }catch (Exception e){
            exi = e;
        }
        assertEquals(exi,null);
    }
}
