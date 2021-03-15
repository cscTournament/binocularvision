package by.gourianova.binocularvision.bean;

import java.time.LocalDate;

public class RegistrationInfo { // Java Bean
    private String name;
    private String surname;
    private String email;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


public  RegistrationInfo(String name, String surname,String email, String password, String balance, LocalDate dateTime){
    setName(name);
    setSurname(surname);
    setEmail(email);
    setPassword(password);
    setBalance(balance);
    setDateTime(dateTime);
}
}
