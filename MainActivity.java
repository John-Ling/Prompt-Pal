package com.example.prompt_pal;

// AUTHOR: John Ling

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

// libraries for files

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView textView;
    private Button sendButton;
    private int taskCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<String> tasks = load("activities.txt");
        setUI();

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = generate_task(tasks);
                textView.setText(task);
            }
        });
    }

    private void setUI(){
        textView = findViewById(R.id.textView);
        sendButton = findViewById(R.id.button);
    }

    private ArrayList<String> load(String filename) {
        // generate ArrayList based from provided text file
        ArrayList<String> tasks = new ArrayList<String>();
        int totalTasks = 0;

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open(filename)));
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(line);
                totalTasks++;
            }
        }
        catch (IOException e) {
            System.out.println("Couldn't load tasks: " + e.getMessage());
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }

        taskCount = totalTasks;
        return tasks;
    }

    private String generate_task(ArrayList<String> tasks) {
        Random random = new Random();
        int index = random.nextInt(taskCount);
        return tasks.get(index);
    }

}