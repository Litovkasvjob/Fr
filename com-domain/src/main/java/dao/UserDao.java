package dao;

import model.Users;

/**
 * Created by Serg on 17.09.2017.
 */
public interface UserDao extends Dao<Users, Integer> {

    Users getByLogin(String login);

    Users removeById(Integer id);

}
