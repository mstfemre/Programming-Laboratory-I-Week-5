public class Package {
    private final String senderName;
    private final String senderAddress;
    private final String senderCity;
    private final String senderState;
    private final String senderZipCode;

    private final String recipientName;
    private final String recipientAddress;
    private final String recipientCity;
    private final String recipientState;
    private final String recipientZipCode;

    private final double weight;
    private final double costPerOunce;

    public Package(String senderName, String senderAddress, String senderCity,
                   String senderState, String senderZipCode,
                   String recipientName, String recipientAddress, String recipientCity,
                   String recipientState, String recipientZipCode,
                   double weight, double costPerOunce) {
        if (!Double.isFinite(weight) || weight <= 0) {
            throw new IllegalArgumentException("Agirlik pozitif ve sonlu olmalidir.");
        }
        if (!Double.isFinite(costPerOunce) || costPerOunce <= 0) {
            throw new IllegalArgumentException("Ons basina ucret pozitif ve sonlu olmalidir.");
        }

        this.senderName = senderName;
        this.senderAddress = senderAddress;
        this.senderCity = senderCity;
        this.senderState = senderState;
        this.senderZipCode = senderZipCode;
        this.recipientName = recipientName;
        this.recipientAddress = recipientAddress;
        this.recipientCity = recipientCity;
        this.recipientState = recipientState;
        this.recipientZipCode = recipientZipCode;
        this.weight = weight;
        this.costPerOunce = costPerOunce;
    }

    public double getWeight() {
        return weight;
    }

    public double getCostPerOunce() {
        return costPerOunce;
    }

    public double calculateCost() {
        return weight * costPerOunce;
    }
}
