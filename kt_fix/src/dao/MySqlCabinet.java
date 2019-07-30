package dao;

import entity.CabinetEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class MySqlCabinet implements  DAOCabinet{

    private Session session;
    private Transaction transaction;
    @Override
    public void create(CabinetEntity cabinetEntity) {
        session = HibernateUtil.getSession().openSession();
        transaction = session.beginTransaction();
        session.save(cabinetEntity);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(CabinetEntity cabinetEntity) {
        session = HibernateUtil.getSession().openSession();
        transaction = session.beginTransaction();
        session.delete(cabinetEntity);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(CabinetEntity cabinetEntity) {
        session = HibernateUtil.getSession().openSession();
        transaction = session.beginTransaction();
        session.update(cabinetEntity);
        transaction.commit();
        session.close();
    }

    @Override
    public CabinetEntity getCabinetById(int id) {
        return HibernateUtil.getSession().openSession().get(CabinetEntity.class, id);

    }

    @Override
    public List<CabinetEntity> getCabinetAll() {
        return HibernateUtil.getSession().openSession().createQuery("from  CabinetEntity ").list();
    }
}
