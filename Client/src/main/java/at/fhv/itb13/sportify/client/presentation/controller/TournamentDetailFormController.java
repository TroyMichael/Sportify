package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.client.application.SessionController;
import at.fhv.itb13.sportify.client.presentation.SportifyGUI;
import at.fhv.itb13.sportify.shared.communication.dtos.MatchDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.SimpleTeamDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.SimpleTournamentDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TournamentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.rmi.RemoteException;

import static at.fhv.itb13.sportify.shared.communication.dtos.MatchDTOImpl.SimpleMatchTeamDTO;

/**
 * Created by Michael on 10.11.2015.
 *
 */
public class TournamentDetailFormController {

    @FXML
    private Label _nameLabel;

    @FXML
    private Label _sportLabel;

    @FXML
    private Label _dateLabel;

    @FXML
    private Label _locationLabel;

    @FXML
    private TableView<SimpleTeamDTO> _allTeamsTableView;

    @FXML
    private TableColumn<SimpleTeamDTO, String> _allTeamsNameColumn;

    @FXML
    private TableView<MatchDTO> _matchTableView;

    @FXML
    private TableColumn<MatchDTO, SimpleMatchTeamDTO> _team1NameColumn;

    @FXML
    private TableColumn<MatchDTO, SimpleMatchTeamDTO> _team2NameColumn;

    @FXML
    private TableColumn<MatchDTO, String> _dateColumn;

    @FXML
    private TableColumn<MatchDTO, String> _timeColumn;

    private ObservableList<SimpleTeamDTO> _allTeamsObservable = FXCollections.observableArrayList();
    private ObservableList<MatchDTO> _matchObservable = FXCollections.observableArrayList();
    private SimpleTournamentDTO _tournament;

    @FXML
    private void initialize() {

        //set values for allTeamsTableView's columns
        _allTeamsNameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        _allTeamsTableView.setItems(_allTeamsObservable);

        //set values for matchTable
        _team1NameColumn.setCellValueFactory(new PropertyValueFactory<>("Team1"));
        _team2NameColumn.setCellValueFactory(new PropertyValueFactory<>("Team2"));
        _dateColumn.setCellValueFactory(new PropertyValueFactory<>("Start"));
        _matchTableView.setItems(_matchObservable);
    }


    @FXML
    private void editTournament() throws RemoteException {

        TournamentDTO tournamentToEdit = SessionController.getInstance().getSession().getTournamentRemote().getByID(_tournament.getId());

        SportifyGUI.getSharedMainApp().loadEditTournamentForm(tournamentToEdit);
    }

    @FXML
    private void back() {
        SportifyGUI.getSharedMainApp().loadTournamentListView();
    }


    public void setTournament(SimpleTournamentDTO tournament) {
        _tournament = tournament;

        //set data
        _nameLabel.setText(_tournament.getDescription());
        _locationLabel.setText(_tournament.getLocation());
        _dateLabel.setText(_tournament.getStartDate().toLocalDate().toString());
        _sportLabel.setText(_tournament.getSport());
        _allTeamsObservable.addAll(_tournament.getTeams());
        _matchObservable.addAll(tournament.getMatches());
    }
}