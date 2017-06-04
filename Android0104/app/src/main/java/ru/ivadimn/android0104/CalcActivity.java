package ru.ivadimn.android0104;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalcActivity extends Activity {

    private static final String OPER_MULT = "*";
    private static final String OPER_DIV = "/";
    private static final String OPER_PLUS = "+";
    private static final String OPER_MINUS = "-";
    private static final String OPER_CLS = "CLS";
    private static final String EQUAL = "=";
    private static final String OPERATION = "OPERATION";
    private static final String RESULT = "RESULT";


    private String operation = "";
    private EditText etOp1;
    private EditText etOp2;
    private TextView tvResult;
    private TextView tvOper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        etOp1 = (EditText) findViewById(R.id.et_op1_id);
        etOp2 = (EditText) findViewById(R.id.et_op2_id);
        tvResult = (TextView) findViewById(R.id.tv_result_id);
        tvOper = (TextView) findViewById(R.id.tv_op_id);

        if (savedInstanceState != null) {
            operation = savedInstanceState.getString(OPERATION);
            tvOper.setText(operation);
            tvResult.setText(String.valueOf(savedInstanceState.getString(RESULT)));
        }
    }

    public void onClick(View view) {
        String op = (String)view.getTag();
        switch(op) {
            case EQUAL:
                if (!operation.isEmpty())
                    showResult();
                break;
            case OPER_CLS:
                clear();
                break;
            default:
                tvOper.setText(operation = op);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(OPERATION, operation);
        outState.putString(RESULT, tvResult.getText().toString());
    }

    private void showResult() {
        double op1;
        double op2;
        double result=0;
        try {
            op1 = Double.valueOf(etOp1.getText().toString());
            op2 = Double.valueOf(etOp2.getText().toString());
        }
        catch(NumberFormatException ex) {
            Toast.makeText(this, "Операнды не определены", Toast.LENGTH_SHORT).show();
            return;
        }

        switch(operation) {
            case OPER_MULT:
                result = op1 * op2;
                break;
            case OPER_DIV:
                if (op2 == 0)
                    result = Double.POSITIVE_INFINITY;
                else
                    result = op1 / op2;
                break;
            case OPER_PLUS:
                result = op1 + op2;
                break;
            case OPER_MINUS:
                result = op1 - op2;
                break;
        }
        tvResult.setText(String.valueOf(result));
    }

    private void clear() {
        operation = "";
        etOp1.setText("");
        etOp2.setText("");
        tvResult.setText("");
        tvOper.setText("");
    }

}
