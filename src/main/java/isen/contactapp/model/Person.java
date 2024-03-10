package isen.contactapp.model;

import java.time.LocalDate;

public class Person {

    private Integer id;
    private String firstName;
    private String lastName;
    private String nickname;
    private String phoneNumber;
    private String address;
    private String emailAddress;
    private LocalDate dateOfBirth;
    
    private String country;
    private String city;
    private String street;
    private String zip;


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

    public void setAddress(String address) { this.address = address; }

    public void setNickname(String nickname) { this.nickname = nickname; }

    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }

    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    
    public void setCountry(String country) { this.country = country; }
    public void setCity(String city) { this.city = city; }
    public void setStreet(String street) { this.street = street; }
    public void setZip(String zip) { this.zip = zip; }
    
    
    
    

    // Getters for Person class.
    public Integer getId() { return this.id; }  

    public String getFirstName() { return this.firstName; }

    public String getLastName() { return this.lastName; }

    public String getFullName() { return this.firstName +" "+ this.lastName; }
    
    public String getNickname() { return this.nickname; }

    public String getPhoneNumber() { return this.phoneNumber; }

    public String getEmailAddress() { return this.emailAddress; }

    public String getAddress() { return this.address; }

    public LocalDate getDateOfBirth() { return this.dateOfBirth; }
    
    public String getCountry() { return this.country; }
    public String getCity() { return this.city; }
    public String getStreet() { return this.street; }
    public String getZip() { return this.zip; }


    @Override
    public String toString() {
		return this.firstName +" "+this.phoneNumber+" "+this.emailAddress+" "+this.nickname;
    }
}
