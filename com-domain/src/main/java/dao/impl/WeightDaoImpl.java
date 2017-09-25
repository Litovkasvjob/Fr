package dao.impl;

import dao.AbstractDao;
import dao.WeightDao;
import model.Weight;

import java.util.List;

/**
 * Created by Serg on 23.09.2017.
 */
public class WeightDaoImpl extends AbstractDao<Weight, Integer> implements WeightDao {


    @Override
    public Weight getById(Integer key) {
        return super.getById(key);
    }

    @Override
    public Weight create(Weight o) {
        return super.create(o);
    }

    @Override
    public boolean update(Weight o) {
        return super.update(o);
    }

    @Override
    public boolean remove(Weight o) {
        return super.remove(o);
    }

    @Override
    public List<Weight> getList() {
        return super.getList();
    }
}
