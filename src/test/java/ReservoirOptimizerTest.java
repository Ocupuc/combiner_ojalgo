

import org.example.ReservoirOptimizer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ReservoirOptimizerTest {

    @Test
    void testOptimize() {

        String[] names = {"Резервуар 1", "Резервуар 2"};
        double[] masses = {100, 200};
        double[] sulfurContents = {0.1, 0.2};
        double[] densities = {0.8, 0.9};
        double desiredTotalMass = 150;
        double desiredAverageSulfur = 0.15;
        double desiredAverageDensity = 0.85;

        ReservoirOptimizer optimizer = new ReservoirOptimizer(
                names, masses, sulfurContents, densities,
                desiredTotalMass, desiredAverageSulfur, desiredAverageDensity,
                true, true);

           assertDoesNotThrow(optimizer::optimizeAndShowResults);
    }
}
