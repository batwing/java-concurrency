package com.java.optional;

import java.util.Optional;
import java.util.stream.Stream;

public class SimpleOptional {
    private static Optional<Integer> addUsingFlatMap(Optional<Integer> op1, Optional<Integer> op2){
        return op1.flatMap(x1 -> op2.map(x2 -> x2 + x1));
    }

    private static Optional<Integer> addUsingStreamReduce(Optional<Integer> op1, Optional<Integer> op2){
        return Stream.of(op1, op2)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .reduce((x,y) -> x + y );
    }

    public static void main(String args[]){
        Optional<Integer> result = addUsingFlatMap(Optional.of(10), Optional.empty());
        System.out.println("1.1 addUsingFlatMap: result = " + result);

        result = addUsingStreamReduce(Optional.of(10), Optional.empty());
        System.out.println("1.2 addUsingStreamReduce: result = " + result);

        result = addUsingFlatMap(Optional.of(10), Optional.of(-13));
        System.out.println("2.1 addUsingFlatMap: result = " + result);

        result = addUsingStreamReduce(Optional.of(10), Optional.of(-13));
        System.out.println("2.2 addUsingStreamReduce: result = " + result);
    }
}
