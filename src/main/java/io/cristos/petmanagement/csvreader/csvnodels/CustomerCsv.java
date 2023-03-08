package io.cristos.petmanagement.csvreader.csvnodels;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import io.cristos.petmanagement.models.enums.Gender;

import java.time.LocalDate;

public class CustomerCsv {
    @CsvBindByName(column = "Index")
    private Integer index;
    @CsvBindByName(column = "User_Id")
    private String userId;
    @CsvBindByName(column = "First_Name")
    private String firstName;
    @CsvBindByName(column = "Last_Name")
    private String lastName;
    @CsvBindByName(column = "Sex")
    private Gender gender;
    @CsvDate(value = "yyyy-MM-dd")
    @CsvBindByName(column = "Date_of_birth")
    private LocalDate bornAt;
    @CsvBindByName(column = "Email")
    private String email;
    @CsvBindByName(column = "Phone")
    private String phone;
    @CsvBindByName(column = "Job_Title")
    private String jobTitle;

    public CustomerCsv() {
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBornAt() {
        return bornAt;
    }

    public void setBornAt(LocalDate bornAt) {
        this.bornAt = bornAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
