package com.resturant.restapi.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@SQLDelete(
        sql="UPDATE MEDIA SET deleted= true where id=?")
@Where(clause = "deleted=false")
public class Media extends BaseEntity implements Serializable {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;

    private String name;

    @Column(length = 1000000,name="FILECONTENT")
    private byte[] fileContent;

}
