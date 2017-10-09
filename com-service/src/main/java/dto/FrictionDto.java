package dto;

import model.Friction;

/**
 * Created by Serg on 23.09.2017.
 */
public class FrictionDto {

    private int id;
    private int idWeight;
    private int loads;
    private double coef_friction;
    private WeightDto weightByIdWeight;

    public FrictionDto() {
    }

    public FrictionDto(Friction friction) {
        this.id = friction.getId();
        this.idWeight = friction.getIdWeight();
        this.loads = friction.getLoads();
        this.coef_friction = friction.getCoef();
        this.weightByIdWeight = new WeightDto(friction.getWeightByIdWeight());

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getIdWeight() {
        return idWeight;
    }

    public void setIdWeight(int idWeight) {
        this.idWeight = idWeight;
    }


    public int getLoads() {
        return loads;
    }

    public void setLoads(int result) {
        this.loads = result;
    }


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

        FrictionDto that = (FrictionDto) o;

        if (id != that.id) return false;
        if (idWeight != that.idWeight) return false;
        if (loads != that.loads) return false;
        return Double.compare(that.coef_friction, coef_friction) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + idWeight;
        result = 31 * result + loads;
        temp = Double.doubleToLongBits(coef_friction);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public WeightDto getWeightByIdWeight() {
        return weightByIdWeight;
    }

    public void setWeightByIdWeight(WeightDto weightByIdWeight) {
        this.weightByIdWeight = weightByIdWeight;
    }
}
