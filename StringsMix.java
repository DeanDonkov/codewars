import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Mixing {
    
    public static Function<String, Map<String, Long>> countLetter = str -> {
        return str.codePoints()
                .filter(Character::isLowerCase).boxed()
                .collect(Collectors.groupingBy(x -> {
                    return new String(Character.toChars(x)).intern();
                }, Collectors.counting()));
    };

    public static String mix(String s1, String s2) {
        StringBuilder result = new StringBuilder();

        Map<String, Long> mapLeft = countLetter.apply(s1);
        Map<String, Long> mapRight = countLetter.apply(s2);

        Map<String, Integer> resultMap = new HashMap<>();

        for (Map.Entry<String, Long> entryLeft : mapLeft.entrySet()) {
            if (mapRight.containsKey(entryLeft.getKey())) {
                int left = entryLeft.getValue().intValue();
                int right = mapRight.get(entryLeft.getKey()).intValue();

                if (left > right) {
                    resultMap.put(String.join("", 
                            Collections.nCopies(left, entryLeft.getKey())), 1);
                } else if (left < right) {
                    resultMap.put(String.join("", 
                            Collections.nCopies(right, entryLeft.getKey())), 2);
                } else {
                    resultMap.put(String.join("", 
                            Collections.nCopies(left, entryLeft.getKey())), 3);
                }
                mapRight.remove(entryLeft.getKey());
            } else {
                resultMap.put(String.join("", 
                         Collections.nCopies(entryLeft.getValue().intValue(), 
                                 entryLeft.getKey())), 1);
            }
        }

        for (Map.Entry<String, Long> entryRight : mapRight.entrySet()) {
            resultMap.put(String.join("", 
                    Collections.nCopies(entryRight.getValue().intValue(), 
                            entryRight.getKey())), 2);
        }

        SortedSet<Map.Entry<String, Integer>> sortedset = 
                new TreeSet<>(new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {

                int cmp = 0;

                Integer o1Length = o1.getKey().length();
                Integer o2Length = o2.getKey().length();
                cmp = o2Length.compareTo(o1Length);
                if (cmp == 0) {
                    cmp = o1.getValue().compareTo(o2.getValue());
                    if (cmp == 0) {
                        cmp = o1.getKey().compareTo(o2.getKey());
                    }
                }
                return cmp;
            }
        });

        sortedset.addAll(resultMap.entrySet());

        sortedset.stream().filter(x -> {
            return x.getKey().length() > 1;
        }).forEach(x -> {
            if (x.getValue() <= 2) {
                result.append(x.getValue().toString());
            } else {
                result.append("=");
            }
            result.append(":");
            result.append(x.getKey());
            result.append("/");
        });

        return result.length() > 1 ? result.deleteCharAt(result.length() - 1).toString() : "";
    }
}
