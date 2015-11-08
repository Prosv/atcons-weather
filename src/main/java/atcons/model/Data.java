package atcons.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "data")
public class Data extends BaseEntity implements Serializable {

    @Column(name = "data")
    private String data;

    @OneToOne
    @JoinColumn(name = "source_id")
    private DataSource source;

    @Column(name = "last_update")
    private Timestamp last_update;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public DataSource getSource() {
        return source;
    }

    public void setSource(DataSource source) {
        this.source = source;
    }

    public Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }
}
