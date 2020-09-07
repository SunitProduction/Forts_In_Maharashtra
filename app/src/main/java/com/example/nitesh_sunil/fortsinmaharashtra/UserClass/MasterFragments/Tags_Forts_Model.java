package com.example.nitesh_sunil.fortsinmaharashtra.UserClass.MasterFragments;

import java.util.List;

public class Tags_Forts_Model {
    private String FortName;
    private String FortAddress;
    private String Description;
    private String BestSeason;
    private String Tags;
    private List<String> ImageUrl;

    public Tags_Forts_Model() {
    }

    public Tags_Forts_Model(String fortName, String fortAddress, String description, String bestSeason, String tags, List<String> imageUrl) {
        FortName = fortName;
        FortAddress = fortAddress;
        Description = description;
        BestSeason = bestSeason;
        Tags = tags;
        ImageUrl = imageUrl;
    }

    public String getFortName() {
        return FortName;
    }

    public void setFortName(String fortName) {
        FortName = fortName;
    }

    public String getFortAddress() {
        return FortAddress;
    }

    public void setFortAddress(String fortAddress) {
        FortAddress = fortAddress;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getBestSeason() {
        return BestSeason;
    }

    public void setBestSeason(String bestSeason) {
        BestSeason = bestSeason;
    }

    public String getTags() {
        return Tags;
    }

    public void setTags(String tags) {
        Tags = tags;
    }

    public List<String> getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
        ImageUrl = imageUrl;
    }
}
