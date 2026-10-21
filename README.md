# Programming Laboratory-I : Week 5

Java inheritance exercise for a package delivery service.

## Classes

- `Package`: stores sender and recipient details, package weight in ounces, and cost per ounce. Shipping cost is `weight * costPerOunce`.
- `TwoDayPackage`: extends `Package` and adds a flat delivery fee.
- `OvernightPackage`: extends `Package` and adds an extra fee per ounce.
- `Main`: creates all three package types and checks costs and invalid inputs.

All classes are included in `Main.java`. ZIP codes are stored as strings to preserve leading zeros. Weight and cost per ounce must be positive and finite; extra fees must be nonnegative and finite.

## Run

Requires JDK 8 or later. Tested with JDK 25.

```sh
javac Main.java
java Main
```

Expected output:

```text
Package: 20.00
TwoDayPackage: 25.00
OvernightPackage: 35.00
Tum testler basarili.
```

The examples use a weight of 10 ounces and a base rate of 2 per ounce, with a flat fee of 5 for two-day delivery or an extra fee of 1.5 per ounce for overnight delivery.

The test program also checks zero additional fees and rejects zero or negative base values, negative extra fees, NaN, and positive infinity. A failed check throws `AssertionError`; assertions do not need to be enabled with `-ea`.
