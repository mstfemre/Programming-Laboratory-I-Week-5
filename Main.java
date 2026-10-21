import java.util.Locale;

// Temel sinif: gonderici, alici ve paketin ortak bilgilerini tutar.
class Package {
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

class TwoDayPackage extends Package {
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

class OvernightPackage extends Package {
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

public class Main {
    public static void main(String[] args) {
        Package standard = createPackage(10.0, 2.0);
        TwoDayPackage twoDay = createTwoDayPackage(5.0);
        OvernightPackage overnight = createOvernightPackage(1.5);

        // Ust sinif referansi, her nesnenin kendi calculateCost metodunu cagirir.
        Package[] packages = {standard, twoDay, overnight};
        double[] expectedCosts = {20.0, 25.0, 35.0};
        for (int i = 0; i < packages.length; i++) {
            double actual = packages[i].calculateCost();
            checkCost(expectedCosts[i], actual);
            System.out.printf(Locale.US, "%s: %.2f%n",
                              packages[i].getClass().getSimpleName(), actual);
        }

        checkCost(20.0, createTwoDayPackage(0.0).calculateCost());
        checkCost(20.0, createOvernightPackage(0.0).calculateCost());

        for (double invalid : new double[] {0.0, -1.0, Double.NaN, Double.POSITIVE_INFINITY}) {
            expectInvalid(() -> createPackage(invalid, 2.0));
            expectInvalid(() -> createPackage(10.0, invalid));
        }
        for (double invalid : new double[] {-1.0, Double.NaN, Double.POSITIVE_INFINITY}) {
            expectInvalid(() -> createTwoDayPackage(invalid));
            expectInvalid(() -> createOvernightPackage(invalid));
        }
        System.out.println("Tum testler basarili.");
    }

    private static Package createPackage(double weight, double costPerOunce) {
        return new Package(
            "Ali Yilmaz", "123 Main St", "Boston", "MA", "02108",
            "Ayse Demir", "456 Oak St", "New York", "NY", "10001",
            weight, costPerOunce);
    }

    private static TwoDayPackage createTwoDayPackage(double flatFee) {
        return new TwoDayPackage(
            "Ali Yilmaz", "123 Main St", "Boston", "MA", "02108",
            "Ayse Demir", "456 Oak St", "New York", "NY", "10001",
            10.0, 2.0, flatFee);
    }

    private static OvernightPackage createOvernightPackage(double additionalFeePerOunce) {
        return new OvernightPackage(
            "Ali Yilmaz", "123 Main St", "Boston", "MA", "02108",
            "Ayse Demir", "456 Oak St", "New York", "NY", "10001",
            10.0, 2.0, additionalFeePerOunce);
    }

    private static void checkCost(double expected, double actual) {
        if (!Double.isFinite(actual) || Math.abs(expected - actual) > 0.000001) {
            throw new AssertionError("Beklenen: " + expected + ", hesaplanan: " + actual);
        }
    }

    private static void expectInvalid(Runnable action) {
        try {
            action.run();
        } catch (IllegalArgumentException expected) {
            return;
        }
        throw new AssertionError("Gecersiz deger reddedilmedi.");
    }
}
