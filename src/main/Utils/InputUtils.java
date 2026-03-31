package main.Utils;

import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;

public class InputUtils {

    private InputUtils() {} // should not be instantiable

    private static final Scanner scanner = new Scanner(System.in);
    
    // helper method to reduce code repetition.
    private static <T> T getInput(String prompt, String errorMsg, Function<String, T> parser, Predicate<T> condition) {
        while (true) {
            System.out.print(prompt + ": ");
            // trim removes leading/trailing whitespace from the input
            String input = scanner.nextLine().trim(); 
            try {
                T value = parser.apply(input);
                if (condition.test(value)) {
                    return value;
                }
                // only print error msg if not empty or blank
                if (!errorMsg.isEmpty() && !errorMsg.isBlank())
                    System.out.println("Error: " + errorMsg);                
            } catch (Exception e) {
                System.out.println("Could not validate input, please try again.");
            }
        }
    }

    public static String getString(String prompt) {
        return getStringUntil(prompt, "Field cannot be empty.", s -> !s.isBlank());
    }

    public static String getStringUntil(String prompt, String error, Predicate<String> condition) {
        return getInput(prompt, error, s -> s, condition);
    }

    public static int getInt(String prompt) {
        return getIntUntil(prompt, "Please enter a valid whole number.", i -> true);
    }

    public static int getIntUntil(String prompt, String error, Predicate<Integer> condition) {
        return getInput(prompt, error, Integer::parseInt, condition);
    }

    public static double getDouble(String prompt) {
        return getDoubleUntil(prompt, "Please enter a valid decimal.", d -> true);
    }

    public static double getDoubleUntil(String prompt, String error, Predicate<Double> condition) {
        return getInput(prompt, error, Double::parseDouble, condition);
    }
    
    public static boolean getConfirmation(String prompt) {
        String input = getStringUntil(
            prompt + " (y/n)", 
            "Enter 'y' or 'n'.", 
            s -> s.equalsIgnoreCase("y") || s.equalsIgnoreCase("n")
        );
        return input.equalsIgnoreCase("y");
    }
}