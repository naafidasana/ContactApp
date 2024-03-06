package isen.contactapp.database;

import isen.contactapp.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDao {

    public List<Person> fetchAllPersons() {
        List<Person> listOfPersons = new ArrayList<>();

        try(Connection connection = DataSourceFactory.getConnection()) {
            try(Statement statement = connection.createStatement()) {
                String sqlQuery = "SELECT * FROM person";
                try(ResultSet results = statement.executeQuery(sqlQuery)) {
                    while(results.next()) {
                        // Create person object and add to listOfPersons array list
                        Person person = new Person(
                                results.getInt("idperson"),
                                results.getString("firstname"),
                                results.getString("lastname"),
                                results.getString("nickname")
                        );

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
        try(Connection connection = DataSourceFactory.getConnection()) {
            String sqlQuery = "INSERT INTO person(lastname, firstname, nickname, phone_number, address, email_address, birth_date) VALUES (?,?,?,?,?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, person.getLastName());
                statement.setString(2, person.getFirstName());
                statement.setString(3, person.getNickname());
                statement.setString(4, person.getPhoneNumber());
                statement.setObject(5, person.getAddress());
                statement.setString(6, person.getEmailAddress());
                statement.setObject(7, person.getDateOfBirth());

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
}
