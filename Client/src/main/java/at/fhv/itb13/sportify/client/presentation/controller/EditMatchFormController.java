package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.client.application.SessionController;
import at.fhv.itb13.sportify.client.presentation.SportifyGUI;
import at.fhv.itb13.sportify.shared.communication.dtos.MatchDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.MatchStatus;
import at.fhv.itb13.sportify.shared.communication.dtos.SimpleTournamentDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TournamentDTO;
import at.fhv.itb13.sportify.shared.communication.exceptions.NotAuthorizedException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Michael on 26.10.2015.
 * <p/>
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
            @Override
            public void changed(ObservableValue ov, MatchStatus T, MatchStatus t) {
                if (ov.getValue().equals(MatchStatus.FINISHED)) {
                    setPointsVisible(true);
                } else {
                    setPointsVisible(false);
                }
            }
        });
    }

    private void setDataToTextFields() {
        if (_matchDTO != null) {
            _durationTextField.setText(_matchDTO.getDuration().toString());
            DateFormat dfTime = new SimpleDateFormat("HH:mm:ss");
            _startTimeTextField.setText("");
            if (_matchDTO.getStart() != null) {
                _startTimeTextField.setText(dfTime.format(_matchDTO.getStart()));
            }
            DateFormat dfDate = new SimpleDateFormat("dd.MM.yyyy");
            _dateTextField.setText("");
            if (_matchDTO.getStart() != null) {
                _dateTextField.setText(dfDate.format(_matchDTO.getStart()));
            }
            _team1TextField.setText(_matchDTO.getTeam1().getName());
            _team2TextField.setText(_matchDTO.getTeam2().getName());
            if (_matchDTO.getMatchStatus().equals("FINISHED")) {
                _statusComboBox.setValue(_statusComboBox.getItems().get(1));
            }
            if (_matchDTO.getTeam1().getPoints() != null) {
                _pointsTeam1.setText(_matchDTO.getTeam1().getPoints());
            }
            if (_matchDTO.getTeam2().getPoints() != null) {
                _pointsTeam2.setText(_matchDTO.getTeam2().getPoints());
            }
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
        SimpleTournamentDTO simpleTournamentDTO = null;
        try {
            simpleTournamentDTO = SessionController.getInstance().getSession().getTournamentRemote().getSimpleTournamentDTOByID(_tournament.getId());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        SportifyGUI.getSharedMainApp().loadTournamentDetailView(simpleTournamentDTO);
    }


    public void setMatchDTO(MatchDTO matchDTO) {
        _matchDTO = matchDTO;
        setDataToTextFields();
    }

    public void setTournamentDTO(TournamentDTO tournamentDTO) {
        _tournament = tournamentDTO;
    }

    private void setPointsVisible(Boolean bool) {
        _pointsLabel.setVisible(bool);
        _pointsTeam1.setVisible(bool);
        _pointsTeam2.setVisible(bool);
    }

    @FXML
    private void saveMatch() throws RemoteException, NotAuthorizedException {
        if (validateInput()) {
            if (_pointsTeam1.getText() != null) {
                _matchDTO.getTeam1().setPoints(_pointsTeam1.getText());
            }
            if (_pointsTeam2.getText() != null) {
                _matchDTO.getTeam2().setPoints(_pointsTeam2.getText());
            }
            _matchDTO.setMatchStatus(_statusComboBox.getSelectionModel().getSelectedItem().name());
            SessionController.getInstance().getSession().getMatchRemote().update(_matchDTO);
            _tournament.addMatch(_matchDTO);
            SessionController.getInstance().getSession().getTournamentRemote().updateTournament(_tournament);
            initSuccessAlert();
            SimpleTournamentDTO simpleTournamentDTO = SessionController.getInstance().getSession().getTournamentRemote().getSimpleTournamentDTOByID(_tournament.getId());
            SportifyGUI.getSharedMainApp().loadTournamentDetailView(simpleTournamentDTO);
        }else{
            initErrorAlert();
        }
    }

    private void initSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Saving successful!");
        alert.setTitle("Saving successful");
        alert.setContentText("The Match was successfully updated!");
        alert.showAndWait();
    }

    private void initErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Components missing!");
        alert.setTitle("Components missing!");
        alert.setContentText("A new Team could not be created due to missing fields!");
        alert.showAndWait();
    }
}