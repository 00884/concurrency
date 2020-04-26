package com.itimoshin.leetcode.algorithms.permutations;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * <p>
 * Write the code that will take a string and make this conversion given a number of rows:
 * <p>
 * string convert(string s, int numRows);
 * Example 1:
 * <p>
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 * Example 2:
 * <p>
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 * <p>
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 */

public class ZigZagConversion {

    public static void main(String[] args) {
        System.out.println(new ZigZagConversion().convert("PAYPALISHIRING", 4));
    }

    public String convert(String s, int numRows) {
        if(numRows == 1) return s;
        StringBuilder stringBuilder = new StringBuilder(s.length());
        int charsBetweenColumns = numRows - 2; //2
        int charsInSection = numRows + charsBetweenColumns; //6
        int sectionLength = 1 + charsBetweenColumns;
        int sections = s.length() / (numRows + charsBetweenColumns); //2
        int columns = sections + charsBetweenColumns * sections;
        int charsForNotFullSection = s.length() % charsInSection;

        if (charsForNotFullSection != 0) {
            columns++;
            if (charsForNotFullSection > numRows) {
                columns += charsForNotFullSection - numRows;
            }
        }

        for (int row = 0; row < numRows; row++) {
            int addCharShift = Math.abs(row - sectionLength);
            if(sectionLength == addCharShift) addCharShift = 0;

            for (int col = 0; col < columns; col++) {
                int sectionShift = (col/sectionLength)*charsInSection;


                Integer charIdx = null;
                if (col % sectionLength == 0) {
                    charIdx = row+sectionShift;
                } else if((col/sectionLength)*sectionLength + addCharShift == col){
                    charIdx = sectionShift+numRows+addCharShift-1;
                }

                if(charIdx!= null && s.length()-1>=charIdx) {
                    stringBuilder.append(s.charAt(charIdx));
                }
            }
        }
        return stringBuilder.toString();
    }
}
/*
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 */
