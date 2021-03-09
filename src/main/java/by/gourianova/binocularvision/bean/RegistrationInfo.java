package by.gourianova.binocularvision.bean;

public class RegistrationInfo { // Java Bean
    private String name;
    private String surname;
    private String email;
    private  String password;

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


public  RegistrationInfo(String name, String surname,String email, String password){
    setName(name);
    setSurname(surname);
    setEmail(email);
    setPassword(password);
}
}
