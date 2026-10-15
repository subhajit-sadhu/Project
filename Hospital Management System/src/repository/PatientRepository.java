package repository;

import model.Patient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PatientRepository {

    private final Map<String, Patient> database = new HashMap<>();

    // CREATE / UPDATE
    public void save(Patient patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Cannot save a null patient.");
        }
        database.put(patient.getPatientId(), patient);
    }

    // READ — single patient by ID
    public Optional<Patient> findById(String patientId) {
        return Optional.ofNullable(database.get(patientId));
    }

    // READ — all patients
    public List<Patient> findAll() {
        return new ArrayList<>(database.values());
    }

    // DELETE
    public boolean deleteById(String patientId) {
        if (database.containsKey(patientId)) {
            database.remove(patientId);
            return true;
        }
        return false;
    }

    // CHECK EXISTENCE
    public boolean existsById(String patientId) {
        return database.containsKey(patientId);
    }

    // COUNT
    public int count() {
        return database.size();
    }
}
