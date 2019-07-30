package dao;

import entity.CabinetEntity;
import entity.EquipmentEntity;
import entity.StatusEntity;
import entity.TypeOfEquipmentEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.loader.plan.build.internal.spaces.EntityQuerySpaceImpl;
import util.HibernateUtil;

import javax.management.Query;
import java.util.List;

public class MySqlEquipment implements DAOEquipment {

    private Session session;
    private Transaction transaction;

    @Override
    public EquipmentEntity getEquipmentById(int id) {
        return HibernateUtil.getSession().openSession().get(EquipmentEntity.class, id);
    }

    @Override
    public List<EquipmentEntity> getEquipmentByName(String name) {
        final String sql = "select  * from  equipment where  name = '" + name + "' ;";
        return HibernateUtil.getSession().openSession().createSQLQuery(sql).addEntity(EquipmentEntity.class).list();
    }

    @Override
    public List<EquipmentEntity> getEquipmentByStatus(StatusEntity status) {
        final String sql = "select * from equipment inner  join status on equipment.status_idstatus=status.idstatus where  status.name_status='" + status.getNameStatus() + "';";
        return HibernateUtil.getSession().openSession().createSQLQuery(sql).addEntity(EquipmentEntity.class).list();

    }

    @Override
    public List<EquipmentEntity> getEquipmentByCabinet(CabinetEntity cabinet) {
        final String sql = "select  * from  equipment inner  join cabinet c on equipment.cabinet_idcabinet = c.idcabinet where c.num_cabinet='" + cabinet.getNumCabinet() + "';";
        return HibernateUtil.getSession().openSession().createSQLQuery(sql).addEntity(EquipmentEntity.class).list();
    }

    @Override
    public List<EquipmentEntity> getEquipmentByType(TypeOfEquipmentEntity type) {
        final String sql = "select * from  equipment inner join type_of_equipment toe on equipment.type_of_equipment_idtype = toe.idtype where  type_name='" + type.getTypeName() + "';";
        return HibernateUtil.getSession().openSession().createSQLQuery(sql).addEntity(EquipmentEntity.class).list();
    }

    @Override
    public List<EquipmentEntity> getEquipmentAll() {
        return (List<EquipmentEntity>) HibernateUtil.getSession().openSession().createQuery("From EquipmentEntity").list();
    }

    public List<EquipmentEntity> searh(String s)
    {
        final  String sql = "select  * from equipment where name LIKE '%"+s+"%';";
        return HibernateUtil.getSession().openSession().createSQLQuery(sql).addEntity(EquipmentEntity.class).list();
    }


    @Override
    public void deleteEquipment(EquipmentEntity equipment) {
        session = HibernateUtil.getSession().openSession();
        transaction = session.beginTransaction();
        session.delete(equipment);
        transaction.commit();
        session.close();
    }


    @Override
    public void createEquipment(EquipmentEntity equipment) {

        session = HibernateUtil.getSession().openSession();
        transaction = session.beginTransaction();
        session.save(equipment);
        transaction.commit();
        session.close();
    }

    @Override
    public void updateEquipment(EquipmentEntity equipment) {
        session = HibernateUtil.getSession().openSession();
        transaction = session.beginTransaction();
        session.update(equipment);
        transaction.commit();
        session.close();
    }
}
