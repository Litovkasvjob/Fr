import dao.DaoFactory;
import dto.FrictionDto;
import dto.UserDto;
import dto.WeightDto;
import service.app.WeightDtoIn;
import service.impl.UserServiceImpl;
import service.impl.WeightServiceImpl;

import java.util.Date;

/**
 * Created by Serg on 24.09.2017.
 */
public class App {
    public static void main(String[] args) {
      /*  UserDto userDto = new UserDto();
        userDto.setFirstname("dfgdsf");
        userDto.setLastname("erertg");
        userDto.setLogin("sdfgdfg");
        userDto.setPassword("adssadf");
        userDto.setRole(UserDto.RoleTypeDto.ROLE_USER);*/
        UserDto userDto = UserServiceImpl.getInstance().getById(7);
        System.out.println(UserServiceImpl.getInstance().getList());
        System.out.println(UserServiceImpl.getInstance().getByLogin("popit"));
        WeightDto weightDto = new WeightDto();
        weightDto.setIdUser(7);
        weightDto.setDate(new Date());
        weightDto.setTime(1);
        weightDto.setWeight("0.009");
        weightDto.setUsersByIdUser(userDto);

    }
}
