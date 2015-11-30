package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.client.application.SessionController;
import at.fhv.itb13.sportify.client.presentation.SportifyGUI;
import at.fhv.itb13.sportify.shared.communication.dtos.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.rmi.RemoteException;
import java.util.List;


public class NewRosterFormController {


    @FXML
    private TableView<PersonDTO> _allMembersTableView;

    @FXML
    private TableColumn<PersonDTO, String> _allMembersFirstNameColumn;

    @FXML
    private TableColumn<PersonDTO, String> _allMembersLastNameColumn;

    @FXML
    private TableView<PersonDTO> _addedMembersTableView;

    @FXML
    private TableColumn<PersonDTO, String> _addedMembersFirstNameColumn;

    @FXML
    private TableColumn<PersonDTO, String> _addedMembersLastNameColumn;

    @FXML
    private Button _cancelButton;

    private ObservableList<PersonDTO> _addedMembersObservable = FXCollections.observableArrayList();

    private DisplayTeamDTO _team;

    private SimpleTournamentDTO _tournament;

    @FXML
    private void initialize() {

        //set values for allMembersTableView's columns
        _allMembersFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("FName"));
        _allMembersLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("LName"));

        //set values for addedMembersTableViews' columns
        _addedMembersFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("FName"));
        _addedMembersLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("LName"));

        _addedMembersTableView.setItems(_addedMembersObservable);

        setAllMembersTableViewData();

    }

    public void setDisplayTeamDTO(DisplayTeamDTO team) {
        _team = team;
    }

    public void setSimpleTournamentDTO(SimpleTournamentDTO tournamentDTO) {
        _tournament = tournamentDTO;
    }

    private void setAllMembersTableViewData() {
        //retrieve list of all members and set the list to the _allMembersTableView
        try {
            List<PersonDTO> allMembers = SessionController.getInstance().getSession().getPersonRemote().getAllPersons();

            if (allMembers != null) {
                //create an observableArrayList and fill it with all members
                ObservableList<PersonDTO> allMembersObservable = FXCollections.observableArrayList();
                allMembers.forEach(person -> allMembersObservable.add(person));
                _allMembersTableView.setItems(allMembersObservable);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void addMember() {
        switchMember(_allMembersTableView, _addedMembersTableView);
    }

    @FXML
    private void removeMember() {
        switchMember(_addedMembersTableView, _allMembersTableView);
    }

    @FXML
    private void removeAllMembers() {
        while (_addedMembersTableView.getItems().size() > 0) {
            PersonDTO personToSwitch = _addedMembersTableView.getItems().get(0);
            _addedMembersTableView.getItems().remove(personToSwitch);
            _allMembersTableView.getItems().add(personToSwitch);
        }
    }

    private void switchMember(TableView<PersonDTO> viewToRemoveFrom, TableView<PersonDTO> viewToAddTo) {
        if (viewToRemoveFrom.getSelectionModel().getSelectedItem() != null) {
            PersonDTO personToSwitch = viewToRemoveFrom.getSelectionModel().getSelectedItem();
            viewToRemoveFrom.getItems().remove(personToSwitch);
            viewToAddTo.getItems().add(personToSwitch);
        }
    }

    @FXML
    private void informMembers() {

        TournamentInvitationMessageDTO message = new TournamentInvitationMessageDTOImpl();
        message.setSimpleTournament(_tournament);
        message.setSender(SessionController.getInstance().getSession().getUserDTO());

        for (PersonDTO p : _addedMembersTableView.getItems()) {
            try {
                SessionController.getInstance().getSession().getMessageRemote().sendMessage(p, message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }


        }

    }


    private void initSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Information successful!");
        alert.setTitle("Information successful");
        alert.setContentText("All Members are now informed");
        alert.showAndWait();
    }

    @FXML
    private void cancelNewRoster() {
        SportifyGUI.getSharedMainApp().loadTeamDetailView(_team);
    }

}