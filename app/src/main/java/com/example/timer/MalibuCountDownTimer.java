package com.example.timer;

import android.os.CountDownTimer;

class MalibuCountDownTimer extends CountDownTimer {

    public MalibuCountDownTimer(long startTime, long interval){
        super(startTime, interval);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        MainActivity.text.setText("Time remain " + millisUntilFinished);
        MainActivity.timeElapsed = MainActivity.startTime - millisUntilFinished;
        MainActivity.timeElapsedView.setText("Time Elapsed: " + String.valueOf(MainActivity.timeElapsed));
    }

    @Override
    public void onFinish() {
        MainActivity.text.setText("Time's up");
        MainActivity.startButton.setText("RESTART");
        MainActivity.timeHasStarted = false;
        MainActivity.timeElapsedView.setText("Time Elapsed " + String.valueOf(MainActivity.startTime));
        MainActivity.showToast();
    }
}
