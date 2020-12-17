/* * * * * * * * * * *
    Date: 2-25-2020
    Author: Jared Duncan
    Teacher: [redacted]
    Class: CS249 Java II
    Description:
    This program displays use of polymorphism, inheritance, and class abstraction. It is designed to satisfy the
    requirements in the module 6 programming assignment.
 * * * * * * * * * * */

public class Detailed extends Vacation {
    /* * * * * * * * * * *
        Initialize data members.
     * * * * * * * * * * */
    private double hotel;
    private double airfare;
    private double meals;
    private double transfers;

    /* * * * * * * * * * *
        Constructor functions.
     * * * * * * * * * * */
    public Detailed() {
        this.destination = "";
        this.departureDate = "";
        this.returnDate = "";
        this.hotel = 0;
        this.airfare = 0;
        this.meals = 0;
        this.transfers = 0;
    }
    public Detailed(String destination, String departureDate, String returnDate,
                    double hotel, double airfare, double meals, double transfers) {
        this.destination = destination;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.hotel = hotel;
        this.airfare = airfare;
        this.meals = meals;
        this.transfers = transfers;
    }

    /* * * * * * * * * * *
        Getter functions.
     * * * * * * * * * * */
    public double getHotelCost() {
        return this.hotel;
    }

    public double getAirfareCost() {
        return this.airfare;
    }

    public double getMealCost() {
        return this.meals;
    }

    public double getTransfersCost() {
        return this.transfers;
    }

    /* * * * * * * * * * *
        Setter functions.
     * * * * * * * * * * */
    public void setHotelCost(double hotel) {
        if (hotel >= 0) { this.hotel = hotel; }
    }

    public void setAirfareCost(double airfare) {
        if (airfare >= 0) { this.airfare = airfare; }
    }

    public void setMealCost(double meals) {
        if (meals >= 0) { this.meals = meals; }
    }

    public void setTransfersCost(double transfers) {
        if (transfers >= 0) { this.transfers = transfers; }
    }

    /* * * * * * * * * * *
        Data displaying functions.
     * * * * * * * * * * */
    public String toString() {
        String message = "Hotel: " + this.hotel + ", Air fare: " + this.airfare +
                ", Meals: " + this.meals + ", Transfers: " + this.transfers;
        message = "Destination: " + this.destination + ", Departure Date: " +
                this.departureDate + ", Return Date: " + this.returnDate + ", " + message;
        return message;
    }
    public double getTotalCost() {
        return this.hotel + this.airfare + this.meals + this.transfers;
    }
}
