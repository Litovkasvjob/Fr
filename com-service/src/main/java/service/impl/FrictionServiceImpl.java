package service.impl;

import dao.DaoFactory;
import dao.FrictionDao;
import dao.WeightDao;
import dto.FrictionDto;
import dto.WeightDto;
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
        Friction friction = new Friction();
        //friction.setId(o.getId());
        friction.setIdWeight(o.getIdWeight());
        friction.setLoads(o.getLoads());
        friction.setCoef(o.getCoef());

        friction.setWeightByIdWeight(weightDao.getById(o.getIdWeight()));
        Friction frictionReturn = frictionDao.create(friction);
        o.setId(frictionReturn.getId());
        return o;
    }

    @Override
    public boolean update(FrictionDto o) {
        Friction friction = frictionDao.getById(o.getId());
        //friction.setId(o.getId());
        friction.setIdWeight(o.getIdWeight());
        friction.setLoads(o.getLoads());
        friction.setCoef(o.getCoef());

        friction.setWeightByIdWeight(weightDao.getById(o.getIdWeight()));
        return frictionDao.update(friction);
    }

    @Override
    public boolean remove(FrictionDto o) {
        Friction friction = frictionDao.getById(o.getId());
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

    public Friction createFriction(FrictionDto frictionDto, WeightDto weightDto) { //right
        Friction friction = new Friction();
        friction.setIdWeight(weightDto.getId());
        friction.setLoads(frictionDto.getLoads());
        friction.setCoef(frictionDto.getCoef());
        friction.setWeightByIdWeight(weightDao.getById(weightDto.getId()));
        Friction frictionReturn = frictionDao.create(friction);
        return frictionReturn;
    }

    public Friction updateFriction(FrictionDto frictionDto) { //right
        Friction friction = frictionDao.getById(frictionDto.getId());

        friction.setLoads(frictionDto.getLoads());
        friction.setCoef(frictionDto.getCoef());
        frictionDao.update(friction);

        return friction;
    }
}
