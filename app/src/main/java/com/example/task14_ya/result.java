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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class result extends AppCompatActivity
{
    ListView lv;
    TextView tv;
    ArrayList<Double> sidraArr = new ArrayList<>();
    ArrayAdapter<Double> adp;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        lv = findViewById(R.id.listItem);
        tv = findViewById(R.id.textView);


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
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        // כותרת לתפריט הקטן
        menu.setHeaderTitle("choose");
        menu.add("mikom");
        menu.add("shom");
    }

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
            tv.setText("סכום הסדרה עד האיבר הנבחר: " + sum);
        }

        return true;
    }
}