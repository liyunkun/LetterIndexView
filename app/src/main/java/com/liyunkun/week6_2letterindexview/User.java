package com.liyunkun.week6_2letterindexview;

/**
 * Created by liyunkun on 2016/9/27 0027.
 * 用于保存item中的用户信息
 */
public class User {

    private String userName;
    private String pinyi;
    private String firstLetter;
    private int userFace;

    public User() {
    }

    public User(String userName, String pinyi, String firstLetter, int userFace) {
        this.userName = userName;
        this.pinyi = pinyi;
        this.firstLetter = firstLetter;
        this.userFace = userFace;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPinyi() {
        return pinyi;
    }

    public void setPinyi(String pinyi) {
        this.pinyi = pinyi;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public int getUserFace() {
        return userFace;
    }

    public void setUserFace(int userFace) {
        this.userFace = userFace;
    }
}
