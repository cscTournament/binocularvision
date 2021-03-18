package by.gourianova.binocularvision.bean;

import java.time.LocalDate;
import java.util.Objects;

public class UserLoginInfo {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return  Objects.equals(getLogin(), user.getLogin()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getUpdateTime(), user.getCreate_time());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogin(), getPassword(),  getUpdateTime());
    }

    @Override
    public String toString() {
        return "User{" +

                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", UpdateTime=" + updateTime +
                "} " + super.toString();
    }

}
