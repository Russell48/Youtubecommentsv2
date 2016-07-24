package veve.youtubecommentsv2;

import java.util.ArrayList;

/**
 * Created by Russell on 7/18/2016.
 */
public class User {

    private String uniqueDeviceIdentifier;
    private String email;
    private String name;
    private String profileImageURL;
    private ArrayList<Integer> favorites = new ArrayList<>();

    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public User(String email, String profileImageURL, String name) {
        this.email = email;
        this.profileImageURL = profileImageURL;
        this.name = name;
    }

    public String getUniqueDeviceIdentifier() {
        return uniqueDeviceIdentifier;
    }

    public void setUniqueDeviceIdentifier(String uniqueDeviceIdentifier) {
        this.uniqueDeviceIdentifier = uniqueDeviceIdentifier;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImageURL() {
        return profileImageURL;
    }

    public void setProfileImageURL(String profileImageURL) {
        this.profileImageURL = profileImageURL;
    }

    public ArrayList<Integer> getFavorites() {
        return favorites;
    }

    public void setFavorites(ArrayList<Integer> favorites) {
        this.favorites = favorites;
    }
}
