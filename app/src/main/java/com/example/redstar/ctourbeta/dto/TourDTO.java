package com.example.redstar.ctourbeta.dto;

/**
 * Created by RedStar on 04.04.2016.
 */
public class TourDTO {

    private Integer id;
    private String name;
    private String image;
    private String info;
    private String date;



    public TourDTO(String title) {
        this.name = title;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public String getImage() {
        return image;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
