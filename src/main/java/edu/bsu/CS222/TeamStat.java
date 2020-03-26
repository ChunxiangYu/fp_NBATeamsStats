package edu.bsu.CS222;

public class TeamStat {
    public static TeamBuilder build() {
        return new TeamBuilder();
    }

    public static final class TeamBuilder {
        private String id;
        private float wins;
        private float loss;


        public TeamBuilder teamId(String id) {
            this.id = id;
            return this;
        }
        public TeamBuilder teamWins(float wins) {
            this.wins = wins;
            return this;
        }
        public TeamStat teamLoss(float loss) {
            this.loss = loss;
            return new TeamStat(this);
        }
    }
    private String id;
    private float wins;
    private float loss;

    private TeamStat(TeamBuilder builder) {
        this.wins = builder.wins;
        this.id = builder.id;
        this.loss = builder.loss;
    }

    public float getLosses() {
        return loss;
    }

    public float getWins() {
        return wins;
    }

    public String getId() {
        return id;
    }

    public String toString() {
        return " ID: " + id + "\n Wins: " + wins + "\n Losses: " + loss;
    }
}
