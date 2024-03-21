package org.example;

public class Main {

    public static void main(String[] args) {
        String[] names = {"Резервуар 1", "Резервуар 2", "Резервуар 3", "Резервуар 4", "Резервуар 5"};
        double[] masses = {100, 200, 150, 180, 120};
        double[] sulfurContents = {0.2, 0.25, 0.22, 0.18, 0.2};
        double[] densities = {0.85, 0.88, 0.86, 0.87, 0.89};
        double desiredTotalMass = 500;
        double desiredAverageSulfur = 0.21;
        double desiredAverageDensity = 0.86;
        boolean optimizeForSulfur = true;
        boolean optimizeForDensity = true;

        ReservoirOptimizer optimizer = new ReservoirOptimizer(names, masses, sulfurContents, densities,
                desiredTotalMass, desiredAverageSulfur, desiredAverageDensity, optimizeForSulfur, optimizeForDensity);

        optimizer.optimizeAndShowResults();
    }
}
