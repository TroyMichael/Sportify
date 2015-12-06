package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.client.application.SessionController;
import at.fhv.itb13.sportify.shared.communication.dtos.MatchDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.MatchStatus;
import at.fhv.itb13.sportify.shared.communication.dtos.TournamentDTO;
import at.fhv.itb13.sportify.shared.communication.exceptions.NotAuthorizedException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.rmi.RemoteException;
import java.util.concurrent.TimeUnit;

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

    @FXML
    private Label _pointsLabel;

    @FXML
    private TextField _pointsTeam1;

    @FXML
    private TextField _pointsTeam2;

    @FXML
    private ComboBox<MatchStatus> _statusComboBox;

    private TournamentDTO _tournament;

    private MatchDTO _matchDTO;


    @FXML
    private void initialize() {
        setStateComboBoxData();
    }

    private void setStateComboBoxData() {

        ObservableList<MatchStatus> stateObservable = FXCollections.observableArrayList();
        stateObservable.add(MatchStatus.PLANNED);
        stateObservable.add(MatchStatus.FINISHED);
        _statusComboBox.getItems().addAll((stateObservable));
        _statusComboBox.setValue(_statusComboBox.getItems().get(0));

        _statusComboBox.valueProperty().addListener(new ChangeListener<MatchStatus>() {
            @Override public void changed(ObservableValue ov, MatchStatus T, MatchStatus t) {
              if(ov.getValue().equals(MatchStatus.FINISHED)){
                  setPointsVisible(true);
              }else{
                  setPointsVisible(false);
              }
            }
        });
    }

    private void setDataToTextFields() {
        if (_matchDTO != null) {
            _durationTextField.setText(_matchDTO.getDuration().toString());
            long millis = _matchDTO.getStart().getTime();
            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                    TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
            _startTimeTextField.setText(hms);
            _dateTextField.setText(_matchDTO.getStart().toString());
            _team1TextField.setText(_matchDTO.getTeam1().getName());
            _team2TextField.setText(_matchDTO.getTeam2().getName());
        }
    }


    private Boolean validateInput() {

        if ((_pointsTeam1 != null) && (_pointsTeam2 != null)) {
            if ((!_pointsTeam1.getText().matches("\\d*")) || (!_pointsTeam2.getText().matches("\\d*"))) {
                return false;
            }

        }
        return true;

    }

    @FXML
    private void cancelNewMatch() {
        // SportifyGUI.getSharedMainApp().loadNewTournamentView(_tournament,_externalDisplayTeamDTOs);
    }


    public void setMatchDTO(MatchDTO matchDTO) {
        _matchDTO = matchDTO;
        setDataToTextFields();
    }

    private void setPointsVisible(Boolean bool){
        _pointsLabel.setVisible(bool);
        _pointsTeam1.setVisible(bool);
        _pointsTeam2.setVisible(bool);
    }

    @FXML
    private void saveMatch() throws RemoteException, NotAuthorizedException {
        if(validateInput()){
            if(_pointsTeam1 != null) {
                _matchDTO.getTeam1().setPoints(_pointsTeam1.getText());
            }
            if(_pointsTeam2 != null){
                _matchDTO.getTeam2().setPoints(_pointsTeam2.getText());
            }
            SessionController.getInstance().getSession().getMatchRemote().update(_matchDTO);
        }
    }
}