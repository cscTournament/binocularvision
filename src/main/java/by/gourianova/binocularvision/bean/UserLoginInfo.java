package by.gourianova.binocularvision.bean;

import java.time.LocalDate;

public class UserLoginInfo {// Java Bean
    LocalDate updateTime;
    private String login;
    private String password;

    public UserLoginInfo(String login, String password, LocalDate updateTime) {
        this.login = login;
        this.password = password;
        this.updateTime = updateTime;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDate updateTime) {
        this.updateTime = updateTime;
    }


}
