package at.fhv.itb13.sportify.server.communication.remote.ejb;

import at.fhv.itb13.sportify.server.application.controller.SportController;
import at.fhv.itb13.sportify.shared.communication.dtos.SimpleSportDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.SportDTO;
import at.fhv.itb13.sportify.shared.communication.remote.ejb.SportRemote;

import javax.ejb.Stateful;
import java.util.List;

@Stateful
public class SportBean implements SportRemote {

    private SportController _sportController;

    public SportBean() {
        _sportController = new SportController();
    }

    @Override
    public List<SportDTO> getSports() {
        return _sportController.getSports();
    }

    @Override
    public List<SimpleSportDTO> getAllSimpleSports() {
        return _sportController.getAllSimpleSports();
    }
}
