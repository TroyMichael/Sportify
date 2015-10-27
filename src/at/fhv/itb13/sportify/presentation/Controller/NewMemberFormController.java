package at.fhv.itb13.sportify.presentation.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Created by Michael on 26.10.2015.
 *
 * Controls the view NewMemberForm. Checks if all required text fields contain values when trying to add a new member
 * and then creates a DTO.
 *
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

        System.out.println(_fNameTextField.getText());
        System.out.println(_lNameTextField.getText());

    }

    @FXML
    private void CancelNewMember() {

        System.out.println("Canceled");

    }
}