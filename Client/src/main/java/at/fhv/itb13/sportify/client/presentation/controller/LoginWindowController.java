package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.client.application.SessionController;
import at.fhv.itb13.sportify.client.presentation.SportifyGUI;
import at.fhv.itb13.sportify.shared.communication.dtos.UserDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.UserDTOImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.security.auth.login.FailedLoginException;
import java.rmi.RemoteException;

/**
 * This controller controls the MainFrame.fxml. It implements the respective functions for each item in the menu bar.
 */
public class LoginWindowController {

    @FXML
    private TextField _usernameTextfield;

    @FXML
    private PasswordField _passwordField;

    private void loadHelloView(String username) {
        SportifyGUI.getSharedMainApp().loadHelloView(username);
    }

    @FXML
    private void loginButtonClicked() throws RemoteException {

        UserDTO userDTO = new UserDTOImpl();
        userDTO.setName(_usernameTextfield.getText());
        userDTO.setPassword(_passwordField.getText());

        try {
            SessionController.getInstance().login(userDTO);
            loadHelloView(_usernameTextfield.getText());
        } catch (FailedLoginException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wrong Password or Username");
            alert.setHeaderText("Wrong Password or Username");
            alert.setContentText("The Password or the Username is wrong. Please try again.");
            alert.showAndWait();
        }
    }
}
