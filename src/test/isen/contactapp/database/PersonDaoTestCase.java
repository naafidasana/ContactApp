package contactapp.database;

import isen.contactapp.database.DataSourceFactory;
import isen.contactapp.database.PersonDao;
import isen.contactapp.model.Person;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class PersonDaoTestCase {

    private PersonDao personDao = new PersonDao();

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
        List<Person> allPersons = personDao.fetchAllPersons();
        assertThat(allPersons).hasSize(3);
        assertThat(allPersons).extracting(Person::getId, Person::getLastName, Person::getFirstName, Person::getNickname).containsOnly(
                tuple(1, "IBRAHIM", "Naafi", "Prof"),
                tuple(2, "KUMAH", "Emmanuel", "EasyBlend"),
                tuple(3, "SAAD", "Mohammad", "Mo"));
    }
}
