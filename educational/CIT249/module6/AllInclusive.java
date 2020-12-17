/* * * * * * * * * * *
    Date: 2-25-2020
    Author: Jared Duncan
    Teacher: [redacted]
    Class: CS249 Java II
    Description:
    This program displays use of polymorphism, inheritance, and class abstraction. It is designed to satisfy the
    requirements in the module 6 programming assignment.
 * * * * * * * * * * */

public class AllInclusive extends Vacation {
    /* * * * * * * * * * *
        Initialize data members.
     * * * * * * * * * * */
    private String brand;
    private int starRating;
    private double price;

    /* * * * * * * * * * *
        Constructor functions.
     * * * * * * * * * * */
    public AllInclusive() {
        this.destination = "";
        this.departureDate = "";
        this.returnDate = "";
        this.brand = "";
        this.starRating = 0;
        this.price = 0;
    }
    public AllInclusive(String destination, String departureDate, String returnDate, String brand) {
        this.destination = destination;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.brand = brand;
        this.starRating = 0;
        this.price = 0;
    }

    /* * * * * * * * * * *
        Getter functions.
     * * * * * * * * * * */
    public String getBrand() {
        return this.brand;
    }

    public int getRating() {
        return this.starRating;
    }

    /* * * * * * * * * * *
        Setter functions.
     * * * * * * * * * * */
    public void setBrand(String brand) {
        if (!brand.equals("")) { this.brand = brand; }
    }

    public void setRating(int rating) {
        if (rating >= 0 && rating <= 5) { this.starRating = rating; }
    }

    public void setPrice(double price) {
        if (price >= 0) { this.price = price; }
    }

    /* * * * * * * * * * *
        Data display functions.
     * * * * * * * * * * */
    public String toString() {
        String stars = "";
        for (int i = 0; i < starRating; i++) {
            stars += "*";
        }
        String message = "Brand: " + this.brand + ", Star Rating: " + stars + ", Price: " + this.price;
        message = "Destination: " + this.destination + ", Departure Date: " +
                this.departureDate + ", Return Date: " + this.returnDate + ", " + message;
        return message;
    }
    public double getTotalCost() {
        return this.price;
    }
}
