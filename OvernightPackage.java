public class OvernightPackage extends Package {
    private final double additionalFeePerOunce;

    public OvernightPackage(String senderName, String senderAddress, String senderCity,
                            String senderState, String senderZipCode,
                            String recipientName, String recipientAddress, String recipientCity,
                            String recipientState, String recipientZipCode,
                            double weight, double costPerOunce, double additionalFeePerOunce) {
        super(senderName, senderAddress, senderCity, senderState, senderZipCode,
              recipientName, recipientAddress, recipientCity, recipientState, recipientZipCode,
              weight, costPerOunce);

        if (!Double.isFinite(additionalFeePerOunce) || additionalFeePerOunce < 0) {
            throw new IllegalArgumentException("Ons basina ek ucret negatif olamaz ve sonlu olmalidir.");
        }
        this.additionalFeePerOunce = additionalFeePerOunce;
    }

    @Override
    public double calculateCost() {
        return getWeight() * (getCostPerOunce() + additionalFeePerOunce);
    }
}
