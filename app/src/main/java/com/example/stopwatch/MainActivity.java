package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView timer;
    Button start, stop, reset;

    private int seconds = 0;
    private boolean running;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);
        reset = findViewById(R.id.reset);
        timer = findViewById(R.id.timer);

        start.setOnClickListener(this::startTimer);
        stop.setOnClickListener(this::stopTimer);
        reset.setOnClickListener(this::resetTimer);

        // Initialize the timer text
        timer.setText("0:00:00");

        // Start the runnable to update the timer
        handler.post(runnable);
    }

    private void startTimer(View view) {
        running = false;
    }

    private void stopTimer(View view) {
        running = true;
    }

    private void resetTimer(View view) {
        running = false;
        seconds = 0;
        timer.setText("00:00:00");
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int hours = seconds / 3600;
            int minutes = (seconds % 3600) / 60;
            int secs = seconds % 60;
            String time = String.format("%d:%02d:%02d", hours, minutes, secs);
            timer.setText(time);
            if (running) {
                seconds++;
            }
            handler.postDelayed(this, 1);
        }
    };
}
