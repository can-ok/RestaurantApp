package com.resturant.restapi.Model;


import org.codehaus.jackson.annotate.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT_CATEGORY")
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Media getCategorymedia() {
        return categorymedia;
    }

    public void setCategorymedia(Media categorymedia) {
        this.categorymedia = categorymedia;
    }
}
