package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.client.application.SessionController;
import at.fhv.itb13.sportify.client.presentation.SportifyGUI;
import at.fhv.itb13.sportify.shared.communication.dtos.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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
    private TableView<DisplayTeamDTO> _teamTableView;

    @FXML
    private TableColumn<DisplayTeamDTO, String> _nameColumn;

    @FXML
    private TableColumn<DisplayTeamDTO, String> _sportColumn;

    @FXML
    private TableColumn<DisplayTeamDTO, String> _trainerColumn;

    private ObservableList<DisplayTeamDTO> _teams = FXCollections.observableArrayList();

    @FXML
    private void initialize() {

        _nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        _sportColumn.setCellValueFactory(new PropertyValueFactory<>("Sport"));
        _trainerColumn.setCellValueFactory(new PropertyValueFactory<>("Trainer"));

        try {
            List<DisplayTeamDTO> tempTeamList = SessionController.getInstance().getSession().getTeamDetailRemote().getAllTeams();
            tempTeamList.forEach(team -> _teams.add(team));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        setFilter();
        setDoubleClickOnMemberTableView();
    }

    private void setFilter() {
        //filtering-process taken from: http://code.makery.ch/blog/javafx-8-tableview-sorting-filtering/

        //wrap observableList into filter list
        //p -> true shows all teams
        FilteredList<DisplayTeamDTO> _filteredTeamList = new FilteredList<>(_teams, p -> true);

        //set changeListener to textfield
        _filterTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            _filteredTeamList.setPredicate(team -> {

                //define here all rules of filtering and what should be searched and filtered

                //if textfield is empty/null show all teams
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                //else compare the filter string to the different columns
                String filterString = _filterTextField.getText().toLowerCase();

                if (team.getName().toLowerCase().contains(filterString)) {
                    return true;
                } else if (team.getSport().getName().toLowerCase().contains(filterString)) {
                    return true;
                } else if ((team.getTrainer().getFName() + " " + team.getTrainer().getLName()).toLowerCase().contains(filterString)) {
                    return true;
                }
                //filter more attributes if wanted

                //if nothing matches, return false, so that that person won't be shown in the list
                return false;
            });
        });

        //FilteredList cannot be modified -> not sortable
        //wrap filteredList in sortedList
        SortedList<DisplayTeamDTO> sortedTeamList = new SortedList<>(_filteredTeamList);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedTeamList.comparatorProperty().bind(_teamTableView.comparatorProperty());

        //set sortedList as items to teamTableView
        _teamTableView.setItems(sortedTeamList);
    }

    private void setDoubleClickOnMemberTableView() {
        //set doubleclick handling of tableViewRow
        _teamTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    if (_teamTableView.getSelectionModel().getSelectedItem() != null) {
                        SportifyGUI.getSharedMainApp().loadTeamDetailView(_teamTableView.getSelectionModel().getSelectedItem());
                    }
                }
            }
        });
    }
}