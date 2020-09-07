package com.example.nitesh_sunil.fortsinmaharashtra.UserClass;

import android.net.Uri;

import java.net.URL;

public class UserData {

    private String UserName;
    private String EmailAddress;
    private String Location_Address;
    private String MobNumber;
    private String ProfileImageUrl;
    private String Post;

    public UserData() {
    }

    public UserData(String userName, String emailAddress, String location_Address, String mobNumber, String profileImageUrl, String post) {
        UserName = userName;
        EmailAddress = emailAddress;
        Location_Address = location_Address;
        MobNumber = mobNumber;
        ProfileImageUrl = profileImageUrl;
        Post = post;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public String getLocation_Address() {
        return Location_Address;
    }

    public void setLocation_Address(String location_Address) {
        Location_Address = location_Address;
    }

    public String getMobNumber() {
        return MobNumber;
    }

    public void setMobNumber(String mobNumber) {
        MobNumber = mobNumber;
    }

    public String getProfileImageUrl() {
        return ProfileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        ProfileImageUrl = profileImageUrl;
    }

    public String getPost() {
        return Post;
    }

    public void setPost(String post) {
        Post = post;
    }
}
