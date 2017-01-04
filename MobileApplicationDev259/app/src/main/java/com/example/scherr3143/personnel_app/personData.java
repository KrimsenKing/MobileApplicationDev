package com.example.scherr3143.personnel_app;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by scherr3143 on 12/8/2016.
 */
public class personData extends MainActivity {

    private int PersonnelID;
    private int PictureID;
    private String Name;
    private String Address;
    private String Phone;
    private String Email;
    private String Position;
    private String SupervisorName;
    private String SupervisorRole;
    private String BirthDate;
    private int Age;
    private String Married;

    public personData(int PersonnelID, int PictureID, String Name, String Address, String Phone, String Email,
                      String Position, String SupervisorName, String SupervisorRole, String BirthDate,int Age,String Married)
    {
        setPersonnelID(PersonnelID);
        setPictureID(PictureID);
        setName(Name);
        setAddress(Address);
        setPhone(Phone);
        setEmail(Email);
        setPosition(Position);
        setSupervisorName(SupervisorName);
        setSupervisorRole(SupervisorRole);
        setBirthDate(BirthDate);
        setAge(Age);
        setMarried(Married);
    }

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
    public String getBirthDate() {
        return BirthDate;
    }
    public void setBirthDate(String birthDate) {
        BirthDate = birthDate;
    }
    public int getAge() {
        return Age;
    }
    public void setAge(int age) {
        Age = age;
    }
    public String getMarried() {
        return Married;
    }
    public void setMarried(String married) {
        Married = married;
    }
}
