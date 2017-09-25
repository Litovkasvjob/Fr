import dao.DaoFactory;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import dao.impl.WeightDaoImpl;
import model.Friction;
import model.Users;
import model.Weight;

import java.util.Date;

/**
 * Created by Serg on 17.09.2017.
 */
public class App {
    public static void main(String[] args) {

        UserDao userDao = DaoFactory.getInstance().getUserDao();
       /* Users user = new Users();
        user.setLogin("litovka");
        user.setPassword("root");
        user.setFirstname("Serg");
        user.setLastname("Litovka");
        user.setRole(Users.RoleType.ROLE_ADMIN);
        userDao.create(user);*/
        System.out.println(userDao.getList());
        //System.out.println(userDao.getByLogin("litovka"));
       // userDao.removeById(6);
       // System.out.println(userDao.getList());
      /*  Weight weight = new Weight();
        weight.setIdUser(7);
        weight.setDate(new Date());
        weight.setTime(2);
        weight.setWeight("0.088");
        weight.setUsersByIdUser(userDao.getById(7));
        DaoFactory.getInstance().getWeightDao().create(weight);*/
        System.out.println(DaoFactory.getInstance().getWeightDao().getList());
        Friction friction = new Friction();
        friction.setIdWeight(2);
        friction.setLoad(500);
        friction.setCoef(0.002);
        friction.setWeightByIdWeight(DaoFactory.getInstance().getWeightDao().getById(2));
        DaoFactory.getInstance().getFrictionDao().create(friction);
    }
}
