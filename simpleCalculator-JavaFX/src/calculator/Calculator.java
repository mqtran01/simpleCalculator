package calculator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
        Evaluator eval = new Evaluator();


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

        TextField txtfld = new TextField();
        GridPane.setConstraints(txtfld,0,0,5,1);
        txtfld.setEditable(false);
        txtfld.setMouseTransparent(true);
        txtfld.setFocusTraversable(false);




        Button btn;
        for (int i = 0; i < 10; i++) {
            btn = new Button(Integer.toString(i));
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println(((Button) event.getSource()).getText());
//                    String txt = ((Button) event.getSource()).getText();
                    txtfld.setText(txtfld.getText() + ((Button) event.getSource()).getText());
                }
            });
            root.getChildren().add(btn);

            Pair<Integer> pair = setLocation(i);
            GridPane.setConstraints(btn, pair.getFirst(), pair.getSecond());
            btn.setPrefSize(btnSize,btnSize);
        }

        Button plus = new Button("+");
        plus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String txt = txtfld.getText();
                if (txt.length() > 0) {
                    if (isLastCharOp(txt)) {
                        txtfld.setText(txt.substring(0, txt.length()-1) + "+");
                    } else {
                        txtfld.setText(txt + "+");
                    }
                }
            }
        });
        GridPane.setConstraints(plus, 3, 4);
        plus.setPrefSize(btnSize,btnSize);


        Button minus = new Button("-");
        minus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String txt = txtfld.getText();
                if (txt.length() > 0) {
                    if (isLastCharOp(txt)) {
                        txtfld.setText(txt.substring(0, txt.length()-1) + "-");
                    } else {
                        txtfld.setText(txt + "-");
                    }
                }
            }
        });
        GridPane.setConstraints(minus, 3, 3);
        minus.setPrefSize(btnSize,btnSize);


        Button times = new Button("*");
        times.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String txt = txtfld.getText();
                if (txt.length() > 0) {
                    if (isLastCharOp(txt)) {
                        txtfld.setText(txt.substring(0, txt.length()-1) + "*");
                    } else {
                        txtfld.setText(txt + "*");
                    }
                }
            }
        });
        GridPane.setConstraints(times, 3, 2);
        times.setPrefSize(btnSize,btnSize);


        Button divide = new Button("/");
        divide.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String txt = txtfld.getText();
                if (txt.length() > 0) {
                    if (isLastCharOp(txt)) {
                        txtfld.setText(txt.substring(0, txt.length()-1) + "/");
                    } else {
                        txtfld.setText(txt + "/");
                    }
                }
            }
        });
        GridPane.setConstraints(divide, 3, 1);
        divide.setPrefSize(btnSize,btnSize);


        Button equals = new Button("=");
        equals.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtfld.setText(eval.evaluate(txtfld.getText()));
            }
        });
        GridPane.setConstraints(equals, 2, 4);
        equals.setPrefSize(btnSize,btnSize);

        Button dot = new Button(".");
        dot.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String txt = txtfld.getText();
                if (txt.length() > 0) {

                    if (!Character.isDigit(txt.charAt(txt.length()-1))) {
                        txtfld.setText(txt.substring(0, txt.length()-1) + ".");
                    } else {
                        txtfld.setText(txt + ".");
                    }
                }
            }
        });
        GridPane.setConstraints(dot, 1, 4);
        dot.setPrefSize(btnSize,btnSize);



        root.getChildren().addAll(txtfld, plus, equals, minus, times, divide, dot);

        Scene overarch = new Scene(window);
        overarch.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getText().length() == 0)
                    return;
                char key = event.getText().charAt(0);
                if (Character.isDigit(key)) {
                    txtfld.setText(txtfld.getText() + key);
                } else if (key == '.' && txtfld.getLength() != 0) {
                    String txt = txtfld.getText();
                    if (!Character.isDigit(txt.charAt(txt.length()-1))) {
                        txtfld.setText(txt.substring(0, txt.length()-1) + ".");
                    } else {
                        txtfld.setText(txt + ".");
                    }
                } else { // operator

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

    private boolean isLastCharOp(String s) {
        if (Evaluator.isOperator(s.substring(s.length() - 1, s.length()))) {
            System.out.println("True");
            return true;
        }
        System.out.println("False");
        return false;
    }

    // TODO implement this into the code
    private String textProtocol(String s, String btn) {
        // Checks if there is a letter (i.e. error)
        if (Character.isAlphabetic(btn.charAt(0)))
            s = "";

        // Regular checks
        if (Character.isDigit(btn.charAt(0))) {
            return s + btn;
        } else {
            // dot or operator
            if (s.length() != 0 && !Character.isDigit(s.charAt(s.length()-1)))
                return s + btn;
        }
        return s;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
