package org.example;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.*;

public class TagFrequencyCounter {

    public static void conut(String url) {
        Map<String, Integer> tagFrequency = getTagFrequency(url);

        // Виведення результатів
        printTagFrequency(tagFrequency);
    }

    public static Map<String, Integer> getTagFrequency(String url) {
        Map<String, Integer> tagFrequency = new HashMap<>();

        try {
            Document document = Jsoup.connect(url).get();
            Elements tags = document.select("*");

            for (Element tag : tags) {
                String tagName = tag.tagName();
                tagFrequency.put(tagName, tagFrequency.getOrDefault(tagName, 0) + 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tagFrequency;
    }

    public static void printTagFrequency(Map<String, Integer> tagFrequency) {
        tagFrequency.remove("#root");
        Set<String> sortedTagsLexicographic = new TreeSet<>(tagFrequency.keySet());
        System.out.println("Результати в лексикографічному порядку:");
        for (String tagName : sortedTagsLexicographic) {
            int frequency = tagFrequency.get(tagName);
            System.out.println(tagName + ": " + frequency);
        }

        System.out.println("\nРезультати в порядку зростання частоти:");
        tagFrequency.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
}
