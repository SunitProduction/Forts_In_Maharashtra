package com.example.nitesh_sunil.fortsinmaharashtra.ModelClass;

import java.util.ArrayList;

public class Package {
    private String PackageName;
    private Integer PackagePrice;
    private ArrayList<String> Cities;
    private ArrayList<Boolean> Facilities;
    private ArrayList<String> Images;

    public Package() {
    }

    public Package(String packageName, Integer packagePrice, ArrayList<String> cities, ArrayList<Boolean> facilities, ArrayList<String> images) {
        PackageName = packageName;
        PackagePrice = packagePrice;
        Cities = cities;
        Facilities = facilities;
        Images = images;
    }

    public String getPackageName() {
        return PackageName;
    }

    public void setPackageName(String packageName) {
        PackageName = packageName;
    }

    public Integer getPackagePrice() {
        return PackagePrice;
    }

    public void setPackagePrice(Integer packagePrice) {
        PackagePrice = packagePrice;
    }

    public ArrayList<String> getCities() {
        return Cities;
    }

    public void setCities(ArrayList<String> cities) {
        Cities = cities;
    }

    public ArrayList<Boolean> getFacilities() {
        return Facilities;
    }

    public void setFacilities(ArrayList<Boolean> facilities) {
        Facilities = facilities;
    }

    public ArrayList<String> getImages() {
        return Images;
    }

    public void setImages(ArrayList<String> images) {
        Images = images;
    }
}
