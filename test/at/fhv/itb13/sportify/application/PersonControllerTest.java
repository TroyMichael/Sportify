package at.fhv.itb13.sportify.application;

import at.fhv.itb13.sportify.application.controller.PersonController;

/**
 * Created by mod on 10/27/15.
 * TODO: Change Name to an existing one
 */
public class PersonControllerTest {
    private PersonController _personController;
//
//    /**
//     * Check if an existing person can be found.
//     */
//    @Test
//    public void getExistingPerson(){
//        try{
//            _personController = PersonController.getInstance();
//            Person p = _personController.getPerson("Molitur");
//            assertNotEquals(p,null);
//        }catch(PersonNotFoundException pnfe){
//            pnfe.printStackTrace();
//        }
//    }
//
//    /**
//     * Check if the exeption is thrown if the person does not exist.
//     */
//    public void getNonExistingPerson(){
//        try{
//            _personController = PersonController.getInstance();
//            _personController.getPerson("YouShouldNotExistInDatabase");
//        }catch(PersonNotFoundException pnfe){
//            assertNotEquals(pnfe,null);
//
//        }
//    }
//
//    /**
//     * Change Value of existing Person
//     */
//    public void changePersonData(){
//        String newLname = "Crabman";
//        try{
//            _personController = PersonController.getInstance();
//            Person p = _personController.getPerson("Molitur");
//            Person editedPerson = p;
//            editedPerson.setLName(newLname);
//            _personController.saveOrupdate(editedPerson);
//            assertEquals(_personController.getPerson("Molitur").getLName(),newLname);
//
//
//        }catch(PersonNotFoundException pnfe){
//            assertNotEquals(pnfe,null);
//
//        }
//    }

}
