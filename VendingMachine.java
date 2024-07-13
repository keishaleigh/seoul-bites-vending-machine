import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * The VendingMachine class represents the vending machine created
 */
public class VendingMachine {
    protected List<Slot> slots;
    private Denomination denomination;
    private List<String> itemNames;
    private List<Double> itemPrices;
    private List<Double> insertedAmounts;
    private List<Double> changeAmounts;
    private Map<Double, Integer> denominationsMap;
    private List<SpecialVendingMachine> specialVendingMachines;
    private int numSlots;
    private double totalValue = 0.0;
    private boolean paymentFinished = false;

    /**
     * Constructor for VendingMachine class
     */
    public VendingMachine() {
        slots = new ArrayList<>();
        this.denomination = new Denomination();
        itemNames = new ArrayList<>();
        itemPrices = new ArrayList<>();
        insertedAmounts = new ArrayList<>();
        changeAmounts = new ArrayList<>();
        denominationsMap = new HashMap<>(); // Initialize denominationsMap
        specialVendingMachines = new ArrayList<>();
    }

    /**
     * Prints the main menu of the vending machine.
     */
    public void displayMainMenu() {
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

    /**
     * Prints the menu for creating a vending machine.
     */
    public void displayCreateVendingMachineMenu() {
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║ ◍                                          ◍ ║");
        System.out.println("║     ╓──────────────────────────────────╖     ║");
        System.out.println("║     ║  CHOOSE TYPE OF VENDING MACHINE  ║     ║");
        System.out.println("║     ╟──────────────────────────────────╢     ║");
        System.out.println("║     ║  ⦾ (1) Regular Vending Machine   ║     ║");
        System.out.println("║     ║  ⦾ (2) Special Vending Machine   ║     ║");
        System.out.println("║     ║  ⦾ (3) Back to Main Menu         ║     ║");
        System.out.println("║     ╙──────────────────────────────────╜     ║");
        System.out.println("║ ◍                                          ◍ ║");
        System.out.println("╚══════════════════════════════════════════════╝");
    }

    /**
     * Prints the menu for testing a vending machine
     */
    public void displayTestVendingMachineMenu() {
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║ ◍                                          ◍ ║");
        System.out.println("║     ╓──────────────────────────────────╖     ║");
        System.out.println("║     ║      TEST A VENDING MACHINE      ║     ║");
        System.out.println("║     ╟──────────────────────────────────╢     ║");
        System.out.println("║     ║  ⦾ (1) Test Vending Features     ║     ║");
        System.out.println("║     ║  ⦾ (2) Maintenance Features      ║     ║");
        System.out.println("║     ║  ⦾ (3) Back to Main Menu         ║     ║");
        System.out.println("║     ╙──────────────────────────────────╜     ║");
        System.out.println("║ ◍                                          ◍ ║");
        System.out.println("╚══════════════════════════════════════════════╝");
    }

    /**
     * Prints the menu for testing the features of a vending machine
     */
    public void displayTestFeaturesVM() {
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║ ◍                                          ◍ ║");
        System.out.println("║     ╓──────────────────────────────────╖     ║");
        System.out.println("║     ║    TEST VENDING MACHINE FEATURES ║     ║");
        System.out.println("║     ╟──────────────────────────────────╢     ║");
        System.out.println("║     ║  ⦾ (1) Start Test                ║     ║");
        System.out.println("║     ║  ⦾ (2) End Test                  ║     ║");
        System.out.println("║     ║  ⦾ (3) Exit VM Features Test     ║     ║");
        System.out.println("║     ╙──────────────────────────────────╜     ║");
        System.out.println("║ ◍                                          ◍ ║");
        System.out.println("╚══════════════════════════════════════════════╝");
    }

    /**
     * Prints the menu for testing vending machine features
     */
    public void displayPurchaseMenu() {
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║ ◍                                          ◍ ║");
        System.out.println("║     ╓──────────────────────────────────╖     ║");
        System.out.println("║     ║    TEST VENDING MACHINE FEATURES ║     ║");
        System.out.println("║     ╟──────────────────────────────────╢     ║");
        System.out.println("║     ║  ⦾ (1) Regular Vending Machine   ║     ║");
        System.out.println("║     ║  ⦾ (2) Special Vending Machine   ║     ║");
        System.out.println("║     ║  ⦾ (3) Exit VM Features Test     ║     ║");
        System.out.println("║     ╙──────────────────────────────────╜     ║");
        System.out.println("║ ◍                                          ◍ ║");
        System.out.println("╚══════════════════════════════════════════════╝");
    }

    /**
     * Displays the menu for payment
     */
    public void displayPaymentMenu() {
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║ ◍                                          ◍ ║");
        System.out.println("║     ╓──────────────────────────────────╖     ║");
        System.out.println("║     ║       PLEASE INSERT MONEY        ║     ║");
        System.out.println("║     ╟──────────────────────────────────╢     ║");
        System.out.println("║     ║  ⦾ (1) Insert Coins              ║     ║");
        System.out.println("║     ║  ⦾ (2) Insert Bills              ║     ║");
        System.out.println("║     ║  ⦾ (3) Finish Payment            ║     ║");
        System.out.println("║     ╙──────────────────────────────────╜     ║");
        System.out.println("║ ◍                                          ◍ ║");
        System.out.println("╚══════════════════════════════════════════════╝");
    }

    /**
     * Displays the options for inserting coins
     */
    public void displayCoinsMenu() {
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║ ◍                                          ◍ ║");
        System.out.println("║     ╓──────────────────────────────────╖     ║");
        System.out.println("║     ║       PLEASE INSERT MONEY        ║     ║");
        System.out.println("║     ╟──────────────────────────────────╢     ║");
        System.out.println("║     ║  ⦾ (1) Insert ₱1 Coin            ║     ║");
        System.out.println("║     ║  ⦾ (2) Insert ₱5 Coin            ║     ║");
        System.out.println("║     ║  ⦾ (3) Insert ₱10 Coin           ║     ║");
        System.out.println("║     ║  ⦾ (4) Insert ₱20 Coin           ║     ║");
        System.out.println("║     ║  ⦾ (5) Go Back                   ║     ║");
        System.out.println("║     ╙──────────────────────────────────╜     ║");
        System.out.println("║ ◍                                          ◍ ║");
        System.out.println("╚══════════════════════════════════════════════╝");
    }

    /**
     * Displays the options for inserting bills
     */
    public void displayBillMenu() {
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║ ◍                                          ◍ ║");
        System.out.println("║     ╓──────────────────────────────────╖     ║");
        System.out.println("║     ║       PLEASE INSERT BILL         ║     ║");
        System.out.println("║     ╟──────────────────────────────────╢     ║");
        System.out.println("║     ║  ⦾ (1) Insert ₱20 Bill           ║     ║");
        System.out.println("║     ║  ⦾ (2) Insert ₱50 Bill           ║     ║");
        System.out.println("║     ║  ⦾ (3) Insert ₱100 Bill          ║     ║");
        System.out.println("║     ║  ⦾ (4) Insert ₱200 Bill          ║     ║");
        System.out.println("║     ║  ⦾ (5) Insert ₱500 Bill          ║     ║");
        System.out.println("║     ║  ⦾ (6) Insert ₱1000 Bill         ║     ║");
        System.out.println("║     ║  ⦾ (7) Go Back                   ║     ║");
        System.out.println("║     ╙──────────────────────────────────╜     ║");
        System.out.println("║ ◍                                          ◍ ║");
        System.out.println("╚══════════════════════════════════════════════╝");
    }

    /**
     * Prints the menu for maintenance features of the vending machine.
     */
    public void displayMaintenanceFeaturesMenu() {
        System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║ ◍                                                       ◍ ║");
        System.out.println("║     ╓───────────────────────────────────────────────╖     ║");
        System.out.println("║     ║             MAINTENANCE FEATURES              ║     ║");
        System.out.println("║     ╟───────────────────────────────────────────────╢     ║");
        System.out.println("║     ║  ⦾ (1) Restock Items                          ║     ║");
        System.out.println("║     ║  ⦾ (2) Change Item Price                      ║     ║");
        System.out.println("║     ║  ⦾ (3) Collect Money from Machine             ║     ║");
        System.out.println("║     ║  ⦾ (4) Replenish Money                        ║     ║");
        System.out.println("║     ║  ⦾ (5) Print List of Transactions             ║     ║");
        System.out.println("║     ║  ⦾ (6) List Quantity of Items Sold            ║     ║");
        System.out.println("║     ║  ⦾ (7) Display Starting Inventory             ║     ║");
        System.out.println("║     ║  ⦾ (8) Display Last Inventory                 ║     ║");
        System.out.println("║     ║  ⦾ (9) Exit Maintenance Features              ║     ║");
        System.out.println("║     ╙───────────────────────────────────────────────╜     ║");
        System.out.println("║ ◍                                                       ◍ ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝");
    }

    /**
     * Displays the menu restock item under maintenance features
     */
    public void displaySlotMenu() {
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║ ◍                                          ◍ ║");
        System.out.println("║     ╓──────────────────────────────────╖     ║");
        System.out.println("║     ║    WHAT WOULD YOU LIKE TO DO?    ║     ║");
        System.out.println("║     ╟──────────────────────────────────╢     ║");
        System.out.println("║     ║  ⦾ (1) Add Item                  ║     ║");
        System.out.println("║     ║  ⦾ (2) Edit Slot                 ║     ║");
        System.out.println("║     ║  ⦾ (3) Go back                   ║     ║");
        System.out.println("║     ╙──────────────────────────────────╜     ║");
        System.out.println("║ ◍                                          ◍ ║");
        System.out.println("╚══════════════════════════════════════════════╝");
    }

    /**
     * Creates a vending machine based on user choice.
     */
    public void createVendingMachine() {
        displayCreateVendingMachineMenu();

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter your choice: ");
        int vendingMachineType = scanner.nextInt();

        switch (vendingMachineType) {
            case 1:
                System.out.print("\nEnter the number of slots (minimum 8 | maximum 12): ");
                numSlots = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                createRegularVendingMachine(numSlots);
                break;
            case 2:
                createSpecialVendingMachine();
                break;
            case 3:
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    /**
     * Creates a regular vending machine.
     * 
     * @param numSlots the number of slots in the the vending machine
     */
    public void createRegularVendingMachine(int numSlots) {
        // Validate the number of slots
        if (numSlots < 8 || numSlots > 12) {
            System.out.println("Error: Number of slots should be between 8 to 12.");
            return; // Exit the method if the number of slots is invalid
        }

        // Initialize slots in the vending machine based on user input
        for (int i = 1; i <= numSlots; i++) {
            Slot slot = new Slot(i, numSlots);
            slots.add(slot);
        }

        System.out.println("\nRegular Vending Machine created successfully!");
    }

    /**
     * Creates a special vending machine.
     */
    public void createSpecialVendingMachine() {
        Scanner scanner = new Scanner(System.in);

        boolean createMorePackages = true;

        while (createMorePackages) {
            System.out.print("What is the name of the package: ");
            String packageName = scanner.nextLine();

            List<Item> packageItems = new ArrayList<>();
            boolean addMoreItems = true;

            while (addMoreItems) {
                displayRVMProducts();
                System.out.print("Will you add an item to the package from the list? (y/n): ");
                char choice = scanner.next().charAt(0);
                scanner.nextLine(); // Consume the newline character

                if (Character.toLowerCase(choice) == 'y') {

                    System.out.print("Enter the slot number of the item you want to add: ");
                    int selectedSlot = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    // Validate the selected slot
                    if (selectedSlot < 1 || selectedSlot > slots.size()) {
                        System.out.println("Invalid slot number. Please choose a valid slot.");
                        continue;
                    }

                    // Get the selected slot from the list of slots
                    Slot slot = slots.get(selectedSlot - 1);

                    // Check if the selected slot is empty
                    if (slot.isSlotEmpty()) {
                        System.out.println("Selected slot is empty. Please choose another slot.");
                        continue;
                    }

                    // Get the item from the selected slot
                    Item item = slot.getItem();

                    // Add the selected item to the package
                    packageItems.add(item);
                } else {
                    // Ask for information to add a custom item to the package
                    System.out.print("Enter the name of the item: ");
                    String itemName = scanner.nextLine();

                    System.out.print("Enter the price of item: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine(); // Consume the newline character

                    System.out.print("Enter the quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    System.out.print("Enter the calorie count: ");
                    int calories = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    // Create a custom item and add it to the package
                    Item customItem = new Item(itemName, price, quantity, calories);
                    packageItems.add(customItem);
                }

                System.out.print("Do you want to add more items to the package? (y/n): ");
                char addMoreChoice = scanner.next().charAt(0);
                scanner.nextLine(); // Consume the newline character

                addMoreItems = Character.toLowerCase(addMoreChoice) == 'y';
            }

            System.out.print("Enter the price for the package: ₱");
            double packagePrice = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline character

            // Create the Special Vending Machine instance and set the package information
            SpecialVendingMachine specialVendingMachine = new SpecialVendingMachine(packageName, packageItems,
                    packagePrice);
            specialVendingMachines.add(specialVendingMachine);

            // Now you have the Special Vending Machine with the package information.
            displaySpecialVendingMachines();

            System.out.print("Do you want to create another package? (y/n): ");
            char createMoreChoice = scanner.next().charAt(0);
            scanner.nextLine(); // Consume the newline character

            createMorePackages = Character.toLowerCase(createMoreChoice) == 'y';
        }

        System.out.println("Special vending machines creation completed.");
    }

    /**
     * Performs the test operations of the vending machine.
     * 
     * @param vendingItems The list of items available in the vending machine.
     */
    public void testVendingMachine(List<Item> vendingItems) {
        Scanner scanner = new Scanner(System.in);

        displayTestVendingMachineMenu();
        System.out.print("\nEnter your choice: ");
        int featureType = scanner.nextInt();

        if (featureType == 1) {
            testVM();
        } else if (featureType == 2) {
            performMaintenanceFeatures();
        }
    }

    /**
     * Performs the vending machine operations based on the selected feature.
     */
    private void testVM() {
        Scanner scanner = new Scanner(System.in);
        displayTestFeaturesVM();
        System.out.print("\nEnter your choice: ");
        int vendingMachineFeatures = scanner.nextInt();

        switch (vendingMachineFeatures) {
            case 1: // start test
                startTest();
                break;
            case 2: // end test
                System.out.println("Ending the test.");
                break;
            case 3: // return to main menu
                return;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    /**
     * Performs the operations for starting the test of the vending machine
     */
    private void startTest() {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;

        displayPurchaseMenu();
        System.out.print("Enter your choice: ");
        int purchaseChoice = scanner.nextInt();

        if (purchaseChoice == 1) {
            displayRVMProducts();
            displayCurrentFunds();
            paymentFinished = false;
            System.out.print("\nEnter the slot number: ");
            int selectedSlot = scanner.nextInt();

            while (!exitLoop) {
                if (!paymentFinished) {
                    performPaymentOperations();

                    if (paymentFinished) {
                        double insertedAmount = denomination.getTotalValue();
                        double changeAmount = insertedAmount - slots.get(selectedSlot - 1).getItem().getItemPrice();
                        // Purchase the item from the selected slot
                        purchaseItem(selectedSlot, insertedAmount, changeAmount);
                    }
                } else {
                    break;
                }
            }
        } else if (purchaseChoice == 2) {
            performPackagePurchase();
        }
    }

    /**
     * Performs the operations for purchasing a package in special vending machine
     */
    private void performPackagePurchase() {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;

        System.out.println("\n1. Purchase a Package");
        System.out.println("2. Customize Your Own Package");
        System.out.print("Enter your choice: ");
        int packageChoice = scanner.nextInt();

        if (packageChoice == 1) {
            performSpecialVendingMachinePurchase();
        } else if (packageChoice == 2) {
            boolean paymentFinished = false;

            while (!exitLoop) {
                if (!paymentFinished) {
                    performPaymentOperations();

                    if (paymentFinished) {
                        performCustomPackagePurchase();
                    }
                }
            }
        }
    }

    /**
     * Performs the operations for purchasing a package from the special vending
     * machine.
     */
    private void performSpecialVendingMachinePurchase() {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;

        displaySpecialVendingMachines();
        displayCurrentFunds();
        boolean SVMpaymentFinished = false;

        while (!exitLoop) {
            if (!SVMpaymentFinished) {
                performPaymentOperations();

                if (SVMpaymentFinished) {
                    boolean selectPackage = true;
                    while (selectPackage) {
                        System.out.print("\nEnter the package name: ");
                        scanner.nextLine(); // Consume the newline character left from the previous nextInt()
                        String packageName = scanner.nextLine();

                        // Find the selected package by name
                        SpecialVendingMachine selectedPackage = null;
                        for (SpecialVendingMachine packageMachine : specialVendingMachines) {
                            if (packageMachine.getPackageName().equalsIgnoreCase(packageName)) {
                                selectedPackage = packageMachine;
                                break;
                            }
                        }

                        if (selectedPackage == null) {
                            System.out.println("Package not found. Please enter a valid package name.");
                        } else {
                            double insertedAmount = denomination.getTotalValue();
                            double packagePrice = selectedPackage.getPackagePrice();
                            double changeAmount = insertedAmount - packagePrice;

                            // Purchase the package
                            purchasePackage(selectedPackage, insertedAmount, changeAmount);

                            selectPackage = false; // Exit the loop after purchasing the package
                            displaySpecialVendingMachines(); // Update the displayed contents after the purchase
                        }
                    }
                }
            }
        }
    }

    /**
     * Performs the operations for purchasing a customized package
     */
    private void performCustomPackagePurchase() {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        List<Item> selectedItems = new ArrayList<>();
        double totalPrice = 0;
        CustomizedPackage customizedPackage = new CustomizedPackage("Custom Package", 0, 1, 0);

        while (!exitLoop) {
            System.out.print("Enter the slot number of the item you want to add to the package (0 to finish): ");
            int selectedSlot = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character left from the previous nextInt()

            if (selectedSlot == 0) {
                exitLoop = true;
            } else if (selectedSlot < 1 || selectedSlot > slots.size()) {
                System.out.println("Invalid slot number. Please choose a valid slot.");
            } else {
                Slot slot = slots.get(selectedSlot - 1);
                if (slot.isSlotEmpty()) {
                    System.out.println("Selected slot is empty. Please choose another slot.");
                } else {
                    Item selectedItem = slot.getItem();
                    selectedItems.add(selectedItem);
                    customizedPackage.addToPackage(selectedItem, selectedSlot); // Add the item with the correct slot
                                                                                // number
                    totalPrice += selectedItem.getItemPrice();
                    System.out.println("Item '" + selectedItem.getItemName() + "' added to the package.");
                }
            }
        }

        // Display the customized package and total price
        System.out.println("\nCustomized Package Contents:");
        for (Item item : selectedItems) {
            System.out.println("- " + item.getItemName() + " - $" + item.getItemPrice());
        }
        System.out.println("Total Price: " + totalPrice);

        // Check if there is enough money inserted to purchase the customized package
        if (totalPrice > totalValue) {
            System.out.println("Insufficient funds. Please insert more money.");
            return;
        }

        // Calculate the change to be provided
        double changeAmount = totalValue - totalPrice;

        // Purchase the customized package
        purchasePackage(customizedPackage, totalValue, changeAmount);

        // Update the currentFunds after the purchase
        totalValue -= totalPrice;
    }

    /**
     * Performs the payment operations for inserting coins or bills
     */
    private void performPaymentOperations() {
        displayPaymentMenu();
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter your choice: ");
        int choicePayment = scanner.nextInt();

        switch (choicePayment) {
            case 1:
                insertCoins();
                break;
            case 2:
                insertBills();
                break;
            case 3:
                System.out.println("\nInserting money...");
                paymentFinished = true;
                break;
            default:
                System.out.println("\nInvalid choice. Please try again.");
                break;
        }
    }

    /**
     * Performs the operations for inserting coins into the vending machine
     */
    private void insertCoins() {
        displayCoinsMenu();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("\nEnter your choice: ");
            int choiceCoin = scanner.nextInt();
            switch (choiceCoin) {
                case 1:
                    System.out.print("Please input the number of ₱1 coins you want to insert: ");
                    int coin1Count = scanner.nextInt();
                    denomination.insertCoin1(coin1Count);
                    break;
                case 2:
                    System.out.print("Please input the number of ₱5 coins you want to insert: ");
                    int coin5Count = scanner.nextInt();
                    denomination.insertCoin5(coin5Count);
                    break;
                case 3:
                    System.out.print("Please input the number of ₱10 coins you want to insert: ");
                    int coin10Count = scanner.nextInt();
                    denomination.insertCoin10(coin10Count);
                    break;
                case 4:
                    System.out.print("Please input the number of ₱20 coins you want to insert: ");
                    int coin20Count = scanner.nextInt();
                    denomination.insertCoin20(coin20Count);
                    break;
                case 5:
                    System.out.println("Going back to the vending machine...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            if (choiceCoin == 5) {
                break;
            }
        }
    }

    /**
     * Performs the operations for inserting bills into the vending machine
     */
    private void insertBills() {
        displayBillMenu();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("\nEnter your choice: ");
            int choiceBill = scanner.nextInt();
            switch (choiceBill) {
                case 1:
                    System.out.print("Please input the number of ₱20 bills you want to insert: ");
                    int bill20Count = scanner.nextInt();
                    denomination.insertBill20(bill20Count);
                    break;
                case 2:
                    System.out.print("Please input the number of ₱50 bills you want to insert: ");
                    int bill50Count = scanner.nextInt();
                    denomination.insertBill50(bill50Count);
                    break;
                case 3:
                    System.out.print("Please input the number of ₱100 bills you want to insert: ");
                    int bill100Count = scanner.nextInt();
                    denomination.insertBill100(bill100Count);
                    break;
                case 4:
                    System.out.print("Please input the number of ₱200 bills you want to insert: ");
                    int bill200Count = scanner.nextInt();
                    denomination.insertBill200(bill200Count);
                    break;
                case 5:
                    System.out.print("Please input the number of ₱500 bills you want to insert: ");
                    int bill500Count = scanner.nextInt();
                    denomination.insertBill500(bill500Count);
                    break;
                case 6:
                    System.out.print("Please input the number of ₱1000 bills you want to insert: ");
                    int bill1000Count = scanner.nextInt();
                    denomination.insertBill1000(bill1000Count);
                    break;
                case 7:
                    System.out.println("Going back to the vending machine...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            if (choiceBill == 7) {
                break;
            }
        }
    }

    /**
     * Performs the maintenance features of the vending machine
     */
    private void performMaintenanceFeatures() {
        Scanner scanner = new Scanner(System.in);
        displayMaintenanceFeaturesMenu();
        System.out.print("\nEnter your choice: ");
        int vendingMachineMaintenance = scanner.nextInt();

        // Implement maintenance features
        switch (vendingMachineMaintenance) {
            case 1: // restock items
                displayRVMProducts();
                slotMenu();
                break;
            case 2: // change price for each item type
                displayRVMProducts();
                setItemPrice();
                break;
            case 3: // collect money from machine
                collectMoney();
                break;
            case 4: // replenish money
                replenishMoney();
                break;
            case 5: // print list of transactions
                displayTransactions();
                break;
            case 6: // list quantity of items sold
                displayItemsSold();
                break;
            case 7: // display starting inventory
                displayStartInventory();
                break;
            case 8: // display last inventory
                displayLastInventory();
                return;
            case 9: // Return to main menu
                return;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    /**
     * Adds a slot to the vending machine.
     *
     * @param slot The slot to be added to the vending machine.
     */
    public void addSlot(Slot slot) {
        slots.add(slot);
    }

    /**
     * Retrieves the Slot object based on the specified slot number.
     *
     * @param slotNumber The slot number of the Slot object to be retrieved.
     * @return The Slot object with the given slot number, or {@code null} if no
     *         matching slot is found.
     */
    public Slot getSlotByNumber(int slotNumber) {
        for (Slot slot : slots) {
            if (slot.getSlotNumber() == slotNumber) {
                return slot;
            }
        }
        return null; // Return null if slot with the given number is not found
    }

    /**
     * Sets the number of slots
     * 
     * @param numSlots the number of slots
     */
    public void setNumSlots(int numSlots) {
        this.numSlots = numSlots;
    }

    /**
     * Getter for slot list
     * 
     * @return slots the slot number
     */
    public List<Slot> getSlots() {
        return slots;
    }

    /**
     * Displays all products in regular vending machine
     */
    public void displayRVMProducts() {
        System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
        System.out.printf("┃ %-5s ┃ %-30s ┃ %-9s ┃ %-8s ┃ %-8s ┃\n", "Slot", "Item Name", "Price", "Quantity",
                "Calories");
        System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");

        for (Slot slot : slots) {
            int slotNumber = slot.getSlotNumber();
            if (isSlotEmpty(slotNumber)) {
                // If the slot is empty, display it with empty values
                System.out.printf("┃ %-5d ┃ %-30s ┃ %-9s ┃ %-8s ┃ %-8s ┃\n", slotNumber, "Empty Slot", "-", "-", "-");
            } else {
                ArrayList<Item> items = slot.getItems();
                for (Item item : items) {
                    System.out.printf("┃ %-5d ┃ %-30s ┃ ₱%-8.2f ┃ %-8d ┃ %-8d ┃\n",
                            slotNumber, item.getItemName(), item.getItemPrice(),
                            item.getItemQuantity(), item.getItemCalories());
                }
            }
        }

        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
    }

    /**
     * Displays the list of special vending machines.
     */
    public void displaySpecialVendingMachines() {
        System.out.println("Special Vending Machines:");

        for (SpecialVendingMachine specialVendingMachine : specialVendingMachines) {
            System.out.println("-------------------------------------------------");
            System.out.println("Package: " + specialVendingMachine.getPackageName() +
                    ", Price: ₱" + specialVendingMachine.getPackagePrice());

            // Display the table header
            System.out.println("+-------------------------------+----------+--------------+---------+");
            System.out.printf("| %-30s | %-9s | %-9s | %-9s |\n", "Item", "itemPrice", "itemQuantity", "itemCalories");
            System.out.println("+-------------------------------+----------+--------------+---------+");

            // Display each item in the package with its details
            for (Item item : specialVendingMachine.getPackageItems()) {
                String itemName = item.getItemName();
                double itemPrice = item.getItemPrice();
                int itemQuantity = item.getItemQuantity();
                int itemCalories = item.getItemCalories();

                // Format the row in the table
                System.out.printf("| %-30s | ₱%-8.2f | %8d | %-8d |\n", itemName, itemPrice, itemQuantity,
                        itemCalories);
            }

            // Display the table footer
            System.out.println("+-------------------------------+----------+--------------+---------+");

            // Display the total calories of the whole package
            int totalCalories = specialVendingMachine.getTotalCalories();
            System.out.println("Total Calories: " + totalCalories);
        }
    }

    /**
     * Performs the purchase of an item from the selected slot in the vending
     * machine.
     * Dispenses the item and provides change if necessary.
     *
     * @param selectedSlot   the slot number of the item to be purchased.
     * @param insertedAmount The amount of money inserted by the user.
     * @param changeAmount   The amount of change to be returned to the user.
     */
    public void purchaseItem(int selectedSlot, double insertedAmount, double changeAmount) {
        // Check if the selected slot is within a valid range
        if (selectedSlot < 1 || selectedSlot > slots.size()) {
            System.out.println("Invalid slot number. Please choose a valid slot.");
            return;
        }

        // Get the selected slot from the list of slots
        Slot slot = slots.get(selectedSlot - 1);

        // Check if the selected slot is empty
        if (slot.isSlotEmpty()) {
            System.out.println("Selected slot is empty. Please choose another slot.");
            return;
        }

        // Get the item from the selected slot
        Item item = slot.getItem();

        // Check if the item is out of stock
        if (item.getItemQuantity() == 0) {
            System.out.println("Selected item is out of stock. Please choose another item.");
            return;
        }

        // Get the price of the item
        double itemPrice = item.getItemPrice();

        // Check if the inserted amount is sufficient to purchase the item
        if (insertedAmount < itemPrice) {
            System.out.println("Insufficient funds. Please insert more money.");
            return;
        }

        // Check if there are sufficient funds to dispense change
        if (changeAmount <= totalValue) {
            // Dispense the change using denominations
            denomination.dispenseChange(changeAmount);
            // Deduct the change amount from the totalValue
            denomination.deductAmount(changeAmount);

            // Decrease the quantity of the purchased item
            item.deductQuantity(1);

            // Subtract the price of the item from the inserted amount
            denomination.subtractAmount(itemPrice);

            // Update the currentFunds after the purchase (add the itemPrice)
            totalValue += itemPrice;

            // Dispense the item
            System.out.println("\nThank you for your purchase.");
            System.out.println("Dispensing item: " + item.getItemName());

            addTransactionEntry(item.getItemName(), itemPrice, insertedAmount, changeAmount);
        } else {
            System.out.println("Sorry, the vending machine doesn't have enough change. Please use exact payment.");
        }
    }

    /**
     * Performs the purchase of a package from the special vending machine.
     * Dispenses the items in the package and provides change if necessary.
     *
     * @param packageToPurchase The SpecialVendingMachine package to be purchased.
     * @param insertedAmount    The amount of money inserted by the user.
     * @param changeAmount      The amount of change to be returned to the user.
     */
    public void purchasePackage(SpecialVendingMachine packageToPurchase, double insertedAmount, double changeAmount) {
        // Get the package items
        List<Item> packageItems = packageToPurchase.getPackageItems();

        // Check if the package is available
        if (packageItems.isEmpty()) {
            System.out.println("Selected package is empty. Please choose another package.");
            return;
        }

        // Check if the inserted amount is sufficient to purchase the package
        double packagePrice = packageToPurchase.getPackagePrice();
        if (insertedAmount < packagePrice) {
            System.out.println("Insufficient funds. Please insert more money.");
            return;
        }

        // Calculate the remaining change after purchasing the package
        double remainingChange = insertedAmount - packagePrice;

        System.out.println("\nThank you for your purchase. Your change: ₱" + remainingChange);

        // Check if there are sufficient funds to dispense change
        if (remainingChange <= totalValue) {
            // Dispense the change using denominations
            denomination.dispenseChange(remainingChange);
            // Deduct the change amount from the totalValue
            denomination.deductAmount(remainingChange);
        } else {
            System.out.println("Sorry, the vending machine doesn't have enough change. Please use exact payment.");
            return;
        }

        // Dispense the items in the package
        for (Item item : packageItems) {
            String itemName = item.getItemName();
            System.out.println("\nDispensing item from package: " + itemName);

            // Decrease the quantity of the item
            item.deductQuantity(1);
        }

        // Subtract the package price from the inserted amount
        denomination.subtractAmount(packagePrice);

        // Update the currentFunds after the purchase (add the itemPrice)
        totalValue += packagePrice;

        addTransactionEntry(packageToPurchase.getPackageName(), packagePrice, insertedAmount, changeAmount);
    }

    /**
     * Purchase a customized package and process the transaction.
     *
     * @param customizedPackage The customized package to be purchased.
     * @param insertedAmount    The amount of money inserted by the user for the
     *                          purchase.
     * @param changeAmount      The change amount to be provided to the user after
     *                          the purchase.
     */
    public void purchasePackage(CustomizedPackage customizedPackage, double insertedAmount, double changeAmount) {
        // Get the package items
        List<Item> packageItems = customizedPackage.getPackageItems();

        // Check if the package is available
        if (packageItems.isEmpty()) {
            System.out.println("Selected package is empty. Please choose another package.");
            return;
        }

        // Calculate the total price of the customized package
        double totalPrice = 0;
        for (Item item : packageItems) {
            totalPrice += item.getItemPrice();
        }

        // Check if the inserted amount is sufficient to purchase the package
        if (insertedAmount < totalPrice) {
            System.out.println("Insufficient funds. Please insert more money.");
            return;
        }

        // Calculate the remaining change after purchasing the package
        double remainingChange = insertedAmount - totalPrice;

        System.out.println("\nThank you for your purchase. Your change: ₱" + remainingChange);

        // Check if there are sufficient funds to dispense change
        if (remainingChange <= totalValue) {
            // Dispense the change using denominations
            denomination.dispenseChange(remainingChange);
            // Deduct the change amount from the totalValue
            denomination.deductAmount(remainingChange);
        } else {
            System.out.println("Sorry, the vending machine doesn't have enough change. Please use exact payment.");
            return;
        }

        // Dispense the items in the package
        for (Item item : packageItems) {
            String itemName = item.getItemName();
            System.out.println("\nDispensing item from package: " + itemName);

            // Decrease the quantity of the item
            item.deductQuantity(1);

            // Update the quantity sold and total amount for the item
            int quantitySold = getQuantitySold(itemName);
            double totalAmount = getTotalAmount(itemName);
            quantitySold++;
            totalAmount += item.getItemPrice();
            updateItemSales(itemName, quantitySold, totalAmount);
        }

        // Subtract the total price of the package from the inserted amount
        denomination.subtractAmount(totalPrice);

        // Update the currentFunds after the purchase (add the itemPrice)
        totalValue += totalPrice;

        // Add transaction entry
        addTransactionEntry(customizedPackage.getItemName(), totalPrice, insertedAmount, changeAmount);
    }

    /**
     * Updates the sales information for an item in the vending machine.
     * This method searches for the item with the given item name in the slots of
     * the vending machine
     * and updates its quantity sold and total amount based on the provided values.
     *
     * @param itemName     The name of the item for which to update the sales
     *                     information.
     * @param quantitySold The new quantity sold value to set for the item.
     * @param totalAmount  The new total amount value to set for the item.
     */
    private void updateItemSales(String itemName, int quantitySold, double totalAmount) {
        for (Slot slot : slots) {
            ArrayList<Item> items = slot.getItems();
            for (Item item : items) {
                if (item.getItemName().equals(itemName)) {
                    item.setQuantitySold(quantitySold);
                    item.setTotalAmount(totalAmount);
                    break;
                }
            }
        }
    }

    /**
     * Displays the slot menu under restock slot [one of the the maintenance
     * feature].
     */
    public void slotMenu() {
        Scanner scanner = new Scanner(System.in);

        displaySlotMenu();
        System.out.print("\nEnter your choice: ");
        int slotChoice = scanner.nextInt();

        switch (slotChoice) {
            case 1:
                addItemToSlot();
                break;

            case 2:
                restockSlot();
                break;

            case 3:
                performMaintenanceFeatures();
                break;

            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    /**
     * Prompts the user to add an item to a slot.
     */
    public void addItemToSlot() {
        Scanner scanner = new Scanner(System.in);

        displayRVMProducts();
        System.out.print("\nEnter the slot number: ");
        int slotNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline character after reading the slot number

        // Find the slot with the specified slot number
        Slot foundSlot = null;
        for (Slot slot : slots) {
            if (slotNumber == slot.getSlotNumber()) {
                foundSlot = slot;
                break;
            }
        }

        if (foundSlot != null) {
            ArrayList<Item> items = foundSlot.getItems();

            // Prompt user for the item name
            System.out.print("\nEnter the item name: ");
            String itemName = scanner.nextLine();

            // Prompt user for the price
            System.out.print("Enter the price: ");
            double price = scanner.nextDouble();
            scanner.nextLine(); // Consume newline character

            // Initialize the quantity to 0
            int itemQuantity = 0;

            // Prompt user to add at least 10 items
            while (itemQuantity < 10) {
                System.out.print("Enter the quantity (minimum 10): ");
                itemQuantity = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                if (itemQuantity < 10) {
                    System.out.println("Error: Minimum quantity should be 10.");
                }
            }

            // Prompt user for the calories
            System.out.print("Enter the calories: ");
            int itemCalories = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            Item item = new Item(itemName, price, itemQuantity, itemCalories);
            item.setInitialQuantity(itemQuantity); // Set the initial quantity
            items.add(item);
            foundSlot.setItemCount(item.getItemQuantity());

            System.out.println("\n" + itemName + " has been added to slot " + slotNumber);
            displayRVMProducts();

            // Ask if the user wants to add another item
            System.out.print("\nDo you want to add another item to this slot? (yes/no): ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("yes")) {
                addItemToSlot(); // Call the method again to add another item to the same slot
            } else {
                slotMenu(); // Return to the slotMenu if the user says "no"
            }
        } else {
            System.out.println("Slot number not found.");
        }
    }

    /**
     * Restocks the items in a vending machine slot.
     */
    public void restockSlot() {
        Scanner scanner = new Scanner(System.in);

        // Ask for the slot number
        System.out.print("Enter the slot number: ");
        int slotNumber = scanner.nextInt();

        // Find the slot with the specified slot number
        Slot foundSlot = null;
        for (Slot slot : slots) {
            if (slotNumber == slot.getSlotNumber()) {
                foundSlot = slot;
                break;
            }
        }

        // If the slot is found, restock the items
        if (foundSlot != null) {
            ArrayList<Item> items = foundSlot.getItems();

            // Display the item names and quantities
            for (Item item : items) {
                System.out.println("Item name: " + item.getItemName());
                System.out.println("Current quantity: " + item.getItemQuantity());

                // Ask for the new quantity
                System.out.print("Enter the new quantity: ");
                int newQuantity = scanner.nextInt();

                // Update the item's quantity
                item.setItemQuantity(newQuantity);

                System.out.println(item.getItemName() + " quantity has been updated to " + item.getItemQuantity());
            }
        } else {
            // If the slot is not found
            System.out.println("Slot number not found.");
        }
    }

    /**
     * Sets the new price of an item in a slot
     */
    public void setItemPrice() {
        Scanner scanner = new Scanner(System.in);

        // Ask for the slot number
        System.out.print("Enter the slot number: ");
        int inputSlotNumber = scanner.nextInt();

        Slot foundSlot = null;
        for (Slot slot : slots) {
            if (inputSlotNumber == slot.getSlotNumber()) {
                foundSlot = slot;
                break;
            }
        }

        if (foundSlot != null) {
            ArrayList<Item> items = foundSlot.getItems();

            // Display the item names
            for (Item item : items) {
                System.out.println("Item name: " + item.getItemName());

                // Ask for the new price
                System.out.print("Enter the new price: ");
                double newPrice = scanner.nextDouble();

                // Update the item's price
                item.setItemPrice(newPrice);

                System.out.println(item.getItemName() + " has now been updated to ₱" + item.getItemPrice());
            }
        } else {
            System.out.println("Slot number not found.");
        }
    }

    /**
     * Collects money from the vending machine and resets the total value to zero.
     */
    public void collectMoney() {
        double collectedAmount = totalValue;
        System.out.println("Collecting money from the vending machine: ₱" + collectedAmount);

        // Reset the currentFunds to 0
        totalValue = 0.0;
    }

    /**
     * Replenishes money in the vending machine
     */
    public void replenishMoney() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Replenish Money");
        System.out.println("━━━━━━━━━━━━━━━━");

        // Prompt the user to enter the number of ₱1 coins to add
        System.out.print("Enter the number of ₱1 coins to add: ");
        int add1Coin = scanner.nextInt();
        denominationsMap.put(1.0, denominationsMap.getOrDefault(1.0, 0) + add1Coin);
        totalValue += add1Coin * 1.0;

        // Prompt the user to enter the number of ₱5 coins to add
        System.out.print("Enter the number of ₱5 coins to add: ");
        int add5Coin = scanner.nextInt();
        denominationsMap.put(5.0, denominationsMap.getOrDefault(5.0, 0) + add5Coin);
        totalValue += add5Coin * 5.0;

        // Prompt the user to enter the number of ₱10 coins to add
        System.out.print("Enter the number of ₱10 coins to add: ");
        int add10Coin = scanner.nextInt();
        denominationsMap.put(10.0, denominationsMap.getOrDefault(10.0, 0) + add10Coin);
        totalValue += add10Coin * 10.0;

        // Prompt the user to enter the number of ₱20 coins to add
        System.out.print("Enter the number of ₱20 coins to add: ");
        int add20Coin = scanner.nextInt();
        denominationsMap.put(20.0, denominationsMap.getOrDefault(20.0, 0) + add20Coin);
        totalValue += add20Coin * 20.0;

        // Prompt the user to enter the number of ₱20 bills to add
        System.out.print("Enter the number of ₱20 bills to add: ");
        int add20Bill = scanner.nextInt();
        denominationsMap.put(200.0, denominationsMap.getOrDefault(200.0, 0) + add20Bill);
        totalValue += add20Bill * 200.0;

        // Prompt the user to enter the number of ₱50 bills to add
        System.out.print("Enter the number of ₱50 bills to add: ");
        int add50Bill = scanner.nextInt();
        denominationsMap.put(50.0, denominationsMap.getOrDefault(50.0, 0) + add50Bill);
        totalValue += add50Bill * 50.0;

        // Prompt the user to enter the number of ₱100 bills to add
        System.out.print("Enter the number of ₱100 bills to add: ");
        int add100Bill = scanner.nextInt();
        denominationsMap.put(100.0, denominationsMap.getOrDefault(100.0, 0) + add100Bill);
        totalValue += add100Bill * 100.0;

        // Prompt the user to enter the number of ₱200 bills to add
        System.out.print("Enter the number of ₱200 bills to add: ");
        int add200Bill = scanner.nextInt();
        denominationsMap.put(200.0, denominationsMap.getOrDefault(200.0, 0) + add200Bill);
        totalValue += add200Bill * 200.0;

        // Prompt the user to enter the number of ₱500 bills to add
        System.out.print("Enter the number of ₱500 bills to add: ");
        int add500Bill = scanner.nextInt();
        denominationsMap.put(500.0, denominationsMap.getOrDefault(500.0, 0) + add500Bill);
        totalValue += add500Bill * 500.0;

        // Prompt the user to enter the number of ₱1000 bills to add
        System.out.print("Enter the number of ₱1000 bills to add: ");
        int add1000Bill = scanner.nextInt();
        denominationsMap.put(1000.0, denominationsMap.getOrDefault(1000.0, 0) + add1000Bill);
        totalValue += add1000Bill * 1000.0;

        System.out.println("\nMoney replenished successfully!");
        displayDenominations();
    }

    /**
     * Displays the denominations and their respective amounts that have been
     * replenished in the vending machine.
     */
    public void displayDenominations() {
        System.out.println("\n┏━━━━━━━━━━ REPLENISHED MONEY ━━━━━┓");
        System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
        System.out.println("  1 Coin: ₱" + denominationsMap.getOrDefault(1.0, 0) * 1.0);
        System.out.println("  5 Coin: ₱" + denominationsMap.getOrDefault(5.0, 0) * 5.0);
        System.out.println("  10 Coin: ₱" + denominationsMap.getOrDefault(10.0, 0) * 10.0);
        System.out.println("  20 Coin: ₱" + denominationsMap.getOrDefault(20.0, 0) * 20.0);
        System.out.println("  20 Bill: ₱" + denominationsMap.getOrDefault(200.0, 0) * 200.0);
        System.out.println("  50 Bill: ₱" + denominationsMap.getOrDefault(50.0, 0) * 50.0);
        System.out.println("  100 Bill: ₱" + denominationsMap.getOrDefault(100.0, 0) * 100.0);
        System.out.println("  200 Bill: ₱" + denominationsMap.getOrDefault(200.0, 0) * 200.0);
        System.out.println("  500 Bill: ₱" + denominationsMap.getOrDefault(500.0, 0) * 500.0);
        System.out.println("  1000 Bill: ₱" + denominationsMap.getOrDefault(1000.0, 0) * 1000.0);
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
    }

    /**
     * Getter for item names involved in the transaction
     * 
     * @return itemNames the item names transacted in the vending machine
     */
    public List<String> getItemNames() {
        return itemNames;
    }

    /**
     * Getter for item prices involved in the transaction
     * 
     * @return itemPrices the item prices transacted in the vending machine
     */
    public List<Double> getItemPrices() {
        return itemPrices;
    }

    /**
     * Getter for inserted amounts involved in the transaction
     * 
     * @return insertedAmounts the insertedAmounts transacted in the vending machine
     */
    public List<Double> getInsertedAmounts() {
        return insertedAmounts;
    }

    /**
     * Getter for amount of changes involved in the transaction
     * 
     * @return changeAmounts the amount of change in the vending machine
     */
    public List<Double> getChangeAmounts() {
        return changeAmounts;
    }

    /**
     * Adds a transaction entry to record details of a vending machine transaction.
     *
     * @param itemName       The name of the item involved in the transaction.
     * @param itemPrice      The price of the item involved in the transaction.
     * @param insertedAmount The amount of money inserted by the customer for the
     *                       transaction.
     * @param changeAmount   The change amount returned to the customer after the
     *                       transaction.
     */
    public void addTransactionEntry(String itemName, double itemPrice, double insertedAmount, double changeAmount) {
        itemNames.add(itemName);
        itemPrices.add(itemPrice);
        insertedAmounts.add(insertedAmount);
        changeAmounts.add(changeAmount);
    }

    /**
     * Displays the recorded transactions with detailed information.
     */
    public void displayTransactions() {
        System.out.println("Transactions:");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("Item Name\t\tPrice\t\tMoney Inserted\t\tChange Dispensed");
        System.out.println("-------------------------------------------------------------------------------------");

        for (int i = 0; i < itemNames.size(); i++) {
            System.out.printf("%-20s\t%.2f\t\t%.2f\t\t\t%.2f%n", itemNames.get(i), itemPrices.get(i),
                    insertedAmounts.get(i), changeAmounts.get(i));
        }

        System.out.println("------------------------------------------------------------------------------------");
    }

    /**
     * Displays a summary of items sold along with their corresponding quantities
     * and total amounts.
     */
    public void displayItemsSold() {
        System.out.println("*----------------------------------------------------------------------*");
        System.out.println("|                       ITEMS SOLD SUMMARY                             |");
        System.out.println("*----------------------------------------------------------------------*");
        System.out.println("Item Name\t\tQuantity Sold\t\tTotal Amount");
        System.out.println("------------------------------------------------------------------------");

        boolean noItemsSold = true; // A flag to track if no items are sold

        for (Slot slot : slots) {
            ArrayList<Item> items = slot.getItems();

            for (Item item : items) {
                String itemName = item.getItemName();
                int quantitySold = getQuantitySold(itemName);
                double totalAmount = getTotalAmount(itemName);

                if (quantitySold > 0) {
                    System.out.printf("%-20s\t%d\t\t\t%.2f%n", itemName, quantitySold, totalAmount);
                    noItemsSold = false; // There's at least one item sold
                }
            }
        }

        if (noItemsSold) {
            System.out.println("No items sold yet.");
        }

        System.out.println("------------------------------------------------------------------------");
    }

    /**
     * Displays the current available funds in the vending machine.
     * The method prints the total value of funds currently available in the vending
     * machine.
     */
    public void displayCurrentFunds() {
        System.out.println("\nCurrent Funds: ₱" + totalValue);
    }

    /**
     * Retrieves the quantity of items sold for a specific item name.
     *
     * @param itemName The name of the item for which the quantity sold is to be
     *                 retrieved.
     * @return The quantity of the specified item sold.
     */
    public int getQuantitySold(String itemName) {
        int quantitySold = 0;

        for (String name : itemNames) {
            if (name.equals(itemName)) {
                quantitySold++;
            }
        }

        return quantitySold;
    }

    /**
     * Retrieves the total amount generated from selling a specific item.
     *
     * @param itemName The name of the item for which the total amount is to be
     *                 retrieved.
     * @return The total amount generated from selling the specified item.
     */
    public double getTotalAmount(String itemName) {
        double totalAmount = 0.0;

        // Iterate through the list of recorded item names and their corresponding
        // prices
        for (int i = 0; i < itemNames.size(); i++) {
            // If the item name matches the specified item name, add its price to the
            // totalAmount
            if (itemNames.get(i).equals(itemName)) {
                totalAmount += itemPrices.get(i);
            }
        }

        return totalAmount;
    }

    /**
     * Checks if a specific slot in the RegularVendingMachine is empty.
     *
     * @param slotNumber The slot number to be checked for emptiness.
     * @return {@code true} if the specified slot is empty; otherwise,
     *         {@code false}.
     */
    public boolean isSlotEmpty(int slotNumber) {
        for (Slot slot : slots) {
            if (slot.getSlotNumber() == slotNumber) {
                return slot.isSlotEmpty();
            }
        }
        return true; // Return true if the slot number is not found
    }

    /**
     * Displays the starting inventory in the Vending Machine.
     */
    public void displayStartInventory() {
        System.out.println("\nStart Inventory in Vending Machine:");
        System.out.println("Slot | Item Name                   | Price    | Start Quantity");
        System.out.println("---------------------------------------------------------");
        for (Slot slot : slots) {
            ArrayList<Item> items = slot.getItems();
            for (Item item : items) {
                System.out.printf("%-4d | %-26s | ₱%-7.2f | %-14d%n",
                        slot.getSlotNumber(), item.getItemName(), item.getItemPrice(), item.getInitialQuantity());
            }
        }
    }

    /**
     * Displays the last inventory in the Vending Machine.
     */
    public void displayLastInventory() {
        System.out.println("\nLast Inventory in Vending Machine:");
        System.out.println("Slot | Item Name                   | Price    | Last Quantity");
        System.out.println("---------------------------------------------------------");
        for (Slot slot : slots) {
            ArrayList<Item> items = slot.getItems();
            for (Item item : items) {
                System.out.printf("%-4d | %-26s | ₱%-7.2f | %-14d%n",
                        slot.getSlotNumber(), item.getItemName(), item.getItemPrice(), item.getItemQuantity());
            }
        }
    }

}