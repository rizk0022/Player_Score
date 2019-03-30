package dao;

import entity.Username;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Shariar Emami
 * @since Jan 14, 2019
 */
public class UsernameDAO extends GenericDAO<Username> {

    public UsernameDAO() {
        super(Username.class);
    }

    public List<Username> findAll() {
        return findResults("Username.findAll", null);
    }

    public Username findByPlayerID(int playerID) {
        Map<String, Object> map = new HashMap<>();
        map.put("playerid", playerID);
        return findResult("Username.findByPlayerid", map);
    }

    public List<Username> findByUsername(String username) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        return findResults("Username.findByUsername", map);
    }
}
