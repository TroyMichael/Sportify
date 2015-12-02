package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.client.application.SessionController;
import at.fhv.itb13.sportify.client.presentation.SportifyGUI;
import at.fhv.itb13.sportify.shared.communication.dtos.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.rmi.RemoteException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Michael on 26.10.2015.
 * <p>
 * Controls the view NewMemberForm. Checks if all required text fields contain values when trying to add a new member
 * and then creates a DTO.
 */
public class EditMatchFormController {

    @FXML
    private TextField _durationTextField;

    @FXML
    private TextField _dateTextField;

    @FXML
    private TextField _startTimeTextField;

    @FXML
    private TextField _team1TextField;

    @FXML
    private TextField _team2TextField;


    private LocalDate _localDate;

    private int _duration;

    private TournamentDTO _tournament;

    private MatchDTO _matchDTO;


    @FXML
    private void initialize(){
        setDataToTextFields();
    }

    private void setDataToTextFields(){
        if(_matchDTO != null){
            _durationTextField.setText(_matchDTO.getDuration().toString());
          //  _startTimeTextField.setText(_matchDTO.getStart().getTime());
            _dateTextField.setText(_matchDTO.getStart().toString());
            _team1TextField.setText(_matchDTO.getTeam1().getName());
            _team2TextField.setText(_matchDTO.getTeam2().getName());
        }
    }



    private Boolean validateInput() {
        Boolean validation = true;
        return true;
    }

    @FXML
    private void cancelNewMatch() {
       // SportifyGUI.getSharedMainApp().loadNewTournamentView(_tournament,_externalDisplayTeamDTOs);
    }


    public void setMatchDTO(MatchDTO matchDTO) {
        _matchDTO = matchDTO;
    }
}