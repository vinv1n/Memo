package com.example.vinvin.memoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vinvin on 22.11.2017.
 */

public class EditTask extends AppCompatActivity {

    private NumberPicker priority;
    private Button date_picker;
    private Button submit_button;
    private EditText new_task;
    private ConstraintLayout date_view;
    private ConstraintLayout edit_date_activity;
    private DatePicker date;

    private int priority_level;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        date_view = (ConstraintLayout) findViewById(R.id.date);
        edit_date_activity = (ConstraintLayout) findViewById(R.id.edit_body);

        new_task = (EditText) findViewById(R.id.input_text);
        submit_button = (Button) findViewById(R.id.submit_button);
        date_picker = (Button) findViewById(R.id.pick_date);
        date = (DatePicker) findViewById(R.id.datePicker);

        priority = (NumberPicker) findViewById(R.id.Priority);

        priority.setMaxValue(10);
        priority.setMinValue(1);
        priority.setWrapSelectorWheel(true);

        GetTaskData();

    }
    private void GetTaskData(){

        final String[] task_list = getIntent().getStringArrayListExtra("Tasks")
                .toArray(getIntent().getStringArrayExtra("Tasks"));

        /*

        At this point listview should be upgraded that it will show dates and priority levels

         */

        priority.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                priority_level = i1;
                priority.setValue(priority_level);
            }
        });

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String task = new_task.getText().toString();
                String[] updated_tasks = ConvertArrays(task_list, task);
                Intent intent_task = new Intent();
                intent_task.putExtra("Updated_tasks", updated_tasks);
                setResult(RESULT_OK, intent_task);
                finish();
            }
        });
        date_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Onfocus();
            }
        });
    }

    /*
    Not working yet
     */
    public void Onfocus() {
        edit_date_activity.setVisibility(View.GONE);
        date_view.setVisibility(View.VISIBLE);
        Button date_accept = (Button) findViewById(R.id.accept);
        date_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int day = date.getDayOfMonth();
                int month = date.getMonth();
                int year = date.getYear();
                OffFocus();
            }
        });
    }
    public void OffFocus(){
        edit_date_activity.setVisibility(View.VISIBLE);
        date_view.setVisibility(View.GONE);
    }
    public String[] ConvertArrays(String[] intent_tasks, String task){
        try {
            String[] updated_task = new String[intent_tasks.length + 1];
            for (int i = 0; i <= intent_tasks.length; i++) {
                updated_task[i] = intent_tasks[i];
            }
            updated_task[intent_tasks.length + 1] = task;
            return updated_task;
        } catch (NullPointerException e) {
            String[] updated_task = new String[1];
            updated_task[0] = task;
            return updated_task;
        }
    }

}
