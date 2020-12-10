package com.resturant.restapi.Model;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(length = 1000000,name="FILECONTENT")
    private byte[] fileContent;

//    @JsonIgnore
//    @OneToMany(mappedBy = "categorymedia")
//    private List<ProductCategory> productCategoryList=new ArrayList<>();


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

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }


//    public List<ProductCategory> getProductCategoryList() {
//        return productCategoryList;
//    }
//
//    public void setProductCategoryList(List<ProductCategory> productCategoryList) {
//        this.productCategoryList = productCategoryList;
//    }
}
