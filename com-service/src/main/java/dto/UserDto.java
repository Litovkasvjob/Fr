package dto;

import model.Users;
import model.Weight;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serg on 23.09.2017.
 */
public class UserDto {
    private int id;
    private String login;
    private String password;
    private String firstname;
    private String lastname;
    private List<WeightDto> weightsById;

    public UserDto() {
    }

    public UserDto(Users user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();

        if (user.getWeightsById() != null) {
            weightsById = new ArrayList<>();
            for (Weight weight : user.getWeightsById()) {
                weightsById.add(new WeightDto(weight));
            }
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }


    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDto userDto = (UserDto) o;

        if (id != userDto.id) return false;
        if (login != null ? !login.equals(userDto.login) : userDto.login != null) return false;
        if (password != null ? !password.equals(userDto.password) : userDto.password != null) return false;
        if (firstname != null ? !firstname.equals(userDto.firstname) : userDto.firstname != null) return false;
        return lastname != null ? lastname.equals(userDto.lastname) : userDto.lastname == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }

    public List<WeightDto> getWeightsById() {
        return weightsById;
    }

    public void setWeightsById(List<WeightDto> weightsById) {

        this.weightsById = weightsById;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

}
