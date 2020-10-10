package com.example.mad;

public class Lecturer {
    private String Namel;
    private String Agel;
    private String Emaill;
    private String Phonel;
    private  String Nicl;
    private String Passwordl;


    public Lecturer(){

}

    public Lecturer(String namel, String agel, String emaill, String phonel, String nicl, String passwordl) {
        Namel = namel;
        Agel = agel;
        Emaill = emaill;
        Phonel = phonel;
        Nicl = nicl;
        Passwordl = passwordl;
    }

    public String getNamel() {
        return Namel;
    }

    public void setNamel(String namel) {
        Namel = namel;
    }

    public String getAgel() {
        return Agel;
    }

    public void setAgel(String agel) {
        Agel = agel;
    }

    public String getEmaill() {
        return Emaill;
    }

    public void setEmaill(String emaill) {
        Emaill = emaill;
    }

    public String getPhonel() {
        return Phonel;
    }

    public void setPhonel(String phonel) {
        Phonel = phonel;
    }

    public String getNicl() {
        return Nicl;
    }

    public void setNicl(String nicl) {
        Nicl = nicl;
    }

    public String getPasswordl() {
        return Passwordl;
    }

    public void setPasswordl(String passwordl) {
        Passwordl = passwordl;
    }
}
