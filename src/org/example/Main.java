
package org.example;

import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть шлях та ім'я файлу: ");
        String filePath = scanner.nextLine();

        try
        {
            // Отримання рядка з максимальною кількістю слів з файлу
            String maxWordCountLine = getMaxWordCountLine(filePath);
            System.out.println("Рядок з максимальною кількістю слів: " + maxWordCountLine);

            // Зчитування ключового символу для шифрування
            System.out.print("Введіть ключовий символ для шифрування: ");
            char encryptionKey = scanner.nextLine().charAt(0);

            // Шифрування рядка та збереження в файл
            encryptAndSaveToFile(filePath, encryptionKey);
            System.out.println("Рядок зашифровано та збережено в файлі.");

            // Дешифрування рядка та виведення на екран
            String decryptedLine = decryptFromFile(filePath, encryptionKey);
            System.out.println("Розшифрований рядок: " + decryptedLine);

            // Підрахунок частоти тегів на сторінці по URL
            System.out.print("Введіть URL сторінки: ");
            String url = scanner.nextLine();
            TagFrequencyCounter.conut(url);
        }
        catch (IOException e)
        {
            System.err.println("Shit happens.. " + e.getMessage());
        }
    }

    private static String getMaxWordCountLine(String filePath) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        String maxLine = "";
        int maxWordCount = 0;

        while ((line = reader.readLine()) != null) {
            String[] words = line.split("\\s+");
            if (words.length > maxWordCount) {
                maxWordCount = words.length;
                maxLine = line;
            }
        }

        reader.close();
        return maxLine;
    }

    private static void encryptAndSaveToFile(String filePath, char encryptionKey) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + "_encrypted"));

        int character;
        while ((character = reader.read()) != -1)
        {
            char encryptedChar = (char) (character + encryptionKey);
            writer.write(encryptedChar);
        }

        reader.close();
        writer.close();
    }

    private static String decryptFromFile(String filePath, char encryptionKey) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(filePath + "_encrypted"));
        StringBuilder decryptedLine = new StringBuilder();

        int character;
        while ((character = reader.read()) != -1)
        {
            char decryptedChar = (char) (character - encryptionKey);
            decryptedLine.append(decryptedChar);
        }

        reader.close();
        return decryptedLine.toString();
    }
}
