import dao.DaoFactory;
import dto.FrictionDto;
import dto.UserDto;
import dto.WeightDto;
import model.Friction;
import model.Weight;
import service.app.WeightDtoIn;
import service.impl.UserServiceImpl;
import service.impl.WeightServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class App {
    public static void main(String[] args) {


/*        UserDto userDto = new UserDto();
        userDto.setFirstname("333sfg");
        userDto.setLastname("222fgdfgrrrrrrr");
        userDto.setLogin("4555dfdf");
        userDto.setPassword("22fgdfgdfu");


        List<WeightDto> list = new ArrayList<>();
        WeightDto weightDto = new WeightDto();

        weightDto.setTime(333);
        weightDto.setWeight(0.4542);
        weightDto.setDate(new Date());

        FrictionDto frictionDto = new FrictionDto();
        frictionDto.setLoads(111);
        frictionDto.setCoef(0.088);

        List<FrictionDto> list1 = new ArrayList<>();
        list1.add(frictionDto);
        weightDto.setFrictionsById(list1);

        list.add(weightDto);

        userDto.setWeightsById(list);

        UserServiceImpl.getInstance().create(userDto);
        System.out.println(userDto);*/

        UserDto userDto = UserServiceImpl.getInstance().getById(60);

        UserServiceImpl.getInstance().remove(userDto);

/*
        FrictionDto frictionDto = new FrictionDto();
        frictionDto.setLoads(500);
        frictionDto.setCoef(0.15);
        frictionDto.setWeightByIdWeight(weightDto);
        frictionDto.setIdWeight(2); //????

        List<FrictionDto> list1 = new ArrayList<>();
        list1.add(frictionDto);
        weightDto.setFrictionsById(list1);
        *//*







       /* UserDto userDto = UserServiceImpl.getInstance().getById(7);
        System.out.println(UserServiceImpl.getInstance().getList());
        System.out.println(UserServiceImpl.getInstance().getByLogin("popit"));
        WeightDto weightDto = new WeightDto();
        weightDto.setIdUser(7);
        weightDto.setDate(new Date());
        weightDto.setTime(1);
        weightDto.setWeight("0.009");
        weightDto.setUsersByIdUser(userDto);*/

    }

}
