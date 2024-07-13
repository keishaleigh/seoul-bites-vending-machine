import java.util.ArrayList;

/**
 * The Slot class represents a slot in a vending machine.
 */
public class Slot {
    private ArrayList<Item> items;
    private int slotCapacity;
    private int itemCount;
    private int slotNumber;

    /**
     * Constructor for the Slot class
     *
     * @param item The item to be added to the slot
     * @param slotNumber The number assigned to the slot
     * 
     */
    public Slot(Item item, int slotNumber) {
        this.items = new ArrayList<>(this.slotCapacity); 
        this.items.add(item); 
        this.itemCount = item.getItemQuantity();
        this.slotNumber = slotNumber;
    }
    
    /**
     * Creates a new slot with the specified slot number and slot capacity.
     *
     * @param slotNumber The number of the slot.
     * @param slotCapacity The maximum capacity of the slot.
     */
    public Slot(int slotNumber, int slotCapacity) {
        this.slotNumber = slotNumber;
        this.slotCapacity = slotCapacity;
        this.items = new ArrayList<>(); // Initialization of the items list with an empty ArrayList
    }

    /**
     * Get the items in the slot
     *
     * @return An array of items in the slot
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * Get the current count of items in the slot
     *
     * @return The number of items in the slot
     */
    public int getItemCount() {
        return itemCount;
    }

    /**
     * Set the itemCount to the specified value.
     *
     * @param itemCount The new value to set as the itemCount.
     */
    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    /**
     * Get the capacity of the slot
     *
     * @return The capacity of the slot
     */
    public int getSlotCapacity() {
        return slotCapacity;
    }

    /**
     * Get the slot number of the specific slot
     *
     * @return The slot number
     */
    public int getSlotNumber() {
        return slotNumber;
    }

    /**
     * Check if the slot is empty (contains no items)
     *
     * @return true if the slot is empty, false otherwise
     */
    public boolean isSlotEmpty() {
        return itemCount == 0;
    }

    /**
     * Retrieves the item from the selected slot.
     *
     * @return The first item in the slot, or null if the slot is empty.
     */
    public Item getItem() {
        if (items.isEmpty()) {
            System.out.println("Slot " + slotNumber + " is empty. No items found.");
            return null;
        }
        
        return items.get(0);
    }
}