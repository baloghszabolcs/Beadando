/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.inf.unideb.beadando.model;

/**
 *
 * @author balogh
 */public class CountDown {
    //CHECKSTYLE:OFF
    private int duration;

    public CountDown(final int duration) {
        this.duration = duration;
    }

    public synchronized void decreaseDuration() {
        this.duration--;
    }

    public void setDuration(final int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public String showDuration() {
        if (this.duration > 0) {
            int mp = this.duration % 60;
            int p = this.duration / 60;
            if (mp <= 9 && p <= 9) {
                return String.format("0%d:0%d", p, mp);
            }
            if (mp <= 9) {
                return String.format("%d:0%d", p, mp);
            }
            if (p <= 9) {
                return String.format("0%d:%d", p, mp);
            }
            return String.format("%d:%d", p, mp);
        }
        return "00:00";
    }

}

