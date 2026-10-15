package util;

public class PatientIdGenerator {
    private static int counter = 0;
    private static final String PREFIX = "HMS";

    // Private constructor — utility class, not meant to be instantiated

    private PatientIdGenerator() {
        throw new UnsupportedOperationException("Utility class — do not instantiate.");
    }

    // Generates the next unique patient ID (thread-safe)

    public static synchronized String generateId() {
        counter++;
        return String.format("%s-%03d", PREFIX, counter);
    }
    // Returns current count without incrementing

    public static int getCurrentCount() {
        return counter;

    }
    // Resets counter — use only in testing/reinitialisation

    public static synchronized void resetCounter() {
        counter = 0;
        System.out.println("WARNING: PatientIdGenerator counter has been reset!");
    }
    // Validates whether a given string matches HMS ID format (e.g., "HMS-007")


    public static boolean isValidIdFormat(String id) {
        if (id == null) return false;
        return id.matches("^HMS-\\d{3}$");
    }
}


