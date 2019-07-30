package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "equipment", schema = "databaseKT")
public class EquipmentEntity {
    private int idequipment;
    private String name;
    private String inventoryNumber;
    private CabinetEntity cabinetByCabinetIdcabinet;
    private StatusEntity statusEntity;
    private TypeOfEquipmentEntity typeOfEquipmentEntity;
    private String description = "";


    @Basic
    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Id
    @Column(name = "idequipment", nullable = false)
    public int getIdequipment() {
        return idequipment;
    }

    public void setIdequipment(int idequipment) {
        this.idequipment = idequipment;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "inv_number", nullable = false, length = 45)
    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipmentEntity that = (EquipmentEntity) o;
        return idequipment == that.idequipment &&
                Objects.equals(name, that.name) &&
                Objects.equals(inventoryNumber, that.inventoryNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idequipment, name, inventoryNumber);
    }

    @ManyToOne
    @JoinColumn(name = "cabinet_idcabinet", referencedColumnName = "idcabinet", nullable = false)
    public CabinetEntity getCabinetByCabinetIdcabinet() {
        return cabinetByCabinetIdcabinet;
    }

    public void setCabinetByCabinetIdcabinet(CabinetEntity cabinetByCabinetIdcabinet) {
        this.cabinetByCabinetIdcabinet = cabinetByCabinetIdcabinet;
    }

    @ManyToOne
    @JoinColumn(name = "status_idstatus")
    public StatusEntity getStatusEntity() {
        return statusEntity;
    }

    public void setStatusEntity(StatusEntity statusEntity) {
        this.statusEntity = statusEntity;
    }

    @ManyToOne
    @JoinColumn(name = "type_of_equipment_idtype")
    public TypeOfEquipmentEntity getTypeOfEquipmentEntity() {
        return typeOfEquipmentEntity;
    }

    public void setTypeOfEquipmentEntity(TypeOfEquipmentEntity typeOfEquipmentEntity) {
        this.typeOfEquipmentEntity = typeOfEquipmentEntity;
    }


    @Override
    public String toString() {
        return "EquipmentEntity{" +
                "idequipment=" + idequipment +
                ", name='" + name + '\'' +
                ", inventoryNumber='" + inventoryNumber + '\'' +
                ", cabinetByCabinetIdcabinet=" + cabinetByCabinetIdcabinet +
                '}';
    }

    @Transient
    public String getStatus() {
        return statusEntity.getNameStatus();
    }
    @Transient
    public String getType() {
        return typeOfEquipmentEntity.getTypeName();

    }

    @Transient
    public String getCabinet() {
        return cabinetByCabinetIdcabinet.getNumCabinet();
    }
}
