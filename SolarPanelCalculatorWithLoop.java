import java.util.Scanner;

/**
 * Solar Panel Profit Calculator - Loop Version
 *
 * Calculates estimated solar energy production and
 * electricity earnings based on user-provided dates,
 * sunrise times, and sunset times.
 *
 * Uses while loops to validate user input and
 * allow users to retry invalid entries.
 */

public class SolarPanelCalculatorWithLoop {

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

        int month = 0, day = 0;
        int sunriseHour = 0, sunriseMinute = 0;
        int sunsetHour = 0, sunsetMinute = 0;

        // Step 1: Get and validate date input
        while (true) {
            System.out.print("Enter today's date [mm-dd]> ");
            if (userInput.hasNextInt()) {
                month = userInput.nextInt();
                if (userInput.hasNextInt()) {
                    day = userInput.nextInt();
                    
                    if ((month == JUNE && day >= 1 && day <= DAYS_IN_JUNE) || 
                        (month == JULY && day >= 1 && day <= DAYS_IN_JULY)) {
                        break; // Valid input
                    } else {
                        System.out.println("Error! Invalid day for the given month.");
                    }
                } else {
                    System.out.println("Error! Invalid day format.");
                    userInput.next(); // Consume invalid input
                }
            } else {
                System.out.println("Error! Invalid month format.");
                userInput.next(); // Consume invalid input
            }
        }

        // Step 2: Get and validate sunrise time
        while (true) {
            System.out.print("Enter the time of sunrise [hh:mm]> ");
            if (userInput.hasNextInt()) {
                sunriseHour = userInput.nextInt();
                if (userInput.hasNextInt()) {
                    sunriseMinute = userInput.nextInt();
                    
                    if (sunriseHour >= MIN_HOURS && sunriseHour <= MAX_HOURS && 
                        sunriseMinute >= MIN_MINUTES && sunriseMinute <= MAX_MINUTES) {
                        break; // Valid input
                    } else {
                        System.out.println("Error! Invalid sunrise time.");
                    }
                } else {
                    System.out.println("Error! Invalid minute format.");
                    userInput.next(); // Consume invalid input
                }
            } else {
                System.out.println("Error! Invalid hour format.");
                userInput.next(); // Consume invalid input
            }
        }

        // Step 3: Get and validate sunset time
        while (true) {
            System.out.print("Enter the time of sunset [hh:mm]> ");
            if (userInput.hasNextInt()) {
                sunsetHour = userInput.nextInt();
                if (userInput.hasNextInt()) {
                    sunsetMinute = userInput.nextInt();
                    
                    if (sunsetHour >= MIN_HOURS && sunsetHour <= MAX_HOURS && 
                        sunsetMinute >= MIN_MINUTES && sunsetMinute <= MAX_MINUTES) {
                        break; // Valid input
                    } else {
                        System.out.println("Error! Invalid sunset time.");
                    }
                } else {
                    System.out.println("Error! Invalid minute format.");
                    userInput.next(); // Consume invalid input
                }
            } else {
                System.out.println("Error! Invalid hour format.");
                userInput.next(); // Consume invalid input
            }
        }

        // Step 4: Ensuring sunset is after sunrise
        double sunriseTime = sunriseHour + (sunriseMinute / MINUTES_IN_HOUR);
        double sunsetTime = sunsetHour + (sunsetMinute / MINUTES_IN_HOUR);
        double sunHours = sunsetTime - sunriseTime;

        if (sunHours <= 0) {
            System.out.println("Error! Sunset must be later than sunrise.");
            return;
        }

        // Step 5: Computing production
        double panelArea = PANEL_WIDTH * PANEL_HEIGHT;
        double production = SOLAR_RADIATION * EFFICIENCY * panelArea * sunHours * NUM_OF_PANELS / WATT_TO_KILOWATT; 
        double earnings = production * ELECTRIC_PRICE;

        // Step 6: Displaying results
        System.out.println("==========================================");
        System.out.printf("Sun hours: %.2f hours\n", sunHours);
        System.out.printf("The production on %02d/%d is: %.2f kWh to a value of: SEK %.2f\n", 
                          day, month, production, earnings);
    }
}
