package org.example;

import org.ojalgo.optimisation.ExpressionsBasedModel;
import org.ojalgo.optimisation.Variable;
import org.ojalgo.optimisation.Expression;

public class OjAlgoExample {

    public static void main(String[] args) {
        // Создаем модель
        ExpressionsBasedModel model = new ExpressionsBasedModel();

        // Создаем переменные для каждого резервуара
        Variable[] vars = new Variable[10];
        for (int i = 0; i < vars.length; i++) {
            vars[i] = Variable.make("Var" + i).lower(0).weight(1); // предположим, что стоимость каждого резервуара равна 1
            model.addVariable(vars[i]);
        }

        // Добавляем ограничение на суммарный объем
        Expression massConstraint = model.addExpression("MassConstraint").level(1000); // 1000 - желаемая общая масса
        for (Variable var : vars) {
            massConstraint.set(var, 1);
        }

        // Добавляем ограничение на содержание серы, если оно задано
        // Допустим, у нас есть желаемое содержание серы в смеси - 0.2
        double desiredSulfurContent = 0.2;
        Expression sulfurConstraint = model.addExpression("SulfurConstraint").level(desiredSulfurContent * 1000);
        for (int i = 0; i < vars.length; i++) {
            // Предположим, что vars[i] содержит 0.1 единиц серы на единицу массы, это значение будет изменяться для каждой переменной в зависимости от реальных данных
            sulfurConstraint.set(vars[i], 0.1);
        }

        // Решаем задачу
        model.minimise();

        // Выводим решение
        for (Variable var : vars) {
            System.out.println(var.getName() + ": " + var.getValue());
        }
    }
}
