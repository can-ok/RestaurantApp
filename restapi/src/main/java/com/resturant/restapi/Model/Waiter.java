package com.resturant.restapi.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "WAITER_TABLE")
@SQLDelete(
        sql="UPDATE WAITER_TABLE  SET deleted= true where id=?")
@Where(clause = "deleted=false")
public class Waiter extends BaseEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;

    private String firstname;

    private String lastname;

    private String email;

    private Date birtdate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="media_id")
    private Media media;

}
