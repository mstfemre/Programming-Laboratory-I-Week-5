import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Package standard = createPackage(10.0, 2.0);
        TwoDayPackage twoDay = createTwoDayPackage(5.0);
        OvernightPackage overnight = createOvernightPackage(1.5);

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
