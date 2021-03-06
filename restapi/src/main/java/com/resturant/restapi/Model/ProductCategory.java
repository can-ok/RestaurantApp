package com.resturant.restapi.Model;


import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
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
public class ProductCategory extends BaseEntity implements Serializable {

    private String name;

    private String description;
//
//    @JsonIgnore
//    @ManyToMany(mappedBy = "productcategory",fetch = FetchType.LAZY)
//    private Set<Product> products;



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "media_id")
    private Media categorymedia;


}
