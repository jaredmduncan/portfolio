/* * * * * * * * * * *
    Date: 2-25-2020
    Author: Jared Duncan
    Teacher: [redacted]
    Class: CS249 Java II
    Description:
    This program displays use of polymorphism, inheritance, and class abstraction. It is designed to satisfy the
    requirements in the module 6 programming assignment.
 * * * * * * * * * * */

public class TrackVacations {
    public static void main (String[] args) {
        /* * * * * * * * * * *
            Create array of vacations.
         * * * * * * * * * * */
        Vacation[] vacations = new Vacation[12];

        /* * * * * * * * * * *
            Add in 'AllInclusive' arrays made without parameters supplied to constructor.
         * * * * * * * * * * */
        for (int i = 0; i < 3; i++) {
            AllInclusive v = new AllInclusive();
            v.setDepartureDate("January");
            v.setReturnDate("February");
            v.setDestination("Hawaii");
            v.setBrand("Epic");
            v.setRating(5);
            v.setPrice(1000);
            vacations[i] = v;
        }

        /* * * * * * * * * * *
            Add in 'AllInclusive' arrays made WITH parameters supplied to constructor.
         * * * * * * * * * * */
        for (int i = 3; i < 6; i++) {
            AllInclusive v = new AllInclusive("Hawaii", "January", "February", "Epic");
            v.setBrand("Epic");
            v.setRating(5);
            v.setPrice(1000);
            vacations[i] = v;
        }


        /* * * * * * * * * * *
            Add in 'Detailed' arrays made without parameters supplied to constructor.
         * * * * * * * * * * */
        for (int i = 6; i < 9; i++) {
            Detailed v = new Detailed();
            v.setDepartureDate("January");
            v.setReturnDate("February");
            v.setDestination("Hawaii");
            v.setHotelCost(1000);
            v.setAirfareCost(500);
            v.setMealCost(400);
            v.setTransfersCost(250);
            vacations[i] = v;
        }


        /* * * * * * * * * * *
            Add in 'AllInclusive' arrays made WITH parameters supplied to constructor.
         * * * * * * * * * * */
        for (int i = 9; i < 12; i++) {
            Detailed v = new Detailed("Hawaii", "January", "February",
                    1000, 500, 400, 250);
            vacations[i] = v;
        }

        /* * * * * * * * * * *
            Display the toString() result of each index in the array.
         * * * * * * * * * * */
        for (int i = 0; i < 12; i++) {
            System.out.println("INDEX: " + i);
            System.out.println(vacations[i].toString()+"\n");
        }

        /* * * * * * * * * * *
            Use getters of each index to display information.
         * * * * * * * * * * */
        for (int i = 0; i < 12; i++) {
            System.out.println("INDEX: " + i);
            System.out.println("Destination: " + vacations[i].getDestination());
            System.out.println("Departure Date: " + vacations[i].getDepartureDate());
            System.out.println("Return Date: " + vacations[i].getReturnDate());
            System.out.println("Total Cost: " + vacations[i].getTotalCost()+"\n");
        }
    }
}
