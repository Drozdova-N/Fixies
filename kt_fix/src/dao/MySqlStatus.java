package dao;

import entity.StatusEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class MySqlStatus implements DAOStatus {

    private Session session;
    private Transaction transaction;
    @Override
    public void create(StatusEntity statusEntity) {
        session = HibernateUtil.getSession().openSession();
        transaction = session.beginTransaction();
        session.save(statusEntity);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(StatusEntity statusEntity) {
        session = HibernateUtil.getSession().openSession();
        transaction = session.beginTransaction();
        session.delete(statusEntity);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(StatusEntity statusEntity) {
        session = HibernateUtil.getSession().openSession();
        transaction = session.beginTransaction();
        session.update(statusEntity);
        transaction.commit();
        session.close();
    }

    @Override
    public StatusEntity getStatusById(int id) {
        return HibernateUtil.getSession().openSession().get(StatusEntity.class,id);
    }

    @Override
    public List<StatusEntity> getStatusAll() {
        return HibernateUtil.getSession().openSession().createQuery("from StatusEntity ").list();
    }
}
