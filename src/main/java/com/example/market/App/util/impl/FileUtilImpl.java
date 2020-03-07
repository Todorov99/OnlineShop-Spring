package com.example.market.App.util.impl;

import com.example.market.App.util.FileUtil;

import java.io.*;

public class FileUtilImpl implements FileUtil {


    @Override
    public void saveJsonStringInFile(String jsonString, String path) throws IOException {

        File file = new File(path);

        FileWriter fileWriter = new FileWriter(file);

        fileWriter.write(jsonString);
        fileWriter.flush();
        fileWriter.close();
    }

    @Override
    public String fileContent(String path) throws IOException {

        File file = new File(path);

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        StringBuilder sb = new StringBuilder();

        String line;

        while ((line = reader.readLine()) != null){
            sb.append(line).append(System.lineSeparator());
        }


        return sb.toString().trim();
    }

}
