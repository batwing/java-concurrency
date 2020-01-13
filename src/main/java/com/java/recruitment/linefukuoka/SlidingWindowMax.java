package com.java.recruitment.linefukuoka;

import java.util.Scanner;

/*
Let's assume you are given a window size of W and an array of integers S and that you can only see the W numbers of S in the window frame. Each time we slide the window over by one frame (from the left), we want you to output the maximum value within the window.
Print each element with white space in-between.

Test case
The first line contains two numbers, window size W and the length of S. Then, array S.
Limits

The length of S is always larger than or equal to W.
The window size W will be an integer in the range, 0 < W < 1,000,000,000.
An element Nn in the stream will be an integer in the range, -3,000,000,000 <= Nn <= 3,000,000,000.

Examples
input
2 5
2 1 2 -1 3
output
2 2 2 3

Analysis of output value
Input is W = 2 and S = [2,1,2,-1,3]. Since the window size is 2, output would be made in the following order:
2 1 2 -1 3 //Output 2 with 2 and 1
2 1 2 -1 3 //After excluding the initial data, output 2 with 1 and 2
2 1 2 -1 3 //Output 2 with 2 and -1
2 1 2 -1 3 //Output 3 with -1 and 3
 */
public class SlidingWindowMax {
    public static void main(String[] args) throws java.lang.Exception
    {
        Scanner scanner = new Scanner(System.in);
        int windowSize = scanner.nextInt();
        int arraySize = scanner.nextInt();
        scanner.nextLine();

        long[] numbers = new long[arraySize];
        String[] notParsedArray = scanner.nextLine().split(" ");
        for (int i = 0; i <= notParsedArray.length-1; i++){
            numbers[i] = Long.parseLong(notParsedArray[i]);
        }

        printOnlyMaxInWindow(numbers, arraySize, windowSize);

        /*
        long[] array = new long[]{ 1L, 3000000000L, 0 };
        printOnlyMaxInWindow(array, array.length, 2);
        */

    }

    private static void printOnlyMaxInWindow(long[] numbers,int arraySize, int windowSize){
        long max;

        for (int i = 0; i <= arraySize - windowSize; i++) {
            max = numbers[i];

            for (int j = 1; j < windowSize; j++) {
                if (numbers[i + j] > max)
                    max = numbers[i + j];
            }
            System.out.print(max + " ");
        }
    }
}
