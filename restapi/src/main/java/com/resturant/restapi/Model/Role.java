package com.resturant.restapi.Model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name="ROLES")
@SQLDelete(
        sql="UPDATE ROLES SET deleted= true where id=?")
@Where(clause = "deleted=false")
public class Role extends BaseEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
