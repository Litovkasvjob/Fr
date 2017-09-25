package service;

import java.util.List;

/**
 * Created by Serg on 23.09.2017.
 */
public interface Service <T, I>{

    T getById(I key);

    T create(T o);

    boolean update(T o);

    boolean remove(T o);

    List<T> getList();

}
