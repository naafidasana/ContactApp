package isen.contactapp.model;

public class Address{
 
    private String city = "";
    private String zipCode = "";
    private String street = "";

    private String country = "";

    public Address(String city, String street, String zipCode, String country) {
        
        this.city = city;
        this.zipCode = zipCode;
        this.street = street;
        this.country = country;
    }

    public Address() {
        // Empty constructor.
    }

    // Setters
    public void setCity(String city) { this.city = city; }

    public void setZipCode(String zipCode) { this.zipCode = zipCode; }

    public void setStreet(String street) { this.street = street; }

    public void setCountry(String country) { this.country = country; }

    // Getters
    public String getCity() {return this.city; }

    public String getZipCode() { return this.zipCode; }

    public String getStreet() { return this.street; }

    public String getCountry() {return this.country; }

    @Override
    public String toString() {
        return String.format(
                "%s\n%s, %s %s",
                this.street,
                this.city,
                this.zipCode,
                this.country
        );
    }
}
