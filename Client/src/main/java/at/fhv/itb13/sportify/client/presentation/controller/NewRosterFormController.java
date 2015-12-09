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
import java.util.HashSet;


public class NewRosterFormController {


    @FXML
    private TableView<SimplePersonDTO> _allMembersTableView;

    @FXML
    private TableColumn<SimplePersonDTO, String> _allMembersFirstNameColumn;

    @FXML
    private TableColumn<SimplePersonDTO, String> _allMembersLastNameColumn;

    @FXML
    private TableView<SimplePersonDTO> _addedMembersTableView;

    @FXML
    private TableColumn<SimplePersonDTO, String> _addedMembersFirstNameColumn;

    @FXML
    private TableColumn<SimplePersonDTO, String> _addedMembersLastNameColumn;

    @FXML
    private Button _cancelButton;

    private ObservableList<SimplePersonDTO> _addedMembersObservable = FXCollections.observableArrayList();

    private DisplayTeamDTO _team;

    private SimpleTournamentDTO _tournament;

    @FXML
    private void initialize() {

        //set values for allMembersTableView's columns
        _allMembersFirstNameColumn.setCellValueFactory(new PropertyValueFactory<SimplePersonDTO, String>("FName"));
        _allMembersLastNameColumn.setCellValueFactory(new PropertyValueFactory<SimplePersonDTO, String>("LName"));

        //set values for addedMembersTableViews' columns
        _addedMembersFirstNameColumn.setCellValueFactory(new PropertyValueFactory<SimplePersonDTO, String>("FName"));
        _addedMembersLastNameColumn.setCellValueFactory(new PropertyValueFactory<SimplePersonDTO, String>("LName"));

        _addedMembersTableView.setItems(_addedMembersObservable);



    }

    public void setDisplayTeamDTO(DisplayTeamDTO team) {
        _team = team;
        setAllMembersTableViewData();
    }

    public void setSimpleTournamentDTO(SimpleTournamentDTO tournamentDTO) {
        _tournament = tournamentDTO;
    }

    private void setAllMembersTableViewData() {
        //retrieve list of all members and set the list to the _allMembersTableView

        HashSet<SimplePersonDTO> allMembers = _team.getMembers();

        if (allMembers != null) {
            //create an observableArrayList and fill it with all members
            ObservableList<SimplePersonDTO> allMembersObservable = FXCollections.observableArrayList();
            allMembersObservable.addAll(allMembers);
            _allMembersTableView.setItems(allMembersObservable);
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
            SimplePersonDTO personToSwitch = _addedMembersTableView.getItems().get(0);
            _addedMembersTableView.getItems().remove(personToSwitch);
            _allMembersTableView.getItems().add(personToSwitch);
        }
    }

    private void switchMember(TableView<SimplePersonDTO> viewToRemoveFrom, TableView<SimplePersonDTO> viewToAddTo) {
        if (viewToRemoveFrom.getSelectionModel().getSelectedItem() != null) {
            SimplePersonDTO personToSwitch = viewToRemoveFrom.getSelectionModel().getSelectedItem();
            viewToRemoveFrom.getItems().remove(personToSwitch);
            viewToAddTo.getItems().add(personToSwitch);
        }
    }

    @FXML
    private void informMembers() throws RemoteException {

        TournamentInvitationMessageDTO message = new TournamentInvitationMessageDTOImpl();
        message.setSimpleTournament(_tournament);
        message.setSender(SessionController.getInstance().getSession().getUserDTO().getName());

        for (SimplePersonDTO p : _addedMembersTableView.getItems()) {
            try {
                SessionController.getInstance().getSession().getMessageRemote().sendMessage(p.getUserName(), message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        initSuccessAlert();
        cancelNewRoster();

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