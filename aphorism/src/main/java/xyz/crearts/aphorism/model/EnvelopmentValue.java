package xyz.crearts.aphorism.model;

import javax.persistence.*;

@Entity(name="EnvelopmentValues")
@Table(indexes = {@Index(columnList="name"), @Index(columnList="name")})
public class EnvelopmentValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String val;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
