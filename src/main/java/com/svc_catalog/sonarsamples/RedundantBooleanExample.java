package com.svc_catalog.sonarsamples;

public class RedundantBooleanExample {
    public boolean isEnabled(boolean flag) {
        if (flag == true) {
            return true;
        }
        return false;
    }
}
