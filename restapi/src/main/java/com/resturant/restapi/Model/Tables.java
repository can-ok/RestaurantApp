package com.resturant.restapi.Model;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tables {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String title;

    private Boolean enabled;


//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "category_id")
//    private TableCategory tableCategory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

//    public TableCategory getTableCategory() {
//        return tableCategory;
//    }
//
//    public void setTableCategory(TableCategory tableCategory) {
//        this.tableCategory = tableCategory;
//    }


}
