package at.fhv.itb13.sportify.client.presentation;


import at.fhv.itb13.sportify.client.communication.JMSCommunication;
import at.fhv.itb13.sportify.client.communication.ServiceLocator;
import at.fhv.itb13.sportify.client.presentation.controller.*;
import at.fhv.itb13.sportify.shared.communication.dtos.*;
import at.fhv.itb13.sportify.shared.communication.exceptions.NotAuthorizedException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.HashSet;
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
            // unwrap exceptions
            Throwable e = throwable.getCause().getCause();
            if (e instanceof RemoteException) {
                ServiceLocator.getInstance().reload();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Server not reachable");
                alert.setTitle("Server not reachable");
                alert.setContentText("The server or your network connection may be down.");
                alert.showAndWait();
            } else if (e instanceof NotAuthorizedException) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("User not authorized");
                alert.setTitle("User not authorized");
                alert.setContentText("The current user is not authorized to fulfill this action.");
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
        _primaryStage.setOnCloseRequest(e -> close());

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

        _mainFrameController.setMenuBarDisable(true);
        _mainFrameController.setLogoutButtonDisable(true);
    }

    public void loadHelloView(String username) {
        _userName = username;
        HelloUserViewController cont = (HelloUserViewController) loadView("view/HelloUserView.fxml", _rootLayout);
        cont.setUsername(_userName);
        _mainFrameController.setMenuBarDisable(false);
        _mainFrameController.setLogoutButtonDisable(false);

        startJMSCommunication();
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
        loadView("view/NewTournamentForm.fxml", _rootLayout);
    }

    public void loadNewTournamentView(TournamentDTO tournament, HashSet<ExternalDisplayTeamDTO> externalDisplayTeamDTOs) {
        NewTournamentFormController cont = (NewTournamentFormController) loadView("view/NewTournamentForm.fxml", _rootLayout);
        cont.setTournament(tournament, externalDisplayTeamDTOs);
    }

    public static SportifyGUI getSharedMainApp() {
        return _sharedMainApp;
    }

    public void loadEditMemberData(PersonDTO person) {
        EditMemberDataController cont = (EditMemberDataController) loadView("view/EditMemberData.fxml", _rootLayout);
        cont.setPerson(person);
    }

    public void loadTournamentListView() {
        loadView("view/TournamentList.fxml", _rootLayout);
    }

    public void loadNewTournamentView(TournamentDTO tournamentDTO) {
        NewTournamentFormController cont = (NewTournamentFormController) loadView("view/NewTournamentForm.fxml", _rootLayout);
        cont.setTournament(tournamentDTO);
    }

    public void loadNewMatchForm(TournamentDTO tournament, HashSet<ExternalDisplayTeamDTO> externalTeams, Boolean newTournament) {
        NewMatchFormController cont = (NewMatchFormController) loadView("view/NewMatchForm.fxml", _rootLayout);
        cont.setTournament(tournament, externalTeams, newTournament);
    }

    public void loadNewRosterForm(SimpleTournamentDTO simpleTournamentDTO, DisplayTeamDTO displayTeamDTO) {
        NewRosterFormController cont = (NewRosterFormController) loadView("view/NewRosterForm.fxml", _rootLayout);
        cont.setDisplayTeamDTO(displayTeamDTO);
        cont.setSimpleTournamentDTO(simpleTournamentDTO);
    }

    /*
    This Method starts a Thread that asks the users queue constantly if there are new Messages.
    If yes, it will show an Alert.
     */
    private void startJMSCommunication() {
        JMSCommunication jmsComThread = new JMSCommunication(_primaryStage, _userName);
        new Thread(jmsComThread).start();
    }

    public void loadTournamentDetailView(SimpleTournamentDTO tournamentToShow) {
        TournamentDetailFormController cont = (TournamentDetailFormController) loadView("view/TournamentDetailForm.fxml", _rootLayout);
        cont.setTournament(tournamentToShow);
    }

    public void loadEditTournamentForm(TournamentDTO tournamentToEdit) {
        EditTournamentFormController cont = (EditTournamentFormController) loadView("view/EditTournamentForm.fxml", _rootLayout);
        cont.setTournament(tournamentToEdit);
    }

    private void close() {
        Platform.exit();
        System.exit(0);
    }

    public void loadEditMatchForm(MatchDTO matchDTO, TournamentDTO tournamentDTO) {
        EditMatchFormController cont = (EditMatchFormController) loadView("view/EditMatchForm.fxml", _rootLayout);
        cont.setMatchDTO(matchDTO);
        cont.setTournamentDTO(tournamentDTO);
    }
}