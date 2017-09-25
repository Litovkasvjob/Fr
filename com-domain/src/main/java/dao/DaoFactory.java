package dao;

import dao.impl.FrictionDaoImpl;
import dao.impl.UserDaoImpl;
import dao.impl.WeightDaoImpl;
import model.Friction;
import model.Users;
import model.Weight;

/**
 * Created by Serg on 23.09.2017.
 */
public class DaoFactory {
    private static DaoFactory instance = null;

    private UserDao userDao;
    private WeightDao weightDao;
    private FrictionDao frictionDao;

    private DaoFactory() {
        loadDaos();
    }

    public static DaoFactory getInstance() {
        if (instance == null) {
            instance = new DaoFactory();
        }
        return instance;
    }

    private void loadDaos() {
        userDao = new UserDaoImpl();
        weightDao = new WeightDaoImpl();
        frictionDao = new FrictionDaoImpl();
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public WeightDao getWeightDao() {
        return weightDao;
    }

    public void setWeightDao(WeightDao weightDao) {
        this.weightDao = weightDao;
    }

    public FrictionDao getFrictionDao() {
        return frictionDao;
    }

    public void setFrictionDao(FrictionDao frictionDao) {
        this.frictionDao = frictionDao;
    }
}
