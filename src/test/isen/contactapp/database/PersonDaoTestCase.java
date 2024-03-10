package contactapp.database;

import isen.contactapp.database.DataSourceFactory;
import isen.contactapp.database.PersonDao;
import isen.contactapp.model.Person;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

import java.sql.*;
import java.util.List;

public class PersonDaoTestCase {

    private PersonDao personDao = new PersonDao();
    private Person updatedPerson;

    @Before
    public void initDb() throws Exception {
        Connection connection = DataSourceFactory.getConnection();
        Statement statement = connection.createStatement();
        // Create `person` table if it does not exist already.
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS person (\r\n"
                + "idperson INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, \r\n"
                + "lastname VARCHAR(45) NOT NULL,  \r\n"
                + "firstname VARCHAR(45) NOT NULL,\r\n"
                + "nickname VARCHAR(45) NOT NULL,\r\n"
                + "phone_number VARCHAR(15) NULL,\r\n"
                + "address VARCHAR(200) NULL,\r\n"
                + "email_address VARCHAR(150) NULL,\r\n"
                + "birth_date DATE NULL);");

        // Delete previously inserted data (from other tests most likely) from data
        statement.executeUpdate("DELETE FROM person");

        // Insert 3 Persons into table. We only insert the non-null values for now.
        statement.executeUpdate("INSERT INTO person(idperson, lastname, firstname, nickname) VALUES (1, 'IBRAHIM', 'Naafi', 'Prof')");
        statement.executeUpdate("INSERT INTO person(idperson, lastname, firstname, nickname) VALUES (2, 'KUMAH', 'Emmanuel', 'EasyBlend')");
        statement.executeUpdate("INSERT INTO person(idperson, lastname, firstname, nickname) VALUES (3, 'SAAD', 'Mohammad', 'Mo')");

        // We close all resources used (statement and connection)
        statement.close();
        statement.close();
    }

    @Test
    public void shouldListAllPersons() {
        // WHEN
        List<Person> allPersons = personDao.fetchAllPersons();
        // THEN
        assertThat(allPersons).hasSize(3);
        assertThat(allPersons).extracting(Person::getId, Person::getLastName, Person::getFirstName, Person::getNickname).containsOnly(
                tuple(1, "IBRAHIM", "Naafi", "Prof"),
                tuple(2, "KUMAH", "Emmanuel", "EasyBlend"),
                tuple(3, "SAAD", "Mohammad", "Mo"));
    }

    @Test
    public void shouldGetPersonById() {
        // WHEN
        Integer id = 1;
        Person person = personDao.getPersonById(id);
        // THEN
        assertThat(person.getId()).isEqualTo(1);
        assertThat(person.getLastName()).isEqualTo("IBRAHIM");
        assertThat(person.getFirstName()).isEqualTo("Naafi");
        assertThat(person.getNickname()).isEqualTo("Prof");
    }

    @Test
    public void shouldNotGetUnknownPerson() {
        // WHEN
        Integer id = 10; // Does not exist in database
        Person person = personDao.getPersonById(id);
        // THEN
        assertThat(person).isNull();
    }

    @Test
    public void shouldAddPerson() throws Exception {
        // WHEN
        Person person = new Person();
        person.setLastName("Duval");
        person.setFirstName("Philip");
        person.setNickname("Awesome Java Teacher");
        Person addedPerson = personDao.addPerson(person);
        // THEN
        Connection connection = DataSourceFactory.getDataSource().getConnection();
        String sqlQuery = "SELECT * FROM person WHERE idperson=?";
        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        statement.setInt(1, person.getId());    // Since objects are passed by reference in Java, the id of the person object is updated for us.
        ResultSet result = statement.executeQuery();
        assertThat(result.next()).isTrue(); // Assert that the update has been successful.
        assertThat(result.getInt("idperson")).isNotNull();
        assertThat(result.getInt("idperson")).isEqualTo(person.getId());
        assertThat(addedPerson.getId()).isEqualTo(person.getId());  // Assert that the returned person and the one passsed to the function are the same
        assertThat(addedPerson.getId()).isNotNull();    // Assert that the id is not null;
        assertThat(result.next()).isFalse();    // Assert that there is no other element in the result set.
        result.close();
        statement.close();
        connection.close();
    }

    @Test
    public void shouldDeletePerson() {
        // WHEN
        Person personToDelete = personDao.getPersonById(3);    // Delete Mohammad from Database
        Integer rowsDeleted = personDao.deletePerson(personToDelete);
        // Then
        assertThat(rowsDeleted).isEqualTo(1);
        assertThat(personDao.getPersonById(3)).isNull();
    }

    @Test
    public void shouldUpdatePerson() {
        // WHEN
        Integer id = 1; // Update all fields of Naafi
        Person personToUpdate = personDao.getPersonById(id);
        // Update information
        personToUpdate.setFirstName("Naafi Dasana"); // Change from Naafi to Naafi Dasana;
        Person updatedPerson = personDao.updatePerson(personToUpdate, id);
        // THEN
        assertThat(updatedPerson.getFirstName()).isEqualTo(personToUpdate.getFirstName());
    }
}
