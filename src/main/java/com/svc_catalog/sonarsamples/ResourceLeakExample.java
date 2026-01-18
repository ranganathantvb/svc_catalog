package com.svc_catalog.sonarsamples;

import java.io.FileInputStream;
import java.io.IOException;

public class ResourceLeakExample {
    public int readFirstByte(String path) throws IOException {
        FileInputStream stream = new FileInputStream(path);
        return stream.read();
    }
}
