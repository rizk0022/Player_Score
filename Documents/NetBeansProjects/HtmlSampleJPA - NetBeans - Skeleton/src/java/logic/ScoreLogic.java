/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import dao.ScoreDAO;
import entity.Player;
import logic.PlayerLogic;
import entity.Score;
import java.sql.Date;
import java.time.Clock;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import static logic.PlayerLogic.FIRST_NAME;
import static logic.PlayerLogic.ID;
import static logic.PlayerLogic.LAST_NAME;

/**
 *
 * @author Youssef Rizk
 */
public class ScoreLogic extends GenericLogic<Score, ScoreDAO> {

    /*static final fields*/
    public static final String ID = "scoreid";
    public static final String SCORE = "score";
    public static final String PLAYER_ID = "id";
    public static final String SUBMISSION = "submission";

    /*Constructor*/
    public ScoreLogic() {
        super(new ScoreDAO());
    }

    /*get all scores*/
    public List<Score> getAll() {
        return get(() -> dao().findAll());
    }

    /*get score by score id*/
    public Score getById(int id) {
        return get(() -> dao().findById(id));
    }

    /*get score with a score*/
    public List<Score> getScoresWith(int score) {
        return get(() -> dao().findByScore(score));
    }

    /*get score with submission date*/
    public List<Score> getScoreOnDate(Date submission) {
        return get(() -> dao().findBySubmission(submission));
    }

    /*get score with player id*/
    public List<Score> getScoresForPlayerID(int playerid) {
        return get(() -> dao().findByPlayerID(playerid));
    }

    /*create entity for score table*/
    public Score createEntity(Map<String, String[]> parameterMap) {
        java.util.Date date = java.util.Date.from(Instant.now(Clock.systemDefaultZone()));
        Score user = new Score();
        if (parameterMap.containsKey(ID)) {
            user.setId(Integer.valueOf(parameterMap.get(ID)[0]));
        }

        if (parameterMap.get(SCORE)[0].isEmpty()
                || parameterMap.get(PLAYER_ID)[0].isEmpty()) {
            throw new NullPointerException();
        }
        if (!parameterMap.get(SCORE)[0].matches("[0-9]+")
                || parameterMap.get(SCORE)[0].length() > 9) {
            throw new IllegalArgumentException();
        }
        user.setScore(Integer.valueOf(parameterMap.get(SCORE)[0]));
        PlayerLogic playerlogic = new PlayerLogic();
        Player player = playerlogic.getPlayerWithId(Integer.valueOf(parameterMap.get(PLAYER_ID)[0]));
        user.setPlayerid(player);

        user.setSubmission(date);

        return user;
    }
}
