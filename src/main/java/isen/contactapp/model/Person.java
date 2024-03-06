package isen.contactapp.model;

import java.time.LocalDate;

public class Person {

    private Integer id;
    private String firstName;
    private String lastName;
    private String nickname;
    private String phoneNumber;
    private Address address;
    private String emailAddress;
    private LocalDate dateOfBirth;

    public Person(Integer id, String firstName, String lastName, String nickname) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
    }

    public Person() {}

    // Setters for Person class. This will be useful for updating contact details
    // of persons later
    public void setId(Integer id) { this.id = id; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public void setAddress(Address address) { this.address = address; }

    public void setNickname(String nickname) { this.nickname = nickname; }

    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }

    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    // Getters for Person class.
    public Integer getId() { return this.id; }

    public String getFirstName() { return this.firstName; }

    public String getLastName() { return this.lastName; }

    public String getNickname() { return this.nickname; }

    public String getPhoneNumber() { return this.phoneNumber; }

    public String getEmailAddress() { return this.emailAddress; }

    public Address getAddress() { return this.address; }

    public LocalDate getDateOfBirth() { return this.dateOfBirth; }
}
