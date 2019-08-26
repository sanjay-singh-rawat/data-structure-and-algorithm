package com.learndatastructure.array.problem2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Time Complexity : O(n)
 * Space Complexity : O(n)
 *
 * @author Sanjay Singh Rawat
 * @since 2019.08.26
 */
public class Solution1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[] ar = new int[n];
        String[] arItems = scanner.nextLine().split(" ");

        for (int i = 0; i < n; i++) {
            ar[i] = Integer.parseInt(arItems[i]);
        }
        scanner.close();

        int result = sockMerchant(n, ar);
        System.out.println(result);
    }

    private static int sockMerchant(int n, int[] ar) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            Integer occurrence = map.get(ar[i]);
            map.put(ar[i], occurrence == null ? 1 : occurrence + 1);
        }

        int pair = 0;
        for (Integer frequency : map.values()) {
            pair += frequency/2;
            /*pair += frequency >> 1;*/
        }

        return pair;
    }
}
