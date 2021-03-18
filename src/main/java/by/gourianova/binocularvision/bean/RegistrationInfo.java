package by.gourianova.binocularvision.bean;

import java.time.LocalDate;
import java.util.Objects;

public class RegistrationInfo {
    private String name;
    private String surname;
    private String login;
    private  String password;
    private  String balance;

    private LocalDate dateTime;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public LocalDate getDateTime() {

        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }


    RegistrationInfo(){

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

//TODO: shoter number of values?
public  RegistrationInfo(String name, String surname,String login, String password, String balance, LocalDate dateTime){
    this.name=name;
    this.surname=surname;
    this.login=login;
    this.password=password;
    this.balance=balance;
    this.dateTime=dateTime;

}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return  Objects.equals(getLogin(), user.getLogin()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getName(), user.getFirstName()) &&
                Objects.equals(getSurname(), user.getLastName()) &&
                Objects.equals(getBalance(), user.getBalance()) &&
                Objects.equals(getDateTime(), user.getCreate_time());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogin(), getPassword(), getName(), getSurname(), getBalance(),  getDateTime());
    }

    @Override
    public String toString() {
        return "User{" +

                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + name + '\'' +
                ", lastName='" + surname + '\'' +
                ", balance=" + balance +
                ", dateTime=" + dateTime +
                "} " + super.toString();
    }

}
