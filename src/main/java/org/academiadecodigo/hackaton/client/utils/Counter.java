package org.academiadecodigo.hackaton.client.utils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author by André Martins <Code Cadet>
 *         Project stalkers (13/07/17)
 *         <Academia de Código_>
 */
public class Counter extends TimerTask {

    private int timeToCount;
    private final Timer timer;

    public Counter(int timeToCount, Timer timer) {
        this.timeToCount = timeToCount;
        this.timer = timer;
    }

    @Override
    public void run() {;
        System.out.println(timeToCount--);
        if (timeToCount < 0) {
            timer.cancel();
        }
    }

}
