package at.fhv.itb13.sportify.application.controller;

import at.fhv.itb13.sportify.database.TournamentMother;
import at.fhv.itb13.sportify.server.application.controller.PersonController;
import at.fhv.itb13.sportify.server.application.controller.TeamController;
import at.fhv.itb13.sportify.server.application.controller.TournamentController;
import at.fhv.itb13.sportify.server.communication.datatransfer.mapper.TournamentMapper;
import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.model.Person;
import at.fhv.itb13.sportify.server.model.Tournament;
import at.fhv.itb13.sportify.shared.communication.dtos.TournamentDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TournamentDTOImpl;
import at.fhv.itb13.sportify.shared.util.IdGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.*;

/**
 * Created by mod on 11/19/15.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(TournamentController.class)
public class TournamentControllerTest {
    @Mock
    private DBFacade _facade;
    @Mock
    private TournamentMapper _mapper;

    private TournamentController _tournamentController;

    @Before
    public void setUp(){
        _tournamentController = new TournamentController(_facade,_mapper);
    }

    @Test
    public void createTournament(){
        TournamentMother tournamentMother = new TournamentMother();
        String id = IdGenerator.createId();
        Tournament tournament = tournamentMother.setId(id).instance();
        TournamentDTO dto = new TournamentDTOImpl();
        dto.setId(id);

        when( _mapper.toDomainObject(dto)).thenReturn(tournament);
        _tournamentController.create(dto);
        verify(_facade, times(1)).beginTransaction();
        verify(_facade, times(1)).create(tournament);
        verify(_facade, times(1)).commitTransaction();
    }

    @Test
    public void saveOrUpdate(){
        //arrange
        TournamentMother tournamentMother = new TournamentMother();
        Tournament tournament = tournamentMother.setId(IdGenerator.createId()).instance();
        TournamentDTO dto = new TournamentDTOImpl();
        dto.setId(tournament.getId());

        when(_mapper.toDomainObject(dto)).thenReturn(tournament);

        //act
        _tournamentController.saveOrUpdate(dto);

        //assert
        verify(_facade, times(1)).beginTransaction();
        verify(_facade, times(1)).createOrUpdate(tournament);
        verify(_facade, times(1)).commitTransaction();

    }


}
