package com.example.gflv1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;

public class ReadAndWrite {

    public ObservableList<MathExpression> readFromFile(String file) {
        ObservableList<MathExpression> list = FXCollections.observableArrayList();

        try(BufferedReader bf = new BufferedReader(new FileReader(new File(file)))) {
            String s;
            while ((s = bf.readLine()) != null) {
                String[] split = s.split(";");
                list.add(new MathExpression(split[0], Double.parseDouble(split[1])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void saveToFile(String fileName, ObservableList<MathExpression> list) {
        try {
            PrintWriter pw = new PrintWriter(fileName);
            if (list == null) return;
            list.stream().map(expression -> expression.getExpression() + ";" + expression.getValuee()).forEach(pw::println);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}


