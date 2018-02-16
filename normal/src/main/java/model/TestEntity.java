package model;

import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;

@Entity
@Table(name = "test")
@Indexed
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column()
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    private String name;

    @Column()
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    private Integer var;

    public TestEntity() { }

    private Long getId() {
        return id;
    }

    private String getName() {
        return name;
    }
    private Integer getVar() {
        return var;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVar(Integer var) {
        this.var = var;
    }

    @Override
    public String toString() {
        return String.format("User id: %d, name: %s", getId(), getName());
    }
}