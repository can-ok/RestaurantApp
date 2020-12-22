package com.resturant.restapi.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@SQLDelete(
        sql="UPDATE PRODUCT SET deleted= true where id=?")
@Where(clause = "deleted=false")
public class Product implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String  title;

    private String description;

    private Integer price;



    //@JsonManagedReference
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="TBL_CATEGORY_Product",joinColumns = @JoinColumn(name="product_id"),inverseJoinColumns = @JoinColumn(name="category_id"))
    private Set<ProductCategory> productcategory=new HashSet<>();


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "media_id")
    private Media media;



}
