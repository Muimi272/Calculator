package com.muimi.calculator;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

public class calculatorStageFrame {
    private Scene scene;
    private Pane root;
    private GridPane buttonsPane;
    private TextField formulaShower;
    private TextField numShower;

    private double firstNum;
    private double secondNum;
    private String operator = "";
    private boolean isCalculateDone = false;
    private boolean shouldResetDisplay = false;
    private String currentFormula = "";

    private static final int WIDTH = 400;
    private static final int HEIGHT = 600;
    private static final int BUTTONS_PANE_HEIGHT = 400;
    private static final int BUTTON_WIDTH_COUNT = 4;
    private static final int BUTTON_HEIGHT_COUNT = 5;
    private static final int BUTTON_GRID_HEIGHT_GAP = 10;
    private static final int BUTTON_GRID_WIDTH_GAP = 10;
    private static final int NUM_SHOWER_WIDTH = 400;
    private static final int TOTAL_SHOWER_HEIGHT = 200;
    private static final int FORMULA_SHOWER_HEIGHT = 80;
    private static final int NUM_SHOWER_HEIGHT = TOTAL_SHOWER_HEIGHT - FORMULA_SHOWER_HEIGHT;

    private static final String FORMULA_SHOWER_STYLE = "-fx-background-color: #c6c6c6;" +
            "-fx-text-fill: #666666;" +
            "-fx-font-family: Arial;" +
            "-fx-font-size: 24;" +
            "-fx-border-width: 0 0 1 0;" +
            "-fx-border-color: #999999;";
    private static final String NUM_SHOWER_STYLE = "-fx-background-color: #c6c6c6;" +
            "-fx-text-fill: #000000;" +
            "-fx-font-family: Arial;" +
            "-fx-font-size: 48;";
    private static final String BUTTONS_PANE_STYLE = "-fx-background-color: #e8e8e8";
    private static final String BUTTON_STYLE = "-fx-alignment: CENTER;" +
            "-fx-background-radius: 10px;" +
            "-fx-border-radius: 10px;" +
            "-fx-background-color: #bbd6e3;" +
            "-fx-text-fill: #000000;" +
            "-fx-font-family: Arial;" +
            "-fx-font-size: 20";
    private static final String BUTTON_HOVER_STYLE = "-fx-alignment: CENTER;" +
            "-fx-background-radius: 10px;" +
            "-fx-border-radius: 10px;" +
            "-fx-background-color: #414d51;" +
            "-fx-text-fill: #c8c8c8;" +
            "-fx-font-family: Arial;" +
            "-fx-font-size: 20";

    public calculatorStageFrame() {
        init();
    }

    private void init() {
        root = new Pane();
        root.setPrefSize(WIDTH, HEIGHT);
        root.setMaxSize(WIDTH, HEIGHT);
        root.setMinSize(WIDTH, HEIGHT);
        scene = new Scene(root);
        addFormulaShower();
        addNumShower();
        addButtonsPane();
    }

    private void addButtonsPane() {
        buttonsPane = new GridPane();
        buttonsPane.setLayoutX(0);
        buttonsPane.setLayoutY(HEIGHT - BUTTONS_PANE_HEIGHT);
        buttonsPane.setHgap(BUTTON_GRID_WIDTH_GAP);
        buttonsPane.setVgap(BUTTON_GRID_HEIGHT_GAP);
        buttonsPane.setAlignment(Pos.CENTER);
        buttonsPane.setPadding(new Insets(10));
        buttonsPane.setStyle(BUTTONS_PANE_STYLE);
        buttonsPane.setPrefSize(WIDTH, BUTTONS_PANE_HEIGHT);
        buttonsPane.setMaxSize(WIDTH, BUTTONS_PANE_HEIGHT);
        buttonsPane.setMinSize(WIDTH, BUTTONS_PANE_HEIGHT);

        addButton("C", 0, 0);
        addButton("⌫", 1, 0);
        addButton("%", 2, 0);
        addButton("÷", 3, 0);
        addButton("7", 0, 1);
        addButton("8", 1, 1);
        addButton("9", 2, 1);
        addButton("×", 3, 1);
        addButton("4", 0, 2);
        addButton("5", 1, 2);
        addButton("6", 2, 2);
        addButton("-", 3, 2);
        addButton("1", 0, 3);
        addButton("2", 1, 3);
        addButton("3", 2, 3);
        addButton("+", 3, 3);
        addButton("±", 0, 4);
        addButton("0", 1, 4);
        addButton(".", 2, 4);
        addButton("=", 3, 4);

        for (int col = 0; col < BUTTON_WIDTH_COUNT; col++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setHgrow(Priority.ALWAYS);
            colConst.setFillWidth(true);
            buttonsPane.getColumnConstraints().add(colConst);
        }
        for (int row = 0; row < BUTTON_HEIGHT_COUNT; row++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setVgrow(Priority.ALWAYS);
            rowConst.setFillHeight(true);
            buttonsPane.getRowConstraints().add(rowConst);
        }

        root.getChildren().add(buttonsPane);
    }

    private void addButton(String text, int col, int row) {
        Button btn = new Button(text);
        btn.setStyle(BUTTON_STYLE);
        btn.setOnMouseEntered(_ -> {
            btn.getScene().setCursor(Cursor.HAND);
            btn.setStyle(BUTTON_HOVER_STYLE);
        });
        btn.setOnMouseExited(_ -> {
            btn.getScene().setCursor(Cursor.DEFAULT);
            btn.setStyle(BUTTON_STYLE);
        });

        switch (text) {
            case "C":
                btn.setOnAction(_ -> clearAll());
                break;
            case "⌫":
                btn.setOnAction(_ -> handleBackspace());
                break;
            case "1", "2", "3", "4", "5", "6", "7", "8", "9", "0":
                btn.setOnAction(_ -> handleNumber(text));
                break;
            case ".":
                btn.setOnAction(_ -> handleDecimalPoint());
                break;
            case "±":
                btn.setOnAction(_ -> handleNegate());
                break;
            case "=":
                btn.setOnAction(_ -> handleEquals());
                break;
            case "÷", "×", "-", "+":
                btn.setOnAction(_ -> handleOperator(text));
                break;
            case "%":
                btn.setOnAction(_ -> handlePercentage());
                break;
        }

        GridPane.setFillWidth(btn, true);
        GridPane.setFillHeight(btn, true);
        btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        buttonsPane.add(btn, col, row);
    }

    private void addFormulaShower() {
        formulaShower = new TextField();
        formulaShower.setLayoutX(0);
        formulaShower.setLayoutY(0);
        formulaShower.setPrefSize(NUM_SHOWER_WIDTH, FORMULA_SHOWER_HEIGHT);
        formulaShower.setMinSize(NUM_SHOWER_WIDTH, FORMULA_SHOWER_HEIGHT);
        formulaShower.setAlignment(Pos.BASELINE_RIGHT);
        formulaShower.setStyle(FORMULA_SHOWER_STYLE);
        formulaShower.setEditable(false);
        formulaShower.setText("");
        root.getChildren().add(formulaShower);
    }

    private void addNumShower() {
        numShower = new TextField();
        numShower.setLayoutX(0);
        numShower.setLayoutY(FORMULA_SHOWER_HEIGHT);
        numShower.setPrefSize(NUM_SHOWER_WIDTH, NUM_SHOWER_HEIGHT);
        numShower.setMinSize(NUM_SHOWER_WIDTH, NUM_SHOWER_HEIGHT);
        numShower.setAlignment(Pos.BASELINE_RIGHT);
        numShower.setText("0");
        numShower.setStyle(NUM_SHOWER_STYLE);
        numShower.setEditable(false);
        root.getChildren().add(numShower);
    }

    private void updateFormulaShower() {
        formulaShower.setText(currentFormula);
    }

    private void clearAll() {
        setNumShowerText("0");
        resetFormula();
        firstNum = 0;
        secondNum = 0;
        operator = "";
        shouldResetDisplay = false;
        isCalculateDone = false;
    }

    private void handleBackspace() {
        if (numShower.getText().equals("ERROR") || isCalculateDone) {
            clearAll();
            return;
        }
        String currentText = numShower.getText();
        if (currentText.length() > 1) {
            String newText = currentText.substring(0, currentText.length() - 1);
            setNumShowerText(newText);

            if (!currentFormula.isEmpty()) {
                currentFormula = currentFormula.substring(0, currentFormula.length() - 1);
                updateFormulaShower();
            }
        } else {
            setNumShowerText("0");
        }
    }

    private void handleNumber(String number) {
        if (isCalculateDone || numShower.getText().equals("ERROR")) {
            clearAll();
        }
        String currentText = numShower.getText();
        if (shouldResetDisplay || currentText.equals("0")) {
            setNumShowerText(number);
        } else {
            setNumShowerText(currentText + number);
        }

        shouldResetDisplay = false;
    }

    private void handleDecimalPoint() {
        if (isCalculateDone || numShower.getText().equals("ERROR")) {
            clearAll();
        }
        String currentText = numShower.getText();
        if (!currentText.contains(".")) {
            setNumShowerText(currentText + ".");
        }
    }

    private void handleNegate() {
        if (numShower.getText().equals("ERROR")) return;
        String currentText = numShower.getText();
        if (currentText.startsWith("-")) {
            setNumShowerText(currentText.substring(1));
        } else if (!currentText.equals("0")) {
            setNumShowerText("-" + currentText);
        }
    }

    private void handleOperator(String op) {
        if (numShower.getText().equals("ERROR")) return;
        try {
            if (operator.isEmpty()) {
                firstNum = Double.parseDouble(numShower.getText());
                operator = op;
            } else {
                if (!isCalculateDone) {
                    secondNum = Double.parseDouble(numShower.getText());
                    double result = calculate(firstNum, secondNum, operator);
                    firstNum = result;
                    operator = op;
                    setNumShowerText(formatResult(result));
                } else operator = op;
            }
            currentFormula = formatNumber(firstNum) + " " + operator;
            updateFormulaShower();
            shouldResetDisplay = true;
            isCalculateDone = false;
        } catch (NumberFormatException e) {
            setNumShowerText("ERROR");
        }
    }

    private void handleEquals() {
        if (operator.isEmpty() || numShower.getText().equals("ERROR")) return;
        try {
            if (!isCalculateDone) {
                secondNum = Double.parseDouble(numShower.getText());
                double result = calculate(firstNum, secondNum, operator);
                currentFormula = formatNumber(firstNum) + " " + operator + " " + formatNumber(secondNum) + " = " + formatResult(result);
                updateFormulaShower();
                setNumShowerText(formatResult(result));
                firstNum = result;
            } else {
                double result = calculate(firstNum, secondNum, operator);
                setNumShowerText(formatResult(result));
                firstNum = result;
                currentFormula = formatNumber(firstNum) + " " + operator + " " + formatNumber(secondNum) + " = " + formatResult(result);
                updateFormulaShower();
            }
            isCalculateDone = true;
            shouldResetDisplay = true;

        } catch (NumberFormatException | ArithmeticException e) {
            setNumShowerText("ERROR");
            resetFormula();
        }
    }

    private void handlePercentage() {
        if (numShower.getText().equals("ERROR")) return;
        try {
            double value = Double.parseDouble(numShower.getText());
            double result = value / 100.0;
            setNumShowerText(formatResult(result));

            if (!operator.isEmpty()) {
                currentFormula = formatNumber(firstNum) + " " + operator + " " + formatNumber(result);
                updateFormulaShower();
            }
        } catch (NumberFormatException e) {
            setNumShowerText("ERROR");
        }
    }

    private double calculate(double a, double b, String op) throws ArithmeticException {
        return switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "×" -> a * b;
            case "÷" -> {
                if (b == 0) throw new ArithmeticException("Division by zero");
                yield a / b;
            }
            default -> b;
        };
    }

    private String formatResult(double num) {
        if (num == (long) num) {
            return String.valueOf((long) num);
        } else {
            String result = String.format("%.6f", num);
            result = result.replaceAll("0*$", "");
            result = result.replaceAll("\\.$", "");
            return result;
        }
    }

    private String formatNumber(double num) {
        return formatResult(num);
    }

    private void resetFormula() {
        currentFormula = "";
        updateFormulaShower();
    }

    public void setNumShowerText(String text) {
        numShower.setText(text);
    }

    public Scene getScene() {
        return scene;
    }
}