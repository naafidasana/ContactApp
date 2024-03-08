package isen.contactapp.model;

public class Address {
 
    private String city;
    private String zipCode;
    private String street;

    public Address(String city, String street, String zipCode) {
        
        this.city = city;
        this.zipCode = zipCode;
        this.street = street;
    }

    @Override
    public String toString() {
//        return String.format(
//                "%s\n%s, %s %s",
//                this.city,
//                this.street,
//                this.zipCode
//              
//        );
    	 return  this.city+" "+ this.street+" "+ this.zipCode;
    }
}
