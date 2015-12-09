package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.client.communication.ServiceLocator;
import at.fhv.itb13.sportify.client.presentation.SportifyGUI;
import at.fhv.itb13.sportify.shared.communication.dtos.DisplayTeamDTO;
import at.fhv.itb13.sportify.shared.communication.remote.ejb.TeamRemote;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

import java.util.List;
import java.util.function.Predicate;


/**
 * Created by Michael on 15.11.2015.
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

        _nameColumn.setCellValueFactory(new PropertyValueFactory<DisplayTeamDTO, String>("Name"));
        _sportColumn.setCellValueFactory(new PropertyValueFactory<DisplayTeamDTO, String>("Sport"));
        _trainerColumn.setCellValueFactory(new PropertyValueFactory<DisplayTeamDTO, String>("Trainer"));

        List<DisplayTeamDTO> tempTeamList = ServiceLocator.getInstance().getRemote(TeamRemote.class).getAllDisplayTeams();
        for (DisplayTeamDTO team : tempTeamList) {
            _teams.add(team);
        }

        setFilter();
        setDoubleClickOnMemberTableView();
    }

    private void setFilter() {
        //filtering-process taken from: http://code.makery.ch/blog/javafx-8-tableview-sorting-filtering/

        //wrap observableList into filter list
        //p -> true shows all teams
        final FilteredList<DisplayTeamDTO> _filteredTeamList = new FilteredList<>(_teams, new Predicate<DisplayTeamDTO>() {
            @Override
            public boolean test(DisplayTeamDTO displayTeamDTO) {
                return true;
            }
        });

        //set changeListener to textfield
        _filterTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, final String newValue) {
                _filteredTeamList.setPredicate(new Predicate<DisplayTeamDTO>() {
                    @Override
                    public boolean test(DisplayTeamDTO team) {
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
                    }
                });
            }
        });

        //FilteredList cannot be modified -> not sortable
        //wrap filteredList in sortedList
        SortedList<DisplayTeamDTO> sortedTeamList = new SortedList<>(_filteredTeamList);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedTeamList.comparatorProperty().

                bind(_teamTableView.comparatorProperty()

                );

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