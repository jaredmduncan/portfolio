/**************************
    Author: Jared Duncan
    Class: CIT249 Java II
    Teacher: [redacted]
    Description: This program reads and writes to and from CSV files while also having an interactive system
    to create, delete, and display data. This program satisfies module 10 for CIT249.
 **************************/

/**************************
    This class stores information about a specific volunteer, obtained from the csv file.
 **************************/
public class volunteersObject {
    /**************************
        Initialize our private variables.
     **************************/
    private String lastName;
    private String firstName;
    private String email;

    /**************************
        Our default constructor (which isn't used actually).
     **************************/
    public volunteersObject() {
        this.lastName = "";
        this.firstName = "";
        this.email = "";
    }

    /**************************
        This is our constructor which is supplied the volunteer's info.
     **************************/
    public volunteersObject(String lastName, String firstName, String email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
    }

    /**************************
        Getters for retrieving stored data.
     **************************/
    public String getLastName() {
        return this.lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getEmail() {
        return this.email;
    }

    /**************************
        Setters for putting stored data.
     **************************/
    public void setLastName(String lastName) {
        if (!(lastName.equals(""))) {
            this.lastName = lastName;
        }
    }

    public void setFirstName(String firstName) {
        if (!(firstName.equals(""))) {
            this.firstName = firstName;
        }
    }

    public void setEmail(String email) {
        if (!(email.equals(""))) {
            this.email = email;
        }
    }

    /**************************
        toString function, which isn't actually used.
     **************************/
    public String toString() {
        return "firstName: " + this.firstName + ", lastName: " + this.lastName + ", email: " + this.email;
    }
}
