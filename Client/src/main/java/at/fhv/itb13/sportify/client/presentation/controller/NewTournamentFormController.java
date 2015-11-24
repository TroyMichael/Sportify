package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.client.application.SessionController;
import at.fhv.itb13.sportify.client.presentation.SportifyGUI;
import at.fhv.itb13.sportify.shared.communication.dtos.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Michael on 10.11.2015.
 *
 */
public class NewTournamentFormController {

    @FXML
    private TextField _nameTextField;

    @FXML
    private ComboBox<SportDTO> _sportComboBox;

    @FXML
    private TextField _dateTextField;

    @FXML
    private TextField _locationTextField;

    @FXML
    private TableView<TeamDetailDTO> _allTeamsTableView;

    @FXML
    private TableColumn<TeamDetailDTO, String> _allTeamsNameColumn;

    @FXML
    private TableView<TeamDetailDTO> _addedTeamsTableView;

    @FXML
    private TableColumn<TeamDetailDTO, String> _addedTeamsNameColumn;

    @FXML
    private TextField _foreignTeamTextField;

//    @FXML
//    private TableView<MatchDetailDTO> _matchTableView;
//
//    @FXML
//    private TableColumn<MatchDetailDTO, String> _matchNumberColumn;
//
//    @FXML
//    private TableColumn<MatchDetailDTO, String> _team1NumberColumn;
//
//    @FXML
//    private TableColumn<MatchDetailDTO, String> _team2NumberColumn;
//
//    @FXML
//    private TableColumn<MatchDetailDTO, String> _dateNumberColumn;
//
//    @FXML
//    private TableColumn<MatchDetailDTO, String> _timeNumberColumn;
//
//    @FXML
//    private TableColumn<MatchDetailDTO, String> _scoreNumberColumn;


    private ObservableList<TeamDetailDTO> _addedTeamsObservable = FXCollections.observableArrayList();

    @FXML
    private void initialize(){

        //set values for allTeamsTableView's columns
        _allTeamsNameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));

        //set values for addedMembersTableViews' columns
        _addedTeamsNameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));

        _addedTeamsTableView.setItems(_addedTeamsObservable);

        getAllTeamsTableViewData();
        setSportComboBoxData();
    }

    private void getAllTeamsTableViewData() {
        //retrieve list of all members and set the list to the _allTeamsTableView
        try {
            List<TeamDetailDTO> allTeams = SessionController.getInstance().getSession().getTeamDetailRemote().getAllTeams();

            if (allTeams != null) {
                //create an observableArrayList and fill it with all teams
                ObservableList<TeamDetailDTO> allMembersObservable = FXCollections.observableArrayList();
                allTeams.forEach(team -> allMembersObservable.add(team));
                setFilterAndDataToAllTeams(allMembersObservable);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void setFilterAndDataToAllTeams(ObservableList<TeamDetailDTO> _teams) {
        //filtering-process taken from: http://code.makery.ch/blog/javafx-8-tableview-sorting-filtering/

        //wrap observableList into filter list
        //p -> true shows all teams
        FilteredList<TeamDetailDTO> _filteredTeamList = new FilteredList<>(_teams, p -> false);

        //set changeListener to sportComboBox
        _sportComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            _filteredTeamList.setPredicate(team -> {

                //define here all rules of filtering and what should be searched and filtered

                //if nothing is selected in the combobox show no teams
                if (newValue == null) {
                    return false;
                }

                //else compare the ID of the selected combobox to the sportIDs of the teams
                String filterID = _sportComboBox.getValue().getId();

                if (team.getSport().getId().equals(filterID)) {
                    return true;
                }
                //filter more attributes if wanted

                //if nothing matches, return false, so that that person won't be shown in the list
                return false;
            });
        });

        //FilteredList cannot be modified -> not sortable
        //wrap filteredList in sortedList
        SortedList<TeamDetailDTO> sortedTeamList = new SortedList<>(_filteredTeamList);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedTeamList.comparatorProperty().bind(_allTeamsTableView.comparatorProperty());

        //set sortedList as items to allTeamsTableView
        _allTeamsTableView.setItems(sortedTeamList);
    }


    private void setSportComboBoxData() {
        try {
            List<SportDTO> sportList;
            sportList = SessionController.getInstance().getSession().getSportRemote().getSports();

            if (sportList != null) {
                ObservableList<SportDTO> sportObservable = FXCollections.observableArrayList();
                sportList.forEach(sport -> sportObservable.add(sport));
                _sportComboBox.getItems().addAll((sportObservable));
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addTeam () {
        switchMember(_allTeamsTableView, _addedTeamsTableView);
    }

    @FXML
    private void removeTeam () {
        switchMember(_addedTeamsTableView, _allTeamsTableView);
    }

    @FXML
    private void removeAllTeams() {
        while (_addedTeamsTableView.getItems().size() > 0) {
            TeamDetailDTO teamToSwitch = _addedTeamsTableView.getItems().get(0);
                _addedTeamsTableView.getItems().remove(teamToSwitch);
                _allTeamsTableView.getItems().add(teamToSwitch);
        }
    }

    private void switchMember (TableView<TeamDetailDTO> viewToRemoveFrom, TableView<TeamDetailDTO> viewToAddTo) {
        if (viewToRemoveFrom.getSelectionModel().getSelectedItem() != null) {
            TeamDetailDTO teamToSwitch = viewToRemoveFrom.getSelectionModel().getSelectedItem();
            viewToRemoveFrom.getItems().remove(teamToSwitch);
            viewToAddTo.getItems().add(teamToSwitch);
        }
    }

    @FXML
    private void saveNewTournament() {

        if (validate()) {
            //gather all information of the new tournament
            String tournamentName = _nameTextField.getText();
            SportDTO selectedSport = _sportComboBox.getValue();
            Set<String> teamIDs = new HashSet<>();

            //create new TournamentDTO
            TournamentDTO newTournament = new TournamentDTOImpl();
            newTournament.setDescription(tournamentName);
            newTournament.setSportID(selectedSport.getId());
            newTournament.setLocation(_locationTextField.getText());
            _addedTeamsTableView.getItems().forEach(team -> newTournament.addTeamID(team.getId()));

            //call createFunction
            try {
                SessionController.getInstance().getSession().getTournamentRemote().createTournament(newTournament);
                initSuccessAlert();
                //TODO switch to tournamentdetail view
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
           initErrorAlert();
        }
    }

    private boolean validate() {
        boolean validation = true;

        if (_nameTextField.getText().length() == 0) {
            _nameTextField.setStyle("-fx-text-box-border:red;");
            validation = false;
        } else {
            _nameTextField.setStyle("-fx-text-box-border:lightgrey;");
        }

        if (_sportComboBox.getValue() == null) {
            _sportComboBox.setStyle("-fx-text-box-border:red;");
            validation = false;
        } else {
            _sportComboBox.setStyle("-fx-text-box-border:lightgrey;");
        }

        if (_dateTextField.getText().length() == 0){
            _dateTextField.setStyle("-fx-text-box-border:red;");
            validation = false;
        } else {
            _dateTextField.setStyle("-fx-text-box-border:lightgrey;");
        }

        if (_locationTextField.getText().length() == 0){
            _locationTextField.setStyle("-fx-text-box-border:red;");
            validation = false;
        } else {
            _locationTextField.setStyle("-fx-text-box-border:lightgrey;");
        }

        return validation;
    }

    @FXML
    private void cancelNewTournament() {
        SportifyGUI.getSharedMainApp().loadHelloView();
    }

    @FXML
    private void addForeignTeam() {
        //TODO
    }

    @FXML
    private void addNewMatch() {
        //TODO
    }

    private void initSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Saving successful!");
        alert.setTitle("Saving successful");
        alert.setContentText("A new Team with the name: " + _nameTextField.getText() + " and the Sport: " + _sportComboBox.getValue().getName() + "' was successfully created!");
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