package com.example.aplicatie;
import clase.Polynomial;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Pair;

public class CalculatorController {
    @FXML
    private TextField poly1Input;
    @FXML
    private TextField poly2Input;
    @FXML
    private TextField polyInput;
    @FXML
    private Label resultLabel;
    @FXML
    private Label resultLabel1;
    @FXML
    private Label aux;

    @FXML
    protected void onSumButtonClick() throws Exception {

        Polynomial p1= new Polynomial(poly1Input.getText());
        Polynomial p2= new Polynomial(poly2Input.getText());
        resultLabel.setText(p1.add(p2).toString());
    }
    @FXML
    protected void onDiffButtonClick() throws Exception{
        Polynomial p1= new Polynomial(poly1Input.getText());
        Polynomial p2= new Polynomial(poly2Input.getText());
        resultLabel.setText(p1.subtract(p2).toString());
    }
    @FXML
    protected void onMultiplyButtonClick() throws Exception{
        Polynomial p1= new Polynomial(poly1Input.getText());
        Polynomial p2= new Polynomial(poly2Input.getText());
        resultLabel.setText(p1.multiply(p2).toString());
    }
    @FXML
    protected void onDivideButtonClick() throws Exception {
        Polynomial p1 = new Polynomial(poly1Input.getText());
        Polynomial p2 = new Polynomial(poly2Input.getText());
        Pair<Polynomial, Polynomial> divisionResult = p1.divide(p2);
        Polynomial quotient = divisionResult.getKey();
        Polynomial remainder = divisionResult.getValue();
        resultLabel.setText("Quotient: " + quotient.toString() + "\nRemainder: " + remainder.toString());
    }
    @FXML
    protected void onTextField1Clicked() throws Exception{
        aux.setText(poly1Input.getText());
    }
    @FXML
    protected void onTextField2Clicked() throws Exception{
        aux.setText(poly2Input.getText());
    }
    @FXML
    protected void onIntegrateButtonClick(ActionEvent event) throws Exception{
        if (polyInput.getText()==null || polyInput.getText().isEmpty() && !(aux.getText() == null || aux.getText().isEmpty())){
            Polynomial p = new Polynomial(aux.getText());
            resultLabel1.setText(p.integrate().toString());
        }
        else {
            Polynomial p = new Polynomial(polyInput.getText());
            resultLabel1.setText(p.integrate().toString());
        }
    }
    @FXML
    protected void onDifferentiateButtonClick(ActionEvent event) throws Exception{
        if (polyInput.getText()==null || polyInput.getText().isEmpty() && !(aux.getText() == null || aux.getText().isEmpty())){
            Polynomial p = new Polynomial(aux.getText());
            resultLabel1.setText(p.differentiate().toString());
        }
        else {
            Polynomial p = new Polynomial(polyInput.getText());
            resultLabel1.setText(p.differentiate().toString());
        }
    }
}