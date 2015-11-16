package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.client.application.SessionController;
import at.fhv.itb13.sportify.shared.communication.dtos.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.rmi.RemoteException;
import java.util.List;


/**
 * Created by Michael on 15.11.2015.
 *
 */
public class TeamListController {

    @FXML
    private TextField _filterTextField;

    @FXML
    private TableView<TeamDetailDTO> _teamTableView;

    @FXML
    private TableColumn<TeamDetailDTO, String> _nameColumn;

    @FXML
    private TableColumn<TeamDetailDTO, String> _sportColumn;

    @FXML
    private TableColumn<TeamDetailDTO, String> _trainerColumn;

    private ObservableList<TeamDetailDTO> _teams = FXCollections.observableArrayList();

    @FXML
    private void initialize() {

        _nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        _sportColumn.setCellValueFactory(new PropertyValueFactory<>("Sport"));
        _trainerColumn.setCellValueFactory(new PropertyValueFactory<>("Trainer"));

        try {
            List<TeamDetailDTO> tempTeamList = SessionController.getInstance().getSession().getTeamDetailRemote().getAllTeams();
            tempTeamList.forEach(team -> _teams.add(team));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        _teamTableView.setItems(_teams);

    }
}