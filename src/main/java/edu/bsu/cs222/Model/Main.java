package edu.bsu.cs222.Model;
import java.util.List;

public class Main{
    public List<String> getTeams() throws Exception {
        return TeamList.getNewListOfTeams().createFullListOfTeams();
    }

    public String getTeamId(String s) throws Exception {
        return TeamList.getNewListOfTeams().getTeamId(s);
    }
    public String getTeamUrlName(String teamId) throws Exception {
        return TeamList.getNewListOfTeams().getTeamUrlName(teamId);
    }

    public String changeToDate(String year){
        String date = null ;
        switch (year) {
            case "2019":
                date = "current";
                break;
            case "2017":
                date = "20180416";
                break;
            case "2016":
                date = "20170413";
                break;
            case "2015":
                date = "20160413";
                break;
            case "2014":
                date = "20150413";
                break;
        }
        return date;
        }

    }

