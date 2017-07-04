package calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalcController {

    @FXML private TextField textField;

    @FXML protected void keyPress(ActionEvent event) {
        String s = textField.getText();
        String btn = ((Button) event.getSource()).getText();
        if (s.length() != 0 && Character.isAlphabetic(s.charAt(0)))
            s = "";

        // Regular checks
        if (Character.isDigit(btn.charAt(0))) {
            s += btn;
        } else { // dot or operator
            // Check for empty string
            if (s.length() == 0) {
                if (btn.equals("+") || btn.equals("-"))
                    s = btn;
            } else if (Character.isDigit(s.charAt(s.length()-1))) {
                // Check for previous character
                s += btn;
            } else {
                s = s.substring(0, s.length() - 1) + btn;
            }
        }

        textField.setText(s);
    }

    @FXML protected void equalsPress() {
        textField.setText(Evaluator.evaluate(textField.getText()));
    }
}
