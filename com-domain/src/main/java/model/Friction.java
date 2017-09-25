package model;

import javax.persistence.*;
import java.util.Arrays;

/**
 * Created by Serg on 17.09.2017.
 */
@Entity
public class Friction {
    private int id;
    private int idWeight;
    private int load;
    private double coef_friction;
    private Weight weightByIdWeight;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "id_weight", nullable = false, insertable = false, updatable = false)
    public int getIdWeight() {
        return idWeight;
    }

    public void setIdWeight(int idWeight) {
        this.idWeight = idWeight;
    }

    @Basic
    @Column(name = "load")
    public int getLoad() {
        return load;
    }

    public void setLoad(int result) {
        this.load = result;
    }

    @Basic
    @Column(name = "coef_friction")
    public double getCoef() {
        return coef_friction;
    }

    public void setCoef(double coef_friction) {
        this.coef_friction = coef_friction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Friction friction = (Friction) o;

        if (id != friction.id) return false;
        if (idWeight != friction.idWeight) return false;
        if (load != friction.load) return false;
        if (Double.compare(friction.coef_friction, coef_friction) != 0) return false;
        return weightByIdWeight != null ? weightByIdWeight.equals(friction.weightByIdWeight) : friction.weightByIdWeight == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + idWeight;
        result = 31 * result + load;
        temp = Double.doubleToLongBits(coef_friction);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (weightByIdWeight != null ? weightByIdWeight.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "id_weight", referencedColumnName = "id", nullable = false)
    public Weight getWeightByIdWeight() {
        return weightByIdWeight;
    }

    public void setWeightByIdWeight(Weight weightByIdWeight) {
        this.weightByIdWeight = weightByIdWeight;
    }
}
