package edu.bsu.cs222;

public class TeamStat {
    public static TeamBuilder build() {
        return new TeamBuilder();
    }

    public static final class TeamBuilder {
        private String id;
        private float wins;
        private float loss;
        private float confRank;
        private float divRank;

        public TeamBuilder teamId(String id) {
            this.id = id;
            return this;
        }
        public TeamBuilder teamWins(float wins) {
            this.wins = wins;
            return this;
        }
        public TeamBuilder teamConfRank(float confRank) {
            this.confRank = confRank;
            return this;
        }
        public TeamBuilder teamDivRank(float divRank) {
            this.divRank = divRank;
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
    private float confRank;
    private float divRank;
    private TeamStat(TeamBuilder builder) {
        this.wins = builder.wins;
        this.id = builder.id;
        this.loss = builder.loss;
        this.confRank = builder.confRank;
        this.divRank = builder.divRank;
    }
    public String toString() {
        return " ID: " + id + "\n Wins: " + wins + "\n Losses: " + loss+ "\n Conference Rank: " + confRank+ "\n Division Rank: " + divRank;
    }
}
