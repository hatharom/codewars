/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package direction;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 */
public class Direction {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] plan = {"NORTH", "EAST", "NORTH", "EAST", "WEST", "WEST", "EAST", "EAST", "WEST", "SOUTH"};
        System.out.println(Arrays.toString(dirReduc(plan)));
    }

    public static String[] dirReduc(String[] arr) {

        ArrayList<String> arr2 = new ArrayList();

        for (int i = 0; i < arr.length; i++) {
            arr2.add(i, arr[i]);;
        }
        int removed = 0;
        boolean change = true;
        System.out.println(arr2);
        while (change == true && arr2.size() > 1) {
            change = false;
            for (int i = 0; i < arr2.size() - (removed + 1); i++) {
                System.out.print(i + ":");
                System.out.println(arr2.get(i));
                if (isOpposite(arr2.get(i), arr2.get(i + 1))) {
                    arr2.remove(i);
                    arr2.remove(i);
                    removed += 2;
                    change = true;
                }
            }
            removed = 0;
        }

        String[] result = new String[arr2.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = arr2.get(i);
        }

        return result;
    }

    public static boolean isOpposite(String i, String j) {
        if (i.equals("NORTH") && j.equals("SOUTH")) {
            return true;
        }
        if (i.equals("SOUTH") && j.equals("NORTH")) {
            return true;
        }
        if (i.equals("WEST") && j.equals("EAST")) {
            return true;
        }
        if (i.equals("EAST") && j.equals("WEST")) {
            return true;
        }
        return false;
    }
}
