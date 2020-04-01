package edu.bsu.CS222;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.InputStream;

public class TeamParser {

    public static final class TeamParserBuilder {
        private InputStream in;
        private String id;

        public TeamParserBuilder(InputStream in)
        {
            this.in = in;
        }

        public TeamParser addId(String id)
        {
            this.id = id;
            return new TeamParser(this);
        }
    }

    public static TeamParserBuilder parseRequest(InputStream in) {
        return new TeamParserBuilder(in);
    }

    private InputStream in;
    private String id;
    private JSONArray jsonArray = new JSONArray();

    public TeamParser(TeamParserBuilder builder)
    {
        this.in = builder.in;
        this.id = builder.id;
    }
    public TeamStat parse() {
        Object json = Configuration.defaultConfiguration().jsonProvider().parse(in, "UTF-8");
        jsonArray.add(JsonPath.read(json, "$.league.standard.teams[?(@.teamId == '" + id + "')].teamId"));
        jsonArray.add(JsonPath.read(json, "$.league.standard.teams[?(@.teamId == '" + id + "')].win"));
        jsonArray.add(JsonPath.read(json, "$.league.standard.teams[?(@.teamId == '" + id + "')].loss"));
        jsonArray.add(JsonPath.read(json, "$.league.standard.teams[?(@.teamId == '" + id + "')].confRank"));
        jsonArray.add(JsonPath.read(json, "$.league.standard.teams[?(@.teamId == '" + id + "')].divRank"));
        jsonArray.add(JsonPath.read(json, "$.league.standard.teams[?(@.teamId == '" + id + "')].streak"));

        return TeamStat.build().teamId(((JSONArray) jsonArray.get(0)).get(0).toString()).teamWins(getFloat(1)).teamLoss(getFloat(2));
    }
    private float getFloat(Integer index){
        return Float.parseFloat(((JSONArray) jsonArray.get(index)).get(0).toString());
    }
}
