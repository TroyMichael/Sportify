package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.client.communication.ServiceLocator;
import at.fhv.itb13.sportify.client.presentation.SportifyGUI;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTOImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Michael on 26.10.2015.
 * <p>
 * Controls the view NewMemberForm. Checks if all required text fields contain values when trying to add a new member
 * and then creates a DTO.
 */

public class SearchMemberFormController {

    @FXML
    private TextField _fNameTextField;

    @FXML
    private TextField _lNameTextField;

    @FXML
    private TextField _streetTextField;

    @FXML
    private TextField _streetNoTextField;

    @FXML
    private TextField _postalCodeTextField;

    @FXML
    private TextField _cityTextField;

    @FXML
    private TextField _eMailTextField;

    @FXML
    private TextField _birthdayTextField;

    @FXML
    private void searchMember() {

        String input = getInput();
        PersonDTO member = new PersonDTOImpl(
                _fNameTextField.getText(),
                _lNameTextField.getText(),
                _streetTextField.getText(),
                _streetNoTextField.getText(),
                _postalCodeTextField.getText(),
                _cityTextField.getText(),
                _eMailTextField.getText(),
                _birthdayTextField.getText()
        );
        List<PersonDTO> results;
        try {

            results = ServiceLocator.getInstance().getPersonRemote().searchPerson(member);
          if(results != null) {
              LoadSearchResultView(results, input);
          }
          else{
              Alert alert = new Alert(Alert.AlertType.WARNING);
              alert.setTitle("No matching member found");
              alert.setHeaderText("Sorry, no matching member could not be found.");
              alert.setContentText("Please make sure you have entered the right data.");
              alert.showAndWait();
          }

             } catch (RemoteException e) {
                 e.printStackTrace();
           }
    }


    private void LoadSearchResultView(List<PersonDTO> results, String searchInput) {
        SportifyGUI.getSharedMainApp().loadSearchResultView(results, searchInput);
    }


    private String getInput() {
        String str = "";


        if(_fNameTextField.getText().length() > 0){
            str = str +  _fNameTextField.getText() + ", " ;
        }
        if(_lNameTextField.getText().length() > 0){
            str = str  + _lNameTextField.getText() + ", ";
        }
        if(_streetTextField.getText().length() > 0){
            str = str  + _streetTextField.getText() + ", ";
        }
        if(_streetNoTextField.getText().length() > 0){
            str = str  + _streetNoTextField.getText() + ", ";
        }
        if(_postalCodeTextField.getText().length() > 0){
            str = str  + _fNameTextField.getText() + ", ";
        }
        if(_cityTextField.getText().length() > 0){
            str = str  + _cityTextField.getText() + ", ";
        }
        if(_eMailTextField.getText().length() > 0){
            str = str  + _eMailTextField.getText() + ", ";
        }
        if(_birthdayTextField.getText().length() > 0){
            str = str  + _birthdayTextField.getText() + ", ";

        }
        return str;
    }
}