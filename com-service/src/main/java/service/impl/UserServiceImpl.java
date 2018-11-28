package service.impl;

import dao.DaoFactory;
import dao.UserDao;
import dao.WeightDao;
import dto.UserDto;
import dto.WeightDto;
import model.Users;
import model.Weight;
import service.app.UserDtoIn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serg on 23.09.2017.
 */
public class UserServiceImpl implements UserDtoIn {

    private static UserServiceImpl service;
    private UserDao userDao;
    private WeightDao weightDao;


    private UserServiceImpl() {
        userDao = DaoFactory.getInstance().getUserDao();
        weightDao = DaoFactory.getInstance().getWeightDao();

    }

    public static synchronized UserServiceImpl getInstance() {
        if (service == null) {
            service = new UserServiceImpl();
        }
        return service;
    }

    @Override
    public UserDto getById(Integer key) {
        UserDto userDto = new UserDto(userDao.getById(key));
        return userDto;
    }

    @Override
    public UserDto create(UserDto o) { //right
        Users user = new Users();
        user.setLogin(o.getLogin());
        user.setPassword(o.getPassword());
        user.setFirstname(o.getFirstname());
        user.setLastname(o.getLastname());

        Users userReturn = userDao.create(user);
        o.setId(userReturn.getId()); // right

        if (o.getWeightsById() != null) {
            List<Weight> list = new ArrayList<>();
            for (WeightDto weightDto : o.getWeightsById()) {
                list.add(WeightServiceImpl.getInstance().createWeight(weightDto, o));
            }
            user.setWeightsById(list);
        }
        userDao.update(user);
        return o;
    }

    @Override
    public boolean update(UserDto o) { //right
        Users user = userDao.getById(o.getId());
        user.setLogin(o.getLogin());
        user.setPassword(o.getPassword());
        user.setFirstname(o.getFirstname());
        user.setLastname(o.getLastname());

        List<Weight> list = new ArrayList<>();

        if (o.getWeightsById() != null) {

            for (int i = 0; i < o.getWeightsById().size() - 1; i++) {

                for (int j = 0; j < user.getWeightsById().size() - 1; j++) {

                    if (user.getWeightsById().get(j).getId() == o.getWeightsById().get(i).getId()) {
                        Weight weight = WeightServiceImpl.getInstance().
                                updateWeight(o.getWeightsById().get(i));
                        list.set(i, weight);
                        break;
                    }

                    if (j == user.getWeightsById().size() - 1) {
                        if (user.getWeightsById().get(j).getId() > o.getWeightsById().get(i).getId() ||
                                user.getWeightsById().get(j).getId() < o.getWeightsById().get(i).getId()) {
                            //check for existence in db
                            for (Weight weight : weightDao.getList()) {
                                if (weight.getId() == o.getWeightsById().get(i).getId()) {
                                    list.set(i, weight);
                                    break;
                                }
                            }
                            Weight weight = WeightServiceImpl.getInstance().
                                    createWeight(o.getWeightsById().get(i), o);
                            list.set(i, weight);
                            continue;
                        }
                    }


                }

            }
            //delete weight from list which was not removed
            for (int i = 0; i < user.getWeightsById().size() - 1; i++) {

                for (int j = 0; j < o.getWeightsById().size() - 1; j++) {

                    if (user.getWeightsById().get(i).getId() == o.getWeightsById().get(j).getId()) {
                        break;
                    }

                    if (j == o.getWeightsById().size() - 1) {
                        weightDao.remove(user.getWeightsById().get(i));
                    }
                }
            }
        }
        user.setWeightsById(list);

        return userDao.update(user);
}

    @Override
    public boolean remove(UserDto o) {
        Users user = userDao.getById(o.getId());
/*        if (user.getWeightsById() != null) {
            for (Weight weight : user.getWeightsById()) {
               weightDao.remove(weight);
            }
        }*/
        return userDao.remove(user);
    }

    @Override
    public List<UserDto> getList() {
        List<UserDto> userDtos = new ArrayList<>();
        for (Users users : userDao.getList()) {
            userDtos.add(new UserDto(users));
        }
        return userDtos;
    }

    @Override
    public UserDto getByLogin(String login) {
        Users user = userDao.getByLogin(login);
        UserDto userDto = new UserDto(user);
        return userDto;
    }

    @Override
    public UserDto removeById(Integer id) {
        Users user = userDao.removeById(id);
        UserDto userDto = new UserDto(user);
        return userDto;
    }

}
