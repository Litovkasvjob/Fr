package dao;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jboss.logging.Logger;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;


public abstract class AbstractDao<T, I extends Serializable> implements Dao<T, I> {

    private static final Logger LOGGER = Logger.getLogger(AbstractDao.class.getName());

    private final Class<T> entityClass;
    private Transaction currentTransaction;
    private Session currentSession;

    public AbstractDao() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    public Session getSession() {
        currentSession = HibernateUtils.getSession();
        LOGGER.info("Session open");
        return currentSession;
    }

    public Session getSessionWithTransaction() {
        currentSession = getSession();
        currentTransaction = currentSession.beginTransaction();
        LOGGER.info("Transaction begin");
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
        LOGGER.info("Session close");
    }

    public void closeCurrentSessionWithTransaction() {
        currentTransaction.commit();
        LOGGER.info("Transaction commit");
        closeCurrentSession();
    }

    public void pollbackTransaction() {
        currentTransaction.rollback();
        LOGGER.info("Transaction rollback");
    }

    @Override
    public T getById(I key) {
        T obj = (T) getSession().get(entityClass, key);
        LOGGER.info(obj.getClass() + " was taken by id");
        closeCurrentSession();
        return obj;
    }

    @Override
    public T create(T o) {
        try {
            getSessionWithTransaction().save(o);
            closeCurrentSessionWithTransaction();
            LOGGER.info("create " + o.getClass().getSimpleName());
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
            LOGGER.info("update " + o.getClass().getSimpleName());
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
            LOGGER.info("delete " + o.getClass().getSimpleName());
            return true;
        } catch (Exception e) {
            pollbackTransaction();
            throw new RuntimeException("Cannot delete " +  o.getClass().getSimpleName(), e);
        }
    }

    @Override
    public List<T> getList() {
        List<T> list = (List<T>) getSession().createCriteria(entityClass).list();
        LOGGER.info("List of " + entityClass.getClass().getSimpleName());
        closeCurrentSession();
        return list;
    }
}




