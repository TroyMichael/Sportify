package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.client.presentation.SportifyGUI;
import at.fhv.itb13.sportify.shared.communication.dtos.DisplayTeamDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.SimplePersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.SimpleTournamentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.HashSet;

/**
 * Created by Michael on 15.11.2015.
 */
public class TeamDetailViewController {

    @FXML
    private Label _teamNameLabel;

    @FXML
    private Label _sportLabel;

    @FXML
    private Label _trainerNameLabel;

    @FXML
    private TableView<SimplePersonDTO> _membersTableView;

    @FXML
    private TableColumn<SimplePersonDTO, String> _firstNameColumn;

    @FXML
    private TableColumn<SimplePersonDTO, String> _lastNameColumn;

    @FXML
    private TableColumn<SimplePersonDTO, String> _birthdateColumn;

    @FXML
    private TableColumn<SimplePersonDTO, String> _emailColumn;

    @FXML
    private TableColumn<SimplePersonDTO, String> _feeColumn;

    private DisplayTeamDTO _team;

    private ObservableList<SimplePersonDTO> _memberList = FXCollections.observableArrayList();

    //TournamentList from Team
    @FXML
    private TextField _filterTextField;

    @FXML
    private TableView<SimpleTournamentDTO> _tournamentTableView;

    @FXML
    private TableColumn<SimpleTournamentDTO, String> _descriptionColumn;

    @FXML
    private TableColumn<SimpleTournamentDTO, String> _startDateColumn;

    @FXML
    private TableColumn<SimpleTournamentDTO, String> _locationColumn;

    private ObservableList<SimpleTournamentDTO> _tournamentList = FXCollections.observableArrayList();


    @FXML
    private void initialize() {

        //set values of each column in Member Table
        _firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("FName"));
        _lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("LName"));
        _birthdateColumn.setCellValueFactory(new PropertyValueFactory<>("BirthDate"));
        _emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
        _feeColumn.setCellValueFactory(new PropertyValueFactory<>("Payed"));

        //set column values in Tournament Table
        _descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        _startDateColumn.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
        _locationColumn.setCellValueFactory(new PropertyValueFactory<>("Location"));


    }

    @FXML
    private void editTeam() {
        SportifyGUI.getSharedMainApp().loadEditTeamForm(_team);
    }

    @FXML
    private void back() {
        SportifyGUI.getSharedMainApp().loadTeamList();
    }

    public void setTeam(DisplayTeamDTO teamToShow) {
        _team = teamToShow;
        _teamNameLabel.setText(_team.getName());
        _trainerNameLabel.setText(_team.getTrainer().getFName() + " " + _team.getTrainer().getLName());
        _sportLabel.setText(_team.getSport().getName());

        HashSet<SimplePersonDTO> _tempList = _team.getMembers();
        _tempList.forEach(person -> _memberList.add(person));
        _membersTableView.setItems(_memberList);


        getAndAddDataToTournamentList();
        setDoubleClickOnTournamentTableView();

    }

    /*
  Gets all tournaments from the database and adds them to _tournamentList
   */
    private void getAndAddDataToTournamentList() {

        HashSet<SimpleTournamentDTO> tempTournamentList = new HashSet<>();
        if(_team.getTournaments() != null) {
            tempTournamentList = _team.getTournaments();
            tempTournamentList.forEach(tournamentDTO -> _tournamentList.add(tournamentDTO));
        }
    }

    /*
  defines that when a doubleclick occurs on a row in memberTableView the given TournamentDTO is loaded in detail
   */
    private void setDoubleClickOnTournamentTableView() {
        //set doubleclick handling of tableViewRow
        _tournamentTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    if (_tournamentTableView.getSelectionModel().getSelectedItem() != null) {
                        SportifyGUI.getSharedMainApp().loadNewRosterForm(_tournamentTableView.getSelectionModel().getSelectedItem(), _team);
                    }
                }
            }
        });
    }



}
