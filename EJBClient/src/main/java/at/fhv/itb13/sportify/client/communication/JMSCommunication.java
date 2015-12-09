package at.fhv.itb13.sportify.client.communication;

import at.fhv.itb13.sportify.client.application.SessionController;
import at.fhv.itb13.sportify.shared.communication.dtos.TournamentInvResponseMessageDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TournamentInvResponseMessageDTOImpl;
import at.fhv.itb13.sportify.shared.communication.dtos.TournamentInvitationMessageDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TournamentInvitationMessageDTOImpl;
import at.fhv.itb13.sportify.shared.communication.remote.ejb.MessageRemote;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.Serializable;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Michael on 28.11.2015.
 */
public class JMSCommunication implements Runnable {

    private Stage _primaryStage;
    private String _userName;

    public JMSCommunication(Stage primaryStage, String userName) {
        _primaryStage = primaryStage;
        _userName = userName;
    }

    public void run() {
        //create TimerTask that asks for new Messages
        TimerTask askForMessages = new TimerTask() {
            @Override
            public void run() {
                //get message
                final Serializable message = ServiceLocator.getInstance().getRemote(MessageRemote.class).getMessage(_userName);

                if (message != null) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            //check what kind of message it is and start respective alert
                            if (message instanceof TournamentInvitationMessageDTOImpl) {
                                TournamentInvitationMessageDTO invMessage = (TournamentInvitationMessageDTOImpl) message;
                                createRosterInvitationAlert(invMessage);
                            } else if (message instanceof TournamentInvResponseMessageDTOImpl) {
                                TournamentInvResponseMessageDTO respMessage = (TournamentInvResponseMessageDTOImpl) message;
                                createInvitationResponseAlert(respMessage);
                            }
                        }
                    });
                }
            }
        };

        //create timer which runs TimerTasks
        Timer timer = new Timer();

        //start timer at fixed rate - every 30s
        //scheduleAtFixedRate(TimerTask task, Long delay, Long interval)
        timer.scheduleAtFixedRate(askForMessages, 0, 30000);
    }

    private void createRosterInvitationAlert(TournamentInvitationMessageDTO invMessage) {
        //set Alert type and contents
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Roster Invitation");
        alert.setHeaderText("Invitation to play in a Tournament");
        alert.setContentText("You have been invited to play in the Tournament " + invMessage.getSimpleTournament().getDescription() +
                " in " + invMessage.getSimpleTournament().getLocation() +
                " on the " + invMessage.getSimpleTournament().getStartDate().toString() + ".\n\n" +
                "Do you want to participate?");

        //set two options and add them to the alert
        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        alert.initOwner(_primaryStage);

        //with alert.showAndWait the alert window is shown. It returns whether the user pressed the Yes or No Button
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {

            TournamentInvResponseMessageDTO respMessage = new TournamentInvResponseMessageDTOImpl();
            respMessage.setSimpleTournamentDTO(invMessage.getSimpleTournament());
            if (result.get() == buttonTypeYes) {
                respMessage.accept(true);
            } else {
                respMessage.accept(false);
            }

            respMessage.setSender(SessionController.getInstance().getSession().getUserDTO().getName());
            ServiceLocator.getInstance().getRemote(MessageRemote.class).sendMessage(invMessage.getSender(), respMessage);
        }
    }

    private void createInvitationResponseAlert(TournamentInvResponseMessageDTO respMessage) {
        Alert invResponseAlert = new Alert(Alert.AlertType.INFORMATION);
        invResponseAlert.setTitle("Response to Roster Invitation");
        invResponseAlert.setHeaderText("Response from " + respMessage.getSender());

        String isPlaying;
        if (respMessage.isAccepted()) {
            isPlaying = "accepted";
        } else {
            isPlaying = "declined";
        }

        invResponseAlert.setContentText(respMessage.getSender() + " has " + isPlaying +
                " to play in " + respMessage.getSimpleTournamentDTO().getDescription() + ".");

        invResponseAlert.initOwner(_primaryStage);
        invResponseAlert.showAndWait();
    }

}