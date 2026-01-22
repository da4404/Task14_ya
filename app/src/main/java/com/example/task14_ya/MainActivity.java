package com.example.task14_ya;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edit1;
    EditText edit2;
    RadioButton rbArithmetic;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit1 = findViewById(R.id.editTextNumberDecimal);
        edit2 = findViewById(R.id.editTextNumberDecimal2);
        rbArithmetic = findViewById(R.id.rbArithmetic);
    }
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == R.id.action_credits)
        {
            Intent intent = new Intent(this, Credits.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
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
            boolean isArithmetic = rbArithmetic.isChecked();
            double first = Double.parseDouble(edit1.getText().toString());
            double diff = Double.parseDouble(edit2.getText().toString());

            Intent intent = new Intent(this, result.class);
            intent.putExtra("first", first);
            intent.putExtra("diff", diff);
            intent.putExtra("isArithmetic", isArithmetic);
            startActivity(intent);
        }
    }
}
