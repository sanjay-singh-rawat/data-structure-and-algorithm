package com.learndatastructure.array.problem1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Find occurrence of an element in an array using HashMap,
 * but this solution is more time consuming than the solution provided in {@link TestClass1}.
 *
 * @author Sanjay Singh Rawat
 * @since 2019.08.25
 */
public class TestClass2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = Stream.of(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            Integer occurrence = map.get(arr[i]);
            map.put(arr[i], occurrence == null ? 1 : occurrence + 1);
        }

        int Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            int query = Integer.parseInt(br.readLine());
            Integer occurrence = map.get(query);
            if (occurrence != null) {
                System.out.println(occurrence);
            } else {
                System.out.println("NOT PRESENT");
            }
        }
    }
}
