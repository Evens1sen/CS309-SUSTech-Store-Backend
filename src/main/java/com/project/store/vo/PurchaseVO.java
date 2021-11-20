package com.project.store.vo;

import com.project.store.enums.AddressType;
import lombok.Data;

import java.util.List;

@Data
public class PurchaseVO {

    // Product Part
    private String name;
    private String description;
    private String fileName;
    private Float price;

    // User Part
    private Integer userId;
    private String userName;
    private String email;
    private String mobile;

    //Address Part
    private AddressType addressType;
    private List<String> locationList;
}
