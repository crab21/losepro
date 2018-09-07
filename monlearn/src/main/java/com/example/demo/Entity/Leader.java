package com.example.demo.Entity;

    import org.bson.types.ObjectId;
    import org.springframework.core.annotation.AliasFor;
import org.springframework.data.annotation.Id;
    import org.springframework.data.annotation.PersistenceConstructor;

    import javax.naming.Name;

/**
 * Created by k on 2018/8/13.
 */
public class Leader {
    @Id
    private ObjectId id;
    private String names;
    private String partMent;

    public Leader() {
    }

    public Leader(String names, String partMent) {
        this.names = names;
        this.partMent = partMent;
    }

    public Leader(ObjectId id, String names, String partMent) {
        this.id = id;
        this.names = names;
        this.partMent = partMent;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getPartMent() {
        return partMent;
    }

    public void setPartMent(String partMent) {
        this.partMent = partMent;
    }
}
