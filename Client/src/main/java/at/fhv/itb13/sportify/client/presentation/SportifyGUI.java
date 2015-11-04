package at.fhv.itb13.sportify.client.presentation;


import at.fhv.itb13.sportify.client.communication.ServiceLocator;
import at.fhv.itb13.sportify.client.presentation.controller.MemberDataController;
import at.fhv.itb13.sportify.client.presentation.controller.SearchResultViewController;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

public class SportifyGUI extends Application {

    private Stage _primaryStage;
    private BorderPane _rootLayout;
    private static SportifyGUI _sharedMainApp;

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

        loadRootLayout();
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

    public static SportifyGUI getSharedMainApp() {
        return _sharedMainApp;
    }
}