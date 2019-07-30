package dao;


import entity.StatusEntity;

import java.util.List;

public interface DAOStatus {
    void create(StatusEntity statusEntity);

    void delete(StatusEntity statusEntity);

    void update(StatusEntity statusEntity);

    StatusEntity getStatusById(int id);

    List<StatusEntity> getStatusAll();
}
