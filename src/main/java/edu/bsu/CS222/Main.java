package edu.bsu.CS222;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Final Project");
        BorderPane pane = new BorderPane();
        VBox vbox = new VBox();
        vbox.setSpacing(10);

        Label statsLabel = new Label(" Please select a team to view the stats");
        statsLabel.setFont(new Font("Arial", 24));
        Label winsLabel = new Label();
        Label lossesLabel = new Label();
        Label IdLabel = new Label();

        vbox.getChildren().addAll(statsLabel,winsLabel, lossesLabel, IdLabel);

        ObservableList<String> teamNames = FXCollections.observableList(getTeams());
        ListView<String> list = new ListView<>(teamNames);
        list.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            try {
                TeamStat teamStat = TeamParser.parseRequest(HttpRequest.getTeamStats()).addId(getTeamId(t1)).parse();
                statsLabel.setText(String.valueOf(teamStat));
                winsLabel.setText(String.valueOf(teamStat.getWins()));
                lossesLabel.setText(String.valueOf(teamStat.getLosses()));
                IdLabel.setText(String.valueOf(teamStat.getId()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        pane.setLeft(list);
        pane.setCenter(vbox);
        Scene scene = new Scene(pane,600, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    public List<String> getTeams() throws Exception {
        return TeamList.getNewListOfTeams().createFullListOfTeams();
    }

    public String getTeamId(String s) throws Exception {
        return TeamList.getNewListOfTeams().getTeamId(s);
    }
}
