package dao;


import entity.TypeOfEquipmentEntity;

import java.util.List;

public interface DAOType {
    void create(TypeOfEquipmentEntity type);
    void delete(TypeOfEquipmentEntity type);
    void update(TypeOfEquipmentEntity type);
    TypeOfEquipmentEntity getTypeById(int id);
    List<TypeOfEquipmentEntity> getTypeAll();

}
