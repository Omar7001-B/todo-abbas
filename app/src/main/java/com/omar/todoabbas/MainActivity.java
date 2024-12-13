package com.omar.todoabbas;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.omar.todoabbas.Adapter.ToDoAdapter;
import com.omar.todoabbas.Model.ToDoModel;
import com.omar.todoabbas.Utils.DatabaseHandler;
import com.omar.todoabbas.Utils.PreferencesManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements DialogCloseListener {

    private RecyclerView tasksRecyclerView;
    private ToDoAdapter tasksAdapter;
    private FloatingActionButton fab;

    private List<ToDoModel> taskList;
    private DatabaseHandler db;
    private PreferencesManager preferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferencesManager = new PreferencesManager(this);
        if (preferencesManager.isDarkMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        if(getSupportActionBar() != null)
            getSupportActionBar().hide();


        db = new DatabaseHandler(this);
        db.openDatabase();

        taskList = new ArrayList<>();

        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        tasksAdapter = new ToDoAdapter(db, MainActivity.this);
        tasksRecyclerView.setAdapter(tasksAdapter);

        fab = findViewById(R.id.fab);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerItemTouchHelper(tasksAdapter));
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView);

        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        tasksAdapter.setTasks(taskList);

        fab.setOnClickListener(v -> AddNewTask.newInstance().show(getSupportFragmentManager(), AddNewTask.TAG));

        ImageButton darkModeButton = findViewById(R.id.darkModeButton);
        darkModeButton.setImageResource(preferencesManager.isDarkMode() ? R.drawable.ic_sun : R.drawable.ic_moon);

        darkModeButton.setOnClickListener(v -> {
            boolean newDarkMode = !preferencesManager.isDarkMode();
            preferencesManager.setDarkMode(newDarkMode);
            darkModeButton.setImageResource(newDarkMode ? R.drawable.ic_sun : R.drawable.ic_moon);
            AppCompatDelegate.setDefaultNightMode(newDarkMode ? 
                AppCompatDelegate.MODE_NIGHT_YES : 
                AppCompatDelegate.MODE_NIGHT_NO);
            recreate();
        });
    }

    @Override
    public void handleDialogClose(DialogInterface dialog) {
        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        tasksAdapter.setTasks(taskList);
        tasksAdapter.notifyDataSetChanged();
        
        // Ensure the RecyclerView updates visually
        tasksRecyclerView.post(() -> {
            tasksAdapter.notifyDataSetChanged();
            tasksRecyclerView.scheduleLayoutAnimation();
        });
    }
}