import java.util.Map;
import java.util.HashMap;

/**
 * The Denomination class represents a denomination system that keeps track of different coins and bills.
 */
public class Denomination {
    private double coin1;
    private double coin5;
    private double coin10;
    private double coin20;

    private double bill20;
    private double bill50;
    private double bill100;
    private double bill200;
    private double bill500;
    private double bill1000;
   
    private double totalValue;
    private Map<Double, Integer> denominationsMap;

    private static Denomination instance; // Singleton instance

    /**
     * public constructor for the Denomination class (singleton pattern)
     */
    public Denomination() {
        this.coin1 = 0;
        this.coin5 = 0;
        this.coin10 = 0;
        this.coin20 = 0;
        this.bill20 = 0;
        this.bill50 = 0;
        this.bill100 = 0;
        this.bill200 = 0;
        this.bill500 = 0;
        this.bill1000 = 0;
        this.totalValue = 0;
        this.denominationsMap = new HashMap<>();
    }

    /**
     * Returns the instance of the Denomination class (singleton pattern)
     *
     * @return the instance of Denomination
     */
    public static Denomination getInstance() {
        if (instance == null) {
            instance = new Denomination();
        }
        return instance;
    }

    /**
     * deduct the change mount from the total value 
     * @param amount is the value of change amount
     */
    public void deductAmount(double amount) {
        totalValue -= amount;
    }
    
    /**
     * Returns the ₱1 coin
     *
     * @return the ₱1 coin
     * 
     */
    public double get1Coin() {
        return coin1;
    }

    /**
     * Returns the ₱5 coin
     *
     * @return the ₱5 coin
     * 
     */
    public double get5Coin() {
        return coin5;
    }

    /**
     * Returns the ₱10 coin
     *
     * @return the ₱10 coin
     * 
     */
    public double get10Coin() {
        return coin10;
    }

    /**
     * Returns the ₱20 coin
     *
     * @return the ₱20 coin
     * 
     */
    public double get20Coin() {
        return coin20;
    }

    /**
     * Returns the ₱20 bill
     *
     * @return the ₱20 bill
     * 
     */
    public double get20Bill() {
        return bill20;
    }

    /**
     * Returns the ₱50 bill
     *
     * @return the ₱50 bill
     * 
     */
    public double get50Bill() {
        return bill50;
    }

    /**
     * Returns the ₱100 bill
     *
     * @return the ₱100 bill
     * 
     */
    public double get100Bill() {
        return bill100;
    }

    /**
     * Returns the ₱200 bill
     *
     * @return the ₱200 bill
     * 
     */
    public double get200Bill() {
        return bill200;
    }

    /**
     * Returns the ₱500 bill
     *
     * @return the ₱500 bill
     * 
     */
    public double get500Bill() {
        return bill500;
    }

    /**
     * Returns the ₱1000 bill
     *
     * @return the ₱1000 bill
     * 
     */
    public double get1000Bill() {
        return bill1000;
    }

    /**
     * Returns the total amount of money inserted
     *
     * @return the total amount of money inserted
     */
    public double getTotalValue() {
        return totalValue;
    }
    
    /**
     * Updates the value of ₱1 coin
     *
     * @param coin1 the current ₱1 coin value
     * 
     */
    public void set1Coin(double coin1) {
        this.coin1 = coin1;
    }

    /**
     * Updates the value of ₱5 coin
     *
     * @param coin5 the current ₱5 coin value
     * 
     */
    public void set5Coin(double coin5) {
        this.coin5 = coin5;
    }

    /**
     * Updates the value of ₱10 coin
     *
     * @param coin10 the current ₱10 coin value
     * 
     */
    public void set10Coin(double coin10) {
        this.coin10 = coin10;
    }

    /**
     * Updates the value of ₱20 coin
     *
     * @param coin20 the current ₱20 coin value
     * 
     */
    public void set20Coin(double coin20) {
        this.coin20 = coin20;
    }
    
    /**
     * Updates the value of ₱20 bill
     *
     * @param bill20 the current ₱20 bill value
     * 
     */
    public void set20Bill(double bill20) {
        this.bill20 = bill20;
    }

    /**
     * Updates the value of ₱50 bill
     *
     * @param bill50 the current ₱50 bill value
     * 
     */
    public void set50Bill(double bill50) {
        this.bill50 = bill50;
    }

    /**
     * Updates the value of ₱100 bill
     *
     * @param bill100 the current ₱100 bill value
     * 
     */
    public void set100Bill(double bill100) {
        this.bill100 = bill100;
    }

    /**
     * Updates the value of ₱200 bill
     *
     * @param bill200 the current ₱200 bill value
     * 
     */
    public void set200Bill(double bill200) {
        this.bill200 = bill200;
    }

    /**
     * Updates the value of ₱500 bill
     *
     * @param bill500 the current ₱500 bill value
     * 
     */
    public void set500Bill(double bill500) {
        this.bill500 = bill500;
    }

    /**
     * Updates the value of ₱1000 bill
     *
     * @param bill1000 the current ₱1000 bill value
     * 
     */
    public void set1000Bill(double bill1000) {
        this.bill1000 = bill1000;
    }

    /**
     * Computes the total amount of money represented by the given denominations object
     *
     * @param denomination The denominations object representing the quantities of different coins and bills.
     * @return The total amount of money computed from the denominations.
     */
    public double computeTotal(Denomination denomination) {
        double totalMoney = 0.0;
        totalMoney += get1Coin();
        totalMoney += get5Coin() * 5.0;
        totalMoney += get10Coin() * 10.0;
        totalMoney += get20Coin() + get20Bill() * 20.0;
        totalMoney += get50Bill() * 50.0;
        totalMoney += get100Bill() * 100.0;
        totalMoney += get200Bill() * 200.0;
        totalMoney += get500Bill() * 500.0;
        totalMoney += get1000Bill() * 1000.0;

        return totalMoney;
    }

    /**
     * Prompts the user to insert ₱1 coins into the vending machine
     * @param coin1Count the value of 1 peso coin inserted
     */
    public void insertCoin1(int coin1Count) {
        // Calculate the value of the inserted coins
        double coin1Value = 1.0 * coin1Count;
    
        // Update the coin 1 count and totalValue
        denominationsMap.put(1.0, denominationsMap.getOrDefault(1.0, 0) + coin1Count);
        totalValue += coin1Value;
    
        System.out.println("You inserted " + coin1Count + " ₱1 coins");
        System.out.println("Total Value: ₱" + totalValue);
    }
    

    /**
     * Prompts the user to insert ₱5 coins into the vending machine
     * @param coin5Count the value of 5 pesos coins inserted
     */
    public void insertCoin5(int coin5Count) {
        // Calculate the value of the inserted coins
        double coint5Value = 5.0 * coin5Count;
    
        // Update the coin 5 count and totalValue
        denominationsMap.put(5.0, denominationsMap.getOrDefault(5.0, 0) + coin5Count);
        totalValue += coint5Value;
    
        System.out.println("You inserted " + coin5Count + " ₱5 coins");
        System.out.println("Total Value: ₱" + totalValue);
    }

    /**
     * Prompts the user to insert ₱10 coins into the vending machine
     * @param coin10Count the value of inserted 10 pesos coins
     */
    public void insertCoin10(int coin10Count) {
        // Calculate the value of the inserted coins
        double coin10Value = 10.0 * coin10Count;
    
        // Update the coin 10 count and totalValue
        denominationsMap.put(5.0, denominationsMap.getOrDefault(10.0, 0) + coin10Count);
        totalValue += coin10Value;
    
        System.out.println("You inserted " + coin10Count + " ₱10 coins");
        System.out.println("Total Value: ₱" + totalValue);
    }

    /**
     * Prompts the user to insert ₱20 coins into the vending machine
     * @param coin20Count the value of inserted 20 pesoscoins
     */
    public void insertCoin20(int coin20Count) {
        // Calculate the value of the inserted coins
        double coint20Value = 20.0 * coin20Count;
    
        // Update the coin 20 count and totalValue
        denominationsMap.put(20.0, denominationsMap.getOrDefault(20.0, 0) + coin20Count);
        totalValue += coint20Value;
    
        System.out.println("You inserted " + coin20Count + " ₱20 coins");
        System.out.println("Total Value: ₱" + totalValue);
    }

    /**
     * Prompts the user to insert ₱20 bills into the vending machine
     * @param bill20Count the value of 20 pesoss bill inserted 
     */
    public void insertBill20(int bill20Count) {
        // Calculate the value of the inserted bills
        double bill20Value = 20.0 * bill20Count;
    
        // Update the bill 20 count and totalValue
        denominationsMap.put(20.0, denominationsMap.getOrDefault(20.0, 0) + bill20Count);
        totalValue += bill20Value;
    
        System.out.println("You inserted " + bill20Count + " ₱20 bills");
        System.out.println("Total Value: ₱" + totalValue);
    }

    /**
     * Prompts the user to insert ₱50 bills into the vending machine
     * @param bill50Count the value of inserted 50 pesos bills
     */
    public void insertBill50(int bill50Count) {
        // Calculate the value of the inserted bills
        double bill50Value = 50.0 * bill50Count;
    
        // Update the bill 50 count and totalValue
        denominationsMap.put(50.0, denominationsMap.getOrDefault(50.0, 0) + bill50Count);
        totalValue += bill50Value;
    
        System.out.println("You inserted " + bill50Count + " ₱50 bills");
        System.out.println("Total Value: ₱" + totalValue);
    }

    /**
     * Prompts the user to insert ₱100 bills into the vending machine
     * @param bill100Count the value of isnerted 100 pesos bills 
     */
    public void insertBill100(int bill100Count) {
        // Calculate the value of the inserted bills
        double bill100Value = 100.0 * bill100Count;
    
        // Update the bill 100 count and totalValue
        denominationsMap.put(100.0, denominationsMap.getOrDefault(100.0, 0) + bill100Count);
        totalValue += bill100Value;
    
        System.out.println("You inserted " + bill100Count + " ₱100 bills");
        System.out.println("Total Value: ₱" + totalValue);
    }

    /**
     * Prompts the user to insert ₱200 bills into the vending machin
     * @param bill200Count the value of inserted 200 pesos bills
     */
    public void insertBill200(int bill200Count) {
        // Calculate the value of the inserted bill
        double bill200Value = 200.0 * bill200Count;
    
        // Update the bill 50 count and totalValue
        denominationsMap.put(200.0, denominationsMap.getOrDefault(200.0, 0) + bill200Count);
        totalValue += bill200Value;
    
        System.out.println("You inserted " + bill200Count + " ₱200 bills");
        System.out.println("Total Value: ₱" + totalValue);
    }

    /**
     * Prompts the user to insert ₱500 bills into the vending machine
     * @param bill500Count the value of inserted 500 pesos bills
     */
    public void insertBill500(int bill500Count) {
        // Calculate the value of the inserted bills
        double bill500Value = 500.0 * bill500Count;
    
        // Update the bill 50 count and totalValue
        denominationsMap.put(500.0, denominationsMap.getOrDefault(500.0, 0) + bill500Count);
        totalValue += bill500Value;
    
        System.out.println("You inserted " + bill500Count + " ₱500 bills");
        System.out.println("Total Value: ₱" + totalValue);
    }

    /**
     * Prompts the user to insert ₱1000 bills into the vending machine
     * @param bill1000Count the value of 1000 pesos bills inserted
     */
    public void insertBill1000(int bill1000Count) {
        // Calculate the value of the inserted bills
        double bill1000Value = 1000.0 * bill1000Count;
    
        // Update the bill 100 count and totalValue
        denominationsMap.put(1000.0, denominationsMap.getOrDefault(1000.0, 0) + bill1000Count);
        totalValue += bill1000Value;
    
        System.out.println("You inserted " + bill1000Count + " ₱1000 bills");
        System.out.println("Total Value: ₱" + totalValue);
    }

    /**
     * Subtracts the specified amount from the total value
     *
     * @param amount the amount to subtract
     */
    public void subtractAmount(double amount) {
        totalValue -= amount;
    }
    
    /**
     * Returns the map containing denominations and their counts
     *
     * @return the denominations map
     */
    public Map<Double, Integer> getDenominationsMap() {
        return denominationsMap;
    }

    /**
     * Returns the quantity of a specific denomination
     *
     * @param denominationValue The value of the denomination
     * @return The quantity of the denomination
     */
    public int getDenominationQuantity(double denominationValue) {
        return denominationsMap.getOrDefault(denominationValue, 0);
    }
     /**
     * Dispenses change using available denominations
     *
     * @param changeAmount the amount of change to be dispensed
     */
    public void dispenseChange(double changeAmount) {
        // Define the denominations for bills and coins
        double[] billDenominations = { 1000.0, 500.0, 200.0, 100.0, 50.0, 20.0 };
        double[] coinDenominations = { 20.0, 10.0, 5.0, 1.0};
    
        System.out.println("\nDispensing change:");
    
        // Keep track of the number of each denomination
        Map<String, Integer> changeBreakdown = new HashMap<>();
        for (double denomination : billDenominations) {
            if (changeAmount >= denomination) {
                int numBills = (int) (changeAmount / denomination);
                changeBreakdown.put("₱" + (int) denomination + " bill(s)", numBills);
                changeAmount -= numBills * denomination;
            }
        }
    
        for (double denomination : coinDenominations) {
            if (changeAmount >= denomination) {
                int numCoins = (int) (changeAmount / denomination);
                changeBreakdown.put("₱" + (int) denomination + " coin(s)", numCoins);
                changeAmount -= numCoins * denomination;
            }
        }
    
        // Check if there is any remaining change (smaller than the smallest denomination)
        if (changeAmount > 0.0) {
            changeBreakdown.put("₱" + (int) (changeAmount * 100) + " cent(s)", 1);
            changeAmount = 0.0;
        }
    
        // Display the breakdown of denominations
        for (Map.Entry<String, Integer> entry : changeBreakdown.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}