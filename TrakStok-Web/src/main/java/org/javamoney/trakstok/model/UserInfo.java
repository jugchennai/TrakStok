package org.javamoney.trakstok.model;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import org.agorava.empireavenue.model.ProfileInfo;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class UserInfo implements Serializable {

	private String userName;
	private String displayName = "Guest";
	private String shortImage;
	private String fullImage;
	private boolean loggedIn = false;
	private Currency preferredCurrency;
	private ProfileInfo profileInfo;

	public ProfileInfo getProfileInfo() {
		return profileInfo;
	}

	public void setProfileInfo(ProfileInfo profileInfo) {
		this.profileInfo = profileInfo;

		setUserName(profileInfo.getTicker());
		setDisplayName(profileInfo.getFirstName());

	}

	public Currency getPreferredCurrency() {
		return preferredCurrency;
	}

	public void setPreferredCurrency(Currency preferredCurrency) {
		this.preferredCurrency = preferredCurrency;
	}

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
