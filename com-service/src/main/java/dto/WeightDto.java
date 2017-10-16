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
    private double weight;
    private List<FrictionDto> frictionsById;
    private UserDto usersByIdUser;



    public WeightDto() {
    }

    public WeightDto(Weight weight) {
        this.id = weight.getId();
        this.idUser = weight.getIdUser();
        this.date = weight.getDate();
        this.time = weight.getTime();
        this.weight = weight.getWeight();

        if (weight.getFrictionsById() != null) {
            this.frictionsById = new ArrayList<>();
            for (Friction friction : weight.getFrictionsById()) {
                this.frictionsById.add(new FrictionDto(friction).setWeightByIdWeight(this));
            }
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public List<FrictionDto> getFrictionsById() {
        return frictionsById;
    }

    public void setFrictionsById(List<FrictionDto> frictionsById) {
        this.frictionsById = frictionsById;
    }

    public UserDto getUsersByIdUser() {
        return usersByIdUser;
    }

    public WeightDto setUsersByIdUser(UserDto usersByIdUser) {
        this.usersByIdUser = usersByIdUser;
        return this;
    }


}
