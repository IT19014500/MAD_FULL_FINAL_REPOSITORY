package com.example.mad;

import android.widget.EditText;

public class Student {
        private String Names;
        private String Ages;
        private String Emails;
        private String Phones;
        private  String Nics;
        private String Passwords;


        public Student(){

        }


    public Student(String name, String age, String email, String phone, String nic, String password) {
         this.Names=name;
        this.Ages=age;
        this.Emails=email;
        this.Phones=phone;
      this.Nics=nic;
        this.Passwords=password;
    }


    public String getNames() {
        return Names;
    }

    public void setNames(String names) {
        this.Names = names;
    }

    public String getAges() {
        return Ages;
    }

    public void setAges(String ages) {
        this.Ages = ages;
    }

    public String getEmails() {
        return Emails;
    }

    public void setEmails(String emails) {
        this.Emails = emails;
    }

    public String getPhones() {
        return Phones;
    }

    public void setPhones(String phones) {
        this.Phones = phones;
    }

    public String getNics() {
        return Nics;
    }

    public void setNics(String nics) {
        this.Nics = nics;
    }

    public String getPasswords() {
        return Passwords;
    }

    public void setPasswords(String passwords) {
        this.Passwords = passwords;
    }
}





























