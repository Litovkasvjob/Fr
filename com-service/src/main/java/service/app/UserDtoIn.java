package service.app;

import dto.UserDto;
import service.Service;

/**
 * Created by Serg on 23.09.2017.
 */
public interface UserDtoIn extends Service<UserDto, Integer> {
    UserDto getByLogin(String login);

    UserDto removeById(Integer id);
}
