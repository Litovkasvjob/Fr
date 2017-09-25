package dao;

import java.util.List;

public interface Dao<T, I> {

    T getById(I key);

    T create(T o);

    boolean update(T o);

    boolean remove(T o);

    List<T> getList();



}
