public class TwoDayPackage extends Package {
    private final double flatFee;

    public TwoDayPackage(String senderName, String senderAddress, String senderCity,
                         String senderState, String senderZipCode,
                         String recipientName, String recipientAddress, String recipientCity,
                         String recipientState, String recipientZipCode,
                         double weight, double costPerOunce, double flatFee) {
        super(senderName, senderAddress, senderCity, senderState, senderZipCode,
              recipientName, recipientAddress, recipientCity, recipientState, recipientZipCode,
              weight, costPerOunce);

        if (!Double.isFinite(flatFee) || flatFee < 0) {
            throw new IllegalArgumentException("Sabit ek ucret negatif olamaz ve sonlu olmalidir.");
        }
        this.flatFee = flatFee;
    }

    @Override
    public double calculateCost() {
        return super.calculateCost() + flatFee;
    }
}
