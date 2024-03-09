package isen.contactapp.database;

import isen.contactapp.model.Address;
import isen.contactapp.model.Person;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonDao {

    public List<Person> fetchAllPersons() {
        List<Person> listOfPersons = new ArrayList<>();

        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sqlQuery = "SELECT * FROM person";
                try (ResultSet results = statement.executeQuery(sqlQuery)) {
                    while (results.next()) {
                        // Create person object and add to listOfPersons array list
                        Person person = new Person(
                                results.getInt("idperson"),
                                results.getString("firstname"),
                                results.getString("lastname"),
                                results.getString("nickname")
                               
                        );
                        
                        person.setPhoneNumber( results.getString("phone_number"));
                        person.setEmailAddress(results.getString("email_address"));
                        String street = results.getString("Street");
                        String city = results.getString("City");
                     
                        String zipCode = results.getString("Zip_Code");
                        
                        // Create an Address object and set it for the person
                        Address address = new Address(city, street, zipCode);
                        
                        person.setAddress(address);
                        

                        listOfPersons.add(person);
                    }
                }
            }
        } catch (SQLException e) {
            // Print stack trace to help us debug the issue.
            e.printStackTrace();
        }
        return listOfPersons;
    }

    public Person addPerson(Person person) {
        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            String sqlQuery = "INSERT INTO person(lastname, firstname, nickname, phone_number, address, email_address, birth_date) VALUES (?,?,?,?,?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, person.getLastName());
                statement.setString(2, person.getFirstName());
                statement.setString(3, person.getNickname());
                statement.setString(4, person.getPhoneNumber());
                statement.setObject(5, person.getAddress());
                statement.setString(6, person.getEmailAddress());
                statement.setObject(7, person.getDateOfBirth());
//                statement.setObject(8, person.getDateOfBirth());
                // Execute update query
                statement.executeUpdate();

                try(ResultSet ids = statement.getGeneratedKeys()) {
                    if (ids.next()) {
                        Integer generatedId = ids.getInt(1);
                        // We set id of person object passed to the method equal to the generated ID.
                        person.setId(generatedId);

                    } else {
                        throw new SQLException("Failed to add new person to contacts.");
                    }
                }

            }
        } catch (SQLException e) {
            // Print stack trace to help us debug
            e.printStackTrace();
        }
        return person;
    }

    public Person getPersonById(Integer id) {

        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            String sqlQuery = "SELECT * FROM person where idperson=?";
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setInt(1, id);
                try (ResultSet result = statement.executeQuery()) {
                    if (result.next()) {
                        Person person = new Person();
                        // Set fields to their respective values if they exist.
                        person.setId(id);
                        person.setLastName(result.getString("lastname"));
                        person.setFirstName(result.getString("firstname"));
                        person.setNickname(result.getString("nickname"));

                        // The following fields in the person table allows null values.
                        // As such, we check if they are non-null before attempting to set the corresponding fields in the person object.
                        if (result.getObject("address") != null) person.setAddress((Address) result.getObject("address"));
                        if (result.getString("email_address") != null) person.setEmailAddress(result.getString("email_address"));
                        if (result.getString("phone_number") != null) person.setPhoneNumber(result.getString("phone_number"));
                        if (result.getObject("birth_date") != null) person.setDateOfBirth((LocalDate) result.getObject("birth_date"));

                        return person;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer deletePerson(Person person) {
        Integer rowsDeleted = 0;
        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            String sqlQuery = "DELETE FROM person WHERE idperson=?";
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setInt(1, person.getId());

                // Execute delete operation
                rowsDeleted = statement.executeUpdate();  // An integer corresponding to the number of rows deleted.
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsDeleted;
    }
}
