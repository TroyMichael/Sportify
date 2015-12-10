package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.client.communication.ServiceLocator;
import at.fhv.itb13.sportify.client.presentation.SportifyGUI;
import at.fhv.itb13.sportify.shared.communication.dtos.*;
import at.fhv.itb13.sportify.shared.communication.exceptions.NotAuthorizedException;
import at.fhv.itb13.sportify.shared.communication.remote.ejb.SessionRemote;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import static at.fhv.itb13.sportify.shared.communication.dtos.MatchDTOImpl.SimpleMatchTeamDTO;

/**
 * Created by Michael on 10.11.2015.
 */
public class NewTournamentFormController {

    @FXML
    private TextField _nameTextField;

    @FXML
    private ComboBox<SimpleSportDTO> _sportComboBox;

    @FXML
    private DatePicker _datePicker;

    @FXML
    private TextField _locationTextField;

    @FXML
    private TableView<DisplayTeamDTO> _allTeamsTableView;

    @FXML
    private TableColumn<DisplayTeamDTO, String> _allTeamsNameColumn;

    @FXML
    private TableView<DisplayTeamDTO> _addedTeamsTableView;

    @FXML
    private TableColumn<DisplayTeamDTO, String> _addedTeamsNameColumn;

    @FXML
    private TextField _foreignTeamTextField;

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

    private ObservableList<DisplayTeamDTO> _allTeamsObservable = FXCollections.observableArrayList();
    private ObservableList<DisplayTeamDTO> _addedTeamsObservable = FXCollections.observableArrayList();
    private ObservableList<MatchDTO> _matchObservable = FXCollections.observableArrayList();
    private HashSet<ExternalDisplayTeamDTO> _externalDisplayTeamDTOs = new HashSet<>();
    private TournamentDTO _tournament;

    @FXML
    private void initialize() {

        //set values for allTeamsTableView's columns
        _allTeamsNameColumn.setCellValueFactory(new PropertyValueFactory<DisplayTeamDTO, String>("Name"));

        //set values for addedMembersTableViews' columns
        _addedTeamsNameColumn.setCellValueFactory(new PropertyValueFactory<DisplayTeamDTO, String>("Name"));

        _addedTeamsTableView.setItems(_addedTeamsObservable);

        getAllTeamsTableViewData();
        setSportComboBoxData();
        _sportComboBox.setValue(_sportComboBox.getItems().get(3));

        //set values for matchTable

        _team1NameColumn.setCellValueFactory(new PropertyValueFactory<MatchDTO, SimpleMatchTeamDTO>("Team1"));
        _team2NameColumn.setCellValueFactory(new PropertyValueFactory<MatchDTO, SimpleMatchTeamDTO>("Team2"));
        _dateColumn.setCellValueFactory(new PropertyValueFactory<MatchDTO, String>("Start"));
        _matchTableView.setItems(_matchObservable);
    }

    private void getAllTeamsTableViewData() {
        //retrieve list of all members and set the list to the _allTeamsTableView
        List<DisplayTeamDTO> allTeams = ServiceLocator.getInstance().getRemote(SessionRemote.class).getTeamRemote().getAllDisplayTeams();

        if (allTeams != null) {
            //create an observableArrayList and fill it with all teams
            for (DisplayTeamDTO team : allTeams) {
                _allTeamsObservable.add(team);
            }
            setFilterAndDataToAllTeams(_allTeamsObservable);
        }
    }

    private void setFilterAndDataToAllTeams(ObservableList<DisplayTeamDTO> _teams) {
        //filtering-process taken from: http://code.makery.ch/blog/javafx-8-tableview-sorting-filtering/

        //wrap observableList into filter list
        //p -> true shows all teams
        //p -> false shows no teams
        final FilteredList<DisplayTeamDTO> _filteredTeamList = new FilteredList<>(_teams, new Predicate<DisplayTeamDTO>() {
            @Override
            public boolean test(DisplayTeamDTO displayTeamDTO) {
                return false;
            }
        });

        //set changeListener to sportComboBox
        _sportComboBox.valueProperty().addListener(new ChangeListener<SimpleSportDTO>() {
            @Override
            public void changed(ObservableValue<? extends SimpleSportDTO> observable, SimpleSportDTO oldValue, final SimpleSportDTO newValue) {
                _filteredTeamList.setPredicate(new Predicate<DisplayTeamDTO>() {
                    @Override
                    public boolean test(DisplayTeamDTO team) {
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
                    }
                });
            }
        });

        //FilteredList cannot be modified -> not sortable
        //wrap filteredList in sortedList
        SortedList<DisplayTeamDTO> sortedTeamList = new SortedList<>(_filteredTeamList);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedTeamList.comparatorProperty().bind(_allTeamsTableView.comparatorProperty());

        //set sortedList as items to allTeamsTableView
        _allTeamsTableView.setItems(sortedTeamList);
    }

    private void setAllTeamsListData(ObservableList<DisplayTeamDTO> teams) {

        //wrap observableList into filter list
        //p -> true shows all teams
        //p -> false shows no teams
        FilteredList<DisplayTeamDTO> _filteredTeamList = new FilteredList<>(teams, new Predicate<DisplayTeamDTO>() {
            @Override
            public boolean test(DisplayTeamDTO displayTeamDTO) {
                return false;
            }
        });

        _filteredTeamList.setPredicate(new Predicate<DisplayTeamDTO>() {
            @Override
            public boolean test(DisplayTeamDTO team) {
                //define here all rules of filtering and what should be searched and filtered

                //if nothing is selected in the combobox show no teams
                if (_sportComboBox.getSelectionModel().getSelectedItem() == null) {
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
            }
        });

        //FilteredList cannot be modified -> not sortable
        //wrap filteredList in sortedList
        SortedList<DisplayTeamDTO> sortedTeamList = new SortedList<>(_filteredTeamList);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedTeamList.comparatorProperty().bind(_allTeamsTableView.comparatorProperty());

        //set sortedList as items to allTeamsTableView
        _allTeamsTableView.setItems(sortedTeamList);
    }


    private void setSportComboBoxData() {
        List<SimpleSportDTO> sportList;
        sportList = ServiceLocator.getInstance().getRemote(SessionRemote.class).getSportRemote().getAllSimpleSports();

        if (sportList != null) {
            ObservableList<SimpleSportDTO> sportObservable = FXCollections.observableArrayList();
            for (SimpleSportDTO sport : sportList) {
                sportObservable.add(sport);
            }
            _sportComboBox.getItems().addAll((sportObservable));
            _sportComboBox.setValue(_sportComboBox.getItems().get(0));
        }
    }

    @FXML
    private void addTeam() {
        if (_allTeamsTableView.getSelectionModel().getSelectedItem() != null) {
            DisplayTeamDTO teamToAdd = _allTeamsTableView.getSelectionModel().getSelectedItem();
            _addedTeamsTableView.getItems().add(teamToAdd);
            _allTeamsObservable.remove(teamToAdd);
            setAllTeamsListData(_allTeamsObservable);
        }
    }

    @FXML
    private void removeTeam() {
        if (_addedTeamsTableView.getSelectionModel().getSelectedItem() != null) {
            DisplayTeamDTO teamToRemove = _addedTeamsTableView.getSelectionModel().getSelectedItem();
            _addedTeamsTableView.getItems().remove(teamToRemove);
            _allTeamsObservable.add(teamToRemove);
            setAllTeamsListData(_allTeamsObservable);
        }
    }

    @FXML
    private void removeAllTeams() {
        while (_addedTeamsTableView.getItems().size() > 0) {
            DisplayTeamDTO teamToSwitch = _addedTeamsTableView.getItems().get(0);
            _addedTeamsTableView.getItems().remove(teamToSwitch);
            _allTeamsObservable.add(teamToSwitch);
        }
        setAllTeamsListData(_allTeamsObservable);
    }

    @FXML
    private void saveNewTournament() throws RemoteException, NotAuthorizedException {

        if (createOrUpdateTournamentDTO()) {
            //call createFunction

            for (ExternalDisplayTeamDTO externalDisplayTeamDTO : _externalDisplayTeamDTOs) {
                externalDisplayTeamDTO.setSport(_sportComboBox.getValue());
                ServiceLocator.getInstance().getRemote(SessionRemote.class).getTeamRemote().createExternalTeam(externalDisplayTeamDTO);
            }

            ServiceLocator.getInstance().getRemote(SessionRemote.class).getTournamentRemote().createTournament(_tournament);
            initSuccessAlert();
            //TODO switch to tournamentdetail view
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

        if (_datePicker.getValue() == null) {
            _datePicker.setStyle("-fx-date-picker-border:red;");
            validation = false;
        } else {
            _datePicker.setStyle("-fx-date-picker-border:lightgrey;");
        }

        if (_locationTextField.getText().length() == 0) {
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
        ExternalDisplayTeamDTO externalDisplayTeamDTO = new ExternalDisplayTeamDTO(_foreignTeamTextField.getText());
        _externalDisplayTeamDTOs.add(externalDisplayTeamDTO);
        _addedTeamsObservable.add(externalDisplayTeamDTO);
        _foreignTeamTextField.clear();
    }

    @FXML
    private void addNewMatch() {
        if (createOrUpdateTournamentDTO()) {
            SportifyGUI.getSharedMainApp().loadNewMatchForm(_tournament, _externalDisplayTeamDTOs, true);
        }
    }

    private boolean createOrUpdateTournamentDTO() {
        if (_tournament == null) {
            _tournament = new TournamentDTOImpl();
        }

        if (validate()) {
            //gather all information of the new tournament
            String tournamentName = _nameTextField.getText();
            SimpleSportDTO selectedSport = _sportComboBox.getValue();
            Date startDate = Date.valueOf(_datePicker.getValue());

            //fill _tournament with gathered data
            _tournament.setDescription(tournamentName);
            _tournament.setSportID(selectedSport.getId());
            _tournament.setLocation(_locationTextField.getText());
            _tournament.setStartDate(startDate);
            for (DisplayTeamDTO team : _addedTeamsTableView.getItems()) {
                _tournament.addTeamID(team.getId());
            }
            for (MatchDTO match : _matchTableView.getItems()) {
                _tournament.addMatch(match);
            }
            return true;
        }
        return false;
    }

    private void initSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Saving successful!");
        alert.setTitle("Saving successful");
        alert.setContentText("A new Tournament with the name: " + _nameTextField.getText() + " and the Sport: " + _sportComboBox.getValue().getName() + "' was successfully created!");
        alert.showAndWait();
    }

    private void initErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Components missing!");
        alert.setTitle("Components missing!");
        alert.setContentText("A new Team could not be created due to missing fields!");
        alert.showAndWait();
    }

    public void setTournament(TournamentDTO tournament, HashSet<ExternalDisplayTeamDTO> externalDisplayTeamDTOs) {
        _tournament = tournament;
        _externalDisplayTeamDTOs = externalDisplayTeamDTOs;
        //set data
        _nameTextField.setText(_tournament.getDescription());
        _locationTextField.setText(_tournament.getLocation());
        _datePicker.setValue(_tournament.getStartDate().toLocalDate());

        //set _sportCombobox value
        for (SimpleSportDTO sport : _sportComboBox.getItems()) {
            if (sport.getId().equals(_tournament.getSportID())) {
                _sportComboBox.setValue(sport);
            }
        }

        //add Teams to addedTeamTableView and remove them from allTeamTableView
        //so teams cannot be chosen twice
        Iterator it = _allTeamsObservable.iterator();
        while (it.hasNext()) {
            DisplayTeamDTO team = (DisplayTeamDTO) it.next();
            if (_tournament.getTeamIDs().contains(team.getId())) {
                it.remove();
                _addedTeamsTableView.getItems().add(team);
            }
        }
        _addedTeamsTableView.getItems().addAll(_externalDisplayTeamDTOs);
        setAllTeamsListData(_allTeamsObservable);
        for (MatchDTO match : _tournament.getMatches()) {
            _matchObservable.add(match);
        }
    }

    public void setTournament(TournamentDTO tournamentDTO) {
        _tournament = tournamentDTO;

        HashSet<ExternalDisplayTeamDTO> externalTeams = new HashSet<>();
        List<ExternalDisplayTeamDTO> teams = null;

        teams = ServiceLocator.getInstance().getRemote(SessionRemote.class).getTeamRemote().getAllExternalTeams();
        Iterator<ExternalDisplayTeamDTO> it = teams.iterator();
        while (it.hasNext()) {
            ExternalDisplayTeamDTO team = it.next();
            if (_tournament.getTeamIDs().contains(team.getId())) {
                externalTeams.add(team);
            }
        }
        setTournament(tournamentDTO, externalTeams);
    }
}