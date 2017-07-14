package org.academiadecodigo.hackaton.client.utils;

import javafx.scene.text.Text;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author by André Martins <Code Cadet>
 *         Project stalkers (13/07/17)
 *         <Academia de Código_>
 */
public class Counter extends TimerTask {

    private final Text timeText;
    private int timeToCount;
    private final Timer timer;

    public Counter(int timeToCount, Timer timer, Text timeText) {
        this.timeToCount = timeToCount;
        this.timer = timer;
        this.timeText = timeText;
    }

    @Override
    public void run() {
        synchronized (timer) {
            timeText.setText("" + timeToCount--);
            if (timeToCount < 0) {
                timer.notifyAll();
                timer.cancel();
            }
        }
    }

}
