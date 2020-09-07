package com.example.nitesh_sunil.fortsinmaharashtra.ModelClass;

import java.util.ArrayList;


public class Place {
    private String PlaceName;
    private String PlaceAddress;
    private String PlaceDesc;
    private ArrayList<String> BestSeason;
    private ArrayList<String> ImageUrls;
    private Integer LikeCount;

    public Place() {
    }

    public Place(String placeName, String placeAddress, String placeDesc, ArrayList<String> bestSeason, ArrayList<String> imageUrls, Integer likeCount) {
        PlaceName = placeName;
        PlaceAddress = placeAddress;
        PlaceDesc = placeDesc;
        BestSeason = bestSeason;
        ImageUrls = imageUrls;
        LikeCount = likeCount;
    }

    public void setPlaceName(String placeName) {
        PlaceName = placeName;
    }

    public void setPlaceAddress(String placeAddress) {
        PlaceAddress = placeAddress;
    }

    public void setPlaceDesc(String placeDesc) {
        PlaceDesc = placeDesc;
    }

    public void setBestSeason(ArrayList<String> bestSeason) {
        BestSeason = bestSeason;
    }

    public void setImageUrls(ArrayList<String> imageUrls) {
        ImageUrls = imageUrls;
    }

    public void setLikeCount(Integer likeCount) {
        LikeCount = likeCount;
    }

    public String getPlaceName() {
        return PlaceName;
    }

    public String getPlaceAddress() {
        return PlaceAddress;
    }

    public String getPlaceDesc() {
        return PlaceDesc;
    }

    public ArrayList<String> getBestSeason() {
        return BestSeason;
    }

    public ArrayList<String> getImageUrls() {
        return ImageUrls;
    }

    public Integer getLikeCount() {
        return LikeCount;
    }
}
