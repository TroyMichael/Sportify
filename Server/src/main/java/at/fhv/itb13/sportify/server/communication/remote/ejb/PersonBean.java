package at.fhv.itb13.sportify.server.communication.remote.ejb;

import at.fhv.itb13.sportify.server.application.controller.PersonController;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.SimplePersonDTO;
import at.fhv.itb13.sportify.shared.communication.enums.RightName;
import at.fhv.itb13.sportify.shared.communication.exceptions.NotAuthorizedException;
import at.fhv.itb13.sportify.shared.communication.remote.ejb.PersonRemote;
import at.fhv.itb13.sportify.shared.communication.remote.ejb.SessionLocal;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.List;

@Stateful
public class PersonBean implements PersonRemote {

    @EJB
    private SessionLocal _sessionBean;

    private PersonController _personController;

    public PersonBean() {
        _personController = new PersonController();
    }

    @Override
    public void create(PersonDTO personDto) throws NotAuthorizedException {
        _sessionBean.authorize(RightName.PERSON_MODIFY);
        _personController.create(personDto);
    }

    @Override
    public List<PersonDTO> searchPerson(PersonDTO personDto) {
        return _personController.searchPerson(personDto);
    }

    @Override
    public void editPerson(PersonDTO personDTO) throws NotAuthorizedException {
        _sessionBean.authorize(RightName.PERSON_MODIFY);
        _personController.saveOrUpdate(personDTO);
    }

    @Override
    public List<PersonDTO> getAllPersons() {
        return _personController.getAllPersons();
    }

    @Override
    public List<SimplePersonDTO> getAllSimplePersons() {
        return _personController.getAllSimplePersons();
    }
}
