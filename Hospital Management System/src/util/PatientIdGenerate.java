package util;

public class PatientIdGenerate {
    private int counter = 1001;

    public String generateId() {
        return "P" + counter++;
    }
}
