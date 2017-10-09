package domain;

/**
 * Created by Serg on 25.08.2017.
 */
public class Load {

    private int id;
    private double load;

    public Load() {
    }

    public Load(int id, double load) {
        this.id = id;
        this.load = load;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLoad() {
        return load;
    }

    public void setLoad(double load) {
        this.load = load;
    }

    public Number[] asArray(){
        return new Number[]{getId(), getLoad()};
    }

    @Override
    public String toString() {
        return "Load{" +
                "id=" + id +
                ", load='" + load + '\'' +
                '}';
    }
}
