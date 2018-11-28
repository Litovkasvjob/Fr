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

        if (updateWeight(weightDto).getClass().getSimpleName().equals(Weight.class.getSimpleName())) {
            return true;
        }
        return false;
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
        weight.setUsersByIdUser(userDao.getById(userDto.getId()));

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

        if (weightDto.getFrictionsById() != null) {

            for (int i = 0; i < weightDto.getFrictionsById().size() - 1; i++) {

                for (int j = 0; j < weight.getFrictionsById().size() - 1; j++) {

                    if (weight.getFrictionsById().get(j).getId() == weightDto.getFrictionsById().get(i).getId()) {
                        Friction friction = FrictionServiceImpl.getInstance().
                                updateFriction(weightDto.getFrictionsById().get(i));
                        list.set(i, friction);
                        break;
                    }

                    if (j == weight.getFrictionsById().size() - 1) {
                        if (weight.getFrictionsById().get(j).getId() > weightDto.getFrictionsById().get(i).getId() ||
                                weight.getFrictionsById().get(j).getId() < weightDto.getFrictionsById().get(i).getId()) {
                            //check for existence in db
                            for (Friction friction : frictionDao.getList()) {
                                if (friction.getId() == weightDto.getFrictionsById().get(i).getId()) {
                                    list.set(i, friction);
                                    break;
                                }
                            }
                            Friction friction = FrictionServiceImpl.getInstance().
                                    createFriction(weightDto.getFrictionsById().get(i), weightDto);
                            list.set(i, friction);
                            continue;
                        }
                    }


                }

            }
            //delete friction from list which was not removed
            for (int i = 0; i < weight.getFrictionsById().size() - 1; i++) {

                for (int j = 0; j < weightDto.getFrictionsById().size() - 1; j++) {

                    if (weight.getFrictionsById().get(i).getId() == weightDto.getFrictionsById().get(j).getId()) {
                        break;
                    }

                    if (j == weightDto.getFrictionsById().size() - 1) {
                        frictionDao.remove(weight.getFrictionsById().get(i));
                    }
                }
            }
        }
        weight.setFrictionsById(list);
        weightDao.update(weight);

        return weight;
    }


}
