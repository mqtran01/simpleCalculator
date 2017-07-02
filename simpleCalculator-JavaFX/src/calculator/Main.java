package calculator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

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
//        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        Scene normalCalc = new Scene(root,800,800);

        GridPane secondPage = new GridPane();
        secondPage.setHgap(10);
        secondPage.setVgap(10);
        Scene helpPage = new Scene(secondPage, 800,800);

        Tab tab1 = new Tab("Calculator", root);
        Tab tab2 = new Tab("Help", secondPage);
        tab1.setClosable(false);
        tab2.setClosable(false);
        window.getTabs().addAll(tab1, tab2);

        Label lbl = new Label("Hello");
        Button ext = new Button("Bye");
        ext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        Button toPg2Btn = new Button("Page 2");
        GridPane.setConstraints(toPg2Btn, 0, 10);
        toPg2Btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                primaryStage.setScene(helpPage);
//                primaryStage.show();
                window.getSelectionModel().select(tab2);

                System.out.println("Changed to page 2");
            }
        });
        TextField txtfld = new TextField();
        GridPane.setConstraints(txtfld,0,0);


        Button toPg1Btn = new Button("Page 1");
        GridPane.setConstraints(toPg1Btn, 0, 11);
        toPg1Btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                primaryStage.setScene(normalCalc);
//                primaryStage.show();
                window.getSelectionModel().select(tab1);
                System.out.println("Changed to page 1");
            }
        });



        Button btn;
        int j = 0;
        ArrayList<Button> keys = new ArrayList<Button>();
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
        GridPane.setConstraints(plus, 10, 10);

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
        GridPane.setConstraints(minus, 11, 10);

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
        GridPane.setConstraints(times, 12, 10);

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
        GridPane.setConstraints(divide, 13, 10);

        Button equals = new Button("=");
        equals.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtfld.setText(eval.evaluate(txtfld.getText()));
            }
        });
        GridPane.setConstraints(equals, 11, 11);


        root.getChildren().addAll(lbl, ext, toPg2Btn, txtfld, plus, equals, minus, times, divide);
        secondPage.getChildren().add(toPg1Btn);
//        primaryStage.setScene(normalCalc);
//        primaryStage.show();


        Scene overarch = new Scene(window);
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
        return new Pair<Integer>(x, y);
    }

    private boolean isLastCharOp(String s) {
        if (Evaluator.isOperator(s.substring(s.length() - 1, s.length()))) {
            System.out.println("True");
            return true;
        }
        System.out.println("False");
        return false;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
