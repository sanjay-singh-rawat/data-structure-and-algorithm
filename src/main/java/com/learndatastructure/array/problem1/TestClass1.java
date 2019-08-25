package com.learndatastructure.array.problem1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Sanjay Singh Rawat
 * @since 2019.08.25
 */
public class TestClass1 {

    public static void main(String[] args) throws IOException {
        /*
        Use BufferedReader instead of Scanner because Scanner is slower than BufferedReader
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int arr[] = new int[N];
        int count[] = new int[1001];

        /*
        input -> 1 1 1 2 2 0
         */
        String[] strings = br.readLine().split("\\s+");

        /*
        int arr[] = new int[N];
            0       1       2       3       4       5
         -----------------------------------------------
        |   1   |   1   |   1   |   2   |   2   |   0   |
         -----------------------------------------------

        int count[] = new int[1001];
            0       1       2       3       4       5       6       7.............................1001
         -----------------------------------------------------------------------------------------------
        |   1   |   3   |   2   |   0   |   0   |   0   |   0   |   0   |   0   |   0   |   0   |   0   |
         -----------------------------------------------------------------------------------------------
         */

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(strings[i]);
            count[arr[i]]++;
        }

        int Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            int q = Integer.parseInt(br.readLine());
            if (count[q] != 0)
                System.out.println(count[q]);
            else
                System.out.println("NOT PRESENT");
        }

        /*int Q = Integer.parseInt(br.readLine());
        int qrr[] = new int[Q];
        for (int i = 0; i < Q; i++) {
            qrr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < Q; i++) {
            if (count[qrr[i]] != 0)
                System.out.println(count[qrr[i]]);
            else
                System.out.println("NOT PRESENT");
        }*/
    }
}
