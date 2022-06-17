package com.example.gflv1;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MathExpressionEditDialogController {
    public TextField editExpression;
    public Label labValue;

    private Stage dialogStage;
    private MathExpression mathExpression;
    private boolean okClicked = false;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMathExpression(MathExpression selectedExpression) {
        this.mathExpression = selectedExpression;
        editExpression.setText(mathExpression.getExpression());
        labValue.setText(String.valueOf(mathExpression.getValuee()));
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void handleOk(ActionEvent actionEvent) {
        if (isInputValid()) {
            mathExpression.setExpression(editExpression.getText());
            mathExpression.setValuee(Double.parseDouble(labValue.getText()));
            okClicked = true;
            dialogStage.close();
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (editExpression.getText() == null || editExpression.getText().length() == 0) {
            errorMessage += "No valid Expression!\n";
        } else {
            MathExpression mathExpression = Util.validExpression(editExpression.getText());
            if ( mathExpression == null) {
                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
            } else
                labValue.setText(String.valueOf(mathExpression.getValuee()));
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    public void handleCancel(ActionEvent actionEvent) {
        dialogStage.close();
    }
}
