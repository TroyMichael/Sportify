package at.fhv.itb13.sportify.client.presentation;


import at.fhv.itb13.sportify.client.communication.ServiceLocator;
import at.fhv.itb13.sportify.client.presentation.controller.*;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.DisplayTeamDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TournamentDTO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

public class SportifyGUI extends Application {

    private Stage _primaryStage;
    private BorderPane _rootLayout;
    private static SportifyGUI _sharedMainApp;
    private MainFrameController _mainFrameController;
    private String _userName;

    /**
     * Starts the GUI
     *
     * @param args - args from the main function
     */
    public static void run(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // set handler for uncaught exceptions
        Thread.currentThread().setUncaughtExceptionHandler((thread, throwable) -> {
            // unwrap exception
            Throwable e = throwable.getCause().getCause();
            if (e instanceof RemoteException) {
                ServiceLocator.getInstance().reload();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Server not reachable");
                alert.setTitle("Server not reachable");
                alert.setContentText("The server or your network connection may be down.");
                alert.showAndWait();
            } else {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("An error occurred");
                alert.setTitle("An error occurred");
                alert.setContentText("Please restart the application.");
                alert.showAndWait();
            }
        });

        _sharedMainApp = this;

        _primaryStage = primaryStage;
        _primaryStage.setTitle("Sportify");
        _primaryStage.setMinHeight(680);
        _primaryStage.setMinWidth(980);
        _primaryStage.setMaximized(true);
        _primaryStage.getIcons().add(new Image("iconSportify.png"));

        loadRootLayout();
        loadLoginWindow();
        _mainFrameController.setMenuBarDisable(true);
    }

    /**
     * This function loads the root layout of Sportify and sets it to the primary stage.
     */
    private void loadRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SportifyGUI.class.getResource("view/MainFrame.fxml"));
            _rootLayout = loader.load();

            Scene root = new Scene(_rootLayout);
            _primaryStage.setScene(root);
            _primaryStage.show();

            _mainFrameController = loader.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the specified view, makes it visibile in the primary stage through the
     * center pane of the given borderpane.
     *
     * @param viewURL the URL to the FXML File that contains the view
     * @param pane    is the pane where the FXML file is loaded into
     * @return the view's controller
     */
    private Object loadView(String viewURL, BorderPane pane) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SportifyGUI.class.getResource(viewURL));
            Node view = loader.load();
            pane.setCenter(view);
            return loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void loadNewMemberForm() {
        loadView("view/NewMemberForm.fxml", _rootLayout);
    }

    public void loadSearchMemberForm() {
        loadView("view/SearchMemberForm.fxml", _rootLayout);
    }

    public void loadSearchResultView(List<PersonDTO> results, String searchInput) {
        SearchResultViewController cont = (SearchResultViewController) loadView("view/SearchResultView.fxml", _rootLayout);
        cont.setResult(results);
        cont.setSearchInput(searchInput);
    }

    public void loadMemberDataView(PersonDTO person, BorderPane pane) {
        MemberDataController cont = (MemberDataController) loadView("view/MemberData.fxml", pane);
        cont.setPerson(person);
    }

    public void loadMemberDataView(PersonDTO person) {
        MemberDataController cont = (MemberDataController) loadView("view/MemberData.fxml", _rootLayout);
        cont.setPerson(person);
    }

    public void loadLoginWindow() {
        loadView("view/LoginWindow.fxml", _rootLayout);
    }

    public void loadHelloView(String username) {
        _userName = username;
        HelloUserViewController cont = (HelloUserViewController) loadView("view/HelloUserView.fxml", _rootLayout);
        cont.setUsername(_userName);
        _mainFrameController.setMenuBarDisable(false);
    }

    public void loadHelloView() {
        HelloUserViewController cont = (HelloUserViewController) loadView("view/HelloUserView.fxml", _rootLayout);
        cont.setUsername(_userName);
    }

    public void loadNewTeamForm() {
        loadView("view/NewTeamForm.fxml", _rootLayout);
    }

    public void loadTeamList() {
        loadView("view/TeamList.fxml", _rootLayout);
    }

    public void loadMemberList() {
        loadView("view/MemberList.fxml", _rootLayout);
    }

    public void loadTeamDetailView(DisplayTeamDTO teamToShow) {
        TeamDetailViewController cont = (TeamDetailViewController) loadView("view/TeamDetailView.fxml", _rootLayout);
        cont.setTeam(teamToShow);
    }

    public void loadEditTeamForm(DisplayTeamDTO team) {
        EditTeamFormController cont = (EditTeamFormController) loadView("view/EditTeamForm.fxml", _rootLayout);
        cont.setTeam(team);
    }

    public void loadNewTournamentView() {
        loadView("view/newTournamentForm.fxml", _rootLayout);
    }

    public void loadNewTournamentView(TournamentDTO tournament) {
        NewTournamentFormController cont = (NewTournamentFormController)loadView("view/NewTournamentForm.fxml", _rootLayout);
        cont.setTournament(tournament);
    }

    public static SportifyGUI getSharedMainApp() {
        return _sharedMainApp;
    }

    public void loadEditMemberData(PersonDTO person) {
        EditMemberDataController cont = (EditMemberDataController) loadView("view/EditMemberData.fxml", _rootLayout);
        cont.setPerson(person);
    }

    public void loadTournamentDataView(TournamentDTO tournamentDTO) {
        //TODO when implementing tournamentDetailController & View
    }

    public void loadNewMatchForm(TournamentDTO tournament) {
        NewMatchFormController cont = (NewMatchFormController)loadView("view/NewMatchForm.fxml", _rootLayout);
        cont.setTournament(tournament);
    }

    public void loadNewRosterForm(SimpleTournamentDTO simpleTournamentDTO){
        loadView("view/NewTournamentForm.fxml", new BorderPane());
    }
}