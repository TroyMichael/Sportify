package at.fhv.itb13.sportify.presentation.Controller;

import at.fhv.itb13.sportify.communication.ServiceLocator;
import at.fhv.itb13.sportify.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.communication.dtos.PersonDTOImpl;
import at.fhv.itb13.sportify.presentation.SportifyGUI;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.rmi.RemoteException;
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
    private TextField _telephoneNoTextField;

    @FXML
    private TextField _eMailTextField;

    @FXML
    private TextField _birthdayTextField;

    @FXML
    private void SearchMember() {


            PersonDTOImpl member = new PersonDTOImpl(
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
               results= ServiceLocator.getInstance().getPersonRemote().searchPerson(member);
                LoadSearchResultView(results);


            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }


    private void LoadSearchResultView(List<PersonDTO> results) { SportifyGUI.getSharedMainApp().loadSearchResultView(results); }

    }


