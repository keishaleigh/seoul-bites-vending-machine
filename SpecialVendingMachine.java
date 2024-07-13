import java.util.List;

/**
 * The SpecialVendingMachine class represents a specialized version of the VendingMachine class.
 * This class extends the VendingMachine class and adds additional attributes and methods specific to the Special Vending Machine.
 */
public class SpecialVendingMachine extends VendingMachine {

    // Additional attributes specific to the Special Vending Machine
    private String packageName; // The name of the package
    private List<Item> packageItems; // The list of items in the package
    private double packagePrice; // The price of the package

    /**
     * Constructor for the SpecialVendingMachine class.
     *
     * @param packageName   The name of the package.
     * @param packageItems  The list of items in the package.
     * @param packagePrice  The price of the package.
     */
    public SpecialVendingMachine(String packageName, List<Item> packageItems, double packagePrice) {
        super(); // Call the constructor of the parent class (VendingMachine)
        this.packageName = packageName;
        this.packageItems = packageItems;
        this.packagePrice = packagePrice;
    }

    /**
     * Gets the name of the package.
     *
     * @return The name of the package.
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * Sets the name of the package.
     *
     * @param packageName The name of the package to be set.
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    /**
     * Gets the list of items in the package.
     *
     * @return The list of items in the package.
     */
    public List<Item> getPackageItems() {
        return packageItems;
    }

    /**
     * Sets the list of items in the package.
     *
     * @param packageItems The list of items to be set in the package.
     */
    public void setPackageItems(List<Item> packageItems) {
        this.packageItems = packageItems;
    }

    /**
     * Gets the price of the package.
     *
     * @return The price of the package.
     */
    public double getPackagePrice() {
        return packagePrice;
    }

    /**
     * Sets the price of the package.
     *
     * @param packagePrice The price of the package to be set.
     */
    public void setPackagePrice(double packagePrice) {
        this.packagePrice = packagePrice;
    }

    /**
     * Calculates and returns the total calories of all items in the package.
     *
     * @return The total calories of all items in the package.
     */
    public int getTotalCalories() {
        int totalCalories = 0;
        for (Item item : packageItems) {
            totalCalories += item.getItemCalories();
        }
        return totalCalories;
    }
}
