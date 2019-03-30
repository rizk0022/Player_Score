/*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
 */
package logic;

import dao.PlayerDAO;
import entity.Player;
import java.time.Clock;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Youssef Rizk
 */
public class PlayerLogic extends GenericLogic<Player, PlayerDAO> {

    /*static final fields*/
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String JOINED = "joined";
    public static final String EMAIL = "email";
    public static final String ID = "id";

    /*Constructor*/
    public PlayerLogic() {
        super(new PlayerDAO());
    }

    /*get all players*/
    public List<Player> getAllPlayers() {
        return get(() -> dao().findAll());
    }

    /*get player with id*/
    public Player getPlayerWithId(int id) {
        return get(() -> dao().findById(id));
    }

    /*get player with the first name*/
    public List<Player> getPlayersWithFirstName(String firstName) {
        return get(() -> dao().findByFirstName(firstName));
    }

    /*get player with the last name*/
    public List<Player> getPlayersWIthLastName(String lastName) {
        return get(() -> dao().findByLastName(lastName));
    }

    /*get players with a join date*/
    public List<Player> getPlayersJoinedOn(Date date) {
        return get(() -> dao().findByJoined(date));
    }

    /*get player with an email*/
    public Player getPlayerWithEmail(String email) {
        return get(() -> dao().findByEmail(email));
    }

    /*create entity for player table*/
    public Player createEntity(Map<String, String[]> parameterMap) {
        Player user = new Player();
        Date date = Date.from(Instant.now(Clock.systemDefaultZone()));
        if (parameterMap.containsKey(ID)) {
            user.setId(Integer.valueOf(parameterMap.get(ID)[0]));
        }

        if (parameterMap.get(ID)[0].isEmpty()
                || parameterMap.get(FIRST_NAME)[0].isEmpty()
                || parameterMap.get(LAST_NAME)[0].isEmpty()
                || parameterMap.get(FIRST_NAME)[0].length() > 45
                || parameterMap.get(LAST_NAME)[0].length() > 45) {
            throw new NullPointerException();
        }
        if (!parameterMap.get(ID)[0].matches("[0-9]+")
                || parameterMap.get(FIRST_NAME)[0].matches("[0-9]+")
                || parameterMap.get(LAST_NAME)[0].matches("[0-9]+")
                || !parameterMap.get(EMAIL)[0].matches("(?=.?[A-Za-z]).+@(?=.?[A-Za-z]).+(?=.?[A-Za-z]{2,4}).+$")) {
            throw new IllegalArgumentException();
        }

        user.setFirstName(parameterMap.get(FIRST_NAME)[0]);
        user.setLastName(parameterMap.get(LAST_NAME)[0]);
        user.setJoined(date);

        user.setEmail(parameterMap.get(EMAIL)[0]);
        return user;
    }
}
