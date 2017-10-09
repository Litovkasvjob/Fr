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
    public UserDto create(UserDto o) {
        Users user = new Users();
        //user.setId(o.getId());
        user.setLogin(o.getLogin());
        user.setPassword(o.getPassword());
        user.setFirstname(o.getFirstname());
        user.setLastname(o.getLastname());

        if (o.getWeightsById() != null) {
            List<Weight> list = new ArrayList<>();
            for (WeightDto weightDto : o.getWeightsById()) {
                list.add(WeightServiceImpl.getInstance().createWeight(weightDto));
            }
            user.setWeightsById(list);
        }

        Users userReturn = userDao.create(user);
        UserDto userDto = new UserDto(userReturn);
        return userDto;
    }

    @Override
    public boolean update(UserDto o) {
        Users user = new Users();
        user.setId(o.getId());
        user.setLogin(o.getLogin());
        user.setPassword(o.getPassword());
        user.setFirstname(o.getFirstname());
        user.setLastname(o.getLastname());

        List<Weight> list = new ArrayList<>();
        if (o.getWeightsById() != null) {
            for (WeightDto weightDto : o.getWeightsById()) {
                list.add(WeightServiceImpl.getInstance().createWeight(weightDto));
            }

            user.setWeightsById(list);
        }
        return userDao.update(user);
    }

    @Override
    public boolean remove(UserDto o) {
        Users user = new Users();
        user.setId(o.getId());
        user.setLogin(o.getLogin());
        user.setPassword(o.getPassword());
        user.setFirstname(o.getFirstname());
        user.setLastname(o.getLastname());

        List<Weight> list = new ArrayList<>();
        if (o.getWeightsById() != null) {
            for (WeightDto weightDto : o.getWeightsById()) {
                list.add(WeightServiceImpl.getInstance().createWeight(weightDto));
            }
        }
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
