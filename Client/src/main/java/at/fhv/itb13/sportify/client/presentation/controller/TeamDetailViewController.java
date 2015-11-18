package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.client.presentation.SportifyGUI;
import at.fhv.itb13.sportify.shared.communication.dtos.SimplePersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TeamDetailDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.HashSet;

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

    private TeamDetailDTO _team;

    private ObservableList<SimplePersonDTO> _memberList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {

        //set values of each column
        _firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("FName"));
        _lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("LName"));
        _birthdateColumn.setCellValueFactory(new PropertyValueFactory<>("BirthDate"));
        _emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
        _feeColumn.setCellValueFactory(new PropertyValueFactory<>("Payed"));

    }

    @FXML
    private void editTeam() {
        SportifyGUI.getSharedMainApp().loadEditTeamForm(_team);
    }

    @FXML
    private void back() {
        SportifyGUI.getSharedMainApp().loadTeamList();
    }

    public void setTeam(TeamDetailDTO teamToShow) {
        _team = teamToShow;
        _teamNameLabel.setText(_team.getName());
        _trainerNameLabel.setText(_team.getTrainer().getFName() + " " + _team.getTrainer().getLName());
        _sportLabel.setText(_team.getSport().getName());

        HashSet<SimplePersonDTO> _tempList = _team.getMembers();
        _tempList.forEach(person -> _memberList.add(person));
        _membersTableView.setItems(_memberList);
    }
}
