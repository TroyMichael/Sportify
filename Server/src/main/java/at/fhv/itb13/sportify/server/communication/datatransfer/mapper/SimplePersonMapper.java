package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.model.Person;
import at.fhv.itb13.sportify.shared.communication.dtos.SimplePersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.SimplePersonDTOImpl;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Michael on 17.11.2015.
 *
 */
public class SimplePersonMapper extends Mapper<SimplePersonDTO, Person> {


    @Override
    public Person toDomainObject(SimplePersonDTO simplePersonDTO) {
        return null;
    }

    @Override
    public SimplePersonDTO toDTOObject(Person domainObject) {

        if (domainObject != null) {
            SimplePersonDTO newDTO = new SimplePersonDTOImpl();
            newDTO.setFName(domainObject.getFName());
            newDTO.setLName(domainObject.getLName());
            newDTO.setId(domainObject.getId());
            return newDTO;
        }
        return null;
    }

    public List<SimplePersonDTO> toDTOList(List<Person> personList) {
        if (personList != null) {
            List<SimplePersonDTO> simplePersonList = new LinkedList<>();
            personList.forEach(p -> simplePersonList.add(toDTOObject(p)));
            return simplePersonList;
        }
        return null;
    }
}
