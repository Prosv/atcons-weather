package atcons.model;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    protected BaseEntity() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
