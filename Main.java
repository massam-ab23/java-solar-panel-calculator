import java.util.Scanner;

/**
 * Solar Panel Profit Calculator
 *
 * Calculates estimated solar energy production and
 * electricity earnings based on the date, sunrise time,
 * sunset time, and solar panel specifications.
 *
 * Supports dates in June and July and validates user input.
 */

public class Main {
    
    // Constants for solar panel calculations
    static final int NUM_OF_PANELS = 26;
    static final double PANEL_WIDTH = 1.7;
    static final int PANEL_HEIGHT = 1;
    static final int SOLAR_RADIATION = 166;  // Wh/m2/hour
    static final double EFFICIENCY = 0.2;  // 20% efficiency
    static final double ELECTRIC_PRICE = 0.9; // SEK per kWh
    static final int JUNE = 6;
    static final int JULY = 7;

    // Constants for validation
    static final int MAX_HOURS = 23;
    static final int MIN_HOURS = 0;
    static final int MAX_MINUTES = 59;
    static final int MIN_MINUTES = 0;
    static final int DAYS_IN_JUNE = 30;
    static final int DAYS_IN_JULY = 31;
    static final double MINUTES_IN_HOUR = 60.0; // Convert minutes to hours
    static final int WATT_TO_KILOWATT = 1000; // Convert Wh to kWh
    
    public static void main(final String[] args) {
        Scanner userInput = new Scanner(System.in);
        userInput.useDelimiter("[-:\\s]+"); // Delimiter for both date & time input

        int month;
        int day;
        int sunriseHour;
        int sunriseMinute;
        int sunsetHour;
        int sunsetMinute;

        // Step 2: Get and validate date input
        System.out.print("Enter today's date [mm-dd]> ");
        
        if (userInput.hasNextInt()) {
            month = userInput.nextInt();
        } else {
            System.out.println("Error! Invalid date format.");
            return;
        }
        
        if (userInput.hasNextInt()) {
            day = userInput.nextInt();
        } else {
            System.out.println("Error! Invalid date format.");
            return;
        }

        // Step 3: Validate month
        if (month != JUNE && month != JULY) {
            System.out.printf("Error! Invalid month. You have entered %d, but only 6 or 7 allowed.\n", month);
            return;
        }

        // Validate day based on month
        if ((month == JUNE && (day < 1 || day > DAYS_IN_JUNE)) || (month == JULY && (day < 1 || day > DAYS_IN_JULY))) {
            System.out.println("Error! Invalid day.");
            return;
        }

        // Step 4: Get and validate sunrise time
        System.out.print("Enter the time of sunrise [hh:mm]> ");
        
        if (userInput.hasNextInt()) {
            sunriseHour = userInput.nextInt();
        } else {
            System.out.println("Error! Invalid time format.");
            return;
        }

        if (userInput.hasNextInt()) {
            sunriseMinute = userInput.nextInt();
        } else {
            System.out.println("Error! Invalid time format.");
            return;
        }

        // Validate sunrise time
        if (sunriseHour < MIN_HOURS || sunriseHour > MAX_HOURS) {
            System.out.printf("Error! Invalid time. Hours must be between %d and %d.\n", MIN_HOURS, MAX_HOURS);
            return;
        }
        if (sunriseMinute < MIN_MINUTES || sunriseMinute > MAX_MINUTES) {
            System.out.printf("Error! Invalid time. Minutes must be between %d and %d.\n", MIN_MINUTES, MAX_MINUTES);
            return;
        }

        // Step 5: Get and validate sunset time
        System.out.print("Enter the time of sunset [hh:mm]> ");
        
        if (userInput.hasNextInt()) {
            sunsetHour = userInput.nextInt();
        } else {
            System.out.println("Error! Invalid time format.");
            return;
        }

        if (userInput.hasNextInt()) {
            sunsetMinute = userInput.nextInt();
        } else {
            System.out.println("Error! Invalid time format.");
            return;
        }

        // Validate sunset time
        if (sunsetHour < MIN_HOURS || sunsetHour > MAX_HOURS) {
            System.out.println("Error! Invalid time. Hours must be between 0 and 23.");
            return;
        }
        if (sunsetMinute < MIN_MINUTES || sunsetMinute > MAX_MINUTES) {
            System.out.println("Error! Invalid time. Minutes must be between 0 and 59.");
            return;
        }

        // Step 6: Ensuring sunset is after sunrise
        double sunriseTime = sunriseHour + (sunriseMinute / MINUTES_IN_HOUR);
        double sunsetTime = sunsetHour + (sunsetMinute / MINUTES_IN_HOUR);
        double sunHours = sunsetTime - sunriseTime;

        if (sunHours <= 0) {
            System.out.println("Error! Sunrise must be before sunset.");
            return;
        }

        // Step 7: Computing production
        double panelArea = PANEL_WIDTH * PANEL_HEIGHT;
        double production = SOLAR_RADIATION * EFFICIENCY * panelArea * sunHours * NUM_OF_PANELS / WATT_TO_KILOWATT; 
        double earnings = production * ELECTRIC_PRICE;

        // Step 8: Displaying results
        System.out.println("==========================================");
        System.out.printf("Sun hours: %.2f hours\n", sunHours);
        System.out.printf("The production on %02d/%d is: %.2f kWh to a value of: SEK %.2f\n", day, month, production, earnings);
    }
}
