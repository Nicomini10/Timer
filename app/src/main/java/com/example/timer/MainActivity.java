package com.example.timer;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static Button startButton;
    static boolean timeHasStarted = false;
    static TextView text;
    static TextView timeElapsedView;
    static MalibuCountDownTimer countDownTimer;

    static long startTime;
    static long timeElapsed = 0;
    static long interval = 1;

    static Context mainContext;
    static CharSequence toastTextStart = "Timer avviato!";
    static CharSequence toastTextStopped = "Timer stoppato!";
    static int toastDuration = Toast.LENGTH_SHORT;

    static EditText inputStartTime;
    static EditText inputFinishTime;

    Calendar calendarStart;
    Calendar calendarFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("prova","activity creata");
        setContentView(R.layout.activity_main);
        startButton = (Button) this.findViewById(R.id.button);
        startButton.setOnClickListener(this);

        text = (TextView) this.findViewById(R.id.timer);
        timeElapsedView = (TextView) this.findViewById(R.id.timeElapsed);

        inputStartTime = (EditText) findViewById(R.id.startTime);
        inputFinishTime = (EditText) findViewById(R.id.finishTime);

        this.mainContext = getApplicationContext();
    }

    @Override
    public void onClick(View v){

        if(timeHasStarted == false){

            if(inputStartTime.getText().toString().isEmpty() && inputFinishTime.getText().toString().isEmpty()) {
                startTime = 5000;
            } else {

                calendarStart = Calendar.getInstance();
                calendarStart.set(Calendar.HOUR_OF_DAY, Integer.parseInt(inputStartTime.getText().toString().split(":", 2)[0]));
                calendarStart.set(Calendar.MINUTE, Integer.parseInt(inputStartTime.getText().toString().split(":", 2)[1]));

                calendarFinish = Calendar.getInstance();
                calendarFinish.set(Calendar.HOUR_OF_DAY, Integer.parseInt(inputFinishTime.getText().toString().split(":", 2)[0]));
                calendarFinish.set(Calendar.MINUTE, Integer.parseInt(inputFinishTime.getText().toString().split(":", 2)[1]));

                startTime = (calendarFinish.getTimeInMillis() - calendarStart.getTimeInMillis());

            }

            countDownTimer = new MalibuCountDownTimer(startTime, interval);
            text.setText(text.getText() + String.valueOf(startTime));

            countDownTimer.start();
            timeHasStarted = true;
            startButton.setText("STOP");
        } else {
            countDownTimer.cancel();
            timeHasStarted = false;
            startButton.setText("RESTART");
        }

        this.showToast();
    }

    public static void showToast(){

        Toast toast;

        if(timeHasStarted)
            toast = Toast.makeText(mainContext, toastTextStart, toastDuration);
        else
            toast = Toast.makeText(mainContext, toastTextStopped, toastDuration);

        toast.show();
    }
}






