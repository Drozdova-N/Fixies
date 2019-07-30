package dao;

import entity.TypeOfEquipmentEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class MySqlType implements DAOType{

    private Session session;
    private Transaction transaction;
    @Override
    public void create(TypeOfEquipmentEntity type) {
        session = HibernateUtil.getSession().openSession();
        transaction = session.beginTransaction();
        session.save(type);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(TypeOfEquipmentEntity type) {
        session = HibernateUtil.getSession().openSession();
        transaction = session.beginTransaction();
        session.delete(type);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(TypeOfEquipmentEntity type) {
        session = HibernateUtil.getSession().openSession();
        transaction = session.beginTransaction();
        session.update(type);
        transaction.commit();
        session.close();
    }

    @Override
    public TypeOfEquipmentEntity getTypeById(int id) {
        return HibernateUtil.getSession().openSession().get(TypeOfEquipmentEntity.class, id);

    }

    @Override
    public List<TypeOfEquipmentEntity> getTypeAll() {
       return HibernateUtil.getSession().openSession().createQuery("from TypeOfEquipmentEntity ").list();
    }
}
