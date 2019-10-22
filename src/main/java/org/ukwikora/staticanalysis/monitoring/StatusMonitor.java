package org.ukwikora.staticanalysis.monitoring;

public class StatusMonitor {
    public boolean isReady() {
        return this.state == State.Ready;
    }

    private State state = State.Ready;

    public void setState(State state) {
        synchronized (this){
            this.state = state;
        }
    }

    public State getState() {
        return state;
    }
}
