package service.impl;

import dao.DaoFactory;
import dao.FrictionDao;
import dao.WeightDao;
import dto.FrictionDto;
import model.Friction;
import service.app.FrictionDtoIn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serg on 24.09.2017.
 */
public class FrictionServiceImpl implements FrictionDtoIn {

    private static FrictionServiceImpl service;
    private FrictionDao frictionDao;
    private WeightDao weightDao;


    private FrictionServiceImpl() {
        frictionDao = DaoFactory.getInstance().getFrictionDao();
        weightDao = DaoFactory.getInstance().getWeightDao();

    }

    public static synchronized FrictionServiceImpl getInstance() {
        if (service == null) {
            service = new FrictionServiceImpl();
        }
        return service;
    }

    @Override
    public FrictionDto getById(Integer key) {
        FrictionDto frictionDto = new FrictionDto(frictionDao.getById(key));
        return frictionDto;
    }

    @Override
    public FrictionDto create(FrictionDto o) {
        Friction friction = createFriction(o);
        Friction frictionReturn = frictionDao.create(friction);
        FrictionDto frictionDto = new FrictionDto(frictionReturn);
        return frictionDto;
    }

    @Override
    public boolean update(FrictionDto o) {
        Friction friction = createFriction(o);
        return frictionDao.update(friction);
    }

    @Override
    public boolean remove(FrictionDto o) {
        Friction friction = createFriction(o);
        return frictionDao.remove(friction);
    }

    @Override
    public List<FrictionDto> getList() {
        List<FrictionDto> list = new ArrayList<>();
        for (Friction friction : frictionDao.getList()) {
            list.add(new FrictionDto(friction));
        }
        return list;
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