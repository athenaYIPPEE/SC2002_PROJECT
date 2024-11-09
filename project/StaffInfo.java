package project;

import java.util.*;

public class StaffInfo {
    protected String name;
    private String dob;
    private Integer age;
    private String gender;
    private String hospitalId;
    private String role;


    // Constructor
    public StaffInfo(String hospitalId, Integer age, String role, String name, String dob, String gender) {
        this.hospitalId = hospitalId;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.age = age;
        this.role = role;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
