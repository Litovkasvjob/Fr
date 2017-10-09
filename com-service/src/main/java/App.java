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

/**
 * Created by Serg on 24.09.2017.
 */
public class App {
    public static void main(String[] args) {

        UserDto userDto = new UserDto();
        userDto.setFirstname("sdssssss");
        userDto.setLastname("ttttttt");
        userDto.setLogin("ghj");
        userDto.setPassword("gkblu");
        UserServiceImpl.getInstance().create(userDto);
        UserDto userDto1 = UserServiceImpl.getInstance().getById(42);
        System.out.println(userDto1);

        List<WeightDto> list = new ArrayList<>();
        WeightDto weightDto = new WeightDto();
        weightDto.setIdUser(userDto1.getId());
        weightDto.setTime(62);
        weightDto.setWeight(0.456);
        weightDto.setDate(new Date());
        weightDto.setUsersByIdUser(userDto1);

        FrictionDto frictionDto = new FrictionDto();
        frictionDto.setLoads(500);
        frictionDto.setCoef(0.15);
        frictionDto.setWeightByIdWeight(weightDto);

        frictionDto.setIdWeight(2); //????

        List<FrictionDto> list1 = new ArrayList<>();
        list1.add(frictionDto);
        weightDto.setFrictionsById(list1);
        list.add(weightDto);
        userDto1.setLogin("ewrwer");
        userDto1.setWeightsById(list);

        UserServiceImpl.getInstance().update(userDto1);
        System.out.println(UserServiceImpl.getInstance().getById(42));



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
