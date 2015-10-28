package at.fhv.itb13.sportify.presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

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
        _sharedMainApp = this;

        _primaryStage = primaryStage;
        primaryStage.setTitle("Sportify");
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
            loader.setLocation(SportifyGUI.class.getResource("View/MainFrame.fxml"));
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
     * center pane of the rootlayout.
     *
     * @param viewURL the URL to the FXML File that contains the view
     * @return the view's controller
     */
    private Object loadView(String viewURL) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SportifyGUI.class.getResource(viewURL));
            Node view = loader.load();

            _rootLayout.setCenter(view);

            return loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void loadNewMemberForm() {
        loadView("View/NewMemberForm.fxml");
    }

    public static SportifyGUI getSharedMainApp() {
        return _sharedMainApp;
    }
}