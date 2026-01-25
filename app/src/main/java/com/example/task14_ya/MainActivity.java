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

/**
 * @author Darya student@email.com
 *
 * @version 1.0
 *
 * @since 18/01/2026
 *
 * This class handles the main screen where the user inputs the first number, the difference/multiplier, and selects the series type.
 */
public class MainActivity extends AppCompatActivity {
    EditText edit1;
    EditText edit2;
    RadioButton rbArithmetic;
    /**
     * Initializes the activity, sets the layout, and binds the UI components.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit1 = findViewById(R.id.editTextNumberDecimal);
        edit2 = findViewById(R.id.editTextNumberDecimal2);
        rbArithmetic = findViewById(R.id.rbArithmetic);
    }
    /**
     * Inflates the options menu for the activity.
     *
     * @param menu The options menu in which you place your items.
     * @return boolean Return true for the menu to be displayed.
     */
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Handles selection of items from the options menu, such as navigating to the Credits screen.
     *
     * @param item The menu item that was selected.
     * @return boolean Return false to allow normal menu processing to proceed, true to consume it here.
     */
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
    /**
     * Validates the user input in the EditText fields to ensure they are not empty or contain only symbols.
     *
     * @return boolean True if the input is valid (numbers), False otherwise.
     */
    public boolean isValidInput() {
        String str1 = edit1.getText().toString();
        String str2 = edit2.getText().toString();
        if (str1.isEmpty() || str2.isEmpty()) {
            return false;
        }
        if (str1.equals("-") || str1.equals(".") || str1.equals("-.")) {
            return false;
        }
        if (str2.equals("-") || str2.equals(".") || str2.equals("-.")) {
            return false;
        }

        return true;
    }
    /**
     * Processes the input and transitions to the result activity.
     * Parses the numbers and series type, then passes them via Intent.
     *
     * @param view The view that was clicked (the button).
     */
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
