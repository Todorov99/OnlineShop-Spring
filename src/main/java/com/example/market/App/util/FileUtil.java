package com.example.market.App.util;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileUtil {

    void saveJsonStringInFile(String jsonString, String path) throws IOException;

    String fileContent(String path) throws IOException;
}
