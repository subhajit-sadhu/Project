package service;

import model.Patient;
import repository.PatientRepository;
import util.PatientIdGenerator;
import java.util.List;
import java.util.Optional;

public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        if (patientRepository == null) {
            throw new IllegalArgumentException("PatientRepository cannot be null.");
        }
        this.patientRepository = patientRepository;
    }

    // REGISTER a new patient
    public Patient registerPatient(String name, int age, String diagnosis, String contactNumber) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Patient name is required.");
        }
        if (age <= 0 || age > 150) {
            throw new IllegalArgumentException("Age must be between 1 and 150.");
        }
        if (contactNumber == null || !contactNumber.matches("\\d{10}")) {
            throw new IllegalArgumentException("Contact number must be exactly 10 digits.");
        }

        String newId = PatientIdGenerator.generateId();
        Patient patient = new Patient(newId, name, age, diagnosis, contactNumber, false);
        patientRepository.save(patient);
        System.out.println("✅ Patient registered successfully with ID: " + newId);
        return patient;
    }

    // FIND patient by ID
    public Optional<Patient> findPatientById(String patientId) {
        if (patientId == null || patientId.trim().isEmpty()) {
            throw new IllegalArgumentException("Patient ID cannot be null or empty.");
        }
        return patientRepository.findById(patientId);
    }

    // GET ALL patients
    public List<Patient> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        if (patients.isEmpty()) {
            System.out.println("No patients currently registered in the system.");
        }
        return patients;
    }

    // UPDATE diagnosis
    public boolean updateDiagnosis(String patientId, String newDiagnosis) {
        Optional<Patient> result = patientRepository.findById(patientId);
        if (result.isPresent()) {
            Patient patient = result.get();
            patient.setDiagnosis(newDiagnosis);
            patientRepository.save(patient);
            System.out.println("✅ Diagnosis updated for patient: " + patientId);
            return true;
        } else {
            System.out.println("❌ Patient not found with ID: " + patientId);
            return false;
        }
    }

    // ADMIT patient
    public boolean admitPatient(String patientId) {
        Optional<Patient> result = patientRepository.findById(patientId);
        if (result.isPresent()) {
            Patient patient = result.get();
            if (patient.isAdmitted()) {
                System.out.println("⚠️  Patient " + patientId + " is already admitted.");
                return false;
            }
            patient.setAdmitted(true);
            patientRepository.save(patient);
            System.out.println("✅ Patient " + patientId + " has been admitted.");
            return true;
        } else {
            System.out.println("❌ Patient not found: " + patientId);
            return false;
        }
    }

    // DISCHARGE patient
    public boolean dischargePatient(String patientId) {
        Optional<Patient> result = patientRepository.findById(patientId);
        if (result.isPresent()) {
            Patient patient = result.get();
            if (!patient.isAdmitted()) {
                System.out.println("⚠️  Patient " + patientId + " is not currently admitted.");
                return false;
            }
            patient.setAdmitted(false);
            patientRepository.save(patient);
            System.out.println("✅ Patient " + patientId + " has been discharged.");
            return true;
        } else {
            System.out.println("❌ Patient not found: " + patientId);
            return false;
        }
    }

    // DELETE patient
    public boolean deletePatient(String patientId) {
        boolean deleted = patientRepository.deleteById(patientId);
        if (deleted) {
            System.out.println("✅ Patient " + patientId + " has been removed from the system.");
        } else {
            System.out.println("❌ No patient found with ID: " + patientId);
        }
        return deleted;
    }

    // COUNT all patients
    public int getTotalPatientCount() {
        return patientRepository.count();
    }
}
