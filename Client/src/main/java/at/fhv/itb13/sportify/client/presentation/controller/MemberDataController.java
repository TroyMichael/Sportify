package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.client.application.SessionController;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

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
    private TextField _eMailTextField;

    @FXML
    private TextField _birthdayTextField;

    @FXML
    private HBox _editButtonsHBox;

    @FXML
    private Button _editButton;

    @FXML
    private Label _memberViewTitle;

    private PersonDTO _person;

    public void setPerson(PersonDTO person) {
        _person = person;

        if (_person != null) {
            if (_person.getFName() != null) {
                _fNameTextField.setText(_person.getFName());
            }
            if (_person.getLName() != null) {
                _lNameTextField.setText(_person.getLName());
            }
            if (_person.getBirthdate() != null) {
                _birthdayTextField.setText(_person.getBirthdate());
            }
            if (_person.getCity() != null) {
                _cityTextField.setText(_person.getCity());
            }
            if (_person.getEmail() != null) {
                _eMailTextField.setText(_person.getEmail());
            }
            if (_person.getPostalCode() != null) {
                _postalCodeTextField.setText(_person.getPostalCode());
            }
            if (_person.getHouseNumber() != null) {
                _streetNoTextField.setText(_person.getHouseNumber());
            }
            if (_person.getStreet() != null) {
                _streetTextField.setText(_person.getStreet());
            }
        }
    }

    @FXML
    private void initialize() {
    }

    @FXML
    private void clickEditButton() {
        _editButton.setVisible(false);
        _editButtonsHBox.setVisible(true);

        _fNameTextField.setEditable(true);
        _lNameTextField.setEditable(true);
        _cityTextField.setEditable(true);
        _birthdayTextField.setEditable(true);
        _eMailTextField.setEditable(true);
        _postalCodeTextField.setEditable(true);
        _streetNoTextField.setEditable(true);
        _streetTextField.setEditable(true);

        _memberViewTitle.setText("Edit Member Details");
    }

    @FXML
    private void clickSaveButton() throws RemoteException {
        if (validateInput()) {
            _person.setFName(_fNameTextField.getText());
            _person.setLName(_lNameTextField.getText());
            _person.setStreet(_streetTextField.getText());
            _person.setHouseNumber(_streetNoTextField.getText());
            _person.setPostalCode(_postalCodeTextField.getText());
            _person.setCity(_cityTextField.getText());
            _person.setEmail(_eMailTextField.getText());
            _person.setBirthdate(_birthdayTextField.getText());

            SessionController.getInstance().getSession().getPersonRemote().editPerson(_person);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Saving successful!");
            alert.setTitle("Saving successful");
            alert.setContentText("The Member '" + _fNameTextField.getText() + " " + _lNameTextField.getText() + "' was successfuly edited!");
            alert.showAndWait();
            cancelEdit();
        }
    }

    @FXML
    private void cancelEdit(){
        _editButton.setVisible(true);
        _editButtonsHBox.setVisible(false);

        _fNameTextField.setEditable(false);
        _lNameTextField.setEditable(false);
        _cityTextField.setEditable(false);
        _birthdayTextField.setEditable(false);
        _eMailTextField.setEditable(false);
        _postalCodeTextField.setEditable(false);
        _streetNoTextField.setEditable(false);
        _streetTextField.setEditable(false);

        _memberViewTitle.setText("Member Details");
    }

    private Boolean validateInput() {
        Boolean validation = true;

        if (_fNameTextField.getText().length() == 0) {
            _fNameTextField.setStyle("-fx-text-box-border: red;");
            validation = false;
        } else {
            _fNameTextField.setStyle("-fx-text-box-border: black;");
        }

        if (_lNameTextField.getText().length() == 0) {
            _lNameTextField.setStyle("-fx-text-box-border: red;");
            validation = false;
        } else {
            _lNameTextField.setStyle("-fx-text-box-border: black;");
        }

        if (_birthdayTextField.getText().length() == 0) {
            _birthdayTextField.setStyle("-fx-text-box-border: red;");
            validation = false;
        } else {
            _birthdayTextField.setStyle("-fx-text-box-border: black;");
        }

        return validation;
    }
}