import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Comparator;
public class Mixing {
    
    public static String mix(String s1, String s2) {
             String regex = "([a-z])";
        Pattern regPattern = Pattern.compile(regex);
        Matcher m1 = regPattern.matcher(s1);
        Matcher m2 = regPattern.matcher(s2);
        Map<String, Integer> letters = new <String, Integer>HashMap();
        Map<String, Integer> letters2 = new <String, Integer>HashMap();
        while (m1.find()) {
            if (letters.get(m1.group()) == null) {
                letters.put(m1.group(), new Integer(1));
            } else {
                Integer count = (Integer) letters.get(m1.group());
                letters.put(m1.group(), count + 1);
            }
        }
        while (m2.find()) {
            if (letters2.get(m2.group()) == null) {
                letters2.put(m2.group(), new Integer(1));
            } else {
                Integer count = (Integer) letters2.get(m2.group());
                letters2.put(m2.group(), count + 1);
            }
        }

        return parseResultMap(buildResultMap(letters, letters2));

    }

    public static HashMap<String, String> buildResultMap(Map<String, Integer> letters1, Map<String, Integer> letters2) {
        String result = "";
        HashMap<String, String> resultMap = new <String, Integer> HashMap();
        ArrayList resultList = new ArrayList();

        for (Map.Entry<String, Integer> entry : letters1.entrySet()) {
            for (Map.Entry<String, Integer> entry2 : letters2.entrySet()) {

                if (entry.getKey().equals(entry2.getKey()) && entry.getValue() != 1) {
                    if (entry.getValue() > entry2.getValue()) {
                        resultMap.put(entry.getKey(), new String("1:" + entry.getValue()));
                    } else if (entry.getValue() < entry2.getValue()) {
                        resultMap.put(entry2.getKey(), new String("2:" + entry2.getValue()));
                    } else {
                        resultMap.put(entry.getKey(), new String("=:" + entry.getValue()));
                    }
                } else {
                    if (!resultMap.containsKey(entry.getKey()) && entry.getValue() != 1) {
                        resultMap.put(entry.getKey(), new String("1:" + entry.getValue()));
                    }
                    if (!resultMap.containsKey(entry2.getKey()) && entry2.getValue() != 1) {
                        resultMap.put(entry2.getKey(), new String("2:" + entry2.getValue()));
                    }
                }
            }
        }
        //  System.out.println(resultMap);
        return resultMap;
    }

    public static String parseResultMap(HashMap<String, String> resultMap) {

        Iterator<Map.Entry<String, String>> it = resultMap.entrySet().iterator();
        String[][] resultList = new String[resultMap.size()][2];
        ArrayList myList = new ArrayList();
        String resultSeq = "";
        int k = 0;
        Iterator<Map.Entry<String, String>> it2 = resultMap.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry<String, String> entry = it2.next();
            resultList[k][0] = entry.getValue().substring(0, 2);
            String charSeq = "";
            Integer charOccurence = Integer.parseInt(entry.getValue().substring(2));
            for (int i = 1; i <= charOccurence; i++) {
                charSeq += entry.getKey();
            }
            resultList[k][1] = charSeq;
            k++;
        }

        Comper comper = new Comper();
        while (!sorted(resultList)) {
            for (int i = 0; i < resultList.length - 1; i++) {
                if (comper.compare(resultList[i], resultList[i + 1]) < 0) {
                    String[] temp = resultList[i];
                    resultList[i] = resultList[i + 1];
                    resultList[i + 1] = temp;
                }
            }
        }

        for (int i = 0; i < resultList.length; i++) {
     
            resultSeq+=resultList[i][0]+resultList[i][1]+"/";
        }
       
       
         if(resultSeq.length()>1){
        resultSeq = resultSeq.substring(0, resultSeq.length() - 1);}
        return resultSeq;
    }

    public static boolean sorted(String[][] resultList) {
        Comper comper = new Comper();
        for (int i = 0; i < resultList.length - 1; i++) {
            if (comper.compare(resultList[i], resultList[i + 1]) < 0) {
                return false;
            }
        }

        return true;
    }
}

class Comper implements Comparator<String[]> {

    public int compare(String[] arr1, String[] arr2) {
        int x = arr1[1].length() - arr2[1].length();
        if (x != 0) {
            return x * 1000;
        } else if (arr1[0].equals("=:") && !arr2[0].equals("=:")) {
            return -100;
        } else if (!arr1[0].equals("=:") && arr2[0].equals("=:")) {
            return 100;
        } else if (arr1[0].equals(arr2[0])) {
            return -1 * (arr1[1].substring(0, 1).compareTo(arr2[1].substring(0, 1)));
        } else {
            return -1 * (arr1[0].substring(0, 1).compareTo(arr2[0].substring(0, 1)));

        }
    
    }

}
