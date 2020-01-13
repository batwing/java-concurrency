package com.java.recruitment.luxoft.russia;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CharCounter {
    @NoArgsConstructor
    public class CharCounterSync {
        private String alphabet = "abcdefghijklmnopqrstuvwxyz";//Assumption that all the letters are in lower case
        @Getter
        private final int[] charCounters = new int[alphabet.length()];

        public void handle(String text) throws Exception {
            for (char c: text.toCharArray()) {
                validateChar(c);
                synchronized (charCounters) {
                    charCounters[getIndexByChar(c)]++;
                }
            }
        }

        private void  validateChar(char c) throws Exception {
            if (c < 'a' || c > 'z')
                throw new Exception(String.format("The character %c is out of range", c));
        }

        private int getIndexByChar(char c){
            return c - 'a';
        }
    }

    @NoArgsConstructor
    public class CharCounterNonBlockingMap {
        private String alphabet = "abcdefghijklmnopqrstuvwxyz";
        @Getter
        private ConcurrentMap<Character, Integer> charCounters = new ConcurrentHashMap<Character, Integer>(alphabet.length());

        public void handle(String text) {
            text.toLowerCase();
            for (char c: text.toCharArray()) {
                charCounters.merge(c, 0, (x, y) -> x+1);
            }
        }

    }

    public void main(String[] args){

    }
}
