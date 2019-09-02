package com.learndatastructure.array.problem4;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Sanjay Singh Rawat
 * @since 2019.08.29
 */
public class Solution1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String[] s = scanner.nextLine().split(" ");
        int[] arr = new int[n];
        for (int i = 0; i < s.length; i++) {
            arr[i] = Integer.parseInt(s[i]);
        }
        int result = equalizeArray(arr);
        scanner.close();
        System.out.println(result);
        System.out.println(minDeletions(new int[]{1, 1, 1}));
    }

    private static int equalizeArray(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            Integer count = map.get(arr[i]);
            map.put(arr[i], count == null ? 1 : count + 1);
        }

        int max = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer value = entry.getValue();
            if (value > max) {
                max = value;
            }
        }

        return arr.length - max;
    }

    public static int minDeletions(int[] a) {
        int max = 1;
        Map<Integer, Integer> nums = new HashMap<>();
        for (int i : a)
            if (!nums.containsKey(i))
                nums.put(i, 1);
            else {
                nums.put(i, nums.get(i) + 1);
                if (max < nums.get(i))
                    max = nums.get(i);
            }
        return a.length - max;
    }
}
