package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.client.application.SessionController;
import at.fhv.itb13.sportify.client.presentation.SportifyGUI;
import at.fhv.itb13.sportify.shared.communication.dtos.SimplePersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.DisplayTeamDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TournamentDTO;
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

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Michael on 15.11.2015.
 *
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

        getAndAddDataToTournamentList();
        setDoubleClickOnTournamentTableView();
        setFilterProcess();

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
    }

    /*
  Gets all tournaments from the database and adds them to _tournamentList
   */
    private void getAndAddDataToTournamentList() {
        try {
            List<SimpleTournamentDTO> tempTournamentList = _team.getAllTournaments();
            tempTournamentList.forEach(tournamentDTO -> _tournamentList.add(tournamentDTO));
        } catch (RemoteException e) {
            e.printStackTrace();
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
                        SportifyGUI.getSharedMainApp().loadNewRosterForm(_tournamentTableView.getSelectionModel().getSelectedItem());
                    }
                }
            }
        });
    }

    /*
 defines the filter process, when the user enters data in the filter text field
 additionally, data is set to the tableview
  */
    private void setFilterProcess() {
        //filtering-process taken from: http://code.makery.ch/blog/javafx-8-tableview-sorting-filtering/

        //wrap observableList into filter list
        //tournamentDTO -> true shows all tournaments
        FilteredList<SimpleTournamentDTO> _filteredTournamentDTOList = new FilteredList<>(_tournamentList, tournamentDTO -> true);

        //set changeListener to textfield
        _filterTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            _filteredTournamentDTOList.setPredicate(tournamentDTO -> {

                //define here all rules of filtering and what should be searched and filtered

                //if textfield is empty/null show all persons
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                //else compare the filter string to the different columns
                String filterString = _filterTextField.getText().toLowerCase();

                if (tournamentDTO.getDescription().toLowerCase().contains(filterString)) {
                    return true;
                } else if (String.valueOf(tournamentDTO.getStartDate()).toLowerCase().contains(filterString)) {
                    return true;
                } else if (tournamentDTO.getLocation().toLowerCase().contains(filterString)) {
                    return true;
                }
                //filter more attributes if wanted

                //if nothing matches, return false, so that the searched tournament won't be shown in the list
                return false;
            });
        });

        //FilteredList cannot be modified -> not sortable
        //wrap filteredList in sortedList
        SortedList<SimpleTournamentDTO> sortedMemberList = new SortedList<>(_filteredTournamentDTOList);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedMemberList.comparatorProperty().bind(_tournamentTableView.comparatorProperty());

        //set sortedList as items to memberTableView
        _tournamentTableView.setItems(sortedMemberList);
    }

}
