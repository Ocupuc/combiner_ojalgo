package org.example;

import lombok.RequiredArgsConstructor;
import org.ojalgo.optimisation.ExpressionsBasedModel;
import org.ojalgo.optimisation.Variable;

@RequiredArgsConstructor
public class ReservoirOptimizer {

    private final String[] names;
    private final double[] masses;
    private final double[] sulfurContents;
    private final double[] densities;
    private final double desiredTotalMass;
    private final double desiredAverageSulfur;
    private final double desiredAverageDensity;
    private final boolean optimizeForSulfur;
    private final boolean optimizeForDensity;

    public void optimizeAndShowResults() {
        ExpressionsBasedModel model = new ExpressionsBasedModel();

        Variable[] variables = new Variable[masses.length];
        for (int i = 0; i < masses.length; i++) {
            variables[i] = Variable.make(names[i]).weight(1).lower(0).upper(masses[i]);
            model.addVariable(variables[i]);
        }

        var totalMassConstraint = model.addExpression("Total_Mass").level(desiredTotalMass);
        for (Variable variable : variables) {
            totalMassConstraint.set(variable, 1.0);
        }

        if (optimizeForSulfur) {
            var sulfurConstraint = model.addExpression("Sulfur_Content").level(desiredAverageSulfur * desiredTotalMass);
            for (int i = 0; i < variables.length; i++) {
                sulfurConstraint.set(variables[i], sulfurContents[i]);
            }
        }

        if (optimizeForDensity) {
            var densityConstraint = model.addExpression("Density_Content").level(desiredAverageDensity * desiredTotalMass);
            for (int i = 0; i < variables.length; i++) {
                densityConstraint.set(variables[i], densities[i]);
            }
        }

        model.minimise();

        double totalSulfur = 0, totalDensity = 0, actualMass = 0;
        for (int i = 0; i < variables.length; i++) {
            double amount = variables[i].getValue().doubleValue();
            actualMass += amount;
            totalSulfur += amount * sulfurContents[i];
            totalDensity += amount * densities[i];
            System.out.println(names[i] + ": взято " + amount + " единиц");
        }

        System.out.println("Общая масса: " + actualMass);
        System.out.println("Среднее содержание серы: " + (totalSulfur / actualMass));
        System.out.println("Средняя плотность: " + (totalDensity / actualMass));
    }
}

