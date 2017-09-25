package dao.impl;

import dao.AbstractDao;
import dao.UserDao;
import model.Users;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by Serg on 17.09.2017.
 */
public class UserDaoImpl extends AbstractDao<Users, Integer> implements UserDao {

    @Override
    public Users getById(Integer key) {
        return super.getById(key);
    }

    @Override
    public Users create(Users o) {
        return super.create(o);
    }

    @Override
    public boolean update(Users o) {
        return super.update(o);
    }

    @Override
    public boolean remove(Users o) {
        return super.remove(o);
    }


    @Override
    public List<Users> getList() {
        return super.getList();
    }

    @Override
    public Users removeById(Integer id) {
        Users users = getById(id);
        remove(users);
        return users;
    }


    @Override
    public Users getByLogin(String login) {
        Criteria criteria = getSession().createCriteria(Users.class)
                .add(Restrictions.eq("login", login));
        List<Users> users = criteria.list();
        closeCurrentSession();
        return (users.size() > 0) ? users.get(0) : null;
    }
}
