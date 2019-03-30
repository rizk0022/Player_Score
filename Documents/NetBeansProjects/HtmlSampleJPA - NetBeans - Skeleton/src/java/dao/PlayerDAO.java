/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Player;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Youssef Rizk
 */
public class PlayerDAO extends GenericDAO<Player> {

    /*Constructor*/
    public PlayerDAO() {
        super(Player.class);
    }

    /*find all players*/
    public List<Player> findAll() {
        return findResults("Player.findAll", null);
    }

    /*find player by player id*/
    public Player findById(int id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        return findResult("Player.findById", map);
    }

    /*find player by first name*/
    public List<Player> findByFirstName(String firstName) {
        Map<String, Object> map = new HashMap<>();
        map.put("first_name", firstName);
        return findResults("Player.findByFirstName", map);
    }

    /*find player by last name*/
    public List<Player> findByLastName(String lastName) {
        Map<String, Object> map = new HashMap<>();
        map.put("last_name", lastName);
        return findResults("Player.findByLastName", map);
    }

    /*find player by join date*/
    public List<Player> findByJoined(Date date) {
        Map<String, Object> map = new HashMap<>();
        map.put("joined", date);
        return findResults("Player.findByJoined", map);
    }

    /*find player by email*/
    public Player findByEmail(String email) {
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);
        return findResult("Player.findByEmail", map);
    }
}
