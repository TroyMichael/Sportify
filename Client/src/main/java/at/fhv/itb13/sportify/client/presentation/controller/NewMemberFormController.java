package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.client.communication.ServiceLocator;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTOImpl;
import at.fhv.itb13.sportify.shared.communication.remote.ControllerFactory;
import at.fhv.itb13.sportify.shared.communication.remote.PersonRemote;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.rmi.RemoteException;

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
    private TextField _eMailTextField;

    @FXML
    private TextField _birthdayTextField;

    /**
     * TODO: get PAYMENTSTATUS
     * @throws RemoteException
     */
    @FXML
    private void saveNewMember() throws RemoteException {

        if (validateInput()) {
            PersonDTOImpl newMember = new PersonDTOImpl(
                    _fNameTextField.getText(),
                    _lNameTextField.getText(),
                    _streetTextField.getText(),
                    _streetNoTextField.getText(),
                    _postalCodeTextField.getText(),
                    _cityTextField.getText(),
                    _eMailTextField.getText(),
                    _birthdayTextField.getText(),
                    true
            );

            ServiceLocator.getInstance().getRemote(ControllerFactory.class).getPersonRemote().create(newMember);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Saving successful!");
            alert.setTitle("Saving successful");
            alert.setContentText("The member '" + _fNameTextField.getText() + " " + _lNameTextField.getText() + "' was saved successfully!");
            alert.showAndWait();
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
    private void cancelNewMember() {
        System.out.println("Cancelled");
    }
}