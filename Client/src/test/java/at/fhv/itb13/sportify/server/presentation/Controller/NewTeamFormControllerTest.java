package at.fhv.itb13.sportify.server.presentation.Controller;

/**
 * Created by Michael on 18.11.2015.
 *
 */

import at.fhv.itb13.sportify.client.presentation.controller.NewTeamFormController;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTOImpl;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Method;

@RunWith(PowerMockRunner.class)
@PrepareForTest(NewTeamFormController.class)
public class NewTeamFormControllerTest {

    private NewTeamFormController _controller;

    public static class AsNonApp extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {
            // this is needed so that a JavaFX thread is started
        }
    }

    @BeforeClass
    public static void setUpClass() throws InterruptedException {

        Thread javaFX = new Thread("JavaFX Init Thread") {
            public void run () {
                Application.launch(AsNonApp.class);
            }
        };
        javaFX.setDaemon(true);
        javaFX.start();
    }

    @Before
    public void setUp() {
        _controller = new NewTeamFormController();
    }

    @Test
    public void switchMember() {

        //setup
        TableView<PersonDTO> _allMemberTableView = new TableView<>();
        TableView<PersonDTO> _addedMemberTableView = new TableView<>();

        ObservableList<PersonDTO> _allMemberList = FXCollections.observableArrayList();
        ObservableList<PersonDTO> _addedMemberList = FXCollections.observableArrayList();

        PersonDTO p1 = new PersonDTOImpl();
        PersonDTO p2 = new PersonDTOImpl();
        _allMemberList.add(p1);
        _allMemberList.add(p2);

        _allMemberTableView.setItems(_allMemberList);
        _addedMemberTableView.setItems(_addedMemberList);

        //select first entry, as the switchMember method tests if something is selected
        _allMemberTableView.getSelectionModel().select(0);

        //act
        try {
            Method switchMemberMethod = NewTeamFormController.class.getDeclaredMethod("switchMember", TableView.class, TableView.class);
            switchMemberMethod.setAccessible(true);
            switchMemberMethod.invoke(new NewTeamFormController(), _allMemberTableView, _addedMemberTableView);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //assert
        assert(_allMemberTableView.getItems().size() == 1);
        assert(_addedMemberTableView.getItems().size() == 1);
        assert(_addedMemberTableView.getItems().get(0) == p1);
    }


    @Test
    public void removeAllMembers() {
        //setup
        TableView<PersonDTO> _allMemberTableView = new TableView<>();
        TableView<PersonDTO> _addedMemberTableView = new TableView<>();

        ObservableList<PersonDTO> _allMemberList = FXCollections.observableArrayList();
        ObservableList<PersonDTO> _addedMemberList = FXCollections.observableArrayList();

        PersonDTO p1 = new PersonDTOImpl();
        PersonDTO p2 = new PersonDTOImpl();
        _addedMemberList.add(p1);
        _addedMemberList.add(p2);

        _allMemberTableView.setItems(_allMemberList);
        _addedMemberTableView.setItems(_addedMemberList);

        //act
        try {
            Method switchMemberMethod = NewTeamFormController.class.getDeclaredMethod("removeAllMembers", TableView.class, TableView.class);
            switchMemberMethod.setAccessible(true);
            switchMemberMethod.invoke(new NewTeamFormController(), _addedMemberTableView, _allMemberTableView);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //asserts
        assert(_addedMemberTableView.getItems().size() == 0);
        assert(_allMemberTableView.getItems().size() == 2);
    }


}