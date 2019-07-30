package dao;

import entity.CabinetEntity;

import java.util.List;

public interface DAOCabinet {
    void create(CabinetEntity cabinetEntity);
    void delete(CabinetEntity cabinetEntity);
    void update(CabinetEntity cabinetEntity);
    CabinetEntity getCabinetById(int id);
    List<CabinetEntity> getCabinetAll();


}
