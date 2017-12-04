package com.rx.rodrigobrauwers.rx;

import android.util.Log;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by rodrigobrauwers on 04/12/17.
 */

public class WordUtils {

    public static boolean isAlmostPalindrome(String source) {
        if (StringUtils.isBlank(source)) {
            return false;
        }

        String reversed = StringUtils.reverse(source);
        char[] sourceChars = source.toCharArray();
        char[] reversedChars = reversed.toCharArray();
        int diffs = 0;

        for (int i=0; i<sourceChars.length; i++) {
            char c1 = sourceChars[i];
            char c2 = reversedChars[i];

            if (c1 != c2) {
                diffs++;
                if (diffs > 2) {
                    return false;
                }
            }
        }

        return true;
    }

}
