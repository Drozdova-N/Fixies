package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cabinet", schema = "databaseKT", catalog = "")
public class CabinetEntity {
    private int idcabinet;
    private String numCabinet;

    @Id
    @Column(name = "idcabinet", nullable = false)
    public int getIdcabinet() {
        return idcabinet;
    }

    public void setIdcabinet(int idcabinet) {
        this.idcabinet = idcabinet;
    }

    @Basic
    @Column(name = "num_cabinet", nullable = false, length = 45)
    public String getNumCabinet() {
        return numCabinet;
    }

    public void setNumCabinet(String numCabinet) {
        this.numCabinet = numCabinet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CabinetEntity that = (CabinetEntity) o;
        return idcabinet == that.idcabinet &&
                Objects.equals(numCabinet, that.numCabinet);
    }

    @Override
    public String toString() {
        return  numCabinet ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcabinet, numCabinet);
    }
}
