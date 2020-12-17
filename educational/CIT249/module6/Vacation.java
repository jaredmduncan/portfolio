/* * * * * * * * * * *
    Date: 2-25-2020
    Author: Jared Duncan
    Teacher: [redacted]
    Class: CS249 Java II
    Description:
    This program displays use of polymorphism, inheritance, and class abstraction. It is designed to satisfy the
    requirements in the module 6 programming assignment.
 * * * * * * * * * * */

public abstract class Vacation {
    /* * * * * * * * * * *
        Initialize data members.
     * * * * * * * * * * */
    protected String destination;
    protected String departureDate;
    protected String returnDate;

    /* * * * * * * * * * *
        Constructor functions.
     * * * * * * * * * * */
    public Vacation() {
        this.destination = "";
        this.departureDate = "";
        this.returnDate = "";
    }
    public Vacation(String destination, String departureDate, String returnDate) {
        this.destination = destination;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
    }

    /* * * * * * * * * * *
        Getter functions.
     * * * * * * * * * * */
    public String getDestination() {
        return this.destination;
    }
    public String getDepartureDate() {
        return this.departureDate;
    }
    public String getReturnDate() {
        return this.returnDate;
    }

    /* * * * * * * * * * *
        Setter functions.
     * * * * * * * * * * */
    public void setDestination(String destination) {
        if (!destination.equals("")) { this.destination = destination; }
    }
    public void setDepartureDate(String departureDate) {
        if (!departureDate.equals("")) { this.departureDate = departureDate; }
    }
    public void setReturnDate(String returnDate) {
        if (!returnDate.equals("")) { this.returnDate = returnDate; }
    }

    /* * * * * * * * * * *
        Data displaying functions.
     * * * * * * * * * * */
    public String toString() {
        String message = "Destination: " + this.destination + ", DepartureDate: " +
                this.departureDate + ", Return Date: " + this.returnDate;
        return message;
    }
    public abstract double getTotalCost();
}
