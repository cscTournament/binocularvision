package by.gourianova.binocularvision.bean;

import java.util.Objects;

public class UserChangeInfo {
    private int id;
    private String login;
    private String password;

    public UserChangeInfo(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return  Objects.equals(getLogin(), user.getLogin()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogin(), getPassword(),  getId());
    }

    @Override
    public String toString() {
        return "User{" +
                ", Id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                "} " + super.toString();
    }

}
