package com.example.gflv1;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MathExpression  {


    private  StringProperty expression;
    private  DoubleProperty valuee;

    public MathExpression() {
        this(null,null);
    }

    public MathExpression(String expression, Double valuee) {
        this.expression = new SimpleStringProperty(expression);
        this.valuee = new SimpleDoubleProperty(valuee);
    }

    public String getExpression() {
        return expression.get();
    }

    public StringProperty expressionProperty() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression.set(expression);
    }

    public double getValuee() {
        return valuee.get();
    }

    public DoubleProperty valueeProperty() {
        return valuee;
    }

    public void setValuee(double valuee) {
        this.valuee.set(valuee);
    }

    @Override
    public String toString() {
        return "MathExpression{" +
                "expression=" + expression +
                ", valuee=" + valuee +
                '}' +"\n";
    }

    /*private void writeObject(ObjectOutputStream out)
            throws IOException {
        out.writeObject(getExpression());
        out.writeObject(getValuee());
    }

    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        expression = new SimpleStringProperty((String) in.readObject());
        valuee = new SimpleDoubleProperty((Double) in.readObject());
    }*/
}
