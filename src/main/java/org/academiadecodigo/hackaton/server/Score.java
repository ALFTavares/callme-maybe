package org.academiadecodigo.hackaton.server;

/**
 * Created by bob on 13-07-2017.
 */
public class Score {
    private String name;
    private int score;
    private long id;

    public Score() {

    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setId(long id) {
        this.id = id;
    }
}
