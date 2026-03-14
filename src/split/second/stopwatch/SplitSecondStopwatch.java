package split.second.stopwatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

enum States {
    READY, STOPPED, RUNNING
}

class Timer {
    private int seconds;
    private int minutes;
    private int hours;

    Timer() {
        this.seconds = 0;
        this.minutes = 0;
        this.hours = 0;
    }

    public void addSeconds(int newSeconds) {
        this.seconds += newSeconds;
        int minutes = this.seconds / 60;
        if (minutes != 0) {
            this.seconds -= minutes * 60;
            addMinutes(minutes);
        }
    }

    public void addMinutes(int newMinutes) {
        this.minutes += newMinutes;
        int hours = this.minutes / 60;
        if (hours != 0) {
            this.minutes -= hours * 60;
            addHours(hours);
        }
    }

    public void addHours(int newHours) {
        this.hours += newHours;
    }

    public void addTime(int seconds, int minutes, int hours) {
        addSeconds(seconds);
        addMinutes(minutes);
        addHours(hours);
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}

public class SplitSecondStopwatch {
    private Timer lapTime = new Timer();
    private Timer totalTime = new Timer();
    private States state = States.READY;
    private List<Timer> previousLaps = new ArrayList<>();

    public void start() {
        if (state == States.RUNNING) {
            throw new IllegalStateException("cannot start an already running stopwatch");
        }
        state = States.RUNNING;
    }

    public void stop() {
        if (state != States.RUNNING) {
            throw new IllegalStateException("cannot stop a stopwatch that is not running");
        }
        state = States.STOPPED;
    }

    public void reset() {
        if (state != States.STOPPED) {
            throw new IllegalStateException("cannot reset a stopwatch that is not stopped");
        }
        state = States.READY;
        previousLaps = new ArrayList<>();
        lapTime = new Timer();
        totalTime = new Timer();
    }

    public void lap() {
        if (state != States.RUNNING) {
            throw new IllegalStateException("cannot lap a stopwatch that is not running");
        }
        previousLaps.add(lapTime);
        lapTime = new Timer();
    }

    public String state() {
        return state.name().toLowerCase();
    }

    public String currentLap() {
        return lapTime.toString();
    }

    public String total() {
        return totalTime.toString();
    }

    public List<String> previousLaps() {
        return previousLaps.stream().map(Timer::toString).toList();
    }

    public void advanceTime(String timeString) {
        if (state != States.STOPPED) {
            int[] time = Arrays.stream(timeString.split(":")).mapToInt(Integer::parseInt).toArray();

            if (time.length == 3) {
                lapTime.addTime(time[2], time[1], time[0]);
                totalTime.addTime(time[2], time[1], time[0]);
            }
        }
    }
}