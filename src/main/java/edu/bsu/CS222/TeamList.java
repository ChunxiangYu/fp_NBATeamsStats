package edu.bsu.CS222;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TeamList {
    private InputStream in;

    private TeamList() throws Exception {
        in = HttpRequest.getTeamList();
    }

    public static TeamList getNewListOfTeams() throws Exception {
        return new TeamList();
    }

    public List<String> createFullListOfTeams() {

        ArrayList<String> teamList = new ArrayList<>();
        JSONArray teamArray = getTeamList();

        for (int i = 0; i < teamArray.size(); i++) {
            teamList.add(teamArray.get(i).toString());
        }
        return teamList;
    }

    private JSONArray getTeamList() {
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(in, "UTF-8");
        return JsonPath.read(document, "$..standard[?(@.isNBAFranchise==true)].fullName");
    }

    public String getTeamId(String teamName) {
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(in, "UTF-8");
        List<String> id = JsonPath.read(document, "$..standard[?(@.fullName=="+ teamName +")].teamId");
        return id.get(0);
    }
}