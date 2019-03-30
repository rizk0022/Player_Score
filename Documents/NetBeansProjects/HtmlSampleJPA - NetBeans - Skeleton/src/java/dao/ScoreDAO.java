/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Score;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Youssef Rizk
 */
public class ScoreDAO extends GenericDAO<Score> {

    /*Constructor*/
    public ScoreDAO() {
        super(Score.class);
    }

    /*find all scores*/
    public List<Score> findAll() {
        return findResults("Score.findAll", null);
    }

    /*find score by score id*/
    public Score findById(int id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        return findResult("Score.findById", map);
    }

    /*find score by score*/
    public List<Score> findByScore(int score) {
        Map<String, Object> map = new HashMap<>();
        map.put("score", score);
        return findResults("Score.findByScore", map);
    }

    /*find score by submission date*/
    public List<Score> findBySubmission(Date submission) {
        Map<String, Object> map = new HashMap<>();
        map.put("submission", submission);
        return findResults("Score.findBySubmission", map);
    }

    /*find score by player id*/
    public List<Score> findByPlayerID(int playerid) {
        Map<String, Object> map = new HashMap<>();
        map.put("Player_id", playerid);
        return findResults("Score.findByPlayerID", map);
    }
}
