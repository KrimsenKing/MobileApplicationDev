package com.example.scherr3143.personnel_app;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by scherr3143 on 12/8/2016.
 */
public class personData {

    private int PersonnelID;
    private int PictureID;
    private String Name;
    private String Address;
    private String Phone;
    private String Email;
    private String Position;
    private String SupervisorName;
    private String SupervisorRole;
    private Date BirthDate;
    private int Age;
    private char Married;

    public personData(int PersonnelID, int PictureID, String Name, String Address, String Phone, String Email,
                      String Position, String SupervisorName, String SupervisorRole, Date BirthDate,char Married)
    {}

    public int getPersonnelID() {
        return PersonnelID;
    }
    public void setPersonnelID(int personnelID) {
        PersonnelID = personnelID;
    }
    public int getPictureID() {
        return PictureID;
    }
    public void setPictureID(int pictureID) {
        PictureID = pictureID;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getAddress() {
        return Address;
    }
    public void setAddress(String address) {
        Address = address;
    }
    public String getPhone() {
        return Phone;
    }
    public void setPhone(String phone) {
        Phone = phone;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }
    public String getPosition() {
        return Position;
    }
    public void setPosition(String position) {
        Position = position;
    }
    public String getSupervisorName() {
        return SupervisorName;
    }
    public void setSupervisorName(String supervisorName) {
        SupervisorName = supervisorName;
    }
    public String getSupervisorRole() {
        return SupervisorRole;
    }
    public void setSupervisorRole(String supervisorRole) {
        SupervisorRole = supervisorRole;
    }
    public Date getBirthDate() {
        return BirthDate;
    }
    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }
    public int getAge() {
        return Age;
    }
    public void setAge() {
        int cYear = Calendar.getInstance().get(Calendar.YEAR);
        Calendar bYear = Calendar.getInstance();
        bYear.setTime(BirthDate);
        Age = cYear - bYear.get(Calendar.YEAR);
    }
    public char getMarried() {
        return Married;
    }
    public void setMarried(char married) {
        Married = married;
    }
}
