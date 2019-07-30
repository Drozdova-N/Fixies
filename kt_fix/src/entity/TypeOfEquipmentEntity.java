package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "type_of_equipment", schema = "databaseKT", catalog = "")
public class TypeOfEquipmentEntity {
    private int idtype;
    private String typeName;

    @Id
    @Column(name = "idtype", nullable = false)
    public int getIdtype() {
        return idtype;
    }

    public void setIdtype(int idtype) {
        this.idtype = idtype;
    }

    @Basic
    @Column(name = "type_name", nullable = false, length = 45)
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeOfEquipmentEntity that = (TypeOfEquipmentEntity) o;
        return idtype == that.idtype &&
                Objects.equals(typeName, that.typeName);
    }

    @Override
    public String toString() {
        return  typeName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idtype, typeName);
    }
}
