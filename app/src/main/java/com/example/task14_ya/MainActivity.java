package com.example.task14_ya;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edit1;
    EditText edit2;
    Switch switchAnswer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        edit1 = findViewById(R.id.editTextNumberDecimal);
        edit2 = findViewById(R.id.editTextNumberDecimal2);
        switchAnswer = findViewById(R.id.switchAnswer);
    }

    public boolean isValidInput() {
        String str1 = edit1.getText().toString();
        String str2 = edit2.getText().toString();
        if (str1.isEmpty() || str2.isEmpty()) {
            return false;
        }

        if (str1.equals(".") || str1.equals("-") || str2.equals(".") || str2.equals("-")) {
            return false;
        }
        return true;
    }

    public void go(View view)
    {
        if (isValidInput()) {
            double first = Double.parseDouble(edit1.getText().toString());
            double diff = Double.parseDouble(edit2.getText().toString());
            boolean isArithmetic = switchAnswer.isChecked();

            Intent intent = new Intent(this, result.class);
            intent.putExtra("first", first);
            intent.putExtra("diff", diff);
            intent.putExtra("isArithmetic", isArithmetic);
            startActivity(intent);
        }
    }
}
