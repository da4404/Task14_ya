package com.example.task14_ya;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
/**
 * @author Darya student@email.com
 *
 * @version 1.0
 *
 * @since 18/01/2026
 *
 * This activity calculates and displays the mathematical series in a ListView and handles context menu actions for position and sum.
 */
public class result extends AppCompatActivity
{
    ListView lv;
    TextView tv;
    ArrayList<Double> sidraArr = new ArrayList<>();
    ArrayAdapter<Double> adp;
    @Override
    /**
     * Initializes the activity, retrieves the series parameters from the intent, calculates the first 20 elements, and populates the ListView.
     * <p>
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down.
     */
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        lv = findViewById(R.id.listItem);
        tv = findViewById(R.id.tvResult);

        Intent gi = getIntent();
        double first = gi.getDoubleExtra("first", 0);
        double diff = gi.getDoubleExtra("diff", 0);
        boolean isArithmetic = gi.getBooleanExtra("isArithmetic", true);
        double currentElement = first;
        sidraArr.clear();
        for (int i = 0; i < 20; i++)
        {
            sidraArr.add(currentElement);
            if (isArithmetic)
            {
                currentElement = currentElement + diff;
            }
            else
            {
                currentElement = currentElement * diff;
            }
        }
        adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sidraArr);
        lv.setAdapter(adp);
        registerForContextMenu(lv);

    }
    /**
     * Creates a floating context menu when a list item is long-pressed, offering options to see the element's position or sum.
     * <p>
     *
     * @param menu The context menu that is being built.
     * @param v The view for which the context menu is being built.
     * @param menuInfo Extra information about the item for which the context menu should be shown.
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("choose: ");
        menu.add("mikom");
        menu.add("shom");
    }
    /**
     * Formats a double to a string, handling scientific notation for very large/small values and removing decimals for integers.
     * <p>
     *
     * @param num The number to format.
     * @return String The formatted representation of the number.
     */
    public String formatNiceResult(double num) {
        if (num == 0) {
            return "0";
        }
        double absVal = Math.abs(num);
        if (absVal >= 10000000 || absVal <= 0.001) {
            return String.format("%.3E", num);
        } else if (num == (long) num) {
            return String.format("%d", (long) num);
        } else {
            return String.format("%.3f", num);
        }
    }
    /**
     * Handles the selection of an item in the context menu.
     * Displays either the position (index) of the element or the cumulative sum up to that element.
     * <p>
     *
     * @param item The context menu item that was selected.
     * @return boolean Return true to consume the menu click.
     */
    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        int position = info.position;
        String selectedOption = item.getTitle().toString();
        if (selectedOption.equals("mikom"))
        {
            tv.setText("האיבר נמצא במקום ה-" + (position + 1));
        }
        else if (selectedOption.equals("shom"))
        {
            double sum = 0;
            for (int i = 0; i <= position; i++)
            {
                sum = sum + sidraArr.get(i);
            }
            tv.setText("סכום הסדרה עד האיבר הנבחר: " + formatNiceResult(sum));
        }

        return true;
    }
}