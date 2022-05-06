package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

/**
 * Класс чтения и записи данных
 */
public class StorageService {
    private int firstIndexArray;
    private int secondIndexArray;
    private final List<String> inputDataList = new ArrayList<>();

    /**
     * Метод чтения строк из файла с фильтрацией на числовые значения
     */
    public void readFromFile() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        requireNonNull(
                                Main.class.getClassLoader().getResourceAsStream("input.txt"))))) {
            for (String line; (line = reader.readLine()) != null; ) {
                if (!line.isEmpty()) {
                    if (line.matches("\\d")) {
                        if (firstIndexArray == 0) {
                            firstIndexArray = Integer.parseInt(line);
                        } else if (secondIndexArray == 0) {
                            secondIndexArray = Integer.parseInt(line);
                        }
                    } else {
                        inputDataList.add(line.trim());
                    }
                }
            }

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод записи карты в файл
     *
     * @param result карта, которую необходимо записать в файл
     */
    public void writeToFile(Map<String, String> result) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("./output.txt"))) {
            for (Map.Entry<String, String> pair : result.entrySet()) {
                writer.write(pair.getKey() + ":" + pair.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getFirstIndexArray() {
        return firstIndexArray;
    }

    public int getSecondIndexArray() {
        return secondIndexArray;
    }

    public List<String> getInputDataList() {
        return inputDataList;
    }
}
