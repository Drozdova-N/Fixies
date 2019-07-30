package dao;

import entity.CabinetEntity;
import entity.EquipmentEntity;
import entity.StatusEntity;
import entity.TypeOfEquipmentEntity;

import java.util.List;

public interface DAOEquipment {

    EquipmentEntity getEquipmentById(int id);

    List<EquipmentEntity> getEquipmentByName(String name);

    List<EquipmentEntity> getEquipmentByStatus(StatusEntity status);

    List<EquipmentEntity> getEquipmentByCabinet(CabinetEntity cabinet);

    List<EquipmentEntity> getEquipmentByType(TypeOfEquipmentEntity type);

    List<EquipmentEntity> getEquipmentAll();

    void deleteEquipment(EquipmentEntity entity);

    void createEquipment(EquipmentEntity equipment);

    void updateEquipment(EquipmentEntity equipment);


}
