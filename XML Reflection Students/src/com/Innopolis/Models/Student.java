package com.Innopolis.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by admin on 08.06.2017.
 */
public class Student implements Serializable {
    private String name;
    private String firstName;
    private String surname;
    private Date dateOfBirth;
    private final Long id;
    private Long groupID;
    private List<Contact> contacts;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public Long getGroupID() {
        return groupID;
    }

    public void setGroupID(Long groupID) {
        this.groupID = groupID;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public Student(String name, String firstName, String surname, Date dateOfBirth, Long groupID) {
        this.name = name;
        this.firstName = firstName;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.groupID = groupID;
        this.id=System.currentTimeMillis();
        this.contacts = new ArrayList<>();
    }

    @Override
    public int hashCode() {
        return (int) (21+id*42);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Student)) return false;
        if (this.id != ((Student) obj).getId()) return false;
        return true;
    }
}
