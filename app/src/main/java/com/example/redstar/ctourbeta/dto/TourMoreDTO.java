package com.example.redstar.ctourbeta.dto;

/**
 * Created by RedStar on 22.05.2016.
 */
public class TourMoreDTO {
    private Integer id;
    private String name;
    private String image;
    private String org;
    private String tel;
    private String address;
    private String site;
    private String date;
    private String info;


    public String getInfo() {
        return info;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getOrg() {
        return org;
    }

    public String getTel() {
        return tel;
    }

    public String getAddress() {
        return address;
    }

    public String getSite() {
        return site;
    }

    public String getDate() {
        return date;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}


