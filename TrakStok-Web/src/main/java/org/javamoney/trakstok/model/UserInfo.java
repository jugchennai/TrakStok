package org.javamoney.trakstok.model;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import java.io.Serializable;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class UserInfo implements Serializable {

    private String userName;
    private String displayName = "Guest";
    private String shortImage;
    private String fullImage;
    private boolean loggedIn = false;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getShortImage() {
        return shortImage;
    }

    public void setShortImage(String shortImage) {
        this.shortImage = shortImage;
    }

    public String getFullImage() {
        return fullImage;
    }

    public void setFullImage(String fullImage) {
        this.fullImage = fullImage;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

}
