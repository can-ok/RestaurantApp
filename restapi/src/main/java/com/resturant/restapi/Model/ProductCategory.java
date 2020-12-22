package com.resturant.restapi.Model;


import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "PRODUCT_CATEGORY")
@SQLDelete(
        sql="UPDATE PRODUCT_CATEGORY SET deleted= true where id=?")
@Where(clause = "deleted=false")
public class ProductCategory {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String name;

    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "productcategory")
    private Set<Product> products;



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "media_id")
    private Media categorymedia;


    public ProductCategory(Integer id, String name) {
        this.id = id;
        this.name = name;
    }


}
