package at.fhv.itb13.sportify.presentation.controller;

import at.fhv.itb13.sportify.communication.dtos.PersonDTOImpl;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Created by Michael on 26.10.2015.
 * <p>
 * Controls the view NewMemberForm. Checks if all required text fields contain values when trying to add a new member
 * and then creates a DTO.
 */

public class NewMemberFormController {

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
    private void SaveNewMember() {

        if (validateInput()) {
<<<<<<< HEAD:Client/src/main/java/at/fhv/itb13/sportify/presentation/Controller/NewMemberFormController.java
            PersonDTOImpl newMember = new PersonDTOImpl();
            newMember.setFName(_fNameTextField.getText());
            newMember.setLName(_lNameTextField.getText());
            newMember.setStreet(_streetTextField.getText());
            newMember.setHouseNumber(_streetNoTextField.getText());
            newMember.setPostalCode(_postalCodeTextField.getText());
            newMember.setCity(_cityTextField.getText());
            newMember.setEmail(_eMailTextField.getText());
            newMember.setBirthdate(_birthdayTextField.getText());

            //PersonController.getInstance().create(newMember);
=======
            PersonDTOImpl newMember = new PersonDTOImpl(
                    _fNameTextField.getText(),
                    _lNameTextField.getText(),
                    _streetTextField.getText(),
                    _streetNoTextField.getText(),
                    _postalCodeTextField.getText(),
                    _cityTextField.getText(),
                    _eMailTextField.getText(),
                    _birthdayTextField.getText()
            );
            PersonController.getInstance().create(newMember);
>>>>>>> 7b88525b61a55e06035b5e6b65cd60ffa15862d0:src/main/java/at/fhv/itb13/sportify/presentation/Controller/NewMemberFormController.java
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

    @FXML
    private void CancelNewMember() {
        System.out.println("Cancelled");
    }
}