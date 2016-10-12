import java.util.Vector;
public class BraceChecker {

  public static boolean isValid(String braces) {
        StringBuilder bracesResult = new StringBuilder(braces);
        boolean flag = true;
      
        char comparing = 0;
        char compareTo = 0;
        boolean result = false;
        int i = 0;
        while (i < bracesResult.length()-1 && flag == true) {
            if((bracesResult.charAt(i)=='}')||(bracesResult.charAt(i)==']')||(bracesResult.charAt(i)==')')) return false;
            switch (bracesResult.charAt(i)) {
                case '(':
                    comparing = '(';
                    compareTo = ')';
                    break;
                case ')':
                    comparing = ')';
                    compareTo = '(';
                    break;
                case '[':
                    comparing = '[';
                    compareTo = ']';
                    break;
                case ']':
                    comparing = ']';
                    compareTo = '[';
                    break;
                case '{':
                    comparing = '{';
                    compareTo = '}';
                    break;
                case '}':
                    comparing = '}';
                    compareTo = '{';
                    break;
            }
            for (int j = i + 1; j < bracesResult.length(); j++) {
                System.out.println(bracesResult);
                System.out.println(bracesResult.charAt(j)+":"+compareTo);
                if (bracesResult.charAt(j) == compareTo && ((j - i) % 2) == 0) {
         
                    flag = false;
                } else if (bracesResult.charAt(j) == compareTo && ((j - i) % 2) == 1) {
                    result = true;
                    bracesResult.deleteCharAt(i);
                    bracesResult.deleteCharAt(j - 1);
                    flag = true;
                    System.out.print(i);
                    break;
                } else {
                    flag = false;
                    result = false;
                }
        
            }
       
        }
        if (bracesResult.length() > 0) {
            return false;
        } else {
            return true;
        }
      
}
}