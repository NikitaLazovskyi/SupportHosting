package service.handler;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
    private final String filePath;
    private final String filePathOutput;

    public FileHandler(String filePath, String filePathOutput) {
        this.filePath = filePath;
        this.filePathOutput = filePathOutput;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFilePathOutput() {
        return filePathOutput;
    }

    public void write(List<String> lines) throws IOException {
        FileWriter fileWriter = new FileWriter(filePathOutput);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (int i = 0; i < lines.size(); i++) {
            if (i == lines.size() - 1) {
                bufferedWriter.write(lines.get(i));
            } else {
                bufferedWriter.write(lines.get(i) + '\n');
            }
        }
        bufferedWriter.close();
        fileWriter.close();
    }

    public String[] read() {
        String[] commands = null;
        try (FileInputStream inputStream = new FileInputStream(filePath); Scanner sc = new Scanner(inputStream, StandardCharsets.UTF_8)) {
            if (sc.hasNextLine()) {
                commands = new String[Integer.parseInt(sc.nextLine())];
                int i = 0;
                while (sc.hasNextLine()) {
                    if (i == commands.length){
                        break;
                    }
                    commands[i] = sc.nextLine();
                    i++;

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return commands;
    }
}
