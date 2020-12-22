package com.resturant.restapi.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@SQLDelete(
        sql="UPDATE TABLES SET deleted= true where id=?")
@Where(clause = "deleted=false")
public class Tables {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String title;

    private Boolean enabled;


    @Column(name="TABLECOUNT")
    private Integer tableCount;




}
