
/*
Given an array of integers, find the highest product you can get from three of the integers.
If array size is less than 3, please printout -1.
Test case
The first line of the input gives the size of the array, N. Then, there are N integers in next line.
Limits
The range of integer in array is -1000 to 1000.
Examples

Input
5
3 1 2 5 4
Output

60
Analysis of output value

In the first example, there are 5 integers in array. Max value is 60(3 * 5 * 4).
*/

package com.java.recruitment.linefukuoka;


/* package whatever; // don't place package name! */

import java.util.*;

class HighestProduct {

    public static void main(String[] args) throws java.lang.Exception
    {

        Scanner scanner = new Scanner(System.in);
        int countOfNumbers = scanner.nextInt();
        scanner.nextLine();

        if (countOfNumbers <= 3) {
            System.out.println("-1");
            return;
        }


        int[] numbers = new int[countOfNumbers];
        int nextNumber;
        String[] notParsedArray = scanner.nextLine().split(" ");
        for (int i = 0; i <= notParsedArray.length-1; i++){
            nextNumber = Integer.parseInt(notParsedArray[i]);
            validateNextNumber(nextNumber);
            numbers[i] = nextNumber;
        }

        System.out.println(getHighestProduct(numbers, countOfNumbers));
    }

    private static void validateNextNumber(int number){
        if (number < -1000 || number > 1000) {
            throw new IllegalArgumentException("All numbers should be between -1000 and 1000 ");
        }
    }

    private static int getHighestProduct(int[] numbers,int countOfNumbers){
        Arrays.sort(numbers);
        int sumFromNegative = numbers[0] * numbers[1] * numbers[countOfNumbers - 1];
        int sumOfPositive = numbers[countOfNumbers - 1] * numbers[countOfNumbers - 2] * numbers[countOfNumbers - 3];
        return Math.max(sumFromNegative, sumOfPositive);
    }

}


        /*
        int nextNumber;
        for (int i=0; i<countOfNumbers-1; i++){
            nextNumber = scanner.nextInt();
            validateNextNumber(nextNumber);
            numbers[i] = nextNumber;
        }
        */

        /*
        numbers = Arrays.stream(scanner.next().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
                */


//System.out.println(getHighestProduct(new int[]{3,1,2,5,4}, 5));