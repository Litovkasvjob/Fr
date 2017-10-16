import dao.DaoFactory;
import dao.UserDao;
import model.Friction;
import model.Users;
import model.Weight;

import java.util.List;

/**
 * Created by Serg on 17.09.2017.
 */
public class App {
    public static void main(String[] args) {

        UserDao userDao = DaoFactory.getInstance().getUserDao();
        Users user = new Users();
        user.setLogin("222litovka1");
        user.setPassword("222oot");
        user.setFirstname("S333erg");
        user.setLastname("333Litovka");
        Users users = userDao.create(user);
        System.out.println(users.getId());

//        Users users = userDao.getById(10);
//        users.setLogin("papa");
//        userDao.update(users);
////
//

//        List list = userDao.getList();
//        for (Object o : list) {
//            System.out.println((Users)o);
//        }

//
//
//        user.setWeightsById();
//        userDao.create(user);
//        System.out.println(userDao.getList());
        //System.out.println(userDao.getByLogin("litovka"));
       // userDao.removeById(6);
       // System.out.println(userDao.getList());
//        Weight weight = new Weight();
//        Users user = userDao.getById(10);
//        weight.setIdUser(user.getId());
//        weight.setDate(new Date());
//        weight.setTime(3);
//        weight.setWeight("0.099");
//        weight.setUsersByIdUser(user);
//        DaoFactory.getInstance().getWeightDao().create(weight);


/*        List list = userDao.getById(7).getWeightsById();
        for (Object o : list) {
            System.out.println((Weight) o);
        }

        System.out.println(DaoFactory.getInstance().getWeightDao().getById(1));
        System.out.println(DaoFactory.getInstance().getWeightDao().getById(2));
        System.out.println(DaoFactory.getInstance().getWeightDao().getById(3));*/


//        System.out.println(DaoFactory.getInstance().getWeightDao().getList());


/*
        Weight weight = DaoFactory.getInstance().getWeightDao().getById(2);
        Friction friction = new Friction();

        List<Friction> frictionList = weight.getFrictionsById();


        friction.setLoads(500);
        friction.setCoef("0.002");
        friction.setWeightByIdWeight(weight);

        friction.setIdWeight(weight.getId());

        System.out.println(friction);

        frictionList.add(friction);
        weight.setFrictionsById(frictionList);
        DaoFactory.getInstance().getFrictionDao().create(friction);

        DaoFactory.getInstance().getWeightDao().update(weight);
*/







    }
}
