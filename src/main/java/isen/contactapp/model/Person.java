package isen.contactapp.model;

import java.time.LocalDate;

/**
 * The Person class represents a contact person with various attributes
 * such as first name, last name, nickname, phone number, address, email address,
 * date of birth, country, city, street, and zip code.
 */
public class Person {

    private Integer id;
    private String firstName;
    private String lastName;
    private String nickname;
    private String phoneNumber;
    private String address;
    private String emailAddress;
    private LocalDate dateOfBirth;

    /**
     * Constructs a Person object with the specified ID, first name, last name, and nickname.
     *
     * @param id        The ID of the person.
     * @param firstName The first name of the person.
     * @param lastName  The last name of the person.
     * @param nickname  The nickname of the person.
     */
    public Person(Integer id, String firstName, String lastName, String nickname) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
    }

    /**
     * Default constructor for the Person class.
     */
    public Person() {
    }

    // Setters for Person class. This will be useful for updating contact details
    // of persons later

    /**
     * Sets the ID of the person.
     *
     * @param id The ID of the person.
     */
    public void setId(Integer id) { this.id = id; }

    /**
     * Sets the first name of the person.
     *
     * @param firstName The first name of the person.
     */

    public void setFirstName(String firstName) { this.firstName = firstName; }

    /**
     * Sets the last name of the person.
     *
     * @param lastName The last name of the person.
     */
    public void setLastName(String lastName) { this.lastName = lastName; }

    /**
     * Sets the phone number of the person.
     *
     * @param phoneNumber The phone number of the person.
     */
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    /**
     * Sets the address of the person.
     *
     * @param address The address of the person.
     */
    public void setAddress(String address) { this.address = address; }

    /**
     * Sets the nickname of the person.
     *
     * @param nickname The nickname of the person.
     */
    public void setNickname(String nickname) { this.nickname = nickname; }

    /**
     * Sets the email address of the person.
     *
     * @param emailAddress The email address of the person.
     */
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }

    /**
     * Sets the date of birth of the person.
     *
     * @param dateOfBirth The date of birth of the person.
     */
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    

    // Getters for Person class.
    /**
     * Gets the ID of the person.
     *
     * @return The ID of the person.
     */
    public Integer getId() { return this.id; }

    /**
     * Gets the first name of the person.
     *
     * @return The first name of the person.
     */
    public String getFirstName() { return this.firstName; }

    /**
     * Gets the last name of the person.
     *
     * @return The last name of the person.
     */
    public String getLastName() { return this.lastName; }

    /**
     * Gets the full name of the person (first name + last name).
     *
     * @return The full name of the person.
     */
    public String getFullName() { return this.firstName +" "+ this.lastName; }

    /**
     * Gets the nickname of the person.
     *
     * @return The nickname of the person.
     */
    public String getNickname() { return this.nickname; }

    /**
     * Gets the phone number of the person.
     *
     * @return The phone number of the person.
     */
    public String getPhoneNumber() { return this.phoneNumber; }

    /**
     * Gets the email address of the person.
     *
     * @return The email address of the person.
     */
    public String getEmailAddress() { return this.emailAddress; }

    /**
     * Gets the address of the person.
     *
     * @return The address of the person.
     */
    public String getAddress() { return this.address; }

    /**
     * Gets the date of birth of the person.
     *
     * @return The date of birth of the person.
     */
    public LocalDate getDateOfBirth() { return this.dateOfBirth; }

    /**
     * Returns a string representation of the person.
     *
     * @return A string representation of the person.
     */
    @Override
    public String toString() {
		return this.firstName +" "+this.phoneNumber+" "+this.emailAddress+" "+this.nickname;
    }
}
