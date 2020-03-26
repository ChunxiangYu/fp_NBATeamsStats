package edu.bsu.CS222;

import java.util.Arrays;
import java.util.List;

public class Team {
    public static final Integer w = 1;
    public static final Integer l = 2;

    private float stat;

    private Team(float stat) {
        this.stat = stat;
    }

    @Override
    public String toString() {
        return "Home Team Score: " + w + " Visitor Score = " + l;
    }
}
