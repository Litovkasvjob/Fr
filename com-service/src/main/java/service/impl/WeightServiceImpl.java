package service.impl;

import dao.DaoFactory;
import dao.FrictionDao;
import dao.UserDao;
import dao.WeightDao;
import dto.FrictionDto;
import dto.UserDto;
import dto.WeightDto;
import model.Friction;
import model.Weight;
import service.app.WeightDtoIn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serg on 24.09.2017.
 */
public class WeightServiceImpl implements WeightDtoIn {

    private static WeightServiceImpl service;
    private WeightDao weightDao;
    private UserDao userDao;
    private FrictionDao frictionDao;


    private WeightServiceImpl() {
        weightDao = DaoFactory.getInstance().getWeightDao();
        userDao = DaoFactory.getInstance().getUserDao();
        frictionDao = DaoFactory.getInstance().getFrictionDao();

    }

    public static synchronized WeightServiceImpl getInstance() {
        if (service == null) {
            service = new WeightServiceImpl();
        }
        return service;
    }

    @Override
    public WeightDto getById(Integer key) {
        WeightDto weightDto = new WeightDto(weightDao.getById(key));
        return weightDto;
    }

    @Override
    public WeightDto create(WeightDto weightDto) {
        Weight weight = new Weight();

        weight.setIdUser(weightDto.getIdUser());
        weight.setDate(weightDto.getDate());
        weight.setTime(weightDto.getTime());
        weight.setWeight(weightDto.getWeight());

        Weight weightReturn = weightDao.create(weight);
        weightDto.setId(weightReturn.getId()); // right

        if (weightDto.getFrictionsById() != null) {
            List<Friction> frictions = new ArrayList<>();
            for (FrictionDto frictionDto : weightDto.getFrictionsById()) {
                frictions.add(FrictionServiceImpl.getInstance().
                        createFriction(frictionDto, weightDto));
            }
            weight.setFrictionsById(frictions);
        }

        weightDao.update(weight);
        return weightDto;
    }


    @Override
    public boolean update(WeightDto weightDto) {
        Weight weight = weightDao.getById(weightDto.getId());

        weight.setIdUser(weightDto.getIdUser());
        weight.setDate(weightDto.getDate());
        weight.setTime(weightDto.getTime());
        weight.setWeight(weightDto.getWeight());

        if (weightDto.getUsersByIdUser() != null) {
            weight.setUsersByIdUser(userDao.getById(weight.getIdUser()));
        }

        if (weightDto.getFrictionsById() != null) {

            List<Friction> frictions = weight.getFrictionsById(); //TODO: new list i think
            for (int i = 0; i < frictions.size() - 1; i++) {
                Friction friction = FrictionServiceImpl.getInstance().
                        updateFriction(weightDto.getFrictionsById().get(i));
            }

            weight.setFrictionsById(frictions);
        }

        return weightDao.update(weight);
    }

    @Override
    public boolean remove(WeightDto weightDto) {
        Weight weight = weightDao.getById(weightDto.getId());
        if (weightDto.getFrictionsById() != null) {
            for (FrictionDto frictionDto : weightDto.getFrictionsById()) {
                FrictionServiceImpl.getInstance().remove(frictionDto);
            }
        }
        return weightDao.remove(weight);
    }

    @Override
    public List<WeightDto> getList() {
        List<WeightDto> weightDtos = new ArrayList<>();
        for (Weight weight : weightDao.getList()) {
            weightDtos.add(new WeightDto(weight));
        }
        return weightDtos;
    }

    public Weight createWeight(WeightDto weightDto, UserDto userDto) { //right
        Weight weight = new Weight();

        weight.setIdUser(userDto.getId());
        weight.setDate(weightDto.getDate());
        weight.setTime(weightDto.getTime());
        weight.setWeight(weightDto.getWeight());

        if (weightDto.getUsersByIdUser() != null) {
            weight.setUsersByIdUser(userDao.getById(weight.getIdUser()));
        }

        Weight weightReturn = weightDao.create(weight);
        weightDto.setId(weightReturn.getId()); // right

        if (weightDto.getFrictionsById() != null) {
            List<Friction> frictions = new ArrayList<>();
            for (FrictionDto frictionDto : weightDto.getFrictionsById()) {
                frictions.add(FrictionServiceImpl.getInstance().createFriction(frictionDto, weightDto));
            }
            weight.setFrictionsById(frictions);
        }

        weightDao.update(weightReturn);
        return weightReturn;
    }

    public Weight updateWeight(WeightDto weightDto) {// TODO:
        Weight weight = weightDao.getById(weightDto.getId());

        weight.setIdUser(weightDto.getIdUser());
        weight.setDate(weightDto.getDate());
        weight.setTime(weightDto.getTime());
        weight.setWeight(weightDto.getWeight());

        if (weightDto.getUsersByIdUser() != null) {
            weight.setUsersByIdUser(userDao.getById(weight.getIdUser()));
        }

        List<Friction> list = new ArrayList<>();

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
        weightDao.update(weight);

        return weight;
    }


}
