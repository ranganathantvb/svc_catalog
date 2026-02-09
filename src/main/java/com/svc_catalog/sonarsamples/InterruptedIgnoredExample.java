package com.svc_catalog.sonarsamples;

public class InterruptedIgnoredExample {
    public void pause() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            /* Clean up whatever needs to be handled before interrupting */
            Thread.currentThread().interrupt();
        }
    }
}
