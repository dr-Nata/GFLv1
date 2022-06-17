package com.example.gflv1;

import org.mariuszgromada.math.mxparser.Expression;

public class Util {

    public static MathExpression validExpression(String text) {
        Expression e = new Expression(text);
        double calculate = e.calculate();
        //answerText.setText(amountOfNumbers(functionText)+" ->>>  " + calculate);
        if (Double.isNaN(calculate)) return null;
        return new MathExpression(e.getExpressionString(), calculate);
    }
}
