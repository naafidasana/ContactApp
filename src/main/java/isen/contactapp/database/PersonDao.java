package isen.contactapp.database;

import isen.contactapp.model.Person;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The PersonDao class provides methods to interact with the database
 * for CRUD operations related to Person entities.
 */
public class PersonDao {

    /**
     * Fetches all persons from the database.
     *
     * @return A list of Person objects containing data fetched from the database.
     */
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
                        person.setAddress(results.getString("address"));


                        // Check if the other fields are not null and set their values correspondingly
                        if (results.getObject("address") != null)
                            person.setAddress(results.getString("address"));
                        if (results.getString("email_address") != null)
                            person.setEmailAddress(results.getString("email_address"));
                        if (results.getString("phone_number") != null)
                            person.setPhoneNumber(results.getString("phone_number"));
                        if (results.getDate("birth_date") != null)
                            person.setDateOfBirth(results.getDate("birth_date").toLocalDate());
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

    /**
     * Adds a new person to the database.
     *
     * @param person The Person object to be added to the database.
     * @return The Person object added to the database.
     */
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



                // Get date object
                LocalDate dobPerson = person.getDateOfBirth();
                if (dobPerson != null) {
                    statement.setObject(7, java.sql.Date.valueOf(dobPerson));
                } else {
                    // Set the date to the null object
                    statement.setObject(7, null);   // replacing null with dobPerson will give us the same result.
                }

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

    /**
     * Retrieves a person from the database by their ID.
     *
     * @param id The ID of the person to be retrieved.
     * @return The Person object corresponding to the given ID, or null if not found.
     */
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
                        if (result.getObject("address") != null) person.setAddress(result.getString("address"));
                        if (result.getString("email_address") != null) person.setEmailAddress(result.getString("email_address"));
                        if (result.getString("phone_number") != null) person.setPhoneNumber(result.getString("phone_number"));
                        if (result.getDate("birth_date") != null) person.setDateOfBirth(result.getDate("birth_date").toLocalDate());

                        return person;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Deletes a person from the database.
     *
     * @param person The Person object to be deleted from the database.
     * @return The number of rows deleted from the database.
     */
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

    /**
     * Updates a Person object in the database.
     *
     * @param person              The updated Person object.
     * @param idOfPersonToUpdate The ID of the person to be updated.
     * @return The updated Person object from the database.
     */
    public Person updatePerson(Person person, Integer idOfPersonToUpdate) {
        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            String sqlQuery = "UPDATE person SET firstname=?, lastname=?, nickname=?, phone_number=?, address=?, email_address=?, birth_date=? WHERE idperson=?";
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setString(1, person.getFirstName());
                statement.setString(2, person.getLastName());
                statement.setString(3, person.getNickname());
                statement.setString(4, person.getPhoneNumber());
                statement.setString(5, person.getAddress());
                statement.setString(6, person.getEmailAddress());
                
                // Convert LocalDate to SQL Date
                LocalDate dateOfBirth = person.getDateOfBirth();
                if (dateOfBirth != null) {
                    statement.setDate(7, java.sql.Date.valueOf(dateOfBirth));
                } else {
                    statement.setNull(7, Types.DATE);
                }

                statement.setInt(8, idOfPersonToUpdate);

                // Execute the update statement
                statement.executeUpdate();

                return getPersonById(person.getId()); // We fetch and return the person. Details should be the updated details though.
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
