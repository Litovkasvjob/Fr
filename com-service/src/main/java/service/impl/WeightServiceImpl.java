package service.impl;

import dao.DaoFactory;
import dao.UserDao;
import dao.WeightDao;
import dto.FrictionDto;
import dto.WeightDto;
import model.Friction;
import model.Weight;
import service.app.WeightDtoIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Serg on 24.09.2017.
 */
public class WeightServiceImpl implements WeightDtoIn{

    private static WeightServiceImpl service;
    private WeightDao weightDao;
    private UserDao userDao;


    private WeightServiceImpl() {
        weightDao = DaoFactory.getInstance().getWeightDao();
        userDao = DaoFactory.getInstance().getUserDao();

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
        weight.setId(weightDto.getId());
        weight.setIdUser(weightDto.getIdUser());
        weight.setDate(weightDto.getDate());
        weight.setTime(weightDto.getTime());
        weight.setWeight(weightDto.getWeight());

        if (weightDto.getUsersByIdUser() != null) {
            weight.setUsersByIdUser(userDao.getById(weight.getIdUser()));
        }


        if (weightDto.getFrictionsById() != null) {
            Collection<Friction> frictions = new ArrayList<>();
            for (FrictionDto frictionDto : weightDto.getFrictionsById()) {
                frictions.add(createFriction(frictionDto));
            }
            weight.setFrictionsById(frictions);
        }

        Weight weightReturn = weightDao.create(weight);
        WeightDto weightDtoReturn = new WeightDto(weightReturn);
        return weightDtoReturn;
    }

    @Override
    public boolean update(WeightDto weightDto) {
        Weight weight = new Weight();
        weight.setId(weightDto.getId());
        weight.setIdUser(weightDto.getIdUser());
        weight.setDate(weightDto.getDate());
        weight.setTime(weightDto.getTime());
        weight.setWeight(weightDto.getWeight());

        Collection<Friction> frictions = new ArrayList<>();
        for (FrictionDto frictionDto : weightDto.getFrictionsById()) {
            frictions.add(createFriction(frictionDto));
        }
        weight.setFrictionsById(frictions);

        return weightDao.update(weight);
    }

    @Override
    public boolean remove(WeightDto weightDto) {
        Weight weight = new Weight();
        weight.setId(weightDto.getId());
        weight.setIdUser(weightDto.getIdUser());
        weight.setDate(weightDto.getDate());
        weight.setTime(weightDto.getTime());
        weight.setWeight(weightDto.getWeight());

        Collection<Friction> frictions = new ArrayList<>();
        for (FrictionDto frictionDto : weightDto.getFrictionsById()) {
            frictions.add(createFriction(frictionDto));
        }
        weight.setFrictionsById(frictions);
        return weightDao.remove(weight);
    }

    @Override
    public List<WeightDto> getList() {
        List<WeightDto> weightDtos = new ArrayList<>();
        for (Weight weight: weightDao.getList()) {
            weightDtos.add(new WeightDto(weight));
        }
        return weightDtos;
    }


    public Friction createFriction(FrictionDto frictionDto) {
        Friction friction = new Friction();
        friction.setId(frictionDto.getId());
        friction.setIdWeight(frictionDto.getIdWeight());
        friction.setLoad(frictionDto.getLoad());
        friction.setCoef(frictionDto.getCoef());

        friction.setWeightByIdWeight(weightDao.getById(frictionDto.getIdWeight()));
        return friction;
    }

}
