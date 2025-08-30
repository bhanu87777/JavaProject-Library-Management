package com.library.utils;

public class InputValidator {

    // Check if string is non-empty
    public static boolean isNonEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }

    // Check if string is numeric
    public static boolean isNumeric(String input) {
        if (!isNonEmpty(input)) return false;
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Validate role (student or librarian)
    public static boolean isValidRole(String role) {
        return Constants.ROLE_STUDENT.equalsIgnoreCase(role)
                || Constants.ROLE_LIBRARIAN.equalsIgnoreCase(role);
    }
}
