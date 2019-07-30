package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "status", schema = "databaseKT")
public class StatusEntity {
    private int idstatus;
    private String nameStatus;

    @Id
    @Column(name = "idstatus", nullable = false)
    public int getIdstatus() {
        return idstatus;
    }

    public void setIdstatus(int idstatus) {
        this.idstatus = idstatus;
    }

    @Basic
    @Column(name = "name_status", nullable = false, length = 45)
    public String getNameStatus() {
        return nameStatus;
    }

    public void setNameStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusEntity that = (StatusEntity) o;
        return idstatus == that.idstatus &&
                Objects.equals(nameStatus, that.nameStatus);
    }

    @Override
    public String toString() {
        return  nameStatus ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idstatus, nameStatus);
    }
}
