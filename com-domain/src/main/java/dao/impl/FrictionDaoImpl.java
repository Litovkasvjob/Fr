package dao.impl;

import dao.AbstractDao;
import dao.FrictionDao;
import model.Friction;

import java.util.List;

/**
 * Created by Serg on 23.09.2017.
 */
public class FrictionDaoImpl extends AbstractDao<Friction, Integer> implements FrictionDao {

    @Override
    public Friction getById(Integer key) {
        return super.getById(key);
    }

    @Override
    public Friction create(Friction o) {
        return super.create(o);
    }

    @Override
    public boolean update(Friction o) {
        return super.update(o);
    }

    @Override
    public boolean remove(Friction o) {
        return super.remove(o);
    }

    @Override
    public List<Friction> getList() {
        return super.getList();
    }
}
