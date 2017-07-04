package calculator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Calculator extends Application {

    private static final int btnSize = 50;

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*
        Parent root = FXMLLoader.load(getClass().getResource("CalcPage.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        */
        TabPane window = new TabPane();
        window.setPrefHeight(600);
        window.setPrefWidth(800);

        GridPane root = new GridPane();
        root.setHgap(1);
        root.setVgap(1);

        GridPane secondPage = new GridPane();
        secondPage.setHgap(10);
        secondPage.setVgap(10);

        Tab tab1 = new Tab("Calculator", root);
        Tab tab2 = new Tab("Help", secondPage);
        tab1.setClosable(false);
        tab2.setClosable(false);
        window.getTabs().addAll(tab1, tab2);

        TextField textField = new TextField();
        GridPane.setConstraints(textField,0,0,5,1);
        textField.setEditable(false);
        textField.setMouseTransparent(true);
        textField.setFocusTraversable(false);
        EventHandler<ActionEvent> press = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textField.setText(textProtocol(textField.getText(), ((Button) event.getSource()).getText()));
            }
        };

        Button btn;
        for (int i = 0; i < 10; i++) {
            btn = new Button(Integer.toString(i));
            btn.setOnAction(press);
            root.getChildren().add(btn);

            Pair<Integer> pair = setLocation(i);
            GridPane.setConstraints(btn, pair.getFirst(), pair.getSecond());
            btn.setPrefSize(btnSize,btnSize);
        }

        Button plus = new Button("+");
        plus.setOnAction(press);
        GridPane.setConstraints(plus, 3, 4);
        plus.setPrefSize(btnSize,btnSize);


        Button minus = new Button("-");
        minus.setOnAction(press);
        GridPane.setConstraints(minus, 3, 3);
        minus.setPrefSize(btnSize,btnSize);


        Button times = new Button("*");
        times.setOnAction(press);
        GridPane.setConstraints(times, 3, 2);
        times.setPrefSize(btnSize,btnSize);


        Button divide = new Button("/");
        divide.setOnAction(press);
        GridPane.setConstraints(divide, 3, 1);
        divide.setPrefSize(btnSize,btnSize);


        Button equals = new Button("=");
        equals.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textField.setText(Evaluator.evaluate(textField.getText()));
            }
        });
        GridPane.setConstraints(equals, 2, 4);
        equals.setPrefSize(btnSize,btnSize);

        Button dot = new Button(".");
        dot.setOnAction(press);
        GridPane.setConstraints(dot, 1, 4);
        dot.setPrefSize(btnSize,btnSize);

        root.getChildren().addAll(textField, plus, equals, minus, times, divide, dot);

        Scene overarch = new Scene(window);
        overarch.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.BACK_SPACE)
                    textField.setText(textField.getLength() == 0 ? "" : textField.getText(0, textField.getLength()-1));
                else if (event.getCode() == KeyCode.ENTER)
                    textField.setText(Evaluator.evaluate(textField.getText()));

                System.out.println(event.getCode());

                switch (event.getText()) {
                    case "0": case "1": case "2":
                    case "3": case "4": case "5":
                    case "6": case "7": case "8":
                    case "9": case "+": case "-":
                    case "*": case "/":
                        textField.setText(textProtocol(textField.getText(), event.getText()));
                        break;
                    default:
                        break;
                }

            }
        });
        primaryStage.setScene(overarch);
        primaryStage.show();
    }

    private static Pair<Integer> setLocation(int i) {
        int x = 0;
        int y = 0;
        switch (i) {
            case 7:
                x = 0;
                y = 1;
                break;
            case 8:
                x = 1;
                y = 1;
                break;
            case 9:
                x = 2;
                y = 1;
                break;
            case 4:
                x = 0;
                y = 2;
                break;
            case 5:
                x = 1;
                y = 2;
                break;
            case 6:
                x = 2;
                y = 2;
                break;
            case 1:
                x = 0;
                y = 3;
                break;
            case 2:
                x = 1;
                y = 3;
                break;
            case 3:
                x = 2;
                y = 3;
                break;
            case 0:
                x = 0;
                y = 4;
                break;

        }
        return new Pair<>(x, y);
    }


    private String textProtocol(String s, String btn) {
        // Checks if there is a letter (i.e. error)
        if (s.length() != 0 && Character.isAlphabetic(s.charAt(0)))
            s = "";

        // Regular checks
        if (Character.isDigit(btn.charAt(0))) {
            return s + btn;
        } else { // dot or operator
            // Check for empty string
            if (s.length() == 0) {
                if (btn.equals("+") || btn.equals("-"))
                    return btn;
                return s;
            }

            // Check for previous character
            if (Character.isDigit(s.charAt(s.length()-1)))
                return s + btn;
            else
                return s.substring(0, s.length()-1) + btn;
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
