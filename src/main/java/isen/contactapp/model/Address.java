package isen.contactapp.model;

public class Address {
    private String streetAddress;
    private String city;
    private String zipCode;
    private String country;

    public Address(String streetAddress, String city, String zipCode, String country) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
    }

    @Override
    public String toString() {
        return String.format(
                "%s\n%s, %s %s",
                this.streetAddress,
                this.city,
                this.zipCode,
                this.country
        );
    }
}
