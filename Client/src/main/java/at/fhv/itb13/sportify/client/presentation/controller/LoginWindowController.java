package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.client.communication.ServiceLocator;
import at.fhv.itb13.sportify.client.presentation.SportifyGUI;
import at.fhv.itb13.sportify.shared.communication.dtos.UserDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.UserDTOImpl;
import at.fhv.itb13.sportify.shared.communication.remote.ControllerFactory;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    private void loginButtonClicked(){

        UserDTO userDTO = new UserDTOImpl();
        userDTO.setName(_usernameTextfield.getText());
        userDTO.setPassword(_passwordField.getText());

        try {
            if(ServiceLocator.getInstance().getRemote(ControllerFactory.class).getUserRemote().login(userDTO)){
                loadHelloView(_usernameTextfield.getText());
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("WRONG PASSWORD");
            }
        } catch (RemoteException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("SERVER CONNECTION FAILED");

            e.printStackTrace();
        }


    }
}
