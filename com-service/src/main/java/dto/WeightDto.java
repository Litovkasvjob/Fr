package dto;

import model.Friction;
import model.Users;
import model.Weight;
import service.app.WeightDtoIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Serg on 23.09.2017.
 */
public class WeightDto {

    private int id;
    private int idUser;
    private Date date;
    private int time;
    private String weight;
    private Collection<FrictionDto> frictionsById = new ArrayList<>();
    private UserDto usersByIdUser;

    public WeightDto() {
    }

    public WeightDto(Weight weight) {
        id = weight.getId();
        idUser = weight.getIdUser();
        date = weight.getDate();
        time = weight.getTime();
        this.weight = weight.getWeight();


        if (weight.getFrictionsById() != null) {
            this.frictionsById = new ArrayList<>();
            for (Friction friction : weight.getFrictionsById()) {
                this.frictionsById.add(new FrictionDto(friction));
            }
        }

        if (weight.getUsersByIdUser() != null) {
            this.usersByIdUser = new UserDto(weight.getUsersByIdUser());
        }


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Collection<FrictionDto> getFrictionsById() {
        return frictionsById;
    }

    public void setFrictionsById(Collection<FrictionDto> frictionsById) {
        this.frictionsById = frictionsById;
    }

    public UserDto getUsersByIdUser() {
        return usersByIdUser;
    }

    public void setUsersByIdUser(UserDto usersByIdUser) {
        this.usersByIdUser = usersByIdUser;
    }


}
