package com.lmh.vo;


import com.lmh.pojo.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductVo extends Product {
    private String picUrl;
    private String brandName;

}
