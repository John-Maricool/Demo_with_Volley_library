package com.example.practicewithrecyclerviewpicaassoandvolley;

public class EachItemAttributes {

    private String name_cat;
    private int Number_likes;
    private String image_ID;

    public EachItemAttributes(String name_cat1, int number_likes, String  image_id){
        name_cat = name_cat1;
        Number_likes = number_likes;
        image_ID = image_id;
    }

    public String getName_cat() {
        return name_cat;
    }

    public int getNumber_likes() {
        return Number_likes;
    }

    public String getImage_ID() {
        return image_ID;
    }
}
