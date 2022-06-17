package com.example.gflv1;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class SavedExpressions implements Serializable {
     @Serial
    private static final long serialVersionUID = 1L;

    ArrayList<MathExpression> list;

    public SavedExpressions(ArrayList<MathExpression> list) {
        this.list = list;
    }

    public ArrayList<MathExpression> getList() {
        return list;
    }

    public void setList(ArrayList<MathExpression> list) {
        this.list = list;
    }
}
