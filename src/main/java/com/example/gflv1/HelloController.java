package com.example.gflv1;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.mariuszgromada.math.mxparser.*;

import java.io.IOException;
import java.util.ArrayList;

public class HelloController {
    private static final String FILE_NAME = "data.txt";
    public Label answerText;
    public TextField tfFunction;
    public TextField tfNumer;

    public TableView<MathExpression> tvExpressions;
    public TableColumn<MathExpression,String> columnExpression;
    public TableColumn<MathExpression,Double> columnValue;

    ArrayList<MathExpression> listMathExpression;
    private ObservableList<MathExpression> observableList;
    private MathExpression selectedMathExpression;
    private ReadAndWrite readAndWriter;


    public void initialize(){
        readAndWriter = new ReadAndWrite();
        observableList = readAndWriter.readFromFile(FILE_NAME);

        columnExpression.setCellValueFactory(cellData -> cellData.getValue().expressionProperty());
        columnValue.setCellValueFactory(cellData -> cellData.getValue().valueeProperty().asObject());
        tvExpressions.setItems(observableList);

        // Слушаем изменения выбора, и при изменении отображаем
        tvExpressions.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selectedMathExpression = newValue);

    }

    private void showMathExpressionEdit(MathExpression selectedExpression) {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloController.class.getResource("mathExpressionEditDialog.fxml"));
            AnchorPane page = loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Expression");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(null);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            MathExpressionEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMathExpression(selectedExpression);

            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onHelloButtonClick() {
        String functionText = tfFunction.getText();

        if (functionText != null && !functionText.equals("")){
            Expression e = new Expression(functionText);
            double calculate = e.calculate();
            answerText.setText(calculate+"\nc=" +amountOfNumbers(functionText) );

            observableList.add(new MathExpression(e.getExpressionString(), calculate));
            readAndWriter.saveToFile(FILE_NAME,observableList);
        }

    }

    private int amountOfNumbers(String functionText) {
        return functionText
                .replaceAll("[^0-9]", " ")
                .trim()
                .split("\\s+")
                .length;
    }

    public void onBtnEdit(ActionEvent actionEvent) {
        showMathExpressionEdit(selectedMathExpression);
        readAndWriter.saveToFile(FILE_NAME,observableList);
    }

    public void onBtnDelete(ActionEvent actionEvent) {

        int selectedIndex = tvExpressions.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            tvExpressions.getItems().remove(selectedIndex);
            readAndWriter.saveToFile(FILE_NAME,observableList);
        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(null);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Expression Selected");
            alert.setContentText("Please select a Expression in the table.");

            alert.showAndWait();
        }
    }

    public void onBtnEquals(ActionEvent actionEvent) {
        /*observableList = readAndWriter.readFromFile(FILE_NAME);
        Finder finder = new Finder(observableList);
        observableList = finder.findEquals(Double.parseDouble(tfNumer.getText()));
        observableList.forEach(System.out::println);*/
    }

    public void onBtnLess(ActionEvent actionEvent) {

    }

    public void onBtnMore(ActionEvent actionEvent) {

    }

    public void onBtnShowAll(ActionEvent actionEvent) {
       // observableList = readAndWriter.readFromFile(FILE_NAME);
    }


}