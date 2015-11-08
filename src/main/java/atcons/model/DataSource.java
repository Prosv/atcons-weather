package atcons.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "data_source")
public class DataSource extends BaseEntity implements Serializable {

    @Column(name = "url")
    private String url;

    @Column(name = "data_type")
    private String type;

    public DataSource() {
        super();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
