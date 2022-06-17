package com.example.gflv1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Finder {
    ObservableList<MathExpression> observableList;

    public Finder(ObservableList<MathExpression> observableList) {
        this.observableList=observableList;
    }

    public ObservableList<MathExpression> findEquals(Double n) {

        ObservableList<MathExpression> list = FXCollections.observableArrayList();

        for (MathExpression expression : observableList) {
            if (Math.abs(expression.getValuee()-n)<=0.000001) list.add(expression);
        }

        return list;
    }
}
