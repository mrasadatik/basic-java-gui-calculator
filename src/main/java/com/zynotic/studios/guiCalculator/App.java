package com.zynotic.studios.guiCalculator;

import javafx.util.Duration;

import java.util.Objects;

import atlantafx.base.theme.PrimerLight;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class App extends Application {

    Font sevenSegmentFont;

    private TextField displayField;
    private double firstNumber = 0;
    private String operator = "";
    private boolean startNewNumber = true;
    private boolean errorOccurred = false;

    public static void main(String[] args) {
        launch(args);
    }

    @SuppressWarnings("exports")
    @Override
    public void start(Stage primaryStage) {
        Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());

        sevenSegmentFont = Font.loadFont(Objects.requireNonNull(getClass().getResource("/assets/fonts/seven_segment.ttf")).toExternalForm(), 30);

        if (sevenSegmentFont == null) {
            System.out.println("Font could not be loaded.");
            return;
        }

        primaryStage.setTitle("GUI Calculator");
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/images/icons/icon.png"))));
        primaryStage.setResizable(false);

        VBox displaySection = new VBox();
        displaySection.setPadding(new Insets(30, 10, 30, 10));
        displaySection.setStyle("-fx-background-color: #3b3f4a;");
        VBox buttonSection = new VBox(10);
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(0, 0, 10, 0));
        vbox.setStyle("-fx-background-color: #d6d8de;");

        displayField = new TextField();
        displayField.setEditable(false);
        displayField.setAlignment(Pos.CENTER_RIGHT);
        displayField.setStyle("-fx-font-weight: bold;-fx-background-color: #d6e4e4;");
        displayField.setPrefHeight(70);
        displayField.setPrefWidth(270);
        displayField.setFont(Font.font(sevenSegmentFont.getFamily(), FontWeight.BOLD, 30));
        displaySection.getChildren().add(displayField);

        GridPane grid = createButtonGrid();
        grid.setAlignment(Pos.CENTER);
        buttonSection.getChildren().add(grid);

        vbox.getChildren().addAll(displaySection, buttonSection);

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createButtonGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        Button button7 = createButton("7", "#426573", "white");
        Button button8 = createButton("8", "#426573", "white");
        Button button9 = createButton("9", "#426573", "white");
        Button buttonDivide = createButton("÷", "#b3c2c2", "black");

        Button button4 = createButton("4", "#426573", "white");
        Button button5 = createButton("5", "#426573", "white");
        Button button6 = createButton("6", "#426573", "white");
        Button buttonMultiply = createButton("×", "#b3c2c2", "black");

        Button button1 = createButton("1", "#426573", "white");
        Button button2 = createButton("2", "#426573", "white");
        Button button3 = createButton("3", "#426573", "white");
        Button buttonSubtract = createButton("-", "#b3c2c2", "black");

        Button button0 = createButton("0", "#426573", "white");
        Button buttonDecimal = createButton(".", "#426573", "white");
        Button buttonDelete = createButton("Del", "#426573", "white");
        Button buttonAdd = createButton("+", "#b3c2c2", "black");

        Button buttonOff = createButton("OFF", "#ce0d08", "white");
        Button buttonClear = createButton("C", "#ed6971", "white");
        Button buttonEquals = createButton("=", "#7eb3e5", "white");
        Button buttonSqrt = createButton("√", "#b3c2c2", "black");

        grid.add(button7, 0, 0);
        grid.add(button8, 1, 0);
        grid.add(button9, 2, 0);
        grid.add(buttonDivide, 3, 0);

        grid.add(button4, 0, 1);
        grid.add(button5, 1, 1);
        grid.add(button6, 2, 1);
        grid.add(buttonMultiply, 3, 1);

        grid.add(button1, 0, 2);
        grid.add(button2, 1, 2);
        grid.add(button3, 2, 2);
        grid.add(buttonSubtract, 3, 2);

        grid.add(button0, 0, 3);
        grid.add(buttonDecimal, 1, 3);
        grid.add(buttonDelete, 2, 3);
        grid.add(buttonAdd, 3, 3);

        grid.add(buttonOff, 0, 4);
        grid.add(buttonClear, 1, 4);
        grid.add(buttonEquals, 2, 4);
        grid.add(buttonSqrt, 3, 4);

        return grid;
    }

    private Button createButton(String label, String bgColor, String color) {
        Button button = new Button(label);
        button.setPrefWidth(60);
        button.setPrefHeight(50);
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        button.setStyle("-fx-font-weight: bold; -fx-background-color: " + bgColor + "; -fx-text-fill: " + color + ";");
        button.setOnAction(e -> buttonClicked(label, button));
        return button;
    }

    private void buttonClicked(String label, Button button) {
        if (errorOccurred) {
            clearDisplay();
        }

        switch (label) {
            case "+", "-", "×", "÷" -> handleOperator(label);
            case "=" -> handleEquals();
            case "." -> handleDecimalPoint();
            case "√" -> handleSquareRoot();
            case "Del" -> handleDelete();
            case "C" -> clearDisplay();
            case "OFF" -> displayOff(button);
            default -> handleNumber(label);
        }
    }

    private void handleOperator(String label) {
        if (!operator.isEmpty()) {
            handleEquals();
        }
        firstNumber = Double.parseDouble(displayField.getText());
        operator = label;
        displayField.setText(firstNumber + " " + (Objects.equals(operator, "÷") ? "/" : operator));
        startNewNumber = true;
    }

    private void handleEquals() {
        if (operator.isEmpty()) {
            return; // No operation to perform
        }
        double secondNumber = Double.parseDouble(displayField.getText().replace(firstNumber + " " + operator + " ", ""));
        double result = 0;
        switch (operator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "×":
                result = firstNumber * secondNumber;
                break;
            case "÷":
                if (secondNumber != 0) {
                    result = firstNumber / secondNumber;
                } else {
                    displayField.setText("Error");
                    errorOccurred = true;
                    return;
                }
                break;
        }
        displayField.setText(String.valueOf(result));
        operator = "";
        startNewNumber = true;
    }

    private void handleSquareRoot() {
        String text = displayField.getText();
        if (text.isEmpty()) {
            displayField.setText("Error");
            errorOccurred = true;
            return;
        }

        try {
            double number = Double.parseDouble(text);
            if (number >= 0) {
                double result = Math.sqrt(number);
                displayField.setText(String.valueOf(result));
                startNewNumber = true;
            } else {
                displayField.setText("Error");
                errorOccurred = true;
            }
        } catch (NumberFormatException e) {
            displayField.setText("Error");
            errorOccurred = true;
        }
    }

    private void handleDecimalPoint() {
        if (!displayField.getText().contains(".")) {
            displayField.appendText(".");
        }
    }

    private void handleNumber(String label) {
        if (startNewNumber) {
            displayField.setText(label);
            startNewNumber = false;
        } else {
            displayField.appendText(label);
        }
    }

    private void handleDelete() {
        String text = displayField.getText();
        if (!text.isEmpty()) {
            displayField.setText(text.substring(0, text.length() - 1));
        }
    }

    private void clearDisplay() {
        displayField.setText("");
        operator = "";
        startNewNumber = true;
        errorOccurred = false;
    }

    private void displayOff(Button button) {
        displayField.setText("Are you Sure?");
        button.setOnAction(
                actionEvent -> {
                    displayField.setText("Ok Fine! BYE");

                    PauseTransition delay = new PauseTransition(Duration.seconds(1));
                    delay.setOnFinished(event -> Platform.exit());
                    delay.play();
                });
    }
}