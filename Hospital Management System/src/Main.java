import model.Patient;
import repository.PatientRepository;
import service.PatientService;
import util.PatientIdGenerator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        PatientRepository patientRepository = new PatientRepository();
        PatientService patientService = new PatientService(patientRepository);
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║   HOSPITAL MANAGEMENT SYSTEM  v1.0       ║");
        System.out.println("╚══════════════════════════════════════════╝");

        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1: registerPatient(patientService, scanner);  break;
                    case 2: viewAllPatients(patientService);           break;
                    case 3: searchPatient(patientService, scanner);    break;
                    case 4: updateDiagnosis(patientService, scanner);  break;
                    case 5: admitPatient(patientService, scanner);     break;
                    case 6: dischargePatient(patientService, scanner); break;
                    case 7: deletePatient(patientService, scanner);    break;
                    case 8: showStats(patientService);                 break;
                    case 0:
                        running = false;
                        System.out.println("\n👋 Thank you for using HMS. Goodbye!");
                        break;
                    default:
                        System.out.println("❌ Invalid choice. Please enter a number from the menu.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a valid number.");
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Error: " + e.getMessage());
            }

            System.out.println();
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│               MAIN MENU                  │");
        System.out.println("├──────────────────────────────────────────┤");
        System.out.println("│  1. Register New Patient                  │");
        System.out.println("│  2. View All Patients                     │");
        System.out.println("│  3. Search Patient by ID                  │");
        System.out.println("│  4. Update Patient Diagnosis              │");
        System.out.println("│  5. Admit Patient                         │");
        System.out.println("│  6. Discharge Patient                     │");
        System.out.println("│  7. Delete Patient Record                 │");
        System.out.println("│  8. Show System Stats                     │");
        System.out.println("│  0. Exit                                  │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    private static void registerPatient(PatientService service, Scanner scanner) {
        System.out.println("\n--- Register New Patient ---");
        System.out.print("Enter name               : ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter age                : ");
        int age;
        try {
            age = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid age entered.");
            return;
        }
        System.out.print("Enter diagnosis          : ");
        String diagnosis = scanner.nextLine().trim();
        System.out.print("Enter contact (10 digits): ");
        String contact = scanner.nextLine().trim();
        Patient patient = service.registerPatient(name, age, diagnosis, contact);
        System.out.println("\n" + patient);
    }

    private static void viewAllPatients(PatientService service) {
        System.out.println("\n--- All Registered Patients ---");
        List<Patient> patients = service.getAllPatients();
        if (!patients.isEmpty()) {
            patients.forEach(System.out::println);
        }
    }

    private static void searchPatient(PatientService service, Scanner scanner) {
        System.out.println("\n--- Search Patient by ID ---");
        System.out.print("Enter Patient ID (e.g., HMS-001): ");
        String id = scanner.nextLine().trim().toUpperCase();
        Optional<Patient> result = service.findPatientById(id);
        if (result.isPresent()) {
            System.out.println("\n" + result.get());
        } else {
            System.out.println("❌ No patient found with ID: " + id);
        }
    }

    private static void updateDiagnosis(PatientService service, Scanner scanner) {
        System.out.println("\n--- Update Patient Diagnosis ---");
        System.out.print("Enter Patient ID    : ");
        String id = scanner.nextLine().trim().toUpperCase();
        System.out.print("Enter new diagnosis : ");
        String diagnosis = scanner.nextLine().trim();
        service.updateDiagnosis(id, diagnosis);
    }

    private static void admitPatient(PatientService service, Scanner scanner) {
        System.out.println("\n--- Admit Patient ---");
        System.out.print("Enter Patient ID: ");
        String id = scanner.nextLine().trim().toUpperCase();
        service.admitPatient(id);
    }

    private static void dischargePatient(PatientService service, Scanner scanner) {
        System.out.println("\n--- Discharge Patient ---");
        System.out.print("Enter Patient ID: ");
        String id = scanner.nextLine().trim().toUpperCase();
        service.dischargePatient(id);
    }

    private static void deletePatient(PatientService service, Scanner scanner) {
        System.out.println("\n--- Delete Patient Record ---");
        System.out.print("Enter Patient ID to delete: ");
        String id = scanner.nextLine().trim().toUpperCase();
        System.out.print("Are you sure? (yes/no)    : ");
        String confirm = scanner.nextLine().trim().toLowerCase();
        if ("yes".equals(confirm)) {
            service.deletePatient(id);
        } else {
            System.out.println("ℹ️  Deletion cancelled.");
        }
    }

    private static void showStats(PatientService service) {
        System.out.println("\n--- System Statistics ---");
        System.out.println("Total patients registered : " + service.getTotalPatientCount());
        System.out.println("Total IDs generated       : " + PatientIdGenerator.getCurrentCount());
    }
}
