import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LabStringAnalyzer {


    public static void main(String[] args) {

        String[] inputStrings = {"Київ", "Львів", "Одеса", "Харків", "Дніпро", "Чернівці", "Івано-Франківськ"};

        System.out.println("Початковий масив: " + Arrays.toString(inputStrings));
        System.out.println("---");

        String[] shorter = findShorterThanAverage(inputStrings);
        System.out.println("Рядки, коротші за середнє: " + Arrays.toString(shorter));

        String[] longer = findLongerThanAverage(inputStrings);
        System.out.println("Рядки, довші за середнє: " + Arrays.toString(longer));
    }


    public static String[] findShorterThanAverage(String[] input) {
        double averageLength = getAverageLength(input);

        List<String> resultList = new ArrayList<>();

        for (String str : input) {
            if (str.length() < averageLength) {
                resultList.add(str); // 
            }
        }
        return resultList.toArray(new String[0]);
    }


    public static String[] findLongerThanAverage(String[] input) {
        double averageLength = getAverageLength(input);
        List<String> resultList = new ArrayList<>();

        for (String str : input) {
            if (str.length() > averageLength) {
                resultList.add(str);
            }
        }

        return resultList.toArray(new String[0]);
    }

    private static double getAverageLength(String[] input) {
        if (input == null || input.length == 0) {
            return 0.0; 
        }

        int totalLength = 0;
        
        
        for (String str : input) {
            totalLength += str.length(); 
        }

        return (double) totalLength / input.length;
    }
}
