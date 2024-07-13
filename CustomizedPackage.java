import java.util.ArrayList;
import java.util.List;


/**
 * Represents a customized package that extends the Item class.
 * A customized package allows adding multiple items along with their corresponding slot numbers.
 */
public class CustomizedPackage extends Item {

    private List<Item> packageItems; // List to store the items in the package
    private List<Integer> packageSlotNumbers; // List to store the slot numbers for each item in the package

    /**
     * Constructs a new CustomizedPackage object with the given parameters.
     *
     * @param itemName     The name of the package.
     * @param itemPrice    The price of the package.
     * @param itemQuantity The quantity of the package.
     * @param itemCalories The calories of the package.
     */
    public CustomizedPackage(String itemName, double itemPrice, int itemQuantity, int itemCalories) {
        super(itemName, itemPrice, itemQuantity, itemCalories);
        this.packageItems = new ArrayList<>();
        this.packageSlotNumbers = new ArrayList<>(); // Initialize the slot number list
    }

    /**
     * Adds an item along with its slot number to the package.
     *
     * @param item The item to be added to the package.
     * @param slotNumber the slot number to be added to the list
     */
    public void addToPackage(Item item, int slotNumber) {
        packageItems.add(item);
        packageSlotNumbers.add(slotNumber); // 
    }

    /**
     * Retrieves the slot number for a given item in the package.
     *
     * @param item The item for which to retrieve the slot number.
     * @return The slot number of the item, or -1 if the item is not found in the package.
     */
    public int getSlotNumberForItem(Item item) {
        int index = packageItems.indexOf(item);
        if (index != -1) {
            return packageSlotNumbers.get(index);
        }
        return -1;
    }

    /**
     * Retrieves the list of items in the package.
     *
     * @return The list of items in the package.
     */
    public List<Item> getPackageItems() {
        return packageItems;
    }
}


