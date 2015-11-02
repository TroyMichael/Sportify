package at.fhv.itb13.sportify.client.presentation.controller;


import at.fhv.itb13.sportify.client.communication.ServiceLocator;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTOImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.rmi.RemoteException;

/**
 * Created by Michael on 26.10.2015.
 * <p>
 * Controls the view NewMemberForm. Checks if all required text fields contain values when trying to add a new member
 * and then creates a DTO.
 */

public class MemberDataController {

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
    private Button _saveButton;

    @FXML
    private Button _editButton;

    private PersonDTO _person;


    public void setPerson(PersonDTO person){
        _person = person;

        if(_person != null) {
            if (_person.getFName() != null) {
                _fNameTextField.setText(_person.getFName());
            }
            if (_person.getLName() != null) {
                _lNameTextField.setText(_person.getLName());
            }
            if(_person.getBirthdate() != null){
                _birthdayTextField.setText(_person.getBirthdate());
            }
            if(_person.getCity() != null){
                _cityTextField.setText(_person.getCity());
            }
            if(_person.getEmail() != null) {
                 _eMailTextField.setText(_person.getEmail());
            }
            if(_person.getPostalCode() != null) {
                 _postalCodeTextField.setText(_person.getPostalCode());
            }
            if(_person.getHouseNumber() != null) {
                 _streetNoTextField.setText(_person.getHouseNumber());
            }
            if(_person.getStreet() != null) {
                 _streetTextField.setText(_person.getStreet());
            }

        }
     }

    @FXML
    private void initialize() {

    }

    @FXML
    private void clickEditButton(){
        _editButton.setVisible(false);
        _saveButton.setVisible(true);

        _fNameTextField.setEditable(true);
        _lNameTextField.setEditable(true);
        _cityTextField.setEditable(true);
        _birthdayTextField.setEditable(true);
        _eMailTextField.setEditable(true);
        _postalCodeTextField.setEditable(true);
        _streetNoTextField.setEditable(true);
        _streetTextField.setEditable(true);

    }

    @FXML
    private void clickSaveButton(){
        if (validateInput()) {
            _person.setFName(_fNameTextField.getText());
            _person.setLName(_lNameTextField.getText());
            _person.setStreet(_streetTextField.getText());
            _person.setHouseNumber(_streetNoTextField.getText());
            _person.setPostalCode(_postalCodeTextField.getText());
            _person.setCity(_cityTextField.getText());
            _person.setEmail(_eMailTextField.getText());
            _person.setBirthdate(_birthdayTextField.getText());

            try {
                ServiceLocator.getInstance().getPersonRemote().editPerson(_person);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }

    private Boolean validateInput() {
        Boolean validation = true;

        if (_fNameTextField.getText().length() == 0) {
            _fNameTextField.setStyle("-fx-text-box-border: red;");
            validation = false;
        }

        if (_lNameTextField.getText().length() == 0) {
            _lNameTextField.setStyle("-fx-text-box-border: red;");
            validation = false;
        }

        if (_birthdayTextField.getText().length() == 0) {
            _birthdayTextField.setStyle("-fx-text-box-border: red;");
            validation = false;
        }

        return validation;
    }
}