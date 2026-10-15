package model;

public class Patient {

    private String patientId;
    private String name;
    private int age;
    private String diagnosis;
    private String contactNumber;
    private boolean isAdmitted;

    // Full Constructor
    public Patient(String patientId, String name, int age,
                   String diagnosis, String contactNumber, boolean isAdmitted) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.diagnosis = diagnosis;
        this.contactNumber = contactNumber;
        this.isAdmitted = isAdmitted;
    }

    // Partial Constructor (ID assigned later)
    public Patient(String name, int age, String diagnosis, String contactNumber) {
        this.name = name;
        this.age = age;
        this.diagnosis = diagnosis;
        this.contactNumber = contactNumber;
        this.isAdmitted = false;
    }

    // Getters
    public String getPatientId()     { return patientId; }
    public String getName()          { return name; }
    public int getAge()              { return age; }
    public String getDiagnosis()     { return diagnosis; }
    public String getContactNumber() { return contactNumber; }
    public boolean isAdmitted()      { return isAdmitted; }

    // Setters with Validation
    public void setPatientId(String patientId) {
        if (patientId == null || patientId.trim().isEmpty()) {
            throw new IllegalArgumentException("Patient ID cannot be null or empty.");
        }
        this.patientId = patientId;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Patient name cannot be null or empty.");
        }
        this.name = name;
    }

    public void setAge(int age) {
        if (age <= 0 || age > 150) {
            throw new IllegalArgumentException("Age must be between 1 and 150.");
        }
        this.age = age;
    }

    public void setDiagnosis(String diagnosis)         { this.diagnosis = diagnosis; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public void setAdmitted(boolean admitted)          { this.isAdmitted = admitted; }

    // toString
    @Override
    public String toString() {
        return String.format(
                "┌─────────────────────────────────────┐%n" +
                        "│         PATIENT RECORD              │%n" +
                        "├─────────────────────────────────────┤%n" +
                        "│  ID          : %-20s │%n" +
                        "│  Name        : %-20s │%n" +
                        "│  Age         : %-20d │%n" +
                        "│  Diagnosis   : %-20s │%n" +
                        "│  Contact     : %-20s │%n" +
                        "│  Status      : %-20s │%n" +
                        "└─────────────────────────────────────┘",
                patientId, name, age, diagnosis,
                contactNumber,
                isAdmitted ? "ADMITTED" : "OUTPATIENT"
        );
    }

    // equals & hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return patientId != null && patientId.equals(patient.patientId);
    }

    @Override
    public int hashCode() {
        return patientId != null ? patientId.hashCode() : 0;
    }
}
