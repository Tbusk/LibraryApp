package com.libraryapp.app;

import java.time.Duration;
import java.time.LocalTime;

public class Timer {

    private LocalTime startTime;
    private LocalTime endTime;

    public void start(){
        startTime = LocalTime.now();
    }

    public void end() {
        this.endTime  = LocalTime.now();
    }

    public long getElapsedTime() {
        return Duration.between(startTime, endTime).toMillis();
    }
}
