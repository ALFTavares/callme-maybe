package org.academiadecodigo.hackaton.shared;

import java.io.Serializable;

/**
 * Created by bob on 13-07-2017.
 */
public class Score implements Serializable {

    private static Long serialVersionUID = 10L;

    private String name;
    private int score;
    private long id;

    public Score() {

    }

    public Score(String name, int score) {
        this.name = name;
        this.score = score;
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

    @Override
    public String toString() {
        return name + " - " + score;
    }
}
