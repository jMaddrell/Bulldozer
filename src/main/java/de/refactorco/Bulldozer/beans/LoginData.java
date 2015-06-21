package de.refactorco.Bulldozer.beans;

/**
 * Bulldozer,
 * Edited: 20/06/15.
 */
public class LoginData {
    private String userName;
    private String password;
    private boolean rememberMe;

    public LoginData() {
    }

    public LoginData(String userName, String password, boolean rememberMe) {
        this.userName = userName;
        this.password = password;
        this.rememberMe = rememberMe;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}