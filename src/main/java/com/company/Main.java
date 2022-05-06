package com.company;

public class Main {

    public static void main(String[] args) {
        StorageService storageService = new StorageService();
        CompareWordsService compareWordsService = new CompareWordsService();

        //читаем строки из файла
        storageService.readFromFile();

        //делим список на два подсписка
        compareWordsService.processingInputData(
                storageService.getInputDataList(),
                storageService.getFirstIndexArray(),
                storageService.getSecondIndexArray()
        );

        //ищем максимально похожие строки и записываем их в файл
        storageService.writeToFile(compareWordsService.compareWords());
    }

}
