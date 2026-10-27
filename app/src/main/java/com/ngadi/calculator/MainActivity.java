package com.ngadi.calculator;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView minDisplay;
    EditText display;
    Button btnPlus, btnMinus, btnMultip, btnDivision, btnEqual, btnPercent, btnClear, btnDelete;

    double firstvalue;
    double secondvalue;

    boolean operator;

    boolean equal;

    String lastOperator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        minDisplay = findViewById(R.id.minDisplay);
        display = findViewById(R.id.display);

        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnMultip = findViewById(R.id.btnMultip);
        btnDivision = findViewById(R.id.btnDivision);
        btnEqual = findViewById(R.id.btnEqual);
        btnPercent = findViewById(R.id.btnPercent);
        btnClear = findViewById(R.id.btnClear);
        btnDelete = findViewById(R.id.btnDelete);

        operator = false;

        equal = false;

        // ----------------------------------------------- clear button ----------------------------------------------
        btnClear.setOnClickListener(v -> {
            //  -------------- if the user enter 0 in the division -------------------
            if(display.getText().toString().equals("Cannot divide by zero")){
                btnDivision.setEnabled(true);
                btnDivision.setBackgroundColor(Color.parseColor("#FF9800"));
                btnMultip.setEnabled(true);
                btnMultip.setBackgroundColor(Color.parseColor("#FF9800"));
                btnMinus.setEnabled(true);
                btnMinus.setBackgroundColor(Color.parseColor("#FF9800"));
                btnPlus.setEnabled(true);
                btnPlus.setBackgroundColor(Color.parseColor("#FF9800"));
                btnEqual.setEnabled(true);
                btnEqual.setBackgroundColor(Color.parseColor("#3F51B5"));
            }
            // ---------------------------------------------------------------------
            display.setText("0");
            minDisplay.setText("");
        });

        // ---------------------------------------------- delete button -------------------------------------------------
        btnDelete.setOnClickListener(v -> {
            String currentValue = display.getText().toString();

            // clear the minDisplay if the equal is the last clicked
            if(equal){
                minDisplay.setText("");
                return;
            }

            // if the operator is the last clicked or the display value is 0 => the delet btn doesn't work
            // and if the display contain just one element => reset to 0
            if(currentValue.equals("0") || operator){
                return;
            } else if(currentValue.length() == 1){
                display.setText("0");
            } else {
                display.setText(currentValue.substring(0, currentValue.length()-1));
            }
        });

        // --------------------------------------------- Plus button ------------------------------------------------------
        btnPlus.setOnClickListener(v -> {
            // if the min display empty => append 2+
            // if already min display has a value => remove "+" and calculate the sum
            if(minDisplay.getText().toString().isEmpty()){

                minDisplay.setText(display.getText().toString() + "+");

            } else if(equal){

                // if equal last pressed => reset the main display to num+
                minDisplay.setText(display.getText().toString() + "+");

            } else if(operator){

                String minvalue = minDisplay.getText().toString();
                minDisplay.setText(minvalue.substring(0, minvalue.length()-1) + "+");

            } else {

                if(lastOperator.equals("+")){

                    // calculate the sum after each Plus btn click ( calculate addition )
                    String oldvalue = minDisplay.getText().toString();
                    String newvalue = display.getText().toString();

                    double val1 = Double.parseDouble(oldvalue.substring(0, oldvalue.length() - 1));  // remove "+"
                    double val2 = Double.parseDouble(newvalue);
                    double sum = val1 + val2;

                    if(sum == (long)sum){
                        display.setText(String.valueOf((long)sum));
                        minDisplay.setText(String.valueOf((long)sum) + "+");
                    } else {
                        display.setText(String.valueOf(sum));
                        minDisplay.setText(String.valueOf(sum) + "+");
                    }

                } else if(lastOperator.equals("-")){

                    // calculate the result after each click ( calculate substraction )
                    String oldvalue = minDisplay.getText().toString();
                    String newvalue = display.getText().toString();

                    double val1 = Double.parseDouble(oldvalue.substring(0, oldvalue.length() - 1));  // remove "-"
                    double val2 = Double.parseDouble(newvalue);
                    double result = val1 - val2;

                    if(result == (long)result){
                        display.setText(String.valueOf((long)result));
                        minDisplay.setText(String.valueOf((long)result) + "+");
                    } else {
                        display.setText(String.valueOf(result));
                        minDisplay.setText(String.valueOf(result) + "+");
                    }

                } else if(lastOperator.equals("*")){

                    // calculate the result after each click ( calculate multiplication )
                    String oldvalue = minDisplay.getText().toString();
                    String newvalue = display.getText().toString();

                    double val1 = Double.parseDouble(oldvalue.substring(0, oldvalue.length() - 1));  // remove "×"
                    double val2 = Double.parseDouble(newvalue);
                    double result = val1 * val2;

                    if(result == (long)result){
                        display.setText(String.valueOf((long)result));
                        minDisplay.setText(String.valueOf((long)result) + "+");
                    } else {
                        display.setText(String.valueOf(result));
                        minDisplay.setText(String.valueOf(result) + "+");
                    }

                } else if(lastOperator.equals("/")){

                    // calculate the result after each click ( calculate multiplication )
                    String oldvalue = minDisplay.getText().toString();
                    String newvalue = display.getText().toString();

                    double val1 = Double.parseDouble(oldvalue.substring(0, oldvalue.length() - 1));  // remove "÷"
                    double val2 = Double.parseDouble(newvalue);

                    //  ---------------- if the user enter 0 in the division -------------------
                    if(val2 == 0){
                        minDisplay.setText("");
                        display.setText("Cannot divide by zero");
                        btnDivision.setEnabled(false);
                        btnDivision.setBackgroundColor(Color.parseColor("#FFCC80"));
                        btnMultip.setEnabled(false);
                        btnMultip.setBackgroundColor(Color.parseColor("#FFCC80"));
                        btnMinus.setEnabled(false);
                        btnMinus.setBackgroundColor(Color.parseColor("#FFCC80"));
                        btnPlus.setEnabled(false);
                        btnPlus.setBackgroundColor(Color.parseColor("#FFCC80"));
                        btnEqual.setEnabled(false);
                        btnEqual.setBackgroundColor(Color.parseColor("#9FA8DA"));

                        equal = true;
                        operator = false;
                        return;
                    }
                    // ------------------------------------------------------------------------

                    double result = val1 / val2;

                    if(result == (long)result){
                        display.setText(String.valueOf((long)result));
                        minDisplay.setText(String.valueOf((long)result) + "+");
                    } else {
                        display.setText(String.valueOf(result));
                        minDisplay.setText(String.valueOf(result) + "+");
                    }

                }

            }

            // update the boolean variables
            operator = true;
            equal = false;
            lastOperator = "+";
        });

        // ------------------------------------------- Minus button -----------------------------------------------------
        btnMinus.setOnClickListener(v -> {
            // if the min display empty => append 2-
            // if already min display has a value => remove "-" and calculate the result
            if(minDisplay.getText().toString().isEmpty()){

                minDisplay.setText(display.getText().toString() + "-");

            } else if(equal){

                // if equal last pressed => reset the main display to num-
                minDisplay.setText(display.getText().toString() + "-");

            } else if(operator){

                String minvalue = minDisplay.getText().toString();
                minDisplay.setText(minvalue.substring(0, minvalue.length()-1) + "-");

            } else {
                if(lastOperator.equals("-")){

                    // calculate the result after each click ( calculate substraction )
                    String oldvalue = minDisplay.getText().toString();
                    String newvalue = display.getText().toString();

                    double val1 = Double.parseDouble(oldvalue.substring(0, oldvalue.length() - 1));  // remove "-"
                    double val2 = Double.parseDouble(newvalue);
                    double result = val1 - val2;

                    if(result == (long)result){
                        display.setText(String.valueOf((long)result));
                        minDisplay.setText(String.valueOf((long)result) + "-");
                    } else {
                        display.setText(String.valueOf(result));
                        minDisplay.setText(String.valueOf(result) + "-");
                    }

                } else if(lastOperator.equals("+")){

                    // calculate the sum after each Plus btn click ( calculate addition )
                    String oldvalue = minDisplay.getText().toString();
                    String newvalue = display.getText().toString();

                    double val1 = Double.parseDouble(oldvalue.substring(0, oldvalue.length() - 1));  // remove "+"
                    double val2 = Double.parseDouble(newvalue);
                    double sum = val1 + val2;

                    if(sum == (long)sum){
                        display.setText(String.valueOf((long)sum));
                        minDisplay.setText(String.valueOf((long)sum) + "-");
                    } else {
                        display.setText(String.valueOf(sum));
                        minDisplay.setText(String.valueOf(sum) + "-");
                    }

                } else if(lastOperator.equals("*")){

                    // calculate the result after each click ( calculate multiplication )
                    String oldvalue = minDisplay.getText().toString();
                    String newvalue = display.getText().toString();

                    double val1 = Double.parseDouble(oldvalue.substring(0, oldvalue.length() - 1));  // remove "×"
                    double val2 = Double.parseDouble(newvalue);
                    double result = val1 * val2;

                    if(result == (long)result){
                        display.setText(String.valueOf((long)result));
                        minDisplay.setText(String.valueOf((long)result) + "-");
                    } else {
                        display.setText(String.valueOf(result));
                        minDisplay.setText(String.valueOf(result) + "-");
                    }

                } else if(lastOperator.equals("/")){

                    // calculate the result after each click ( calculate multiplication )
                    String oldvalue = minDisplay.getText().toString();
                    String newvalue = display.getText().toString();

                    double val1 = Double.parseDouble(oldvalue.substring(0, oldvalue.length() - 1));  // remove "÷"
                    double val2 = Double.parseDouble(newvalue);

                    //  ---------------- if the user enter 0 in the division -------------------
                    if(val2 == 0){
                        minDisplay.setText("");
                        display.setText("Cannot divide by zero");
                        btnDivision.setEnabled(false);
                        btnDivision.setBackgroundColor(Color.parseColor("#FFCC80"));
                        btnMultip.setEnabled(false);
                        btnMultip.setBackgroundColor(Color.parseColor("#FFCC80"));
                        btnMinus.setEnabled(false);
                        btnMinus.setBackgroundColor(Color.parseColor("#FFCC80"));
                        btnPlus.setEnabled(false);
                        btnPlus.setBackgroundColor(Color.parseColor("#FFCC80"));
                        btnEqual.setEnabled(false);
                        btnEqual.setBackgroundColor(Color.parseColor("#9FA8DA"));

                        equal = true;
                        operator = false;
                        return;
                    }
                    // ------------------------------------------------------------------------

                    double result = val1 / val2;

                    if(result == (long)result){
                        display.setText(String.valueOf((long)result));
                        minDisplay.setText(String.valueOf((long)result) + "-");
                    } else {
                        display.setText(String.valueOf(result));
                        minDisplay.setText(String.valueOf(result) + "-");
                    }

                }

            }

            // update the operator variables
            operator = true;
            equal = false;
            lastOperator = "-";
        });

        // ----------------------------------------------- Multiply button ----------------------------------------------------------
        btnMultip.setOnClickListener(v -> {

            // if the min display empty => append 2×
            // if already min display has a value => remove "×" and calculate the result
            if(minDisplay.getText().toString().isEmpty()){

                minDisplay.setText(display.getText().toString() + "×");

            } else if(equal){

                // if equal last pressed => reset the min display to num×
                minDisplay.setText(display.getText().toString() + "×");

            } else if(operator){

                // change the operator for example 2+ => 2×
                String minvalue = minDisplay.getText().toString();
                minDisplay.setText(minvalue.substring(0, minvalue.length()-1) + "×");

            } else {

                if(lastOperator.equals("*")){

                    // calculate the result after each click ( calculate multiplication )
                    String oldvalue = minDisplay.getText().toString();
                    String newvalue = display.getText().toString();

                    double val1 = Double.parseDouble(oldvalue.substring(0, oldvalue.length() - 1));  // remove "×"
                    double val2 = Double.parseDouble(newvalue);
                    double result = val1 * val2;

                    if(result == (long)result){
                        display.setText(String.valueOf((long)result));
                        minDisplay.setText(String.valueOf((long)result) + "×");
                    } else {
                        display.setText(String.valueOf(result));
                        minDisplay.setText(String.valueOf(result) + "×");
                    }

                } else if(lastOperator.equals("-")){

                    // calculate the result after each click ( calculate substraction )
                    String oldvalue = minDisplay.getText().toString();
                    String newvalue = display.getText().toString();

                    double val1 = Double.parseDouble(oldvalue.substring(0, oldvalue.length() - 1));  // remove "-"
                    double val2 = Double.parseDouble(newvalue);
                    double result = val1 - val2;

                    if(result == (long)result){
                        display.setText(String.valueOf((long)result));
                        minDisplay.setText(String.valueOf((long)result) + "×");
                    } else {
                        display.setText(String.valueOf(result));
                        minDisplay.setText(String.valueOf(result) + "×");
                    }

                } else if(lastOperator.equals("+")){

                    // calculate the sum after each Plus btn click ( calculate addition )
                    String oldvalue = minDisplay.getText().toString();
                    String newvalue = display.getText().toString();

                    double val1 = Double.parseDouble(oldvalue.substring(0, oldvalue.length() - 1));  // remove "+"
                    double val2 = Double.parseDouble(newvalue);
                    double sum = val1 + val2;

                    if(sum == (long)sum){
                        display.setText(String.valueOf((long)sum));
                        minDisplay.setText(String.valueOf((long)sum) + "×");
                    } else {
                        display.setText(String.valueOf(sum));
                        minDisplay.setText(String.valueOf(sum) + "×");
                    }

                } else if(lastOperator.equals("/")){

                    // calculate the result after each click ( calculate multiplication )
                    String oldvalue = minDisplay.getText().toString();
                    String newvalue = display.getText().toString();

                    double val1 = Double.parseDouble(oldvalue.substring(0, oldvalue.length() - 1));  // remove "÷"
                    double val2 = Double.parseDouble(newvalue);

                    //  ---------------- if the user enter 0 in the division -------------------
                    if(val2 == 0){
                        minDisplay.setText("");
                        display.setText("Cannot divide by zero");
                        btnDivision.setEnabled(false);
                        btnDivision.setBackgroundColor(Color.parseColor("#FFCC80"));
                        btnMultip.setEnabled(false);
                        btnMultip.setBackgroundColor(Color.parseColor("#FFCC80"));
                        btnMinus.setEnabled(false);
                        btnMinus.setBackgroundColor(Color.parseColor("#FFCC80"));
                        btnPlus.setEnabled(false);
                        btnPlus.setBackgroundColor(Color.parseColor("#FFCC80"));
                        btnEqual.setEnabled(false);
                        btnEqual.setBackgroundColor(Color.parseColor("#9FA8DA"));

                        equal = true;
                        operator = false;
                        return;
                    }
                    // ------------------------------------------------------------------------

                    double result = val1 / val2;

                    if(result == (long)result){
                        display.setText(String.valueOf((long)result));
                        minDisplay.setText(String.valueOf((long)result) + "×");
                    } else {
                        display.setText(String.valueOf(result));
                        minDisplay.setText(String.valueOf(result) + "×");
                    }

                }

            }

            // update the operator variables
            operator = true;
            equal = false;
            lastOperator = "*";
        });

        // ----------------------------------------------- Division button ----------------------------------------------------------
        btnDivision.setOnClickListener(v -> {

            // if the min display empty => append 2÷
            // if already min display has a value => remove "÷" and calculate the result
            if(minDisplay.getText().toString().isEmpty()){

                minDisplay.setText(display.getText().toString() + "÷");

            } else if(equal){

                // if equal last pressed => reset the min display to num÷
                minDisplay.setText(display.getText().toString() + "÷");

            } else if(operator){

                // change the operator for example 2+ => 2÷
                String minvalue = minDisplay.getText().toString();
                minDisplay.setText(minvalue.substring(0, minvalue.length()-1) + "÷");

            } else {

                if(lastOperator.equals("/")){

                    // calculate the result after each click ( calculate multiplication )
                    String oldvalue = minDisplay.getText().toString();
                    String newvalue = display.getText().toString();

                    double val1 = Double.parseDouble(oldvalue.substring(0, oldvalue.length() - 1));  // remove "÷"
                    double val2 = Double.parseDouble(newvalue);

                    //  ---------------- if the user enter 0 in the division -------------------
                    if(val2 == 0){
                        minDisplay.setText("");
                        display.setText("Cannot divide by zero");
                        btnDivision.setEnabled(false);
                        btnDivision.setBackgroundColor(Color.parseColor("#FFCC80"));
                        btnMultip.setEnabled(false);
                        btnMultip.setBackgroundColor(Color.parseColor("#FFCC80"));
                        btnMinus.setEnabled(false);
                        btnMinus.setBackgroundColor(Color.parseColor("#FFCC80"));
                        btnPlus.setEnabled(false);
                        btnPlus.setBackgroundColor(Color.parseColor("#FFCC80"));
                        btnEqual.setEnabled(false);
                        btnEqual.setBackgroundColor(Color.parseColor("#9FA8DA"));

                        equal = true;
                        operator = false;
                        return;
                    }
                    // ------------------------------------------------------------------------

                    double result = val1 / val2;

                    if(result == (long)result){
                        display.setText(String.valueOf((long)result));
                        minDisplay.setText(String.valueOf((long)result) + "÷");
                    } else {
                        display.setText(String.valueOf(result));
                        minDisplay.setText(String.valueOf(result) + "÷");
                    }

                } else if(lastOperator.equals("*")){

                    // calculate the result after each click ( calculate multiplication )
                    String oldvalue = minDisplay.getText().toString();
                    String newvalue = display.getText().toString();

                    double val1 = Double.parseDouble(oldvalue.substring(0, oldvalue.length() - 1));  // remove "×"
                    double val2 = Double.parseDouble(newvalue);
                    double result = val1 * val2;

                    if(result == (long)result){
                        display.setText(String.valueOf((long)result));
                        minDisplay.setText(String.valueOf((long)result) + "÷");
                    } else {
                        display.setText(String.valueOf(result));
                        minDisplay.setText(String.valueOf(result) + "÷");
                    }

                } else if(lastOperator.equals("-")){

                    // calculate the result after each click ( calculate substraction )
                    String oldvalue = minDisplay.getText().toString();
                    String newvalue = display.getText().toString();

                    double val1 = Double.parseDouble(oldvalue.substring(0, oldvalue.length() - 1));  // remove "-"
                    double val2 = Double.parseDouble(newvalue);
                    double result = val1 - val2;

                    if(result == (long)result){
                        display.setText(String.valueOf((long)result));
                        minDisplay.setText(String.valueOf((long)result) + "÷");
                    } else {
                        display.setText(String.valueOf(result));
                        minDisplay.setText(String.valueOf(result) + "÷");
                    }

                } else if(lastOperator.equals("+")){

                    // calculate the sum after each Plus btn click ( calculate addition )
                    String oldvalue = minDisplay.getText().toString();
                    String newvalue = display.getText().toString();

                    double val1 = Double.parseDouble(oldvalue.substring(0, oldvalue.length() - 1));  // remove "+"
                    double val2 = Double.parseDouble(newvalue);
                    double sum = val1 + val2;

                    if(sum == (long)sum){
                        display.setText(String.valueOf((long)sum));
                        minDisplay.setText(String.valueOf((long)sum) + "÷");
                    } else {
                        display.setText(String.valueOf(sum));
                        minDisplay.setText(String.valueOf(sum) + "÷");
                    }

                }

            }

            // update the operator variables
            operator = true;
            equal = false;
            lastOperator = "/";
        });

        // ------------------------------------------------ Equal button ------------------------------------------------------------
        btnEqual.setOnClickListener(v -> {

            if(minDisplay.getText().toString().isEmpty()) {

                // if the min display empty => append 2=
                String currentValue = display.getText().toString();

                double val = Double.parseDouble(currentValue);
                if (val == (long) val) {
                    minDisplay.setText((long) val + "=");
                    display.setText(String.valueOf((long) val));
                } else {
                    minDisplay.setText(val + "=");
                }

            } else if(lastOperator.equals("+")){

                 if(equal){

                    // if equal pressed again => keep the second value from the last operation and add it to the equal value
                    double oldResult = Double.parseDouble(display.getText().toString());
                    firstvalue = Double.parseDouble(display.getText().toString());
                    double sum = firstvalue + secondvalue;

                    if(firstvalue == (long)firstvalue && secondvalue == (long)secondvalue){
                        minDisplay.setText(String.valueOf((long)oldResult) + "+" + String.valueOf((long)secondvalue) + "=");
                        display.setText(String.valueOf((long)sum));
                    } else {
                        minDisplay.setText(String.valueOf(oldResult) + "+" + String.valueOf(secondvalue) + "=");
                        display.setText(String.valueOf(sum));
                    }

                }else {

                    // min display => 2+3= | display => 5
                    String oldvalue = minDisplay.getText().toString();
                    String newvalue = display.getText().toString();

                    firstvalue = Double.parseDouble(oldvalue.substring(0, oldvalue.length() - 1));
                    secondvalue = Double.parseDouble(newvalue);
                    double sum = firstvalue + secondvalue;

                    if(sum == (long)sum){
                        display.setText(String.valueOf((long)sum));
                    } else {
                        display.setText(String.valueOf(sum));
                    }
                    minDisplay.setText(oldvalue + newvalue + "=");  // oldvalue already have the operator

                }

            } else if(lastOperator.equals("-")){

                if(equal){

                    // if equal pressed again => keep the second value from the last operation
                    double oldResult = Double.parseDouble(display.getText().toString());
                    firstvalue = Double.parseDouble(display.getText().toString());
                    double result = firstvalue - secondvalue;

                    if(firstvalue == (long)firstvalue && secondvalue == (long)secondvalue){
                        minDisplay.setText(String.valueOf((long)oldResult) + "-" + String.valueOf((long)secondvalue) + "=");
                        display.setText(String.valueOf((long)result));
                    } else {
                        minDisplay.setText(String.valueOf(oldResult) + "-" + String.valueOf(secondvalue) + "=");
                        display.setText(String.valueOf(result));
                    }

                }else {

                    // min display => 3-2= | display => 1
                    String oldvalue = minDisplay.getText().toString();
                    String newvalue = display.getText().toString();

                    firstvalue = Double.parseDouble(oldvalue.substring(0, oldvalue.length() - 1));
                    secondvalue = Double.parseDouble(newvalue);
                    double result = firstvalue - secondvalue;

                    if(result == (long)result){
                        display.setText(String.valueOf((long)result));
                    } else {
                        display.setText(String.valueOf(result));
                    }
                    minDisplay.setText(oldvalue + newvalue + "=");  // oldvalue already have the operator

                }

            } else if(lastOperator.equals("*")){

                if(equal){

                    // if equal pressed again => keep the second value from the last operation
                    double oldResult = Double.parseDouble(display.getText().toString());
                    firstvalue = Double.parseDouble(display.getText().toString());
                    double result = firstvalue * secondvalue;

                    if(firstvalue == (long)firstvalue && secondvalue == (long)secondvalue){
                        minDisplay.setText(String.valueOf((long)oldResult) + "×" + String.valueOf((long)secondvalue) + "=");
                        display.setText(String.valueOf((long)result));
                    } else {
                        minDisplay.setText(String.valueOf(oldResult) + "×" + String.valueOf(secondvalue) + "=");
                        display.setText(String.valueOf(result));
                    }

                }else {

                    // min display => 3×2= | display => 6
                    String oldvalue = minDisplay.getText().toString();
                    String newvalue = display.getText().toString();

                    firstvalue = Double.parseDouble(oldvalue.substring(0, oldvalue.length() - 1));
                    secondvalue = Double.parseDouble(newvalue);
                    double result = firstvalue * secondvalue;

                    if(result == (long)result){
                        display.setText(String.valueOf((long)result));
                    } else {
                        display.setText(String.valueOf(result));
                    }
                    minDisplay.setText(oldvalue + newvalue + "=");  // oldvalue already have the operator

                }

            } else if(lastOperator.equals("/")){

                if(equal){

                    // if equal pressed again => keep the second value from the last operation
                    double oldResult = Double.parseDouble(display.getText().toString());
                    firstvalue = Double.parseDouble(display.getText().toString());
                    double result = firstvalue / secondvalue;

                    if(firstvalue == (long)firstvalue && secondvalue == (long)secondvalue){
                        minDisplay.setText(String.valueOf((long)oldResult) + "÷" + String.valueOf((long)secondvalue) + "=");
                        display.setText(String.valueOf((long)result));
                    } else {
                        minDisplay.setText(String.valueOf(oldResult) + "÷" + String.valueOf(secondvalue) + "=");
                        display.setText(String.valueOf(result));
                    }

                }else {

                    // min display => 3÷2= | display => 1.5
                    String oldvalue = minDisplay.getText().toString();
                    String newvalue = display.getText().toString();

                    firstvalue = Double.parseDouble(oldvalue.substring(0, oldvalue.length() - 1));
                    secondvalue = Double.parseDouble(newvalue);

                    //  ---------------- if the user enter 0 in the division -------------------
                    if(secondvalue == 0){
                        minDisplay.setText("");
                        display.setText("Cannot divide by zero");
                        btnDivision.setEnabled(false);
                        btnDivision.setBackgroundColor(Color.parseColor("#FFCC80"));
                        btnMultip.setEnabled(false);
                        btnMultip.setBackgroundColor(Color.parseColor("#FFCC80"));
                        btnMinus.setEnabled(false);
                        btnMinus.setBackgroundColor(Color.parseColor("#FFCC80"));
                        btnPlus.setEnabled(false);
                        btnPlus.setBackgroundColor(Color.parseColor("#FFCC80"));
                        btnEqual.setEnabled(false);
                        btnEqual.setBackgroundColor(Color.parseColor("#9FA8DA"));

                        equal = true;
                        operator = false;
                        return;
                    }
                    // ------------------------------------------------------------------------

                    double result = firstvalue / secondvalue;

                    if(result == (long)result){
                        display.setText(String.valueOf((long)result));
                    } else {
                        display.setText(String.valueOf(result));
                    }
                    minDisplay.setText(oldvalue + newvalue + "=");  // oldvalue already have the operator

                }

            }

            // update the equal and operator variables
            equal = true;
            operator = false;
        });

    }

    // --------------------------------------------- append buttons to display ---------------------------------------------------
    public void addToDisplay(View view){
        Button btn = (Button) view;
        String value = btn.getText().toString();
        String currentValue = display.getText().toString();

        if(value.equals(".")){
            if(currentValue.contains(".") || operator){
                return;
            }
        }

        if (currentValue.equals("0") || operator == true){

            if(value.equals(".")){
                display.setText("0.");
            } else {
                display.setText(value);
            }

        } else if(equal){

            //  ---------------- if the user enter 0 in the division -------------------
            if(currentValue.equals("Cannot divide by zero")){
                btnDivision.setEnabled(true);
                btnDivision.setBackgroundColor(Color.parseColor("#FF9800"));
                btnMultip.setEnabled(true);
                btnMultip.setBackgroundColor(Color.parseColor("#FF9800"));
                btnMinus.setEnabled(true);
                btnMinus.setBackgroundColor(Color.parseColor("#FF9800"));
                btnPlus.setEnabled(true);
                btnPlus.setBackgroundColor(Color.parseColor("#FF9800"));
                btnEqual.setEnabled(true);
                btnEqual.setBackgroundColor(Color.parseColor("#3F51B5"));
            }
            //  ------------------------------------------------------------------------

            minDisplay.setText("");
            display.setText(value);

        } else {

            display.setText(currentValue + value);
            
        }

        operator = false;
        equal = false;
    }

}