package edu.bsu.cs222.View;

import edu.bsu.cs222.Model.*;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.List;

public class NBATeamsStatesProject extends Application {
        Main main = new Main();
        @Override
        public void start(Stage primaryStage) throws Exception {
            primaryStage.setTitle("Final Project");
            BorderPane pane = new BorderPane();
            VBox yearBox = new VBox();
            VBox compareBox = new VBox();
            VBox statsBox = new VBox();
            yearBox.setSpacing(10);
            yearBox.setAlignment(Pos.TOP_CENTER);
            statsBox.setSpacing(10);
            statsBox.setAlignment(Pos.TOP_CENTER);
            compareBox.setSpacing(10);
            compareBox.setAlignment(Pos.TOP_CENTER);

            Label yearLabel = new Label("First, select a season year");
            yearLabel.setFont(new Font(18));
            Label seasonYearLabel = new Label();
            Label teamIdLabel = new Label();
            Label compareTeamIdLabel = new Label();
            Label scheduleLabel = new Label();

            Label statesLabel = new Label("Second, select a team to view the stats:");
            statesLabel.setFont(new Font(18));
            Label compareLabel = new Label("Third, compared with another team");
            compareLabel.setFont(new Font(18));

            Label statsLabel = new Label();
            statsLabel.setFont(new Font(18));
            Label compareTeamLabel = new Label();
            compareTeamLabel.setFont(new Font(18));

            final ComboBox compareTeamComboBox = new ComboBox();
            final ComboBox yearComboBox = new ComboBox();
            yearComboBox.setItems(FXCollections.observableArrayList(
                    "2019", "2017","2016","2015")
            );
            yearComboBox.getSelectionModel().selectedItemProperty().addListener((ChangeListener<String>) (ov, t, t1) ->  {
                seasonYearLabel.setText((String) yearComboBox.getValue());
            });
            final ComboBox teamListComboBox = new ComboBox();
            teamListComboBox.getItems().addAll(
                    FXCollections.observableList(main.getTeams())
            );
            teamListComboBox.valueProperty().addListener((ChangeListener<String>) (ov, t, t1) -> {
                try {
                    String date = main.changeToDate(seasonYearLabel.getText());
                    TeamStat teamStat = TeamParser.parseRequest(URLCreator.getTeamStats(date)).addId(main.getTeamId(t1)).parse();
                    teamIdLabel.setText(main.getTeamId(t1));
                    statsLabel.setText(String.valueOf(teamStat));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            compareTeamComboBox.getItems().addAll(
                    FXCollections.observableList(main.getTeams())
            );
            compareTeamComboBox.valueProperty().addListener((ChangeListener<String>) (ov, t, t1) -> {
                try {
                    String date = main.changeToDate(seasonYearLabel.getText());
                    TeamStat teamStat = TeamParser.parseRequest(URLCreator.getTeamStats(date)).addId(main.getTeamId(t1)).parse();
                    compareTeamIdLabel.setText(main.getTeamId(t1));
                    compareTeamLabel.setText(String.valueOf(teamStat));
                    Object urlName = TeamParser.parseRequest(URLCreator.getTeamList()).addId(main.getTeamId(t1)).parseUrlName();
                    TeamStat teamStat1 = TeamParser.parseRequest(URLCreator.getTeamSchedule(seasonYearLabel.getText(), (String) urlName))


                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            yearBox.getChildren().addAll(yearLabel,yearComboBox);
            statsBox.getChildren().addAll(statesLabel,teamListComboBox,statsLabel);
            compareBox.getChildren().addAll(compareLabel,compareTeamComboBox,compareTeamLabel,scheduleLabel);
            pane.setLeft(yearBox);
            pane.setCenter(statsBox);
            pane.setRight(compareBox);
            Scene scene = new Scene(pane,1000, 500);
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }

    }


