package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.client.communication.ServiceLocator;
import at.fhv.itb13.sportify.client.presentation.SportifyGUI;
import at.fhv.itb13.sportify.shared.communication.dtos.SimpleTournamentDTO;
import at.fhv.itb13.sportify.shared.communication.remote.ejb.SessionRemote;
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
 * Created by KYUSS on hütt.
 */
public class TournamentListController {

    @FXML
    private TextField _filterTextField;

    @FXML
    private TableView<SimpleTournamentDTO> _tournamentTableView;

    @FXML
    private TableColumn<SimpleTournamentDTO, String> _descriptionColumn;

    @FXML
    private TableColumn<SimpleTournamentDTO, String> _sportColumn;

    @FXML
    private TableColumn<SimpleTournamentDTO, String> _startDateColumn;

    @FXML
    private TableColumn<SimpleTournamentDTO, String> _locationColumn;

    private ObservableList<SimpleTournamentDTO> _tournamentList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {

        //set column values
        _descriptionColumn.setCellValueFactory(new PropertyValueFactory<SimpleTournamentDTO, String>("Description"));
        _sportColumn.setCellValueFactory(new PropertyValueFactory<SimpleTournamentDTO, String>("Sport"));
        _startDateColumn.setCellValueFactory(new PropertyValueFactory<SimpleTournamentDTO, String>("StartDate"));
        _locationColumn.setCellValueFactory(new PropertyValueFactory<SimpleTournamentDTO, String>("Location"));

        getAndAddDataToTournamentList();
        setDoubleClickOnTournamentTableView();
        setFilterProcess();
    }

    /*
    defines that when a doubleclick occurs on a row in TournamentTableView the given TournamentDTO is loaded in detail
     */
    private void setDoubleClickOnTournamentTableView() {
        //set doubleclick handling of tableViewRow
        _tournamentTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    if (_tournamentTableView.getSelectionModel().getSelectedItem() != null) {
                        SimpleTournamentDTO tournamentToShow = _tournamentTableView.getSelectionModel().getSelectedItem();
                        SportifyGUI.getSharedMainApp().loadTournamentDetailView(tournamentToShow);
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
        final FilteredList<SimpleTournamentDTO> _filteredTournamentDTOList = new FilteredList<>(_tournamentList, new Predicate<SimpleTournamentDTO>() {
            @Override
            public boolean test(SimpleTournamentDTO simpleTournamentDTO) {
                return true;
            }
        });

        //set changeListener to textfield
        _filterTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, final String newValue) {
                _filteredTournamentDTOList.setPredicate(new Predicate<SimpleTournamentDTO>() {
                    @Override
                    public boolean test(SimpleTournamentDTO tournamentDTO) {
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
                    }
                });
            }
        });

        //FilteredList cannot be modified -> not sortable
        //wrap filteredList in sortedList
        SortedList<SimpleTournamentDTO> sortedTournamentList = new SortedList<>(_filteredTournamentDTOList);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedTournamentList.comparatorProperty().bind(_tournamentTableView.comparatorProperty());

        //set sortedList as items to TournamentTableView
        _tournamentTableView.setItems(sortedTournamentList);
    }

    /*
    Gets all tournaments from the database and adds them to _tournamentList
     */
    private void getAndAddDataToTournamentList() {
        List<SimpleTournamentDTO> tempTournamentList = ServiceLocator.getInstance().getRemote(SessionRemote.class).getTournamentRemote().getAllSimpleTournaments();
        for (SimpleTournamentDTO tournamentDTO : tempTournamentList) {
            _tournamentList.add(tournamentDTO);
        }
    }

    @FXML
    private void newTournament() {
        SportifyGUI.getSharedMainApp().loadNewTournamentView();
    }
}