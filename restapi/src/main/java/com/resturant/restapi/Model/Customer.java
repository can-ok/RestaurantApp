package com.resturant.restapi.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@SQLDelete(
        sql="UPDATE Customer SET deleted= true where id=?")
@Where(clause = "deleted=false")
public class Customer extends BaseEntity{



    @Column(name="FIRSTNAME")
    private String firstName;

    @Column(name="LASTNAME")
    private String lastName;
    @Column(name="CITY")
    private String city;

    @Column(name="ADDRESS")
    private String address;

    @Column(name="PHONENUMBER")
    private String phoneNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "media_id")
    private Media media;
}
