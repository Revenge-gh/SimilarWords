package com.company;

import org.apache.commons.text.similarity.LevenshteinDistance;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс обработки строк
 */
public class CompareWordsService {
    private final LevenshteinDistance levenshteinDistance = LevenshteinDistance.getDefaultInstance();
    private List<String> firstPartWords = new ArrayList<>();
    private List<String> secondPartWords = new ArrayList<>();

    /**
     * Метод разделяющий список строк на два списка
     *
     * @param list             список входных данных
     * @param firstIndexArray  количество строк в первом списке
     * @param secondIndexArray количество строк во втором списке
     */

    public void processingInputData(List<String> list, int firstIndexArray, int secondIndexArray) {
        for (int i = 0; i < firstIndexArray; i++) {
            firstPartWords.add(list.get(i));
        }
        for (int i = firstIndexArray; i < firstIndexArray + secondIndexArray; i++) {
            secondPartWords.add(list.get(i));
        }
    }

    /**
     * Метод выполняющий сопоставления максимально похожих строк
     * из первого списка со строками из второго
     *
     * @return карту максимально похожих строк
     */
    public Map<String, String> compareWords() {
        if (firstPartWords.isEmpty() || secondPartWords.isEmpty()) {
            return null;
        }

        Map<String, String> result = new LinkedHashMap<>();

        if (firstPartWords.size() + secondPartWords.size() == 2) {
            result.put(firstPartWords.get(0), secondPartWords.get(0));
            return result;
        }

        if (firstPartWords.size() < secondPartWords.size()) {
            List<String> temp = new ArrayList<>(firstPartWords);
            firstPartWords = secondPartWords;
            secondPartWords = temp;
        }


        for (int i = 0; i < firstPartWords.size(); i++) {
            String[] strings1 = firstPartWords.get(i).split(" ");
            for (int j = 0; j < secondPartWords.size(); j++) {
                String[] strings2 = secondPartWords.get(j).split(" ");
                for (int k = 0; k < strings1.length; k++) {
                    String s1 = strings1[k];
                    for (int l = 0; l < strings2.length; l++) {
                        String s2 = strings2[l];
                        if (levenshteinDistance.apply(s1, s2) < 3) {
                            result.put(firstPartWords.get(i), secondPartWords.get(j));
                        }
                    }
                }
            }

            if (!result.containsKey(firstPartWords.get(i))) {
                result.put(firstPartWords.get(i), "?");
            }
        }

        return result;
    }

}
