import java.util.*;


/**
 * main use is to transform percentage grade to letter grade
 * (optional for now)
 */
public class GradeScheme {

    private HashMap<Character, Double> gradeScheme;

    public GradeScheme() {
        gradeScheme = new HashMap<>();
    }

    public HashMap<Character, Double> getGradeScheme() {
        return gradeScheme;
    }

    public void changeGradeScheme(char letter, double percentage) {
        gradeScheme.put(letter, percentage);
        sortAccordingToPercentage(gradeScheme);
    }

    private void sortAccordingToPercentage(HashMap<Character, Double> map) {
        List<Map.Entry<Character, Double>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Character, Double>>() {
            @Override
            public int compare(Map.Entry<Character, Double> o1, Map.Entry<Character, Double> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        HashMap<Character, Double> tempMap = new HashMap<>();
        for (Map.Entry<Character, Double> entry : list) {
            tempMap.put(entry.getKey(), entry.getValue());
        }
        gradeScheme = tempMap;
    }

    public void clearGradeScheme() {
        gradeScheme.clear();
    }
}
