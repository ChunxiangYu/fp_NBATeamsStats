package edu.bsu.CS222;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Final Project");
        BorderPane pane = new BorderPane();
        VBox statsBox = new VBox();
        VBox compareBox = new VBox();
        statsBox.setSpacing(10);
        compareBox.setSpacing(10);
        Label yearLabel = new Label(" Please enter a year");
        yearLabel.setFont(new Font(18));
        TextField yearTextField = new TextField();
        yearTextField.setMaxSize(100,2);
        Label statsLabel = new Label(" Please select a team to view the stats:");
        statsLabel.setFont(new Font(18));
        Label compareLabel = new Label(" Compared with");
        compareLabel.setFont(new Font(18));
        Label compareResultLabel = new Label();
        compareResultLabel.setFont(new Font(18));
        Label winsLabel = new Label();
        Label lossesLabel = new Label();
        Label IdLabel = new Label();
        final ComboBox compareComboBox = new ComboBox();
        compareComboBox.getItems().addAll(
                FXCollections.observableList(getTeams())
        );
        compareComboBox.valueProperty().addListener((ChangeListener<String>) (ov, t, t1) -> {
            try {
                int year = Integer.parseInt(yearTextField.getText());
                String date = changeToDate(year);
                TeamStat teamStat = TeamParser.parseRequest(HttpRequest.getTeamStats(date)).addId(getTeamId(t1)).parse();
                compareResultLabel.setText(String.valueOf(teamStat));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        statsBox.getChildren().addAll(yearLabel,yearTextField,statsLabel,winsLabel, lossesLabel, IdLabel);
        compareBox.getChildren().addAll(compareLabel,compareComboBox,compareResultLabel);
        ObservableList<String> teamNames = FXCollections.observableList(getTeams());
        ListView<String> list = new ListView<>(teamNames);
        list.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            try {
                int year = Integer.parseInt(yearTextField.getText());
                String date = changeToDate(year);
                TeamStat teamStat = TeamParser.parseRequest(HttpRequest.getTeamStats(date)).addId(getTeamId(t1)).parse();
                statsLabel.setText(String.valueOf(teamStat));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        pane.setLeft(list);
        pane.setCenter(statsBox);
        pane.setRight(compareBox);
        Scene scene = new Scene(pane,1000, 500);
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
    public String changeToDate(int year){
        String date = null ;
        switch (year) {
            case 2019:
                date = "current";
                break;
            case 2017:
                date = "20180416";
                break;
            case 2016:
                date = "20170413";
                break;
            case 2015:
                date = "20160413";
                break;
            case 2014:
                date = "20150413";
                break;
        }
        return date;
        }

    }

