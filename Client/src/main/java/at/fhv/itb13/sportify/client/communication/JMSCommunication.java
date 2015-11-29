package at.fhv.itb13.sportify.client.communication;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Michael on 28.11.2015.
 *
 */
public class JMSCommunication extends Thread {

    private Stage _primaryStage;

    public JMSCommunication(Stage primaryStage) {
        _primaryStage = primaryStage;
    }

    public void run() {

        //create TimerTask that asks for new Messages
        TimerTask askForMessages = new TimerTask() {
            @Override
            public void run() {
                //ask for messages
                //check what message it is and start respective alert
            }
        };

        //create timer which runs TimerTasks
        Timer timer = new Timer();

        //start timer at fixed rate
        //scheduleAtFixedRate(TimerTask task, Long delay, Long interval)
        timer.scheduleAtFixedRate(askForMessages, 0, 30000);

    }

    private void createRosterInvitationAlert() {

        //set Alert type and contents
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Roster Invitation");
        alert.setHeaderText("Invitation to play in a Tournament");
        alert.setContentText("You have been invited to play in the Tournament TournamentName at TournamentLocation on the TournamentDate.\n" +
                "Do you want to participate?");

        //set two options and add them to the alert
        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        alert.initOwner(_primaryStage);

        //with alert.showAndWait the alert window is shown. It returns whether the user pressed the Yes or No Button
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == buttonTypeYes) {
                //create yes message for trainer
                System.out.println("yes");
            } else if (result.get() == buttonTypeNo) {
                //create no message for trainer
                System.out.println("no");
            }
        }
    }

    private void createInvitationResponseAlert() {
        Alert invResponseAlert = new Alert(Alert.AlertType.INFORMATION);
        invResponseAlert.setTitle("Response to Roster Invitation");
        invResponseAlert.setHeaderText("Response from PlayerName");
        invResponseAlert.setContentText("PlayerName has accepted/declined to play in TournamentName.");

        invResponseAlert.initOwner(_primaryStage);
        invResponseAlert.showAndWait();
    }
}