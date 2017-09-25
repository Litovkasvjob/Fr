package dao;


import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;


public abstract class AbstractDao<T, I extends Serializable> implements Dao<T, I> {

    private final Class<T> entityClass;
    private Transaction currentTransaction;
    private Session currentSession;

    public AbstractDao() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    public Session getSession() {
        currentSession = HibernateUtils.getSession();
        return currentSession;
    }

    public Session getSessionWithTransaction() {
        currentSession = getSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
        System.out.println("session close");
    }

    public void closeCurrentSessionWithTransaction() {
        currentTransaction.commit();
        currentSession.close();
        System.out.println("session close");
    }

    public void pollbackTransaction() {
        currentTransaction.rollback();
    }

    @Override
    public T getById(I key) {
        T obj = (T) getSession().get(entityClass, key);
        closeCurrentSession();
        return obj;
    }

    @Override
    public T create(T o) {
        try {
            getSessionWithTransaction().save(o);
            closeCurrentSessionWithTransaction();
            System.out.println("create " + o.getClass().getSimpleName());
            return o;
        } catch (Exception e) {
            pollbackTransaction();
            throw new RuntimeException("Cannot save " + o.getClass().getSimpleName(), e);
        }
    }

    @Override
    public boolean update(T o) {
        try {
            getSessionWithTransaction().update(o);
            closeCurrentSessionWithTransaction();
            System.out.println("update " + o.getClass().getSimpleName());
            return true;
        } catch (Exception e) {
            pollbackTransaction();
            throw new RuntimeException("Cannot update" + o.getClass().getSimpleName(), e);
        }
    }

    @Override
    public boolean remove(T o) {
        try {
            getSessionWithTransaction().delete(o);
            closeCurrentSessionWithTransaction();
            System.out.println("delete " + o.getClass().getSimpleName());
            return true;
        } catch (Exception e) {
            pollbackTransaction();
            throw new RuntimeException("Cannot delete " +  o.getClass().getSimpleName(), e);
        }
    }

    @Override
    public List<T> getList() {
        List<T> list = (List<T>) getSession().createCriteria(entityClass).list();
        closeCurrentSession();
        return list;
    }
}




