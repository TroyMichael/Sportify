package at.fhv.itb13.sportify.dataTransfer;

import at.fhv.itb13.sportify.dataTransfer.dataTransferObjects.PersonDTO;
import at.fhv.itb13.sportify.model.entities.Person;
import junit.framework.TestCase;

/**
 * Created by KYUSS on 28.10.2015.
 */
public class DTOAssemblerTest extends TestCase {

    public void testBuildDTO() throws Exception {
        DTOAssembler dtoAssembler = new DTOAssembler();
        Person soap = new Person("Simon", "Hartmann", "Flachsweg", "1", "6835", "Muntlix", "simon1hartmann@gmail.com", "11.12.1991");

        DTOObject dtoObject = dtoAssembler.buildDTO(soap);
        PersonDTO soapDTO = (PersonDTO) dtoObject;

        System.out.println(soapDTO.getFName());
        assertTrue(soapDTO.getBirthdate().equals(soap.getBirthdate()));
        System.out.println(soapDTO.getLName());
        assertTrue(soap.getCity().equals(soapDTO.getCity()));
        System.out.println(soapDTO.getBirthdate());
    }

    public void testUpdateDTOObject() throws Exception {

    }
}