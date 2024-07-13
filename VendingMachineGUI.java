import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachineGUI extends JFrame implements ActionListener {
    private VendingMachine vendingMachine;
    private List<Slot> slots;
    private List<SpecialVendingMachine> specialVendingMachines = new ArrayList<>();

    private JFrame vendingMachineFrame;

    private JButton createVmButton;
    private JButton testVmButton;
    private JButton exitButton;
    private JButton slotButton;
    private JButton[] buttons;
    private JDialog vendingMachineDialog;

    private List<JLabel> quantityLabels;
    private JTextField itemNameField;
    private JTextField priceField;
    private JTextField quantityField;
    private JTextField caloriesField;
    private JLabel currentFundsLabel;

    private Map<Double, Integer> coinDenominationsMap;
    private Map<Double, Integer> billDenominationsMap;
    private double currentFunds = 0.0;

    /**
     * Constructor for Vending Machine GUI class
     */
    public VendingMachineGUI() {
        vendingMachine = new VendingMachine();
        slots = new ArrayList<>();
        quantityLabels = new ArrayList<>();

        // Creating the frame
        JFrame frame = new JFrame("SEOUL BITES VENDING MACHINE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600); // Adjust the size based on your preference
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        // Creating the main panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(210, 210, 210)); // Light gray background

        // Creating the button panel
        JPanel buttonPanel = createButtonPanel();
        GridBagConstraints gbcMain = new GridBagConstraints();
        gbcMain.insets = new Insets(10, 10, 10, 10);
        gbcMain.gridy = 1;
        gbcMain.fill = GridBagConstraints.BOTH; // Fill both horizontally and vertically
        gbcMain.weightx = 1.0; // Allow horizontal expansion
        gbcMain.weighty = 1.0; // Allow vertical expansion
        mainPanel.add(buttonPanel, gbcMain);

        // Adding the main panel to the frame
        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }

    /**
     * This method creates a panel for the main menu
     * @return buttonPanel the panel for the main menu
     */
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(new Color(210, 210, 210)); // Light gray background
        GridBagConstraints gbcButtons = new GridBagConstraints();
        gbcButtons.insets = new Insets(10, 10, 10, 10); // Adjust spacing

        createVmButton = createButton("Create a Vending Machine");
        testVmButton = createButton("Test a Vending Machine");
        exitButton = createButton("Exit");

        createVmButton.addActionListener(this);
        testVmButton.addActionListener(this);
        exitButton.addActionListener(this);

        gbcButtons.gridx = 0;
        gbcButtons.gridy = 0;
        buttonPanel.add(createVmButton, gbcButtons);
        gbcButtons.gridy = 1;
        buttonPanel.add(testVmButton, gbcButtons);
        gbcButtons.gridy = 2;
        buttonPanel.add(exitButton, gbcButtons);

        // Create a titled border for the button panel
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Main Menu");
        titledBorder.setTitleFont(new Font("Verdana", Font.BOLD, 16));
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        buttonPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20), // Add padding around the panel
                titledBorder));

        return buttonPanel;
    }

    /**
     * This method creates the button for the main menu
     * @param text
     * @return button used for the main menu options
     */
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(300, 60));
        button.setFont(new Font("Verdana", Font.BOLD, 14));
        button.setForeground(Color.BLACK);
        button.setBackground(new Color(240, 240, 240)); // Light gray background
        button.setOpaque(true);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 100), 2), // Dark border
                BorderFactory.createEmptyBorder(5, 15, 5, 15) // Add padding around the text
        ));
        button.setFocusPainted(false); // Remove the focus border

        // Add a subtle hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(220, 220, 220)); // Light gray hover color
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(240, 240, 240)); // Restore the original color on exit
            }
        });

        return button;
    }

    /**
     * 
     * @param text
     * @return
     */
    private JRadioButton createRadioButton(String text) {
        JRadioButton radioButton = new JRadioButton(text);
        radioButton.setFont(new Font("Verdana", Font.PLAIN, 16));
        radioButton.setBackground(new Color(210, 210, 210)); // Light gray background
        radioButton.setFocusPainted(false);
        radioButton.setBorderPainted(false);
        radioButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        radioButton.setPreferredSize(new Dimension(250, 40));
        return radioButton;
    }

    /**
     * Keypad used to input number of slot(s) in creating a Vending Machine
     * @param title
     * @return
     */
    private String showKeypadInputDialog(String title) {
        JPanel keypadPanel = new JPanel(new GridLayout(4, 3, 10, 10));
        keypadPanel.setBackground(new Color(48, 56, 65)); // Dark background
        keypadPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        JTextField inputField = new JTextField(10);
        inputField.setEditable(false);
        inputField.setFont(new Font("Courier New", Font.BOLD, 26)); // Custom font for the input field
        inputField.setBackground(new Color(216, 223, 231)); // Light gray background
        inputField.setHorizontalAlignment(JTextField.CENTER); // Center-align the text

        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton(Integer.toString(i));
            button.setFont(new Font("Courier New", Font.BOLD, 24)); // Custom font for the buttons
            button.setBackground(new Color(255, 255, 255)); // White background
            button.setForeground(new Color(48, 56, 65)); // Dark text color
            button.setOpaque(true);
            button.setBorder(BorderFactory.createLineBorder(new Color(48, 56, 65), 2)); // Dark border
            button.addActionListener(e -> inputField.setText(inputField.getText() + button.getText()));
            keypadPanel.add(button);
        }

        JButton clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Courier New", Font.BOLD, 18)); // Custom font for the button
        clearButton.setBackground(new Color(255, 69, 69)); // Red background
        clearButton.setForeground(Color.WHITE); // White text color
        clearButton.setBorder(BorderFactory.createLineBorder(new Color(48, 56, 65), 2)); // Dark border
        clearButton.addActionListener(e -> inputField.setText(""));
        keypadPanel.add(clearButton);

        JButton zeroButton = new JButton("0");
        zeroButton.setFont(new Font("Courier New", Font.BOLD, 24)); // Custom font for the button
        zeroButton.setBackground(new Color(255, 255, 255)); // White background
        zeroButton.setForeground(new Color(48, 56, 65)); // Dark text color
        zeroButton.setOpaque(true);
        zeroButton.setBorder(BorderFactory.createLineBorder(new Color(48, 56, 65), 2)); // Dark border
        zeroButton.addActionListener(e -> inputField.setText(inputField.getText() + "0"));
        keypadPanel.add(zeroButton);

        String[] options = { "OK", "Cancel" };
        int option = JOptionPane.showOptionDialog(
                null,
                new Object[] { title, inputField, keypadPanel },
                "Input",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null, // No background image
                options,
                options[0]);

        if (option == JOptionPane.OK_OPTION) {
            return inputField.getText();
        }

        return null;
    }

    /**
    * This method is called when buttons are clicked or menu items are selected.
    * It determines the source of the event and performs the appropriate action
    * based on the component that triggered the event.
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == createVmButton) {
            createVendingMachineOptions();
        } else if (source == testVmButton) {
            testVendingFeaturesOptions();
        } else if (source == exitButton) {
            exitProgram();
        } else if (source == slotButton) {
            slotMenu();
        }
    }


    /**
     * This method creates the vending machine frame 
     * @return
     */
    private JFrame createVendingMachineFrame() {
        JFrame vendingMachineFrame = new JFrame("SEOUL BITES VENDING MACHINE");
        vendingMachineFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        vendingMachineFrame.setLayout(new BorderLayout());
        vendingMachineFrame.setBackground(new Color(240, 240, 240)); // Light gray background

        // Create the panel to hold the buttons for slot numbers
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(new Color(240, 240, 240)); // Light gray background

        // Create the buttons and labels for slot numbers
        for (int i = 0; i < slots.size(); i++) {
            Slot slot = slots.get(i);

            JPanel slotPanel = new JPanel(new BorderLayout());
            slotPanel.setPreferredSize(new Dimension(300, 200)); // Fixed size for each slot
            slotPanel.setBackground(new Color(72, 57, 91)); // dark purple background           
            slotPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Black border

            JLabel slotLabel = new JLabel("Slot " + slot.getSlotNumber());
            slotLabel.setFont(new Font("Verdana", Font.BOLD, 16));
            slotLabel.setForeground(Color.WHITE);
            slotLabel.setHorizontalAlignment(JLabel.CENTER);
            slotPanel.add(slotLabel, BorderLayout.NORTH);

            // Create the panel for items
            JPanel itemPanel = new JPanel();
            itemPanel.setBackground(new Color(240, 240, 240)); // Light gray background
            itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
            itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));

            // Check if there are items in the slot to determine if it should have fixed size
            boolean hasItems = !slot.getItems().isEmpty();
            if (!hasItems) {
                itemPanel.add(Box.createRigidArea(new Dimension(0, 50))); // Add empty space for empty slot
            }

            for (Item item : slot.getItems()) {
                JPanel itemInfoPanel = new JPanel(new GridBagLayout());
                itemInfoPanel.setBackground(new Color(250, 250, 250)); // Lighter gray background

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.insets = new Insets(5, 5, 5, 5); // Adjust spacing within the itemInfoPanel

                JLabel itemNameLabel = new JLabel(item.getItemName());
                itemNameLabel.setFont(new Font("Verdana", Font.BOLD, 16));
                itemNameLabel.setHorizontalAlignment(JLabel.CENTER);
                itemInfoPanel.add(itemNameLabel, gbc);

                gbc.gridy = 1;
                JLabel priceLabel = new JLabel("Price: ₱ " + item.getItemPrice());
                priceLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
                itemInfoPanel.add(priceLabel, gbc);

                gbc.gridy = 2;
                JLabel quantityLabel = new JLabel("Quantity: " + item.getItemQuantity());
                quantityLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
                itemInfoPanel.add(quantityLabel, gbc);
                quantityLabels.add(quantityLabel);

                gbc.gridy = 3;
                JLabel caloriesLabel = new JLabel("Calories: " + item.getItemCalories());
                caloriesLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
                itemInfoPanel.add(caloriesLabel, gbc);

                itemPanel.add(itemInfoPanel);
            }

            slotPanel.add(itemPanel, BorderLayout.CENTER);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = i % 2;
            gbc.gridy = i / 2;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.insets = new Insets(10, 10, 10, 10); // Adjust spacing between slots
            buttonPanel.add(slotPanel, gbc);
        }

        // Add the buttonPanel to the left side of the vending machine frame
        vendingMachineFrame.add(buttonPanel, BorderLayout.WEST);

        // titled border for the vending machine panel
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Regular Vending Machine");
        titledBorder.setTitleFont(new Font("Verdana", Font.BOLD, 24));
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        buttonPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20), // Add padding around the frame
                titledBorder));

        // Set the border for the content pane of the vending machine frame
        ((JComponent) vendingMachineFrame.getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Show the vending machine frame
        vendingMachineFrame.pack();
        vendingMachineFrame.setLocationRelativeTo(null); // Center the frame on the screen
        vendingMachineFrame.setVisible(true);

        // Return the vending machine frame
        return vendingMachineFrame;
    }

    /**
     * 
     * @param specialVendingMachines
     * @return
     */
    private JFrame specialVendingMachineFrame(List<SpecialVendingMachine> specialVendingMachines) {
        JFrame specialPackagesFrame = new JFrame("SEOUL BITES VENDING MACHINE");
        specialPackagesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        specialPackagesFrame.setLayout(new BorderLayout());
        specialPackagesFrame.setBackground(new Color(240, 240, 240)); // Light gray background
    
        JPanel packagePanel = new JPanel(new GridBagLayout());
        packagePanel.setBackground(new Color(240, 240, 240)); // Light gray background
    
        // Create the buttons and labels for slot numbers
        for (int i = 0; i < specialVendingMachines.size(); i++) {
            SpecialVendingMachine svm = specialVendingMachines.get(i);
    
            JPanel packageInfoPanel = new JPanel(new BorderLayout());
            packageInfoPanel.setPreferredSize(new Dimension(300, 200)); // Fixed size for each slot
            packageInfoPanel.setBackground(new Color(72, 57, 91)); // dark purple background
            packageInfoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Black border
    
            JLabel packageTitleLabel = new JLabel("Package Name: " + svm.getPackageName());
            packageTitleLabel.setFont(new Font("Verdana", Font.BOLD, 16));
            packageTitleLabel.setForeground(Color.WHITE);
            packageTitleLabel.setHorizontalAlignment(JLabel.CENTER);
            packageInfoPanel.add(packageTitleLabel, BorderLayout.NORTH);
    
            JPanel itemPanel = new JPanel();
            itemPanel.setBackground(new Color(240, 240, 240)); // Light gray background
            itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
            itemPanel.setLayout(new GridBagLayout());
    
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.NORTHWEST;
            gbc.insets = new Insets(5, 5, 5, 5); // Adjust spacing within the itemInfoPanel
    
            int totalCalories = 0;
    
            for (Item item : svm.getPackageItems()) {
                JLabel itemNameLabel = new JLabel(item.getItemName());
                itemNameLabel.setFont(new Font("Verdana", Font.BOLD, 16));
                itemPanel.add(itemNameLabel, gbc);
    
                gbc.gridy++;
                JLabel priceLabel = new JLabel("Price: ₱ " + item.getItemPrice());
                priceLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
                itemPanel.add(priceLabel, gbc);
    
                gbc.gridy++;
                JLabel caloriesLabel = new JLabel("Calories: " + item.getItemCalories());
                caloriesLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
                itemPanel.add(caloriesLabel, gbc);
    
                gbc.gridy++;
                JLabel spacerLabel = new JLabel(" "); // Add a spacer to separate items
                itemPanel.add(spacerLabel, gbc);
    
                totalCalories += item.getItemCalories();
            }
    
            // Remove the last spacer
            itemPanel.remove(--gbc.gridy);
    
            // Total Calories label for the package
            gbc.gridy++;
            JLabel totalCaloriesLabel = new JLabel("Total Calories: " + totalCalories);
            totalCaloriesLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
            itemPanel.add(totalCaloriesLabel, gbc);
    
            // Package Price label
            gbc.gridy++;
            JLabel packagePriceLabel = new JLabel("Package Price: ₱" + svm.getPackagePrice());
            packagePriceLabel.setFont(new Font("Verdana", Font.BOLD, 14));
            itemPanel.add(packagePriceLabel, gbc);
    
            packageInfoPanel.add(itemPanel, BorderLayout.CENTER);
    
            GridBagConstraints gbcPackagePanel = new GridBagConstraints();
            gbcPackagePanel.gridx = i % 2;
            gbcPackagePanel.gridy = i / 2;
            gbcPackagePanel.weightx = 1.0;
            gbcPackagePanel.weighty = 1.0;
            gbcPackagePanel.fill = GridBagConstraints.BOTH;
            gbcPackagePanel.insets = new Insets(10, 10, 10, 10); // Adjust spacing between slots
            packagePanel.add(packageInfoPanel, gbcPackagePanel);
        }
    
        // titled border for the vending machine panel
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Special Vending Machine Packages");
        titledBorder.setTitleFont(new Font("Verdana", Font.BOLD, 24));
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        packagePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20), // Add padding around the frame
                titledBorder));
    
        // Show the vending machine frame
        specialPackagesFrame.add(packagePanel, BorderLayout.CENTER);
        specialPackagesFrame.pack();
        specialPackagesFrame.setLocationRelativeTo(null); // Center the frame on the screen
        specialPackagesFrame.setVisible(true);
    
        return specialPackagesFrame;
    }
    
    
        
    /**
     * This method is called to choose the type of vending machine to create
     * Option to choose Regular or Special Vending Machine
     */
    private void createVendingMachineOptions() {
        JFrame optionsFrame = new JFrame("SEOUL BITES VENDING MACHINE");
        optionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        optionsFrame.setLayout(new BorderLayout());
        optionsFrame.setBackground(new Color(240, 240, 240)); // Light gray background

        // Create the vending machine panel look-alike border
        Border vendingMachineBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(139, 69, 19), 5), // Outer border (chocolate color)
                BorderFactory.createEmptyBorder(20, 20, 20, 20) // Inner padding
        );

        // Create the panel to hold the options
        JPanel optionsPanel = new JPanel(new GridBagLayout());
        optionsPanel.setBackground(new Color(210, 210, 210)); // Light gray background
        optionsPanel.setBorder(vendingMachineBorder);

        JRadioButton regularRadioButton = createRadioButton("Regular Vending Machine");
        JRadioButton specialRadioButton = createRadioButton("Special Vending Machine");
        JRadioButton backRadioButton = createRadioButton("Back to Main Menu");

        regularRadioButton.addActionListener(e -> {
            optionsFrame.dispose();
            createRVM();
        });

        specialRadioButton.addActionListener(e -> {
            optionsFrame.dispose();
            createSVM();
        });

        backRadioButton.addActionListener(e -> {
            optionsFrame.dispose();
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20); // Adjust spacing
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        optionsPanel.add(regularRadioButton, gbc);

        gbc.gridy = 1;
        optionsPanel.add(specialRadioButton, gbc);

        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        optionsPanel.add(backRadioButton, gbc);

        // Create a titled border for the options panel
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Create Vending Machine");
        titledBorder.setTitleFont(new Font("Verdana", Font.BOLD, 16));
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        optionsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20), // Add padding around the panel
                titledBorder));

        // Add the options panel to the center of the options frame
        optionsFrame.add(optionsPanel, BorderLayout.CENTER);

        // Set the preferred size of the options frame
        optionsFrame.setPreferredSize(new Dimension(400, 300));

        // Show the options frame
        optionsFrame.pack();
        optionsFrame.setLocationRelativeTo(null); // Center the frame on the screen
        optionsFrame.setVisible(true);
    }

    /**
     * Create a new frame to display test Vending Features 
     * Option to choose to test Vending Machine or Maintenance
     */
    private void testVendingFeaturesOptions() {
        JFrame optionsFrame = new JFrame("SEOUL BITES VENDING MACHINE");
        optionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        optionsFrame.setLayout(new BorderLayout());
        optionsFrame.setBackground(new Color(240, 240, 240)); // Light gray background

        // Create the vending machine panel look-alike border
        Border vendingMachineBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(139, 69, 19), 5), // Outer border (chocolate color)
                BorderFactory.createEmptyBorder(20, 20, 20, 20) // Inner padding
        );

        // Create the panel to hold the options
        JPanel optionsPanel = new JPanel(new GridBagLayout());
        optionsPanel.setBackground(new Color(210, 210, 210)); // Light gray background
        optionsPanel.setBorder(vendingMachineBorder);

        JRadioButton testRadioButton = createRadioButton("Test Vending Features");
        JRadioButton maintenanceRadioButton = createRadioButton("Maintenance Features");
        JRadioButton backRadioButton = createRadioButton("Back to Main Menu");

        testRadioButton.addActionListener(e -> {
            optionsFrame.dispose();
            testVendingFeatures();
        });

        maintenanceRadioButton.addActionListener(e -> {
            optionsFrame.dispose();
            maintenanceFeatures();
        });

        backRadioButton.addActionListener(e -> {
            optionsFrame.dispose();
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20); // Adjust spacing
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        optionsPanel.add(testRadioButton, gbc);

        gbc.gridy = 1;
        optionsPanel.add(maintenanceRadioButton, gbc);

        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        optionsPanel.add(backRadioButton, gbc);

        // Create a titled border for the options panel
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Test Vending Machine Features");
        titledBorder.setTitleFont(new Font("Verdana", Font.BOLD, 16));
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        optionsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20), // Add padding around the panel
                titledBorder));

        // Add the options panel to the center of the options frame
        optionsFrame.add(optionsPanel, BorderLayout.CENTER);

        // Set the preferred size of the options frame
        optionsFrame.setPreferredSize(new Dimension(400, 300));

        // Show the options frame
        optionsFrame.pack();
        optionsFrame.setLocationRelativeTo(null); // Center the frame on the screen
        optionsFrame.setVisible(true);
    }

    /**
     * Method to create a regular vending machine
     * Asks the user how many slots in the vending machine would they like to create
     * Minimum of 8 slots and maximum of 12 slots
     */
    private void createRVM() {
        String numSlotsInput = showKeypadInputDialog("Enter the number of slots (min 8 & max 12):");
        if (numSlotsInput == null) {
            return; // User canceled the input, exit the method
        }
    
        int numSlots;
        
        try {
            numSlots = Integer.parseInt(numSlotsInput);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
            return;
        }
    
        // Validate the number of slots
        if (numSlots < 8 || numSlots > 12) {
            JOptionPane.showMessageDialog(null, "Error: Number of slots should be between 8 and 12 (inclusive).");
            return;
        }
    
        // Create the regular vending machine
        vendingMachine.createRegularVendingMachine(numSlots);
        slots = vendingMachine.getSlots();
    
        // Create the main frame for the vending machine panel dialog
        vendingMachineDialog = new JDialog();
        vendingMachineDialog.setTitle("SEOUL BITES VENDING MACHINE");
        vendingMachineDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        vendingMachineDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                // Show the message after the vending machine panel dialog is closed
                JOptionPane.showMessageDialog(null, "Regular Vending Machine created successfully!");
            }
        });
    
        // Create the panel to hold the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(numSlots / 2, 2, 20, 20));
        buttonPanel.setBackground(new Color(240, 240, 240)); // Light gray background
    
        // Create the buttons and labels for slot numbers
        buttons = new JButton[numSlots];
        for (int i = 0; i < numSlots; i++) {
            final int slotIndex = i;
            JPanel slotPanel = new JPanel(new BorderLayout());
            slotPanel.setBackground(new Color(72, 57, 91)); // dark purple background
            slotPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Black border
    
            JLabel slotLabel = new JLabel("Slot " + (i + 1));
            slotLabel.setFont(new Font("Verdana", Font.BOLD, 16));
            slotLabel.setHorizontalAlignment(JLabel.CENTER);
            slotLabel.setForeground(Color.WHITE);
            slotPanel.add(slotLabel, BorderLayout.NORTH);
    
            buttons[i] = new JButton("Item " + (i + 1));
            buttons[i].setFont(new Font("Verdana", Font.BOLD, 14));
            buttons[i].setOpaque(true);
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Slot slot = slots.get(slotIndex);
                    if (slot.getItems().isEmpty()) {
                        vendingMachineDialog.setVisible(false);
                        JOptionPane.showMessageDialog(null, "Add an item to this slot first.");
                        vendingMachineDialog.setVisible(true);
                    } else {
                        Item item = slot.getItems().get(0); // Assuming there's only one item per slot
                        String message = "Item Name: " + item.getItemName() + "\n"
                                + "Price: ₱ " + item.getItemPrice() + "\n"
                                + "Quantity: " + item.getItemQuantity() + "\n"
                                + "Calories: " + item.getItemCalories();
                        JOptionPane.showMessageDialog(null, message, "Item Details", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            });
            slotPanel.add(buttons[i], BorderLayout.CENTER);
    
            buttonPanel.add(slotPanel);
        }
    
        // Create the "Back to Create Options" button
        JButton backButton = new JButton("Back to Create Options");
        backButton.setFont(new Font("Verdana", Font.BOLD, 14));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vendingMachineDialog.dispose(); // Close the vending machine dialog
                createVendingMachineOptions();
            }
        });
    
        // Create a panel to hold the vending machine and the back button
        JPanel mainPanel = new JPanel(new BorderLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(buttonPanel, BorderLayout.CENTER); // Add the vending machine panel
        mainPanel.add(backButton, BorderLayout.SOUTH); // Add the "Back to Create Options" button
    
        // Create a titled border for the vending machine panel
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Regular Vending Machine");
        titledBorder.setTitleFont(new Font("Verdana", Font.BOLD, 16));
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20), // Add padding around the panel
                titledBorder));
    
        vendingMachineDialog.add(mainPanel);
        vendingMachineDialog.pack();
        vendingMachineDialog.setLocationRelativeTo(null); // Center the dialog on the screen
        vendingMachineDialog.setVisible(true);
    }
    
    /**
     * Method to create a Special Vending Machine 
     * Make specialize package to sell in the vending machine
     */
    private void createSVM() {
        boolean createMorePackages = true;

        // Call the createVendingMachineFrame() method to create the vending machine frame
        vendingMachineFrame = createVendingMachineFrame();

        while (createMorePackages) {

            // Prompt for the title of the package
            String packageTitle = JOptionPane.showInputDialog(null, "Enter the title of the package:");
    
            // Create the panel to hold the package options
            JPanel packagePanel = new JPanel(new GridBagLayout());
            packagePanel.setBackground(new Color(240, 240, 240)); // Light gray background
    
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 20, 10, 20); // Adjust spacing
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.WEST;
    
            gbc.gridx = 0;
            gbc.gridy = 0;
            packagePanel.add(new JLabel("Package Title:"), gbc);
    
            gbc.gridx = 1;
            JTextField packageTitleField = new JTextField(20);
            packageTitleField.setText(packageTitle); // Set the title if entered previously
            packagePanel.add(packageTitleField, gbc);
    
            gbc.gridx = 0;
            gbc.gridy = 1;
            packagePanel.add(new JLabel("What is the name of the item:"), gbc);
    
            gbc.gridx = 1;
            JTextField itemNameField = new JTextField(20);
            packagePanel.add(itemNameField, gbc);
    
            List<Item> packageItems = new ArrayList<>();
            boolean addMoreItems = true;
            
            while (addMoreItems) {
                int option = JOptionPane.showOptionDialog(null, "Will you add an item to the package from the list?", "Add Item",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Yes", "No"}, "Yes");
        
                if (option == JOptionPane.YES_OPTION) {
                    promptAndAddToPackage(packageItems);
        
                } else {
                    String itemName = JOptionPane.showInputDialog(null, "Enter the name of the item:");
                    double itemPrice = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the price of the item: ₱"));
                    int quantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the quantity:"));
                    int calories = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the calorie count:"));
        
                    Item customItem = new Item(itemName, itemPrice, quantity, calories);
                    packageItems.add(customItem);
                }
        
                int addMoreChoice = JOptionPane.showOptionDialog(null,
                        "Do you want to add more items to the package?", "Add More Items",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                        new Object[]{"Yes", "No"}, "Yes");
        
                addMoreItems = addMoreChoice == JOptionPane.YES_OPTION;
            }
        
            double packagePrice = 0.0;
            if (!packageItems.isEmpty()) {
                packagePrice = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the price for the package: ₱"));
            }
        
            if (packageItems.isEmpty() && packagePrice <= 0) {
                JOptionPane.showMessageDialog(null, "The package is empty and has no price. It will not be created.");
                return; // Exit the method if the package is empty and has no price
            }

            
            // Create the Special Vending Machine instance and add it to the list
            SpecialVendingMachine specialVendingMachine = new SpecialVendingMachine(packageTitleField.getText(), packageItems, packagePrice);
            specialVendingMachines.add(specialVendingMachine);

            // Show the created special vending machine information
            StringBuilder message = new StringBuilder("Package Name: ").append(packageTitle).append("\n\n");
            message.append("Package Items:\n");
            for (Item item : packageItems) {
                message.append("- ").append(item.getItemName()).append(" (₱").append(item.getItemPrice()).append(")\n");
            }
            message.append("\nPackage Price: ₱").append(packagePrice);

            JOptionPane.showMessageDialog(null, message.toString(), "Special Vending Machine Package Created", JOptionPane.INFORMATION_MESSAGE);
        
            int createMoreChoice = JOptionPane.showOptionDialog(null,
                    "Do you want to create another package?", "Create More Packages",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                    new Object[]{"Yes", "No"}, "Yes");

            createMorePackages = createMoreChoice == JOptionPane.YES_OPTION;

            if (createMoreChoice == JOptionPane.NO_OPTION) {
                vendingMachineFrame.dispose();
                JOptionPane.showMessageDialog(null, "Special vending machines creation completed.");
            }
        }
        specialVendingMachineFrame(specialVendingMachines);
    }
    
    /**
     * 
     * @param packageItems
     */
    private void promptAndAddToPackage(List<Item> packageItems) {
        // Prompt the user for the slot number
        String slotNumberInput = JOptionPane.showInputDialog(null, "Enter the slot number of the item you want to add:");
    
        try {
            // Parse the slot number input to an integer
            int selectedSlot = Integer.parseInt(slotNumberInput);
    
            // Validate the selected slot
            if (selectedSlot < 1 || selectedSlot > slots.size()) {
                JOptionPane.showMessageDialog(null, "Invalid slot number. Please choose a valid slot.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Exit the method if the slot number is invalid
            }
    
            // Get the selected slot from the list of slots
            Slot slot = slots.get(selectedSlot - 1);
    
            // Check if the selected slot is empty
            if (slot.isSlotEmpty()) {
                JOptionPane.showMessageDialog(null, "Selected slot is empty. Please choose another slot.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Exit the method if the slot is empty
            }
    
            // Get the item from the selected slot
            Item item = slot.getItem();
    
            // Add the selected item to the package
            packageItems.add(item);
    
            JOptionPane.showMessageDialog(null, "Item added to the package: " + item.getItemName(), "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            // Handle the case where the user entered an invalid number format
            JOptionPane.showMessageDialog(null, "Invalid slot number. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Creates Frame to testVending Features
     */
    private void testVendingFeatures() {
        JFrame testMenuFrame = new JFrame("SEOUL BITES VENDING MACHINE");
        testMenuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        testMenuFrame.setLayout(new BorderLayout());
        testMenuFrame.setBackground(new Color(240, 240, 240)); // Light gray background
    
        // Create the testing panel look-alike border
        Border testingBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(139, 69, 19), 5), // Outer border (chocolate color)
                BorderFactory.createEmptyBorder(20, 20, 20, 20) // Inner padding
        );
    
        // Create the panel to hold the testing options
        JPanel testMenuPanel = new JPanel(new GridBagLayout());
        testMenuPanel.setBackground(new Color(210, 210, 210)); // Light gray background
        testMenuPanel.setBorder(testingBorder);
    
        JRadioButton startRadioButton = createRadioButton("Start Test");
        JRadioButton endRadioButton = createRadioButton("End Test");
        JRadioButton returnRadioButton = createRadioButton("Return to Main Menu");
    
        // Create a ButtonGroup so that only one radio button can be selected at a time
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(startRadioButton);
        buttonGroup.add(endRadioButton);
        buttonGroup.add(returnRadioButton);
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20); // Adjust spacing
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
    
        gbc.gridx = 0;
        gbc.gridy = 0;
        testMenuPanel.add(startRadioButton, gbc);
    
        gbc.gridy = 1;
        testMenuPanel.add(endRadioButton, gbc);
    
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        testMenuPanel.add(returnRadioButton, gbc);
    
        // Create a titled border for the testing panel
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Testing Features");
        titledBorder.setTitleFont(new Font("Verdana", Font.BOLD, 16));
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        testMenuPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20), // Add padding around the panel
                titledBorder));
    
        // Add the testing panel to the center of the frame
        testMenuFrame.add(testMenuPanel, BorderLayout.CENTER);
    
        // Set the preferred size of the frame and make it visible
        testMenuFrame.setPreferredSize(new Dimension(400, 300));
    
        startRadioButton.addActionListener(e -> {
            testMenuFrame.dispose();
            startTest();
        });
    
        endRadioButton.addActionListener(e -> {
            testMenuFrame.dispose();
            // End test
        });
    
        returnRadioButton.addActionListener(e -> {
            testMenuFrame.dispose();
            // Return to the main menu or testing options
            // Depending on your implementation, you might need to call a different method here.
        });
    
        // Show the frame
        testMenuFrame.pack();
        testMenuFrame.setLocationRelativeTo(null); // Center the frame on the screen
        testMenuFrame.setVisible(true);
    }
    
    // TEST FEATURES

    /**
     * This method displays the options when start test option is chosen
     * Option to test the created regular vending machine or to test the created special vending machine
     */
    private void startTest() {
        JFrame frame = new JFrame("Testing Features");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setBackground(new Color(240, 240, 240)); // Light gray background
    
        // Create the testing panel look-alike border
        Border testingBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(139, 69, 19), 5), // Outer border (chocolate color)
                BorderFactory.createEmptyBorder(20, 20, 20, 20) // Inner padding
        );
    
        // Create the panel to hold the testing options
        JPanel testingPanel = new JPanel(new GridBagLayout());
        testingPanel.setBackground(new Color(210, 210, 210)); // Light gray background
        testingPanel.setBorder(testingBorder);
    
        JRadioButton regularVendingButton = createRadioButton("Test Regular Vending Machine");
        JRadioButton specialVendingButton = createRadioButton("Test Special Vending Machine");
        JRadioButton backButton = createRadioButton("Go Back to Testing Features");
    
        regularVendingButton.addActionListener(e -> {
            frame.dispose();
            testRVMFrame();
        });
    
        specialVendingButton.addActionListener(e -> {
            frame.dispose();
            // testSpecialVendingMachine();
        });
    
        backButton.addActionListener(e -> {
            frame.dispose();
            testVendingFeatures();
        });
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20); // Adjust spacing
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
    
        gbc.gridx = 0;
        gbc.gridy = 0;
        testingPanel.add(regularVendingButton, gbc);
    
        gbc.gridy = 1;
        testingPanel.add(specialVendingButton, gbc);
    
        gbc.gridy = 2;
        testingPanel.add(backButton, gbc);
    
        // Create a titled border for the testing panel
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Testing Features");
        titledBorder.setTitleFont(new Font("Verdana", Font.BOLD, 16));
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        testingPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20), // Add padding around the panel
                titledBorder));
    
        // Add the testing panel to the center of the frame
        frame.add(testingPanel, BorderLayout.CENTER);
    
        // Set the preferred size of the frame and make it visible
        frame.setPreferredSize(new Dimension(400, 300));
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }

    /**
     * 
     * 
     * @param slot
     * @return
     */
    private JPanel createSlotPanel(Slot slot) {
        JPanel slotPanel = new JPanel(new BorderLayout());
        slotPanel.setPreferredSize(new Dimension(300, 200)); // Fixed size for each slot
        slotPanel.setBackground(new Color(220, 220, 220)); // Light gray background
        slotPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Black border
    
        JLabel slotLabel = new JLabel("Slot " + slot.getSlotNumber());
        slotLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        slotLabel.setHorizontalAlignment(JLabel.CENTER);
        slotPanel.add(slotLabel, BorderLayout.NORTH);
    
        // Create the panel for items
        JPanel itemPanel = new JPanel();
        itemPanel.setBackground(new Color(240, 240, 240)); // Light gray background
        itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
    
        // Check if there are items in the slot to determine if it should have fixed size
        boolean hasItems = !slot.getItems().isEmpty();
        if (!hasItems) {
            itemPanel.add(Box.createRigidArea(new Dimension(0, 50))); // Add empty space for empty slot
        }
    
        for (Item item : slot.getItems()) {
            JPanel itemInfoPanel = new JPanel(new GridBagLayout());
            itemInfoPanel.setBackground(new Color(250, 250, 250)); // Lighter gray background
    
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(5, 5, 5, 5); // Adjust spacing within the itemInfoPanel
    
            JLabel itemNameLabel = new JLabel(item.getItemName());
            itemNameLabel.setFont(new Font("Verdana", Font.BOLD, 16));
            itemNameLabel.setHorizontalAlignment(JLabel.CENTER);
            itemInfoPanel.add(itemNameLabel, gbc);
    
            gbc.gridy = 1;
            JLabel priceLabel = new JLabel("Price: ₱ " + item.getItemPrice());
            priceLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
            itemInfoPanel.add(priceLabel, gbc);
    
            gbc.gridy = 2;
            JLabel quantityLabel = new JLabel("Quantity: " + item.getItemQuantity());
            quantityLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
            itemInfoPanel.add(quantityLabel, gbc);
            quantityLabels.add(quantityLabel);
    
            gbc.gridy = 3;
            JLabel caloriesLabel = new JLabel("Calories: " + item.getItemCalories());
            caloriesLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
            itemInfoPanel.add(caloriesLabel, gbc);
    
            itemPanel.add(itemInfoPanel);
        }
    
        slotPanel.add(itemPanel, BorderLayout.CENTER);
    
        // Create the purchase button for this slot
        JButton purchaseButton = new JButton("Purchase Item");
        purchaseButton.setFont(new Font("Verdana", Font.BOLD, 14));
        purchaseButton.setOpaque(true);
        purchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Set the item price based on the selected slot (you can customize this)
                double itemPrice = slot.getItem().getItemPrice();
                // Open the insert money frame to allow the user to insert money
                insertMoneyFrame(itemPrice, slot);

                for (Component component : itemPanel.getComponents()) {
                    if (component instanceof JPanel) {
                        JPanel itemInfoPanel = (JPanel) component;
                        for (Component infoComponent : itemInfoPanel.getComponents()) {
                            if (infoComponent instanceof JLabel) {
                                JLabel label = (JLabel) infoComponent;
                                if (label.getText().startsWith("Quantity: ")) {
                                    label.setText("Quantity: " + slot.getItem().getItemQuantity());
                                    label.repaint();
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        });
    
        // Add the purchase button to the bottom of the slot panel
        slotPanel.add(purchaseButton, BorderLayout.SOUTH);
    
        return slotPanel;
    }

    /**
    * Displays the Test RVM (Regular Vending Machine) frame, which shows the slots
    * and allows users to interact with the vending machine.
    */
    private void testRVMFrame() {
        JFrame testRvmFrame = new JFrame("Seoul Bites Vending Machine");
        testRvmFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        testRvmFrame.setLayout(new BorderLayout());
        testRvmFrame.setBackground(new Color(240, 240, 240)); // Light gray background
    
        // Create the panel to hold the buttons for slot numbers
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(new Color(240, 240, 240)); // Light gray background
    
        // Create the buttons and labels for slot numbers
        for (int i = 0; i < slots.size(); i++) {
            Slot slot = slots.get(i);
            JPanel slotPanel = createSlotPanel(slot);
    
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = i % 2;
            gbc.gridy = i / 2;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.insets = new Insets(10, 10, 10, 10); // Adjust spacing between slots
            buttonPanel.add(slotPanel, gbc);
        }
    
        // Create a panel to hold the buttonPanel and currentFundsLabel
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Add the buttonPanel to the main panel in the center
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
    
        // Create a titled border for the mainPanel
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Test Regular Vending Machine");
        titledBorder.setTitleFont(new Font("Verdana", Font.BOLD, 24));
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20), // Add padding around the frame
                titledBorder));
    
        // Set the border for the content pane of the testRvmFrame
        ((JComponent) testRvmFrame.getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
        // Create a label to display the current funds
        currentFunds = computeTotalFunds(coinDenominationsMap, billDenominationsMap);
        currentFundsLabel = new JLabel("Current Funds: ₱" + currentFunds); // Peso sign here
        currentFundsLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        currentFundsLabel.setForeground(Color.BLACK); // Black color

         // Create the "Back to Create Options" button
         JButton backButton = new JButton("Back to Test Vending Features");
         backButton.setFont(new Font("Verdana", Font.BOLD, 14));
         backButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 testRvmFrame.dispose(); // Close the vending machine dialog
                 testVendingFeaturesOptions();
             }
         });
    
        // Add the current funds label to the main panel at the top
        mainPanel.add(currentFundsLabel, BorderLayout.NORTH);

        // Add back button to the main panel at the bottom
        mainPanel.add(backButton, BorderLayout.SOUTH);
    
        // Add the main panel to the testRvmFrame
        testRvmFrame.add(mainPanel);
    
        // Show the testRvmFrame
        testRvmFrame.pack();
        testRvmFrame.setLocationRelativeTo(null); // Center the frame on the screen
        testRvmFrame.setVisible(true);
    }
    
    /**
    * Method used to insert money to purchase an item
    * @param itemPrice
    * @param slot
    */
    private void insertMoneyFrame(double itemPrice, Slot slot) {
        JFrame insertMoneyFrame = new JFrame("Insert Money");
        insertMoneyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        insertMoneyFrame.setLayout(new BorderLayout());

        // Create the replenishing panel (left side)
        JPanel replenishingPanel = new JPanel();
        replenishingPanel.setLayout(new GridLayout(0, 2, 10, 10));

        // Initialize the denominations map for coins and bills separately
        coinDenominationsMap = new HashMap<>();
        coinDenominationsMap.put(1.0, 0);
        coinDenominationsMap.put(5.0, 0);
        coinDenominationsMap.put(10.0, 0);
        coinDenominationsMap.put(20.0, 0);

        billDenominationsMap = new HashMap<>();
        billDenominationsMap.put(20.0, 0);
        billDenominationsMap.put(50.0, 0);
        billDenominationsMap.put(100.0, 0);
        billDenominationsMap.put(200.0, 0);
        billDenominationsMap.put(500.0, 0);
        billDenominationsMap.put(1000.0, 0);

        // Create panels for coins and bills
        JPanel coinPanel = createDenominationPanel(coinDenominationsMap, "Coins");
        JPanel billPanel = createDenominationPanel(billDenominationsMap, "Bills");

        // Add the panels to the replenishing panel
        replenishingPanel.add(coinPanel);
        replenishingPanel.add(billPanel);

        // Add the replenishing panel to the left component of the split pane
        insertMoneyFrame.add(replenishingPanel, BorderLayout.CENTER);

        // Create a panel for the buttons (below the replenishing panel)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Add the "Insert Money" button below the replenishing panel
        JButton insertButton = new JButton("Insert Money");
        insertButton.setFont(new Font("Verdana", Font.BOLD, 14));
        insertButton.setBackground(Color.WHITE);
        insertButton.setForeground(Color.BLACK);
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 // Calculate the inserted Amount inserted by the user
                 double insertedAmount = computeTotalFunds(coinDenominationsMap, billDenominationsMap);

                 // Update the denominations map and total funds with the user-inserted money
                updateDenominations(coinDenominationsMap, coinPanel);
                updateDenominations(billDenominationsMap, billPanel);

                  // Check if the item quantity is greater than 0 before proceeding with the purchase
                    if (slot.getItem().getItemQuantity() <= 0) {
                        JOptionPane.showMessageDialog(insertMoneyFrame,
                                "This item is out of stock. Please choose another item.",
                                "Out of Stock", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    // Check if the user inserted an amount less than the item price
                    if (insertedAmount < itemPrice) {
                        JOptionPane.showMessageDialog(insertMoneyFrame,
                                "Insufficient funds. Please insert more money.",
                                "Insufficient Funds", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    // Calculate the change to be dispensed (if any)
                    double change = insertedAmount - itemPrice;

                    if (change == 0) {
                        insertMoneyFrame.dispose();

                        // Show a message for a successful purchase without any change
                        JOptionPane.showMessageDialog(insertMoneyFrame,
                                "Thank you for your purchase! No change to be dispensed.",
                                "Purchase Successful", JOptionPane.INFORMATION_MESSAGE);

                        // Deduct the item price from current funds (no change to dispense)
                        currentFunds += itemPrice;
                        currentFundsLabel.setText("Current Funds: ₱" + currentFunds);

                        // Update transaction entry and decrease item quantity
                        vendingMachine.addTransactionEntry(slot.getItem().getItemName(), itemPrice, insertedAmount, 0);
                        slot.getItem().deductQuantity(1);

                     } else if (change > 0) {
                         // Implement the action to dispense the change here
                         Map<Double, Integer> changeDenominationsMap = new HashMap<>();
                    
                        // Iterate through coins first
                        for (Double coinDenomination : coinDenominationsMap.keySet()) {
                            int count = (int) (change / coinDenomination);
                            if (count > 0) {
                                int availableCount = coinDenominationsMap.getOrDefault(coinDenomination, 0);
                                int dispensedCount = Math.min(count, availableCount);
                                changeDenominationsMap.put(coinDenomination, dispensedCount);
                                change -= dispensedCount * coinDenomination;
                                currentFunds -= dispensedCount * coinDenomination; // Update currentFunds
                            }
                        }

                        // Iterate through bills next
                        for (Double billDenomination : billDenominationsMap.keySet()) {
                            int count = (int) (change / billDenomination);
                            if (count > 0) {
                                int availableCount = billDenominationsMap.getOrDefault(billDenomination, 0);
                                int dispensedCount = Math.min(count, availableCount);
                                changeDenominationsMap.put(billDenomination, dispensedCount);
                                change -= dispensedCount * billDenomination;
                                currentFunds -= dispensedCount * billDenomination; // Update currentFunds
                            }
                        }

                        insertMoneyFrame.dispose();

                        // Display a message with the change amount and breakdown
                        String changeMessage = "Please take your change: ₱" + (insertedAmount - itemPrice) + "\n\nChange Breakdown:\n";
                        String coinsMessage = "";
                        String billsMessage = "";

                        for (Double denomination : changeDenominationsMap.keySet()) {
                            int count = changeDenominationsMap.get(denomination);
                            if (count > 0) {
                                if (denomination >= 20.0) {
                                    // Dispense bills
                                    billsMessage += "₱" + denomination + " Bill(s): " + count + "\n";
                                } else {
                                    // Dispense coins
                                    coinsMessage += "₱" + denomination + " Coin(s): " + count + "\n";
                                }
                            }
                        }

                        // Combine coins and bills messages into the changeMessage
                        changeMessage += coinsMessage + billsMessage;

                        JOptionPane.showMessageDialog(insertMoneyFrame, changeMessage, "Change Dispensed", JOptionPane.INFORMATION_MESSAGE);
                        
                         // Update the currentFunds after the successful purchase (deduct itemPrice and change)
                        currentFunds -= itemPrice;
                        currentFundsLabel.setText("Current Funds: ₱" + currentFunds);
            
                        // Update transaction entry and decrease item quantity
                        vendingMachine.addTransactionEntry(slot.getItem().getItemName(), itemPrice, insertedAmount, (insertedAmount - itemPrice));
                        slot.getItem().deductQuantity(1);
            
                        
                    } else {
                        // Insufficient denominations to dispense the change, cancel the order
                        JOptionPane.showMessageDialog(insertMoneyFrame,
                                "Insufficient denominations to dispense the change. Order canceled.",
                                "Order Canceled", JOptionPane.WARNING_MESSAGE);
                    }
            
                    // Close the insertMoneyFrame after completing the purchase or showing the warning
                    insertMoneyFrame.dispose();
    
            }
        });

        buttonPanel.add(insertButton);

        // Add the "Cancel" button to cancel the insertion
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Verdana", Font.BOLD, 14));
        cancelButton.setBackground(Color.WHITE);
        cancelButton.setForeground(Color.RED);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the insertMoneyFrame if the user cancels insertion
                insertMoneyFrame.dispose();
            }
        });
        buttonPanel.add(cancelButton);

        // Add the button panel to the insertMoneyFrame (below the replenishing panel)
        insertMoneyFrame.add(buttonPanel, BorderLayout.SOUTH);

        // Create a titled border for the insertMoneyFrame
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Insert Money Denominations");
        titledBorder.setTitleFont(new Font("Verdana", Font.BOLD, 24));
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        replenishingPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20), // Add padding around the frame
                titledBorder));

        // Set the border for the content pane of the insertMoneyFrame
        ((JComponent) insertMoneyFrame.getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Show the insertMoneyFrame
        insertMoneyFrame.pack();
        insertMoneyFrame.setLocationRelativeTo(null); // Center the frame on the screen
        insertMoneyFrame.setVisible(true);
    }

    /**
     * Method that updates the denominations map based on the user-inserted money
     * @param denominationsMap
     * @param panel
     */
    private void updateDenominations(Map<Double, Integer> denominationsMap, JPanel panel) {
        for (Component component : panel.getComponents()) {
            if (component instanceof JPanel) {
                JPanel denominationPanel = (JPanel) component;
                JLabel denominationLabel = (JLabel) denominationPanel.getComponent(0);
                JTextField denominationTextField = (JTextField) denominationPanel.getComponent(1);
    
                double denomination = Double.parseDouble(denominationLabel.getText().split(" ")[1]); // Get the denomination value from the label
                int count = Integer.parseInt(denominationTextField.getText());
    
                denominationsMap.put(denomination, count);
    
                // Update the denominationLabel with the new quantity
                denominationLabel.setText("₱ " + denomination + ": " + count);
            }
        }
    }
    
    // MAINTENANCE FEATURES 

    /**
     * Method to display maintenance features
     */
    private void maintenanceFeatures() {
        JFrame maintenanceFrame = new JFrame("SEOUL BITES VENDING MACHINE");
        maintenanceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        maintenanceFrame.setLayout(new BorderLayout());
        maintenanceFrame.setBackground(new Color(240, 240, 240)); // Light gray background

        // Create the vending machine panel look-alike border
        Border vendingMachineBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(139, 69, 19), 5), // Outer border (chocolate color)
                BorderFactory.createEmptyBorder(20, 20, 20, 20) // Inner padding
        );

        // Create the panel to hold the maintenance options
        JPanel maintenancePanel = new JPanel(new GridBagLayout());
        maintenancePanel.setBackground(new Color(210, 210, 210)); // Light gray background
        maintenancePanel.setBorder(vendingMachineBorder);

        JRadioButton restockRadioButton = createRadioButton("Restock Items");
        JRadioButton changePriceRadioButton = createRadioButton("Change Item Price");
        JRadioButton collectMoneyRadioButton = createRadioButton("Collect Money from Machine");
        JRadioButton replenishMoneyRadioButton = createRadioButton("Replenish Money");
        JRadioButton printTransactionsRadioButton = createRadioButton("Print List of Transactions");
        JRadioButton listQuantityRadioButton = createRadioButton("List Quantity of Items Sold");
        JRadioButton displayStartingRadioButton = createRadioButton("Display Starting Inventory");
        JRadioButton displayLastRadioButton = createRadioButton("Display Last Inventory");
        JRadioButton exitRadioButton = createRadioButton("Exit Maintenance Features");

        restockRadioButton.addActionListener(e -> {
            maintenanceFrame.dispose();
            slotMenu();
        });

        changePriceRadioButton.addActionListener(e -> {
            maintenanceFrame.dispose();
            setItemPrice();
        });

        collectMoneyRadioButton.addActionListener(e -> {
            maintenanceFrame.dispose();
            collectMoney();
        });

        replenishMoneyRadioButton.addActionListener(e -> {
            maintenanceFrame.dispose();
            replenishDenominationsFrame();  
        });

        printTransactionsRadioButton.addActionListener(e -> {
            maintenanceFrame.dispose();
            displayTransactions();
        });

        listQuantityRadioButton.addActionListener(e -> {
            maintenanceFrame.dispose();
            displayItemsSold();
        });

        displayStartingRadioButton.addActionListener(e -> {
            maintenanceFrame.dispose();
            displayStarting();
        });

        displayLastRadioButton.addActionListener(e -> {
            maintenanceFrame.dispose();
            displayLast();
        });

        exitRadioButton.addActionListener(e -> {
            maintenanceFrame.dispose();
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20); // Adjust spacing
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        maintenancePanel.add(restockRadioButton, gbc);

        gbc.gridy = 1;
        maintenancePanel.add(changePriceRadioButton, gbc);

        gbc.gridy = 2;
        maintenancePanel.add(collectMoneyRadioButton, gbc);

        gbc.gridy = 3;
        maintenancePanel.add(replenishMoneyRadioButton, gbc);

        gbc.gridy = 4;
        maintenancePanel.add(printTransactionsRadioButton, gbc);

        gbc.gridy = 5;
        maintenancePanel.add(listQuantityRadioButton, gbc);

        gbc.gridy = 6;
        maintenancePanel.add(displayStartingRadioButton, gbc);

        gbc.gridy = 7;
        maintenancePanel.add(displayLastRadioButton, gbc);

        gbc.gridy = 8;
        maintenancePanel.add(exitRadioButton, gbc);

        // Create a titled border for the maintenance panel
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Maintenance Features");
        titledBorder.setTitleFont(new Font("Verdana", Font.BOLD, 16));
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        maintenancePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20), // Add padding around the panel
                titledBorder));

        // Add the maintenance panel to the center of the maintenance frame
        maintenanceFrame.add(maintenancePanel, BorderLayout.CENTER);

        // Set the preferred size of the maintenance frame
        maintenanceFrame.setPreferredSize(new Dimension(400, 600));

        // Show the maintenance frame
        maintenanceFrame.pack();
        maintenanceFrame.setLocationRelativeTo(null); // Center the frame on the screen
        maintenanceFrame.setVisible(true);
    }

    /**
     * Method to display options for restock item
     * Option to add item to chosen slot or restock item quantity to chosen slot
     */
    private void slotMenu() {
        JFrame slotMenuFrame = new JFrame("SEOUL BITES VENDING MACHINE");
        slotMenuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        slotMenuFrame.setLayout(new BorderLayout());
        slotMenuFrame.setBackground(new Color(240, 240, 240)); // Light gray background

        // Create the panel to hold the slot options
        JPanel slotMenuPanel = new JPanel(new GridBagLayout());
        slotMenuPanel.setBackground(new Color(210, 210, 210)); // Light gray background

        JRadioButton addItemRadioButton = createRadioButton("Add Item");
        JRadioButton editItemRadioButton = createRadioButton("Edit Item");
        JRadioButton returnRadioButton = createRadioButton("Return to Main Menu");

        // Create a ButtonGroup so that only one radio button can be selected at a time
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(addItemRadioButton);
        buttonGroup.add(editItemRadioButton);
        buttonGroup.add(returnRadioButton);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20); // Adjust spacing
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        slotMenuPanel.add(addItemRadioButton, gbc);

        gbc.gridy = 1;
        slotMenuPanel.add(editItemRadioButton, gbc);

        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        slotMenuPanel.add(returnRadioButton, gbc);

        // Create a titled border for the slot menu panel
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Slot Menu");
        titledBorder.setTitleFont(new Font("Verdana", Font.BOLD, 16));
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        slotMenuPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20), // Add padding around the panel
                titledBorder));

        // Add action listeners to the radio buttons
        addItemRadioButton.addActionListener(e -> {
            slotMenuFrame.dispose();
            addItemToSlot();
        });

        editItemRadioButton.addActionListener(e -> {
            slotMenuFrame.dispose();
            restockSlot();
        });

        returnRadioButton.addActionListener(e -> {
            slotMenuFrame.dispose();
        });

        // Add the slot menu panel to the center of the slot menu frame
        slotMenuFrame.add(slotMenuPanel, BorderLayout.CENTER);

        // Set the preferred size of the slot menu frame
        slotMenuFrame.setPreferredSize(new Dimension(400, 300));

        // Show the slot menu frame
        slotMenuFrame.pack();
        slotMenuFrame.setLocationRelativeTo(null); // Center the frame on the screen
        slotMenuFrame.setVisible(true);
    }

    /**
     * Getter method to get 
     */
    public List<Slot> getSlots() {
        return slots;
    }

    /**
     * Method to add item to slot
     */
    private void addItemToSlot() {
        JFrame vendingMachineFrame = createVendingMachineFrame();

        // Get the buttonPanel from the vendingMachineFrame
        JPanel buttonPanel = (JPanel) vendingMachineFrame.getContentPane().getComponent(0);

        // Get the number of slots
        int numSlots = slots.size();

        // Add the "Add Item" buttons for each slot
        for (int i = 0; i < numSlots; i++) {
            final int slotIndex = i;

            // Create the "Add Item" button for this slot
            JButton addItemButton = new JButton("Add Item to Slot " + (i + 1));
            addItemButton.setFont(new Font("Verdana", Font.BOLD, 14));
            addItemButton.setOpaque(true);
            addItemButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Show the input dialog to get item details
                    int option = showAddItemInputDialog();

                    if (option == JOptionPane.OK_OPTION) {
                        String itemName = itemNameField.getText();
                        double price;
                        int itemQuantity;
                        int itemCalories;

                        try {
                            price = Double.parseDouble(priceField.getText());
                            itemQuantity = Integer.parseInt(quantityField.getText());
                            itemCalories = Integer.parseInt(caloriesField.getText());
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid values.");
                            return;
                        }

                        if (itemQuantity < 10) {
                            JOptionPane.showMessageDialog(null, "Error: Minimum quantity should be 10.");
                            return;
                        }

                        Item item = new Item(itemName, price, itemQuantity, itemCalories);
                        item.setInitialQuantity(itemQuantity);
                        slots.get(slotIndex).getItems().add(item);
                        slots.get(slotIndex).setItemCount(item.getItemQuantity());

                        // Update the item information panel for the selected slot
                        JPanel slotPanel = (JPanel) buttonPanel.getComponent(slotIndex);
                        JPanel itemPanel = (JPanel) slotPanel.getComponent(1);
                        addOrUpdateItemInfoPanel(itemPanel, item);

                        JOptionPane.showMessageDialog(null, itemName + " has been added to Slot " + (slotIndex + 1));
                    }
                }
            });

            // Add the "Add Item" button to the bottom of the slot panel
            JPanel slotPanel = (JPanel) buttonPanel.getComponent(i);
            slotPanel.add(addItemButton, BorderLayout.SOUTH);
        }

        addBackButtonToPanel(buttonPanel, vendingMachineFrame);

        vendingMachineFrame.pack();
        vendingMachineFrame.setLocationRelativeTo(null); // Center the frame on the screen
        vendingMachineFrame.setVisible(true);
    }

    /**
     * Helper method to ask for user input where 
     */
    private int showAddItemInputDialog() {
        // Create a custom JPanel with text fields for each input
        JPanel inputPanel = new JPanel(new BorderLayout(10, 10));
        inputPanel.setBackground(new Color(72, 57, 91));
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 5),
                BorderFactory.createEmptyBorder(20, 20, 20, 20) // Add padding
        ));

        JLabel titleLabel = new JLabel("Add Item to Slot");
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 18)); // Custom font for the title
        titleLabel.setForeground(Color.WHITE); 
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        inputPanel.add(titleLabel, BorderLayout.NORTH);

        // Create a nested JPanel with GridLayout for labels and text fields
        JPanel inputFieldsPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        inputFieldsPanel.setOpaque(false); // Make the nested panel transparent

        JLabel itemNameLabel = new JLabel("Item Name:");
        itemNameLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        itemNameLabel.setForeground(Color.WHITE); 
        inputFieldsPanel.add(itemNameLabel);
        itemNameField = new JTextField(20);
        inputFieldsPanel.add(itemNameField);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        priceLabel.setForeground(Color.WHITE); 
        inputFieldsPanel.add(priceLabel);
        priceField = new JTextField(10);
        inputFieldsPanel.add(priceField);

        JLabel quantityLabel = new JLabel("Quantity (minimum 10):");
        quantityLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        quantityLabel.setForeground(Color.WHITE); 
        inputFieldsPanel.add(quantityLabel);
        quantityField = new JTextField(10);
        inputFieldsPanel.add(quantityField);

        JLabel caloriesLabel = new JLabel("Calories:");
        caloriesLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        caloriesLabel.setForeground(Color.WHITE); 
        inputFieldsPanel.add(caloriesLabel);
        caloriesField = new JTextField(10);
        inputFieldsPanel.add(caloriesField);

        inputPanel.add(inputFieldsPanel, BorderLayout.CENTER);

        // Show the input dialog
        int option = JOptionPane.showOptionDialog(
                null,
                inputPanel,
                "Add Item to Slot",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new String[] { "Add Item", "Cancel" },
                "Add Item");

        return option;
    }

    /**
     * Helper method to update item information when an item is added to a slot
     */
    private void addOrUpdateItemInfoPanel(JPanel itemPanel, Item item) {
        JPanel itemInfoPanel = new JPanel(new BorderLayout());
        itemInfoPanel.setBackground(new Color(250, 250, 250)); // Lighter gray background
    
        // Add padding around the itemInfoPanel
        int padding = 10;
        itemInfoPanel.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
    
        JLabel itemNameLabel = new JLabel(item.getItemName());
        itemNameLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        itemNameLabel.setHorizontalAlignment(JLabel.CENTER);
        itemInfoPanel.add(itemNameLabel, BorderLayout.NORTH);
    
        JPanel detailsPanel = new JPanel();
        detailsPanel.setBackground(new Color(250, 250, 250)); // Lighter gray background
        detailsPanel.setLayout(new GridBagLayout());
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Adjust spacing within the detailsPanel
    
        JLabel priceLabel = new JLabel("Price: ₱ " + item.getItemPrice());
        priceLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
        detailsPanel.add(priceLabel, gbc);
    
        gbc.gridy = 1;
        JLabel quantityLabel = new JLabel("Quantity: " + item.getItemQuantity());
        quantityLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
        detailsPanel.add(quantityLabel, gbc);
        quantityLabels.add(quantityLabel);
    
        gbc.gridy = 2;
        JLabel caloriesLabel = new JLabel("Calories: " + item.getItemCalories());
        caloriesLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
        detailsPanel.add(caloriesLabel, gbc);
    
        itemInfoPanel.add(detailsPanel, BorderLayout.CENTER);
    
        // Remove any existing components in the item panel and add the new item info
        // panel
        itemPanel.removeAll();
        itemPanel.add(itemInfoPanel);
        itemPanel.revalidate();
        itemPanel.repaint();
    }
    
    /**
     * Method to restock item quantity
     */
    private void restockSlot() {
        JFrame vendingMachineFrame = createVendingMachineFrame();

        // Get the buttonPanel from the vendingMachineFrame
        JPanel buttonPanel = (JPanel) vendingMachineFrame.getContentPane().getComponent(0);

        // Get the number of slots
        int numSlots = slots.size();

        // Add the restock buttons for each slot
        for (int i = 0; i < numSlots; i++) {
            Slot slot = slots.get(i);
            final int slotIndex = i;

            // Create the restock button for this slot
            JButton restockButton = new JButton("Restock Slot " + slot.getSlotNumber());
            restockButton.setFont(new Font("Verdana", Font.BOLD, 14));
            restockButton.setOpaque(true);
            restockButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Prompt the user for the new quantity
                    int newQuantity = Integer.parseInt(JOptionPane.showInputDialog(vendingMachineFrame,
                            "Enter the new quantity:", "Restock Slot", JOptionPane.PLAIN_MESSAGE));

                    // Get the item associated with this slot
                    Slot clickedSlot = slots.get(slotIndex);
                    Item itemToUpdate = clickedSlot.getItems().get(0); // Assuming there's only one item per slot

                    itemToUpdate.setItemQuantity(newQuantity);

                    // Update the quantity label in the item information panel for the selected slot
                    // This is assuming that the item information panel is the second component in
                    // the slotPanel
                    JPanel slotPanel = (JPanel) buttonPanel.getComponent(slotIndex);
                    JPanel itemPanel = (JPanel) slotPanel.getComponent(1);
                    Component[] itemInfoPanels = itemPanel.getComponents();
                    for (Component comp : itemInfoPanels) {
                        if (comp instanceof JPanel) {
                            JPanel itemInfoPanel = (JPanel) comp;
                            Component[] labels = itemInfoPanel.getComponents();
                            for (Component label : labels) {
                                if (label instanceof JLabel) {
                                    JLabel quantityLabel = (JLabel) label;
                                    if (quantityLabel.getText().startsWith("Quantity:")) {
                                        quantityLabel.setText("Quantity: " + itemToUpdate.getItemQuantity());
                                        break; // No need to iterate further
                                    }
                                }
                            }
                        }
                    }
                }
            });

            // Add the restock button to the bottom of the slot panel
            JPanel slotPanel = (JPanel) buttonPanel.getComponent(i);
            slotPanel.add(restockButton, BorderLayout.SOUTH);
        }

        addBackButtonToPanel(buttonPanel, vendingMachineFrame);

        vendingMachineFrame.pack();
        vendingMachineFrame.setLocationRelativeTo(null); // Center the frame on the screen
        vendingMachineFrame.setVisible(true);
    }

    /**
     * Method to set new item price
     */
    private void setItemPrice() {
        JFrame vendingMachineFrame = createVendingMachineFrame();

        // Get the buttonPanel from the vendingMachineFrame
        JPanel buttonPanel = (JPanel) vendingMachineFrame.getContentPane().getComponent(0);

        // Get the number of slots
        int numSlots = slots.size();

        // Add the "Set Item Price" buttons for each slot
        for (int i = 0; i < numSlots; i++) {
            Slot slot = slots.get(i);
            final int slotIndex = i;

            // Create the "Set Item Price" button for this slot
            JButton setPriceButton = new JButton("Set Item Price for Slot " + slot.getSlotNumber());
            setPriceButton.setFont(new Font("Verdana", Font.BOLD, 14));
            setPriceButton.setOpaque(true);
            setPriceButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Prompt the user for the new price
                    double newPrice;
                    try {
                        newPrice = Double.parseDouble(JOptionPane.showInputDialog(vendingMachineFrame,
                                "Enter the new item price:", "Set Item Price", JOptionPane.PLAIN_MESSAGE));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid price.");
                        return;
                    }

                    // Update the item prices for all items in the slot
                    Slot clickedSlot = slots.get(slotIndex);
                    for (Item item : clickedSlot.getItems()) {
                        item.setItemPrice(newPrice);
                    }

                    // Update the item information panels for the selected slot
                    JPanel slotPanel = (JPanel) buttonPanel.getComponent(slotIndex);
                    JPanel itemPanel = (JPanel) slotPanel.getComponent(1);

                    // Find the price label and update its text
                    for (Component component : itemPanel.getComponents()) {
                        if (component instanceof JPanel) {
                            JPanel subPanel = (JPanel) component;
                            for (Component subComponent : subPanel.getComponents()) {
                                if (subComponent instanceof JLabel) {
                                    JLabel label = (JLabel) subComponent;
                                    String labelText = label.getText();
                                    if (labelText.startsWith("Price: ")) {
                                        label.setText("Price: ₱" + newPrice);
                                    }
                                }
                            }
                        }
                    }
                }
            });

            // Add the "Set Item Price" button to the bottom of the slot panel
            JPanel slotPanel = (JPanel) buttonPanel.getComponent(i);
            slotPanel.add(setPriceButton, BorderLayout.SOUTH);
        }

        addBackButtonToPanel(buttonPanel, vendingMachineFrame);

        vendingMachineFrame.pack();
        vendingMachineFrame.setLocationRelativeTo(null); // Center the frame on the screen
        vendingMachineFrame.setVisible(true);
    }

    /**
     * Method to collect money from vending machine
     * Resets the totalFunds to 0
     */
    private void collectMoney() {
        // Check if the denominations maps have been initialized
        if (coinDenominationsMap == null || billDenominationsMap == null) {
            JOptionPane.showMessageDialog(null, "Please replenish the money first.");
            return;
        }

        double totalFunds = computeTotalFunds(coinDenominationsMap, billDenominationsMap);
        JOptionPane.showMessageDialog(null, "Collecting money from the vending machine: ₱" + totalFunds);
        vendingMachine.collectMoney();
    }   

    // Method to create the replenish denominations frame
    /**
     * 
     */
    private void replenishDenominationsFrame() {
        JFrame replenishFrame = new JFrame("SEOUL BITES VENDING MACHINE - Replenish Money");
        replenishFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        replenishFrame.setLayout(new BorderLayout());

        // Create a split pane to divide the left and right sides
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(500); // Set the initial divider location

        // Create the replenishing panel (left side)
        JPanel replenishingPanel = new JPanel();
        replenishingPanel.setLayout(new GridLayout(0, 2, 10, 10));

        // Initialize the denominations map for coins and bills separately
        coinDenominationsMap = new HashMap<>();
        coinDenominationsMap.put(1.0, 0);
        coinDenominationsMap.put(5.0, 0);
        coinDenominationsMap.put(10.0, 0);
        coinDenominationsMap.put(20.0, 0);

        billDenominationsMap = new HashMap<>();
        billDenominationsMap.put(20.0, 0);
        billDenominationsMap.put(50.0, 0);
        billDenominationsMap.put(100.0, 0);
        billDenominationsMap.put(200.0, 0);
        billDenominationsMap.put(500.0, 0);
        billDenominationsMap.put(1000.0, 0);

        // Create panels for coins and bills
        JPanel coinPanel = createDenominationPanel(coinDenominationsMap, "Coins");
        JPanel billPanel = createDenominationPanel(billDenominationsMap, "Bills");

        // Add the panels to the replenishing panel
        replenishingPanel.add(coinPanel);
        replenishingPanel.add(billPanel);

        // Add the replenishing panel to the left component of the split pane
        splitPane.setLeftComponent(replenishingPanel);

        // Create the panel for the replenishing information (right side)
        JPanel replenishingInfoPanel = new JPanel(new BorderLayout());
        replenishingInfoPanel.setBackground(new Color(240, 240, 240)); // Light gray background

        // Create a text area to display the replenishing information
        JTextArea infoTextArea = new JTextArea();
        infoTextArea.setEditable(false);
        infoTextArea.setFont(new Font("Verdana", Font.PLAIN, 14));

        // Wrap the text area inside a JScrollPane
        JScrollPane scrollPane = new JScrollPane(infoTextArea);
        replenishingInfoPanel.add(scrollPane, BorderLayout.CENTER);

        // Add the replenishing info panel to the right component of the split pane
        splitPane.setRightComponent(replenishingInfoPanel);

        // Set the preferred sizes for the left and right components of the split pane
        splitPane.getLeftComponent().setPreferredSize(new Dimension(700, 400));
        splitPane.getRightComponent().setPreferredSize(new Dimension(300, 400));

        // Set the divider location based on the preferred size of the left component
        splitPane.setDividerLocation(splitPane.getLeftComponent().getPreferredSize().width);

        // Add the split pane to the replenish frame
        replenishFrame.add(splitPane, BorderLayout.CENTER);

        // Create a panel for the buttons (below the split pane)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Add the "Finish Replenishing" button below the split pane
        JButton finishButton = new JButton("Finish Replenishing");
        finishButton.setFont(new Font("Verdana", Font.BOLD, 14));
        finishButton.setBackground(Color.WHITE);
        finishButton.setForeground(Color.BLACK);
        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Compute the total funds after replenishing
                double totalFunds = computeTotalFunds(coinDenominationsMap, billDenominationsMap);

                // Prepare the replenishing information to be displayed
                StringBuilder messageBuilder = new StringBuilder();
                messageBuilder.append("Money replenished successfully!\n\n");
                messageBuilder.append("Coins:\n");
                messageBuilder.append("₱ 1.0: " + coinDenominationsMap.getOrDefault(1.0, 0) * 1.0 + "\n");
                messageBuilder.append("₱ 5.0: " + coinDenominationsMap.getOrDefault(5.0, 0) * 5.0 + "\n");
                messageBuilder.append("₱ 10.0: " + coinDenominationsMap.getOrDefault(10.0, 0) * 10.0 + "\n");
                messageBuilder.append("₱ 20.0: " + coinDenominationsMap.getOrDefault(20.0, 0) * 20.0 + "\n\n");
                messageBuilder.append("Bills:\n");
                messageBuilder.append("₱ 20.0: " + billDenominationsMap.getOrDefault(20.0, 0) * 20.0 + "\n");
                messageBuilder.append("₱ 50.0: " + billDenominationsMap.getOrDefault(50.0, 0) * 50.0 + "\n");
                messageBuilder.append("₱ 100.0: " + billDenominationsMap.getOrDefault(100.0, 0) * 100.0 + "\n");
                messageBuilder.append("₱ 200.0: " + billDenominationsMap.getOrDefault(200.0, 0) * 200.0 + "\n");
                messageBuilder.append("₱ 500.0: " + billDenominationsMap.getOrDefault(500.0, 0) * 500.0 + "\n");
                messageBuilder.append("₱ 1000.0: " + billDenominationsMap.getOrDefault(1000.0, 0) * 1000.0 + "\n\n");
                messageBuilder.append("Total Funds: ₱ " + totalFunds);

                // Set the message in the text area
                infoTextArea.setText(messageBuilder.toString());
            }
        });
        buttonPanel.add(finishButton);

        // Add the "Back to Maintenance Features" button to the button panel
        addBackButtonToPanel(buttonPanel, replenishFrame);
        
        // Add the button panel to the replenish frame (below the split pane)
        replenishFrame.add(buttonPanel, BorderLayout.SOUTH);
    
        // Create a titled border for the replenish frame
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Replenish Money Denominations");
        titledBorder.setTitleFont(new Font("Verdana", Font.BOLD, 24));
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        replenishingPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20), // Add padding around the frame
                titledBorder));

        // Set the border for the content pane of the replenish frame
        ((JComponent) replenishFrame.getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Show the replenish frame
        replenishFrame.pack();
        replenishFrame.setLocationRelativeTo(null); // Center the frame on the screen
        replenishFrame.setVisible(true);
    }


    // Method to compute total funds
    private double computeTotalFunds(Map<Double, Integer> coinDenominationsMap, Map<Double, Integer> billDenominationsMap) {
        double totalFunds = 0.0;
        for (Double denomination : coinDenominationsMap.keySet()) {
            int count = coinDenominationsMap.get(denomination);
            totalFunds += denomination * count;
        }
        for (Double denomination : billDenominationsMap.keySet()) {
            int count = billDenominationsMap.get(denomination);
            totalFunds += denomination * count;
        }
        return totalFunds;
    }


    // Create the denomination panel
    /**
     * 
     */
    private JPanel createDenominationPanel(Map<Double, Integer> denominationsMap, String panelTitle) {
        JPanel denominationPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Create and add the title label for the denomination panel
        JLabel titleLabel = new JLabel(panelTitle);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        denominationPanel.add(titleLabel, gbc);

        // Create and add the GUI components for each denomination
        int row = 1;
        for (Double denomination : denominationsMap.keySet()) {
            gbc.gridx = 0;
            gbc.gridy = row;
            gbc.gridwidth = 1;

            JLabel denominationLabel = new JLabel("₱ " + denomination + ": " + denominationsMap.get(denomination));
            denominationLabel.setFont(new Font("Verdana", Font.BOLD, 14));
            denominationPanel.add(denominationLabel, gbc);

            gbc.gridx = 1;
            gbc.gridy = row;
            gbc.gridwidth = 1;
            JButton subtractButton = new JButton("-");
            subtractButton.setFont(new Font("Verdana", Font.BOLD, 14));
            subtractButton.setBackground(Color.WHITE);
            subtractButton.setForeground(Color.RED);
            subtractButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Handle subtracting quantity when "-" button is clicked
                    subtractDenominationQuantity(denomination, denominationsMap);
                    denominationLabel.setText("₱ " + denomination + ": " + denominationsMap.get(denomination));
                }
            });
            denominationPanel.add(subtractButton, gbc);

            gbc.gridx = 2;
            gbc.gridy = row;
            gbc.gridwidth = 1;
            JButton addButton = new JButton("+");
            addButton.setFont(new Font("Verdana", Font.BOLD, 14));
            addButton.setBackground(Color.WHITE);
            addButton.setForeground(Color.GREEN);
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Handle adding quantity when "+" button is clicked
                    addDenominationQuantity(denomination, denominationsMap);
                    denominationLabel.setText("₱ " + denomination + ": " + denominationsMap.get(denomination));
                }
            });
            denominationPanel.add(addButton, gbc);

            row++;
        }

        return denominationPanel;
    }

    /**
     * 
     */
    private void subtractDenominationQuantity(Double denomination, Map<Double, Integer> denominationsMap) {
        int quantity = denominationsMap.getOrDefault(denomination, 0);
        if (quantity > 0) {
            denominationsMap.put(denomination, quantity - 1);
        }   
    }

    /**
     * 
     */
    private void addDenominationQuantity(Double denomination, Map<Double, Integer> denominationsMap) {
        int quantity = denominationsMap.getOrDefault(denomination, 0);
        denominationsMap.put(denomination, quantity + 1);
    }

    /**
     * Method to display transactions transacted in the vending machine
     */
    private void displayTransactions() {
        List<String> itemNames = vendingMachine.getItemNames();
        List<Double> itemPrices = vendingMachine.getItemPrices();
        List<Double> insertedAmounts = vendingMachine.getInsertedAmounts();
        List<Double> changeAmounts = vendingMachine.getChangeAmounts();
    
        JFrame receiptFrame = new JFrame("SEOUL BITES VENDING MACHINE");
        receiptFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        receiptFrame.setLayout(new BorderLayout());
    
        JPanel receiptPanel = new JPanel();
        receiptPanel.setLayout(new BoxLayout(receiptPanel, BoxLayout.Y_AXIS));
    
        JLabel headerLabel = new JLabel("TRANSACTION RECEIPT");
        headerLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        receiptPanel.add(headerLabel);
    
        receiptPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add some space between the header and transactions
    
        // Add the header for the columns
        String headerInfo = String.format("%-16s%-30s%-12s%-16s%-8s",
                "Transaction No", "Item Name", "Item Price", "Inserted Money", "Change");
        JLabel headerInfoLabel = new JLabel(headerInfo);
        headerInfoLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        headerInfoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        receiptPanel.add(headerInfoLabel);
    
        for (int i = 0; i < itemNames.size(); i++) {
            // Create the transaction information for each row
            String transactionInfo = String.format("%-16d%-30s₱%-11.2f₱%-15.2f₱%-7.2f",
                    i + 1, itemNames.get(i), itemPrices.get(i), insertedAmounts.get(i), changeAmounts.get(i));
            JLabel transactionLabel = new JLabel(transactionInfo);
            transactionLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
            transactionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            receiptPanel.add(transactionLabel);
        }
    
        receiptFrame.add(receiptPanel, BorderLayout.CENTER);
    
        receiptFrame.pack();
        receiptFrame.setLocationRelativeTo(null); // Center the frame on the screen
        receiptFrame.setVisible(true);
    }    

    /**
     * Method to display items sold in the vending machine
     */
    public void displayItemsSold() {
        JFrame itemsSoldFrame = new JFrame("SEOUL BITES VENDING MACHINE");
        itemsSoldFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        itemsSoldFrame.setLayout(new BorderLayout());
    
        JPanel itemsSoldPanel = new JPanel();
        itemsSoldPanel.setLayout(new BoxLayout(itemsSoldPanel, BoxLayout.Y_AXIS));

        JLabel headerLabel = new JLabel("ITEMS SOLD SUMMARY");
        headerLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        itemsSoldPanel.add(headerLabel);
        
        itemsSoldPanel.add(Box.createRigidArea(new Dimension(0, 20)));
    
        JLabel headerInfoLabel = new JLabel(String.format("%-20s%-15s%-15s", "Item Name", "Quantity Sold", "Total Amount"));
        headerInfoLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        headerInfoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        itemsSoldPanel.add(headerInfoLabel);
    
        itemsSoldPanel.add(Box.createRigidArea(new Dimension(0, 5)));
    
        boolean noItemsSold = true; // A flag to track if no items are sold
    
        for (Slot slot : slots) {
            ArrayList<Item> items = slot.getItems();
    
            for (Item item : items) {
                String itemName = item.getItemName();
                int quantitySold = vendingMachine.getQuantitySold(itemName);
                double totalAmount = item.getTotalAmount();
    
                if (quantitySold > 0) {
                    JLabel itemLabel = new JLabel(String.format("%-20s%-15d₱%-15.2f", itemName, quantitySold, totalAmount));
                    itemLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
                    itemLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    itemsSoldPanel.add(itemLabel);
                    noItemsSold = false; // There's at least one item sold
                }
            }
        }
    
        if (noItemsSold) {
            JLabel noItemsLabel = new JLabel("No items sold yet.");
            noItemsLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
            noItemsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            itemsSoldPanel.add(noItemsLabel);
        }
    
        itemsSoldPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        itemsSoldFrame.add(itemsSoldPanel);
    
        itemsSoldFrame.pack();
        itemsSoldFrame.setLocationRelativeTo(null); // Center the frame on the screen
        itemsSoldFrame.setVisible(true);
    }

    /**
     * Method to display starting inventory (item/s are not yet purchased) in the vending machine
     */
    public void displayStarting() {
        JFrame startInventoryFrame = new JFrame("SEOUL BITES VENDING MACHINE");
        startInventoryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        startInventoryFrame.setLayout(new BorderLayout());
    
        JPanel startInventoryPanel = new JPanel();
        startInventoryPanel.setLayout(new BoxLayout(startInventoryPanel, BoxLayout.Y_AXIS));
    
        JLabel headerLabel = new JLabel("STARTING INVENTORY IN VENDING MACHINE", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        startInventoryPanel.add(headerLabel);
    
        startInventoryPanel.add(Box.createRigidArea(new Dimension(0, 20)));
    
        JLabel headerInfoLabel = new JLabel(String.format("%-5s%-30s%-10s%-15s", "Slot", "Item Name", "Price", "Start Quantity"));
        headerInfoLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        headerInfoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        startInventoryPanel.add(headerInfoLabel);
    
        startInventoryPanel.add(Box.createRigidArea(new Dimension(0, 5)));
    
        for (Slot slot : slots) {
            ArrayList<Item> items = slot.getItems();
            for (Item item : items) {
                String itemInfo = String.format("%-5d%-30s₱%-9.2f%-15d", slot.getSlotNumber(), item.getItemName(), item.getItemPrice(), item.getInitialQuantity());
                JLabel itemLabel = new JLabel(itemInfo);
                itemLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
                itemLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                startInventoryPanel.add(itemLabel);
            }
        }
    
        startInventoryPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        startInventoryFrame.add(startInventoryPanel);
    
        startInventoryFrame.pack();
        startInventoryFrame.setLocationRelativeTo(null); // Center the frame on the screen
        startInventoryFrame.setVisible(true);
    }

    /**
     * Method to display last inventory (after purchasing item/s)
     */
    public void displayLast(){
        JFrame startInventoryFrame = new JFrame("SEOUL BITES VENDING MACHINE");
        startInventoryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        startInventoryFrame.setLayout(new BorderLayout());
    
        JPanel startInventoryPanel = new JPanel();
        startInventoryPanel.setLayout(new BoxLayout(startInventoryPanel, BoxLayout.Y_AXIS));
    
        JLabel headerLabel = new JLabel("LAST INVENTORY IN VENDING MACHINE", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        startInventoryPanel.add(headerLabel);
    
        startInventoryPanel.add(Box.createRigidArea(new Dimension(0, 20)));
    
        JLabel headerInfoLabel = new JLabel(String.format("%-5s%-30s%-10s%-15s", "Slot", "Item Name", "Price", "Last Quantity"));
        headerInfoLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        headerInfoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        startInventoryPanel.add(headerInfoLabel);
    
        startInventoryPanel.add(Box.createRigidArea(new Dimension(0, 5)));
    
        for (Slot slot : slots) {
            ArrayList<Item> items = slot.getItems();
            for (Item item : items) {
                String itemInfo = String.format("%-5d%-30s₱%-9.2f%-15d", slot.getSlotNumber(), item.getItemName(), item.getItemPrice(), item.getItemQuantity());
                JLabel itemLabel = new JLabel(itemInfo);
                itemLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
                itemLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                startInventoryPanel.add(itemLabel);
            }
        }
    
        startInventoryPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        startInventoryFrame.add(startInventoryPanel);
    
        startInventoryFrame.pack();
        startInventoryFrame.setLocationRelativeTo(null); // Center the frame on the screen
        startInventoryFrame.setVisible(true);
    }

    /**
     * Method to create a back to maintenance button
     * @return backButton 
     */
    private JButton createBackToMaintenanceButton() {
        JButton backButton = new JButton("Back to Maintenance Features");
        backButton.setFont(new Font("Verdana", Font.BOLD, 14));
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(Color.BLACK);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the maintenanceFeatures() method to go back to maintenance options
                maintenanceFeatures();
            }
        });
        return backButton;
    }

    /**
     * 
     * @param panel
     * @param vendingMachineFrame
     */
    private void addBackButtonToPanel(JPanel panel, JFrame vendingMachineFrame) {
        JButton backButton = createBackToMaintenanceButton();
        GridBagConstraints gbcBackButton = new GridBagConstraints();
        gbcBackButton.gridx = 0;
        gbcBackButton.gridy = panel.getComponentCount(); // Get the next available row
        gbcBackButton.gridwidth = panel.getComponentCount(); // Set the gridwidth to span all columns
        gbcBackButton.fill = GridBagConstraints.HORIZONTAL;
        gbcBackButton.anchor = GridBagConstraints.CENTER; // Center the button
        gbcBackButton.insets = new Insets(10, 10, 10, 10); // Adjust spacing
        panel.add(backButton, gbcBackButton);

        // Add action listener to the back button
        backButton.addActionListener(e -> {
            vendingMachineFrame.dispose(); // Dispose of the vending machine frame
        });
    }

    /**
     * Method to exit the program
     */
    private void exitProgram() {
        int result = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to exit the program?",
                "Exit Program",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Exiting the program!");
            System.exit(0);
        }
    }

    /**
     * 
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VendingMachineGUI();
            }
        });
    }
}