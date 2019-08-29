package com.learndatastructure.array.problem3;

import java.util.Scanner;
import java.util.stream.Stream;

/**
 * @author Sanjay Singh Rawat
 * @since 2019.08.28
 */
public class Solution1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String next = scanner.nextLine();
        String[] c = next.split(" ");
        int[] arr = Stream.of(c).mapToInt(Integer::parseInt).toArray();
        int result = jumpingOnClouds(n, arr);
        System.out.println(result);
    }

    // 0 0 1 0 0 0 1 0 1 0 0    = min 6 jumps, max 7 jumps
    // 0 1 0 0 0 1 0            = min 3 jumps, max 4 jumps
    // 0 1 0                    = min/max 2 jumps
    // 0 0                      = min/max 2 jumps
    private static int jumpingOnClouds(int n, int[] c) {
        int jump = 0;
        for (int i = 0; i < n - 1; i++) {
            if (i < n - 2 && c[i + 2] == 1) {
                jump++;
            } else {
                jump++;
                i++;
            }
        }
        return jump;
    }

    private static int jumpingOnClouds(int[] c) {
        int jumps = 0;
        for(int i = 0; i < c.length - 1; i++){
            jumps++;
            if( i  + 2 < c.length && c[i + 2] == 0){
                i++;
            }
        }
        return jumps;
    }
}
