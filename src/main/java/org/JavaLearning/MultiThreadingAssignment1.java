package src.main.java.org.JavaLearning;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiThreadingAssignment1 {
    public static final Map<String,Integer>conCurrentHashmap = new ConcurrentHashMap<>();

    private static void countFrequency(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(" ");
                for (String word : words) {
                    word = word.toLowerCase().replaceAll("[^a-z]", "");
                    if (!word.isEmpty()) {
                        conCurrentHashmap.merge(word, 1, Integer::sum);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error processing file: " + fileName);
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {

        String filePath1 = "file1.txt";
        String filePath2 = "file2.txt";
        String filePath3 = "file3.tx3";

        String[] paths = {filePath1, filePath2, filePath3};
        ExecutorService executer = Executors.newFixedThreadPool(3);


        for(String path:paths) {
            executer.submit(() -> countFrequency(path));
        }

        executer.shutdown();

        try {
            if(!executer.awaitTermination(60, TimeUnit.SECONDS)) {
                executer.shutdownNow();
            }
        } catch (Exception e) {
            executer.shutdownNow();
            Thread.currentThread().interrupt();
        }

        for(Map.Entry<String, Integer>entry : conCurrentHashmap.entrySet()) {
            System.out.println(entry.getKey() + "->" + entry.getValue());
        }
    }
}
