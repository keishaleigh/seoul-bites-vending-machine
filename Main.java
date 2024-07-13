import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VendingMachine vendingMachine = new VendingMachine();

        while (true) {
            printMainMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    vendingMachine.createVendingMachine();
                    break;
                case 2:
                    List<Item> vendingItems = new ArrayList<>();
                    vendingMachine.testVendingMachine(vendingItems);
                    break;
                case 3:
                    exit();
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }
    
    public static void printMainMenu() {
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║ ◍                                          ◍ ║");
        System.out.println("║     ╓──────────────────────────────────╖     ║");
        System.out.println("║     ║            MAIN MENU             ║     ║");
        System.out.println("║     ╟──────────────────────────────────╢     ║");
        System.out.println("║     ║  ⦾ (1) Create a Vending Machine  ║     ║");
        System.out.println("║     ║  ⦾ (2) Test a Vending Machine    ║     ║");
        System.out.println("║     ║  ⦾ (3) Exit                      ║     ║");
        System.out.println("║     ╙──────────────────────────────────╜     ║");
        System.out.println("║ ◍                                          ◍ ║");
        System.out.println("╚══════════════════════════════════════════════╝");
    }

    public static void exit() {
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║ ◍                                          ◍ ║");
        System.out.println("║              Thank you for using             ║");
        System.out.println("║         Seoul Bites Vending Machine!         ║");
        System.out.println("║ ◍                                          ◍ ║");
        System.out.println("╚══════════════════════════════════════════════╝");
    }
}

