package model;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.IndexColumn;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Serg on 17.09.2017.
 */
@Entity
public class Weight {
    private int id;
    private int idUser;
    private Date date;
    private int time;
    private String weight;
    private Collection<Friction> frictionsById = new ArrayList<>();
    private Users usersByIdUser;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "id_user", nullable = false, insertable = false, updatable = false)
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "time", nullable = false)
    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Basic
    @Column(name = "weight", nullable = true)
    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Weight weight1 = (Weight) o;

        if (id != weight1.id) return false;
        if (idUser != weight1.idUser) return false;
        if (time != weight1.time) return false;
        if (date != null ? !date.equals(weight1.date) : weight1.date != null) return false;
        return weight != null ? weight.equals(weight1.weight) : weight1.weight == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + idUser;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + time;
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        return result;
    }


    @OneToMany(mappedBy = "weightByIdWeight")
    public Collection<Friction> getFrictionsById() {
        return frictionsById;
    }

    public void setFrictionsById(Collection<Friction> frictionsById) {
        this.frictionsById = frictionsById;
    }

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    public Users getUsersByIdUser() {
        return usersByIdUser;
    }

    public void setUsersByIdUser(Users usersByIdUser) {
        this.usersByIdUser = usersByIdUser;
    }

    @Override
    public String toString() {
        return "Weight{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", date=" + date +
                ", time=" + time +
                ", weight='" + weight + '\'' +
                '}';
    }
}
