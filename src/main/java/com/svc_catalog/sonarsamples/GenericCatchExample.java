package com.svc_catalog.sonarsamples;

public class GenericCatchExample {
    public int parseNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (Exception e) {
            throw new IllegalStateException("Invalid number", e);
        }
    }
}
