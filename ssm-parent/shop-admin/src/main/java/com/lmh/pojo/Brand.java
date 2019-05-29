package com.lmh.pojo;

import java.util.Date;

public class Brand {
    private Long brandId;

    private String brandName;

    private String telephone;

    private String brandWeb;

    private String brandLogo;

    private String brandDesc;

    private Integer brandStatus;

    private Integer brandOrder;

    private Date modifiedTime;

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getBrandWeb() {
        return brandWeb;
    }

    public void setBrandWeb(String brandWeb) {
        this.brandWeb = brandWeb == null ? null : brandWeb.trim();
    }

    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo == null ? null : brandLogo.trim();
    }

    public String getBrandDesc() {
        return brandDesc;
    }

    public void setBrandDesc(String brandDesc) {
        this.brandDesc = brandDesc == null ? null : brandDesc.trim();
    }

    public Integer getBrandStatus() {
        return brandStatus;
    }

    public void setBrandStatus(Integer brandStatus) {
        this.brandStatus = brandStatus;
    }

    public Integer getBrandOrder() {
        return brandOrder;
    }

    public void setBrandOrder(Integer brandOrder) {
        this.brandOrder = brandOrder;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}